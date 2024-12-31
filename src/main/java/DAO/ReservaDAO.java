package DAO;

import model.Reserva;
import model.Apartamento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    static final String url = "jdbc:mysql://127.0.0.1/sistemaReserva";
    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String user = "root";
    static final String senha = "lo.lo123";

    // Method to obtain a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, senha);
    }

    // Insert a new reservation into the database
    public void inserir(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reserva (id_cliente, id_apartamento, data_inicio, data_fim, quantidade_pessoas) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Verificar se o cliente existe antes de inserir a reserva
            if (!clienteExiste(reserva.getClienteId())) {
                throw new IllegalArgumentException("Cliente não encontrado para o ID especificado.");
            }

            ps.setInt(1, reserva.getClienteId());
            ps.setInt(2, reserva.getApartamentoId());
            ps.setString(3, reserva.getDataInicio());
            ps.setString(4, reserva.getDataFim());
            ps.setInt(5, reserva.getQuantidadePessoas());

            ps.executeUpdate();
        }
    }

    // Retrieve all reservations from the database
    public List<Reserva> buscarTodas() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setClienteId(rs.getInt("id_cliente"));
                reserva.setApartamentoId(rs.getInt("id_apartamento"));
                reserva.setDataInicio(rs.getString("data_inicio"));
                reserva.setDataFim(rs.getString("data_fim"));
                reserva.setQuantidadePessoas(rs.getInt("quantidade_pessoas"));

                reservas.add(reserva);
            }
        }
        return reservas;
    }

    // Retrieve a reservation by its ID from the database
    public Reserva buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM reserva WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Reserva reserva = new Reserva();
                    reserva.setId(rs.getInt("id"));
                    reserva.setClienteId(rs.getInt("id_cliente"));
                    reserva.setApartamentoId(rs.getInt("id_apartamento"));
                    reserva.setDataInicio(rs.getString("data_inicio"));
                    reserva.setDataFim(rs.getString("data_fim"));
                    reserva.setQuantidadePessoas(rs.getInt("quantidade_pessoas"));

                    return reserva;
                }
            }
        }
        return null;
    }

    // Update an existing reservation in the database
    public void atualizar(Reserva reserva) throws SQLException {
        String sql = "UPDATE reserva SET id_cliente = ?, id_apartamento = ?, data_inicio = ?, data_fim = ?, quantidade_pessoas = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getClienteId());
            stmt.setInt(2, reserva.getApartamentoId());
            stmt.setString(3, reserva.getDataInicio());
            stmt.setString(4, reserva.getDataFim());
            stmt.setInt(5, reserva.getQuantidadePessoas());

            stmt.setInt(6, reserva.getId());
            stmt.executeUpdate();
        }
    }

    // Delete a reservation from the database by its ID
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM reserva WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Retrieve reservations by client email
    public List<Reserva> buscarPorCliente(String emailCliente) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT r.id, r.id_cliente, r.id_apartamento, r.data_inicio, r.data_fim, r.quantidade_pessoas, "
                   + "a.cidade, a.bairro "  // Selecionar também os campos do apartamento
                   + "FROM reserva r "
                   + "JOIN apartamento a ON r.id_apartamento = a.id "
                   + "JOIN cliente c ON r.id_cliente = c.id "
                   + "WHERE c.email = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emailCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reserva reserva = new Reserva();
                    reserva.setId(rs.getInt("id"));
                    reserva.setClienteId(rs.getInt("id_cliente"));
                    reserva.setApartamentoId(rs.getInt("id_apartamento"));
                    reserva.setDataInicio(rs.getString("data_inicio"));
                    reserva.setDataFim(rs.getString("data_fim"));
                    reserva.setQuantidadePessoas(rs.getInt("quantidade_pessoas"));

                    // Criar o objeto Apartamento e setar seus atributos
                    Apartamento apartamento = new Apartamento();
                    apartamento.setCidade(rs.getString("cidade"));
                    apartamento.setBairro(rs.getString("bairro"));

                    // Associar o apartamento à reserva
                    reserva.setApartamento(apartamento);

                    reservas.add(reserva);
                }
            }
        }
        return reservas;
    }

    // Check if a client exists by ID
    private boolean clienteExiste(int clienteId) throws SQLException {
        String sql = "SELECT id FROM cliente WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Retorna true se encontrar o cliente, false caso contrário
            }
        }
    }

    // Other methods as needed

}

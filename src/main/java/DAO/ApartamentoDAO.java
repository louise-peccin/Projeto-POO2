package DAO;

import model.Apartamento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ApartamentoDAO {
    static final String url = "jdbc:mysql://127.0.0.1/sistemaReserva";
    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String user = "root";
    static final String senha = "lo.lo123";

    public void inserir(Apartamento apto, String emailProprietario) {
        String sql = "INSERT INTO apartamento (cidade, bairro, quartos, hospedes_max, preco, descricao, proprietario_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, senha);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Busca o id do proprietário pelo email
            int proprietarioId = buscarUsuarioIdPorEmail(emailProprietario);
            if (proprietarioId == -1) {
                throw new SQLException("Proprietário não encontrado");
            }

            ps.setString(1, apto.getCidade());
            ps.setString(2, apto.getBairro());
            ps.setInt(3, apto.getQuartos());
            ps.setInt(4, apto.getHospedesMax());
            ps.setDouble(5, apto.getPreco());
            ps.setString(6, apto.getDescricao());
            ps.setInt(7, proprietarioId); // Define o proprietario_id

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Apartamento inserido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum apartamento inserido.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao inserir apartamento no banco de dados: " + ex.getMessage());
        }
    }

    public void atualizar(Apartamento apto) {
        String sql = "UPDATE apartamento SET cidade=?, bairro=?, quartos=?, hospedes_max=?, preco=?, descricao=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(url, user, senha);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, apto.getCidade());
            ps.setString(2, apto.getBairro());
            ps.setInt(3, apto.getQuartos());
            ps.setInt(4, apto.getHospedesMax());
            ps.setDouble(5, apto.getPreco());
            ps.setString(6, apto.getDescricao());
            ps.setInt(7, apto.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Apartamento atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum apartamento atualizado");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar apartamento no banco de dados: " + ex.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM apartamento WHERE id=?";

        try (Connection conn = DriverManager.getConnection(url, user, senha);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir apartamento do banco de dados: " + ex.getMessage());
        }
    }

    public List<Apartamento> listar() {
        List<Apartamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM apartamento";

        try (Connection conn = DriverManager.getConnection(url, user, senha);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String cidade = rs.getString("cidade");
                String bairro = rs.getString("bairro");
                int quartos = rs.getInt("quartos");
                int hospedesMax = rs.getInt("hospedes_max");
                double preco = rs.getDouble("preco");
                String descricao = rs.getString("descricao");

                Apartamento apto = new Apartamento(cidade, bairro, quartos, hospedesMax, preco, descricao);
                apto.setId(id); // Define o id do apartamento
                lista.add(apto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar apartamentos: " + ex.getMessage());
        }
        return lista;
    }

    private int buscarUsuarioIdPorEmail(String email) throws SQLException {
        String sql = "SELECT id FROM proprietario WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(url, user, senha);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    JOptionPane.showMessageDialog(null, "Proprietário não encontrado para o email: " + email);
                    return -1; // Ou outro valor que indique erro
                }
            }
        }
    }

    public List<Apartamento> buscarPorEmail(String emailUsuario) {
         List<Apartamento> listaApartamentos = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
        conn = DriverManager.getConnection(url, user, senha); // Obter conexão usando DriverManager (não ideal em produção)
        String sql = "SELECT id, cidade, bairro, quartos, hospedes_max, preco, descricao " +
                     "FROM apartamento " +
                     "WHERE proprietario_id = (SELECT id FROM proprietario WHERE email = ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, emailUsuario);
        
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String cidade = rs.getString("cidade");
            String bairro = rs.getString("bairro");
            int quartos = rs.getInt("quartos");
            int hospedesMax = rs.getInt("hospedes_max");
            double preco = rs.getDouble("preco");
            String descricao = rs.getString("descricao");
            
            Apartamento apto = new Apartamento(cidade, bairro, quartos, hospedesMax, preco, descricao);
            apto.setId(id); // Define o ID do apartamento
            listaApartamentos.add(apto);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao buscar apartamentos por email: " + ex.getMessage());
    } finally {
        // Fechar recursos
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    return listaApartamentos;
    }
}

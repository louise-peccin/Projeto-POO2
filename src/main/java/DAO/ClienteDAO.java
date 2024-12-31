package DAO;

import model.Cliente;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.ResultSet;

public class ClienteDAO {
    static final String url = "jdbc:mysql://127.0.0.1/sistemaReserva";
    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String user = "root";
    static final String senha = "lo.lo123";

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, cidade, nascimento, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, senha);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Preencher os parâmetros do PreparedStatement
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getCidade());

            // Converter a string de nascimento para java.sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataNascimento = new Date(sdf.parse(cliente.getNascimento()).getTime());
            ps.setDate(4, dataNascimento);

            ps.setString(5, cliente.getEmail());
            

            // Executar a inserção
            int rowsAffected = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao inserir cliente no banco de dados: " + ex.getMessage());
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro na conversão de data: " + ex.getMessage());
        }
    }
    
    public boolean checkEmail(String email) {
        boolean emailExists = false;
        String query = "SELECT * FROM cliente WHERE email = ?";

        try (Connection con = DriverManager.getConnection(url, user, senha);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                emailExists = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return emailExists;
    }
}

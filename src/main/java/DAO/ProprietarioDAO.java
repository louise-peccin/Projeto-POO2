package DAO;

import model.Proprietario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.sql.ResultSet;

public class ProprietarioDAO {
    static final String url = "jdbc:mysql://127.0.0.1/sistemaReserva";
    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String user = "root";
    static final String senha = "lo.lo123";
    private Connection conexao; 

    public void inserir(Proprietario prop) {
        String sql = "INSERT INTO proprietario (nome, cpf, cidade, nascimento, email, qtde_aptos) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, senha);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prop.getNome());
            ps.setString(2, prop.getCpf());
            ps.setString(3, prop.getCidade());


            Date dataNascimento = Date.valueOf(prop.getNascimento());
            ps.setDate(4, dataNascimento);

            ps.setString(5, prop.getEmail());
            ps.setInt(6, prop.getQtdeAptos());
            


            int rowsAffected = ps.executeUpdate();

            

        } catch (SQLException ex) {
            ex.printStackTrace(); 
            JOptionPane.showMessageDialog(null, "Erro ao inserir proprietário no banco de dados: " + ex.getMessage());
        }
    }
    
    public boolean checkEmail(String email) {
        boolean emailExists = false;
        String query = "SELECT * FROM proprietario WHERE email = ?";

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
    
    
    public int buscarIdPorEmail(String email) throws SQLException {
        String sql = "SELECT id FROM proprietario WHERE email = ?";
    try (Connection conn = ConexaoBanco.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
    }
    return -1; 
    }
}

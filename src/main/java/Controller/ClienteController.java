package controller;

import DAO.ClienteDAO;
import model.Cliente;
import javax.swing.JOptionPane;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO(); // Corrigir a criação do DAO com a conexão correta
    }

    public void inserirCliente(Cliente cliente) {
        try {
            clienteDAO.inserir(cliente);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage());
        }
    }

    // Métodos adicionais conforme necessário, como atualizar, excluir, buscar etc.
}

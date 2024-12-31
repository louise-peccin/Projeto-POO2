package controller;

import DAO.ProprietarioDAO;
import model.Proprietario;
import javax.swing.JOptionPane;

public class ProprietarioController {
    private ProprietarioDAO proprietarioDAO;

    public ProprietarioController() {
        this.proprietarioDAO = new ProprietarioDAO(); // Corrigir a criação do DAO com a conexão correta
    }

    public void inserirProprietario(Proprietario proprietario) {
        try {
            proprietarioDAO.inserir(proprietario);
            JOptionPane.showMessageDialog(null, "Proprietário cadastrado com sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar proprietário: " + ex.getMessage());
        }
    }

    // Métodos adicionais conforme necessário, como atualizar, excluir, buscar etc.
}

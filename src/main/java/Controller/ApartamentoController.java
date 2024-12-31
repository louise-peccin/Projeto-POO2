package controller;

import DAO.ApartamentoDAO;
import model.Apartamento;
import javax.swing.JOptionPane;

public class ApartamentoController {
    private ApartamentoDAO apartamentoDAO;

    public ApartamentoController() {
        this.apartamentoDAO = new ApartamentoDAO(); // Corrigir a criação do DAO com a conexão correta
    }

    public void inserirApartamento(Apartamento apartamento, String emailProprietario) {
        try {
            apartamentoDAO.inserir(apartamento, emailProprietario);
            JOptionPane.showMessageDialog(null, "Apartamento inserido com sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao inserir apartamento: " + ex.getMessage());
        }
    }

    public void atualizarApartamento(Apartamento apartamento) {
        try {
            apartamentoDAO.atualizar(apartamento);
            JOptionPane.showMessageDialog(null, "Apartamento atualizado com sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar apartamento: " + ex.getMessage());
        }
    }

    public void excluirApartamento(int id) {
        try {
            apartamentoDAO.excluir(id);
            JOptionPane.showMessageDialog(null, "Apartamento excluído com sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir apartamento: " + ex.getMessage());
        }
    }

    // Métodos adicionais conforme necessário

}

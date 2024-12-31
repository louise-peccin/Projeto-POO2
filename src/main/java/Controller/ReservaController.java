package controller;

import DAO.ReservaDAO;
import model.Reserva;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO(); 
    }

    public void inserirReserva(Reserva reserva) {
        try {
            reservaDAO.inserir(reserva);
            JOptionPane.showMessageDialog(null, "Reserva cadastrada com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar reserva: " + ex.getMessage());
        }
    }

    public List<Reserva> buscarReservas() {
        try {
            return reservaDAO.buscarTodas();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao buscar reservas: " + ex.getMessage());
            return null; // ou lançar uma exceção, dependendo do comportamento desejado
        }
    }

    public Reserva buscarReservaPorId(int id) {
        try {
            return reservaDAO.buscarPorId(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao buscar reserva por ID: " + ex.getMessage());
            return null; // ou lançar uma exceção, dependendo do comportamento desejado
        }
    }

    public void atualizarReserva(Reserva reserva) {
        try {
            reservaDAO.atualizar(reserva);
            JOptionPane.showMessageDialog(null, "Reserva atualizada com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar reserva: " + ex.getMessage());
        }
    }

    public void excluirReserva(int id) {
        try {
            reservaDAO.excluir(id);
            JOptionPane.showMessageDialog(null, "Reserva excluída com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir reserva: " + ex.getMessage());
        }
    }

    // Outros métodos conforme necessário

}

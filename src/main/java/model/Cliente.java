
package model;

import java.util.List;
import java.util.ArrayList;


public class Cliente  {
    private String nome;
    private String cpf;
    private String cidade;
    private String nascimento;
    private String email;
    private int reservas;
    private List<Reserva> historico;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

   
    public int getReservas() {
        return reservas;
    }

    public void setReservas(int reservas) {
        this.reservas = reservas;
    }

    public List<Reserva> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Reserva> historico) {
        this.historico = historico;
    }
    
    
}

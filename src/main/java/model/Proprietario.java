
package model;

public class Proprietario {
    private int qtdeAptos;
    private String nome;
    private String cpf;
    private String cidade;
    private String nascimento;
    private String email;

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

    
    public int getQtdeAptos() {
        return qtdeAptos;
    }

    public void setQtdeAptos(int qtdeAptos) {
        this.qtdeAptos = qtdeAptos;
    }
    
}

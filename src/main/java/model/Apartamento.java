package model;

public class Apartamento {
    private int id;
    private String cidade;
    private String bairro;
    private int quartos;
    private int hospedesMax;
    private double preco;
    private String descricao;

    public Apartamento(){}
    
    public Apartamento(String cidade, String bairro, int quartos, int hospedesMax, double preco, String descricao) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.quartos = quartos;
        this.hospedesMax = hospedesMax;
        this.preco = preco;
        this.descricao = descricao;
    }

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getQuartos() {
        return quartos;
    }

    public void setQuartos(int quartos) {
        this.quartos = quartos;
    }

    public int getHospedesMax() {
        return hospedesMax;
    }

    public void setHospedesMax(int hospedesMax) {
        this.hospedesMax = hospedesMax;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}

package model;

public class Reserva {
    private int id;
    private int clienteId;
    private int apartamentoId; 
    private String dataInicio;
    private String dataFim;
    private int quantidadePessoas;
    private String statusReserva; 
    private Apartamento apartamento;

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }
    
    public Reserva() {
        
    }

    public Reserva(int id, int clienteId, int apartamentoId, String dataInicio, String dataFim, int quantidadePessoas, String statusReserva) {
        this.id = id;
        this.clienteId = clienteId;
        this.apartamentoId = apartamentoId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quantidadePessoas = quantidadePessoas;
        this.statusReserva = statusReserva;
    }

    
    public int getApartamentoId() {
        return apartamentoId;
    }

    public void setApartamentoId(int apartamentoId) {
        this.apartamentoId = apartamentoId;
    }

    public String getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(String statusReserva) {
        this.statusReserva = statusReserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }
}

package Entidades;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Aluguel {

    public enum FormaPagamento {
        CREDITO("Cartão de Crédito"),
        DEBITO("Cartão Débito"),
        PIX("Pix"),
        TRANSFERENCIA("Transferência"),
        BOLETO("Boleto");

        private String descricao;

        FormaPagamento(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    private int idAluguel;
    private int idCliente;
    private int idCarro;

    private Cliente cliente;
    private Carro carro;

    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private LocalTime horaRetirada;
    private LocalTime horaDevolucao;
    private double valorContrato;
    private FormaPagamento formaDePagamento;

    public Aluguel(Cliente cliente, Carro carro, LocalDate dataRetirada, LocalDate dataDevolucao, LocalTime horaRetirada, LocalTime horaDevolucao, 
    double valorContrato, FormaPagamento formaPagamento) {

        this.cliente = cliente;
        this.carro = carro;

        this.dataRetirada = LocalDate.now();
        this.dataDevolucao = dataDevolucao;

        this.horaRetirada = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.horaDevolucao = horaDevolucao;

        this.valorContrato = valorContrato;
        this.formaDePagamento = formaPagamento;
    }

    public Aluguel() {}
 
    //GETTERS & SETTERS DOS IDs
    public int getIdAluguel() {return idAluguel;}
    public void setIdAluguel(int idAluguel) {this.idAluguel = idAluguel;}

    public int getIdCliente() {return idCliente;}
    public void setIdCliente(int idCliente) {this.idCliente = idCliente;}

    public int getIdCarro() {return idCarro;}
    public void setIdCarro(int idCarro) {this.idCarro = idCarro;}


    //GETTERS & SETTERS DO CARRO E CLIENTE
    public Cliente getCliente() {return cliente;}
    public void setCliente(Cliente cliente) {this.cliente = cliente;}

    public Carro getCarro() {return carro;}
    public void setCarro(Carro carro) {this.carro = carro;}

    //GETTERS & SETTERS DOS DEMAIS ATRIBUTOS
    public void setDataRetirada(String novaDataRetirada) {

        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataRetirada = LocalDate.parse(novaDataRetirada, formatter);
            } catch (DateTimeParseException exception) {
        System.out.println("Data de Retirada Inválida. Siga a formatação (dd/MM/yyyy).");
        this.dataRetirada = null;
        }
    }
    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataDevolucao(String novaDataDevolucao) {

        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataDevolucao = LocalDate.parse(novaDataDevolucao, formatter);
            } catch (DateTimeParseException exception) {
        System.out.println("Data de Devolução Inválida. Siga a formatação (dd/MM/yyyy).");
        this.dataDevolucao = null;
        }
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setHoraRetirada(String novaHoraRetirada) {
        
        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.horaRetirada = LocalTime.parse(novaHoraRetirada, formatter);
            } catch (DateTimeParseException exception) {
        System.out.println("Hora de Retirada Inválida. Siga a formatação (HH:mm).");
        this.horaRetirada = null;
        }
    }
    public LocalTime getHoraRetirada() {
        return horaRetirada;
    }

    public void setHoraDevolucao(String novaHoraDevolucao) {

        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.horaDevolucao = LocalTime.parse(novaHoraDevolucao, formatter);
            } catch (DateTimeParseException exception) {
        System.out.println("Hora de Devolução Inválida. Siga a formatação (HH:mm).");
        this.horaDevolucao = null;
        }
    }
    public LocalTime getHoraDevolucao() {
        return horaDevolucao;
    }

    public void setValorContrato(double novoValorContrato) {
        this.valorContrato = novoValorContrato;
    }
    public double getValorContrato() {
        return valorContrato;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaDePagamento = formaPagamento;
    }
    public FormaPagamento getFormaPagamento() {
        return formaDePagamento;
    }
}

package br.com.extractor.easyfinance.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Despesa extends RealmObject implements Titulo {

    @PrimaryKey
    private String id;
    private Tipo tipo;
    private String descricao;
    private Date dataPaga;
    private Date dataVencimento;
    private double valorPago;

    public Despesa() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataPaga() {
        return dataPaga;
    }

    public void setDataPaga(Date dataPaga) {
        this.dataPaga = dataPaga;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

}

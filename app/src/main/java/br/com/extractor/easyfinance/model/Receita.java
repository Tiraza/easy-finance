package br.com.extractor.easyfinance.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Receita extends RealmObject implements Titulo {

    @PrimaryKey
    private long id;
    private Tipo tipo;
    private String descricao;
    private Date dataPaga;
    private Date dataVencimento;
    private double valorPago;

    public Receita() {
        this.id = System.nanoTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

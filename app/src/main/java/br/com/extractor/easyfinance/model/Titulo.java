package br.com.extractor.easyfinance.model;

import java.util.Date;

public interface Titulo {

    Tipo getTipo();

    void setTipo(Tipo tipo);

    String getDescricao();

    void setDescricao(String descricao);

    Date getDataPaga();

    void setDataPaga(Date dataPaga);

    Date getDataVencimento();

    void setDataVencimento(Date dataVencimento);

    double getValorPago();

    void setValorPago(double valorPago);

}

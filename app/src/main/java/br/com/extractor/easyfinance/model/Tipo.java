package br.com.extractor.easyfinance.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Tipo extends RealmObject {

    @PrimaryKey
    private String id;
    private String descricao;
    /**
     * @see TipoReceita
     */
    private int tipo;

    public Tipo() {
        this.id = UUID.randomUUID().toString();
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

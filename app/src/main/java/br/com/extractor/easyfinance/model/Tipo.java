package br.com.extractor.easyfinance.model;

import br.com.extractor.easyfinance.model.domain.CategoriaTitulo;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Tipo extends RealmObject implements Entidade {

    @PrimaryKey
    private long id;
    private String descricao;

    /**
     * @see CategoriaTitulo
     */
    private int categoria;

    public Tipo() {
        this.id = System.nanoTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

}

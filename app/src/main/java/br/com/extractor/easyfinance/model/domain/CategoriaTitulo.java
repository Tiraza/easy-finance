package br.com.extractor.easyfinance.model.domain;

public enum CategoriaTitulo {

    DESPESA(0, "Despesa"), RECEITA(1, "Receita"), AMBOS(2, "Ambos");

    private final int codigo;
    private final String descricao;

    CategoriaTitulo(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static CategoriaTitulo getById(int codigo) {

        for (CategoriaTitulo categoriaTitulo : CategoriaTitulo.values()) {
            if (categoriaTitulo.getCodigo() == codigo) {
                return categoriaTitulo;
            }
        }

        return null;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

}

package extractor.com.easyfinance.model.entities;

public enum TipoReceita {

    DESPESA(0, "Despesa"), RECEITA(1, "Receita"), AMBOS(2, "Ambos");

    private final int codigo;
    private final String descricao;

    TipoReceita(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}

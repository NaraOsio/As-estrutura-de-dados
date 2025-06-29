package model.jogo;

public class CartaSorteReves {
    String descricao;
    AcaoSorteReves acao;

    public CartaSorteReves(String descricao, AcaoSorteReves acao) {
        this.descricao = descricao;
        this.acao = acao;
    }

    public String getDescricao() {
        return descricao;
    }

    public AcaoSorteReves getAcao() {
        return acao;
    }
}

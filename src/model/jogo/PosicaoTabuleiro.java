package model.jogo;

public class PosicaoTabuleiro {
    private PosicaoTabuleiro posicaoAnterior;
    private PosicaoTabuleiro posicaoProxima;
    private String nome;

    public PosicaoTabuleiro(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PosicaoTabuleiro getPosicaoAnterior() {
        return posicaoAnterior;
    }

    public void setPosicaoAnterior(PosicaoTabuleiro posicaoAnterior) {
        this.posicaoAnterior = posicaoAnterior;
    }

    public PosicaoTabuleiro getPosicaoProxima() {
        return posicaoProxima;
    }

    public void setPosicaoProxima(PosicaoTabuleiro posicaoProxima) {
        this.posicaoProxima = posicaoProxima;
    }

    @Override
    public String toString() {
        return nome;
    }
}

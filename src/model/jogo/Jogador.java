package model.jogo;

import java.util.ArrayList;

public class Jogador {
    private String nome;
    private int saldo;
    private PosicaoTabuleiro posicaoAtual;
    private ArrayList<PosicaoTabuleiroImovel> propriedades;
    private boolean emPrisao;
    private int rodadasPrisao;
    private int quantidadeDadosIguais;
    private boolean falido;

    public Jogador(String nome, int saldo) {
        this.nome = nome;
        this.saldo = saldo;
        propriedades = new ArrayList<>();
    }

    public int getQuantidadeDadosIguais() {
        return quantidadeDadosIguais;
    }

    public void setQuantidadeDadosIguais(int quantidadeDadosIguais) {
        this.quantidadeDadosIguais = quantidadeDadosIguais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void adicionarSaldo(int valor) {
        this.saldo += valor;
    }

    public PosicaoTabuleiro getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(PosicaoTabuleiro posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public void adicionarPropriedade(PosicaoTabuleiroImovel propriedade) {
        this.propriedades.add(propriedade);
    }

    public ArrayList<PosicaoTabuleiroImovel> getPropriedades() {
        return propriedades;
    }

    public boolean isFalido() {
        return falido;
    }

    public void setFalido(boolean falido) {
        this.falido = falido;
    }

    public boolean isEmPrisao() {
        return emPrisao;
    }

    public void setEmPrisao(boolean emPrisao) {
        this.emPrisao = emPrisao;
    }

    public int getRodadasPrisao() {
        return rodadasPrisao;
    }

    public void setRodadasPrisao(int rodadasPrisao) {
        this.rodadasPrisao = rodadasPrisao;
    }

    public void exibirStatusCompleto() {
        System.out.println("- " + getNome() + " | Saldo: " + getSaldo()
                + (isFalido() ? " (Falido)" : "" + (isEmPrisao() ? " (Na Prisão)" : "")));
        System.out.println("  Posição Atual: " + getPosicaoAtual().getNome());
        System.out.println("  Propriedades: ");
        if (getPropriedades().isEmpty()) {
            System.out.println("  Nenhuma propriedade.");
        } else {
            for (PosicaoTabuleiroImovel propriedade : getPropriedades()) {
                System.out.println("  - " + propriedade.getNome() + " (Valor: " + propriedade.getPreco() + ")");
            }
        }
    }
}

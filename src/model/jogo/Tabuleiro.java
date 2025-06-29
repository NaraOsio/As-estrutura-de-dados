package model.jogo;

import java.util.ArrayList;

import model.configuracao.ConfiguracaoPartidaImovel;

public class Tabuleiro {
    private PosicaoTabuleiro primeiraPosicao;
    private PosicaoTabuleiro ultimaPosicao;
    private PosicaoTabuleiro posicaoPrisao;
    private BaralhoSorteReves baralhoSorteReves;

    private int totalCasas;

    public Tabuleiro() {
    }

    public void configurarTabuleiro(ArrayList<ConfiguracaoPartidaImovel> imoveisIniciais) {
        baralhoSorteReves = new BaralhoSorteReves();
        int posicao = 0;
        int sequenciaImovel = 0;

        while (sequenciaImovel < imoveisIniciais.size()) {
            if (posicao == 0) {
                adicionarPosicaoTabuleiro(new PosicaoTabuleiroInicio());
            } else if (posicao % 6 == 0) {
                adicionarPosicaoTabuleiro(new PosicaoTabuleiroImposto());
            } else if (posicao % 5 == 0) {
                adicionarPosicaoTabuleiro(new PosicaoTabuleiroSorteReves());
            } else if (posicao % 9 == 0) {
                adicionarPosicaoTabuleiro(new PosicaoTabuleiroRestituicao());
            } else {
                ConfiguracaoPartidaImovel imovel = imoveisIniciais.get(sequenciaImovel);
                adicionarPosicaoTabuleiro(
                        new PosicaoTabuleiroImovel(imovel.getNome(), imovel.getPreco(), imovel.getAluguel()));
                sequenciaImovel++;
            }

            posicao++;
        }

        ultimaPosicao.setPosicaoProxima(primeiraPosicao);

        this.totalCasas = posicao;
        adicionarPrisao(primeiraPosicao, 0);

        listarPosicoes();
    }

    private void adicionarPrisao(PosicaoTabuleiro posicaoAtual, int index) {
        if (index == totalCasas / 2) {
            PosicaoTabuleiro posicaoPrisao = new PosicaoTabuleiroPrisao();
            this.posicaoPrisao = posicaoPrisao;
            var proximaPosicao = posicaoAtual.getPosicaoProxima();

            posicaoAtual.setPosicaoProxima(posicaoPrisao);
            posicaoPrisao.setPosicaoAnterior(posicaoAtual);
            posicaoPrisao.setPosicaoProxima(proximaPosicao);
            totalCasas++;
        } else {
            index++;
            adicionarPrisao(posicaoAtual.getPosicaoProxima(), index);
        }
    }

    private void adicionarPosicaoTabuleiro(PosicaoTabuleiro posicao) {
        if (primeiraPosicao == null) {
            primeiraPosicao = posicao;
            ultimaPosicao = posicao;
        } else {
            ultimaPosicao.setPosicaoProxima(posicao);
            posicao.setPosicaoAnterior(ultimaPosicao);
            ultimaPosicao = posicao;
        }
    }

    public PosicaoTabuleiro getPrimeiraPosicao() {
        return primeiraPosicao;
    }

    public PosicaoTabuleiro getPosicaoPrisao() {
        return posicaoPrisao;
    }

    private void listarPosicoes() {
        PosicaoTabuleiro posicaoAtual = primeiraPosicao;
        int index = 0;

        System.out.println("Listando posições do tabuleiro:");
        while (index < totalCasas) {
            System.out.println("Posição " + index + ": " + posicaoAtual.getNome());
            posicaoAtual = posicaoAtual.getPosicaoProxima();
            index++;
        }
    }

    public BaralhoSorteReves getBaralhoSorteReves() {
        return baralhoSorteReves;
    }
}

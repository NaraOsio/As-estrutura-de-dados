package model.jogo;

import java.util.ArrayList;

import main.Funcoes;
import model.configuracao.ConfiguracaoPartidaImovel;
import model.configuracao.ConfiguracaoPartidaJogador;
import model.configuracao.ConfiguracaoPartidaValoresPadrao;

public class Partida {
    private Tabuleiro tabuleiro;
    private int dado1;
    private int dado2;

    private ArrayList<Jogador> jogadores;
    private int jogadorAtualIndex;
    private int rodadaAtual;
    private int numeroMaximoRodadas;
    private int salarioPorVolta;

    public Partida() {
        this.tabuleiro = new Tabuleiro();
        this.jogadores = new ArrayList<>();
        this.jogadorAtualIndex = 0;
    }

    public void configurarPartida(ArrayList<ConfiguracaoPartidaJogador> jogadores,
            ArrayList<ConfiguracaoPartidaImovel> imoveis, ConfiguracaoPartidaValoresPadrao valores) {
        tabuleiro.configurarTabuleiro(imoveis);

        for (ConfiguracaoPartidaJogador jogadorConfig : jogadores) {
            Jogador jogador = new Jogador(jogadorConfig.getNome(), valores.getSaldoInicial());
            jogador.setPosicaoAtual(tabuleiro.getPrimeiraPosicao());
            this.jogadores.add(jogador);
        }

        this.salarioPorVolta = valores.getSalarioPorVolta();
        this.numeroMaximoRodadas = valores.getNumeroMaximoRodadas();
    }

    public void iniciarPartida() {
        System.out.println("\n\n\n");
        System.out.println("Partida iniciada com " + this.jogadores.size() + " jogadores.");

        boolean sairDoJogo = false;

        while (!sairDoJogo) {

            if (jogadores.stream().filter(i -> i.isFalido()).count() == 1) {
                System.out.println("Apenas um jogador restante. Fim do jogo!");
                sairDoJogo = true;
                continue;
            }

            Jogador jogadorAtual = jogadores.get(jogadorAtualIndex);

            if (rodadaAtual >= numeroMaximoRodadas) {
                System.out.println("Número máximo de rodadas atingido. Fim do jogo!");
                sairDoJogo = true;
                continue;
            }

            exibirPainelGeral();

            System.out.println("=========================================");
            System.out.println("=== RODADA " + (rodadaAtual) + "/20 - VEZ DE: " + jogadorAtual.getNome() + " ===");
            System.out.println("=========================================");
            System.out.println("Saldo: " + jogadorAtual.getSaldo());
            System.out
                    .println("Posição Atual: " + jogadorAtual.getPosicaoAtual().getNome());

            System.out.println("\n");

            // verifica se o jogador está falido
            if (jogadorAtual.isFalido()) {
                System.out.println("Você está falido e não pode jogar.");
                jogadorAtualIndex = (jogadorAtualIndex + 1) % jogadores.size();
                continue;
            }

            // verifica se o jogador está na prisão
            if (jogadorAtual.isEmPrisao()) {
                System.out.println("Você está na prisão. Jogue os dados para tentar sair.");
                System.out.println("Você precisa tirar dois dados iguais para sair da prisão.");
                System.out.println("Pressione Enter para jogar os dados...");
                Funcoes.lerEntradaString();

                int dado1 = Funcoes.lancarDado();
                System.out.println("Você lançou o dado 1: " + dado1);
                Funcoes.aguardar(1);
                int dado2 = Funcoes.lancarDado();
                System.out.println("Você lançou o dado 2: " + dado2);
                Funcoes.aguardar(1);

                if (dado1 == dado2) {
                    System.out.println("Você tirou dados iguais. Está liberado.\n\n");
                    jogadorAtual.setEmPrisao(false);
                    jogadorAtual.setRodadasPrisao(0);
                } else {
                    jogadorAtual.setRodadasPrisao(jogadorAtual.getRodadasPrisao() + 1);
                    if (jogadorAtual.getRodadasPrisao() >= 3) {
                        jogadorAtual.setEmPrisao(false);
                        jogadorAtual.setRodadasPrisao(0);
                        jogadorAtual.setPosicaoAtual(tabuleiro.getPrimeiraPosicao());
                        System.out.println("Você cumpriu sua pena e agora pode jogar novamente na próxima rodada.");
                    } else {
                        System.out.println(
                                "Você não tirou dados iguais. Você permanecerá na prisão por mais uma rodada.");
                        System.out.println(
                                "Você está na prisão há " + (jogadorAtual.getRodadasPrisao() + 1) + " rodadas.");
                        System.out.println("Pressione Enter para continuar o jogo...");
                        Funcoes.lerEntradaString();
                    }

                    jogadorAtualIndex = (jogadorAtualIndex + 1) % jogadores.size();
                    continue;
                }
            }

            System.out.println("--- O que você deseja fazer? ---");
            System.out.println("1. Lançar Dados e Mover");
            System.out.println("2. Ver Meu Status Completo (Saldo e Propriedades)");
            System.out.println("3. Ver Ranking de Jogadores");
            System.out.println("0. Desistir do Jogo");

            System.out.print(">> Escolha uma opção: ");
            int opcao = Funcoes.lerEntradaInt();

            switch (opcao) {
                case 1:
                    if (jogadorAtualIndex == 0) {
                        rodadaAtual++;
                    }

                    lancarDadosEMovimentar(jogadorAtual);
                    break;
                case 2:
                    jogadorAtual.exibirStatusCompleto();
                    break;
                case 3:
                    exibirRanking();
                    break;
                case 0:
                    sairDoJogo = true;
                    System.out.println("Você desistiu do jogo.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            Funcoes.aguardar(2);
            System.out.println("\n\n");
        }

        System.out.println("Fim da partida!");
        exibirPainelFinalizacao();
    }

    private void lancarDadosEMovimentar(Jogador jogador) {
        this.dado1 = Funcoes.lancarDado();
        System.out.println("Você lançou o dado 1: " + dado1);
        Funcoes.aguardar(1);

        this.dado2 = Funcoes.lancarDado();
        System.out.println("Você lançou o dado 2: " + dado2);
        Funcoes.aguardar(1);

        int total = dado1 + dado2;
        System.out.println("Você lançou os dados: " + dado1 + " e " + dado2 + " (Total: " + total + ")");

        movimentarJogador(jogador, total);

        // dados iguais
        if (dado1 == dado2) {
            // dados iguais 3 vezes
            if (jogador.getQuantidadeDadosIguais() >= 2) {
                System.out.println("Você tirou três pares consecutivos! Você foi enviado para a prisão.");
                jogador.setPosicaoAtual(tabuleiro.getPosicaoPrisao());
                jogador.setEmPrisao(true);
                jogador.setQuantidadeDadosIguais(0);
            } else {
                jogador.setQuantidadeDadosIguais(jogador.getQuantidadeDadosIguais() + 1);
                System.out.println("Dados iguais! Jogue novamente.");
            }
        } else {
            jogador.setQuantidadeDadosIguais(0);
            System.out.println("Jogada finalizada. Próximo jogador...");
            jogadorAtualIndex = (jogadorAtualIndex + 1) % jogadores.size();
        }

    }

    private void movimentarJogador(Jogador jogador, int total) {
        PosicaoTabuleiro novaPosicao = jogador.getPosicaoAtual();
        var index = 0;

        if (total == 0)
            return;

        if (total > 0) {
            while (index < total) {
                novaPosicao = novaPosicao.getPosicaoProxima();

                if (novaPosicao instanceof PosicaoTabuleiroInicio) {
                    jogador.adicionarSaldo(this.salarioPorVolta);
                    System.out.println("Você completou uma volta no tabuleiro e recebeu o salário de: "
                            + this.salarioPorVolta);
                }

                index++;
            }

            jogador.setPosicaoAtual(novaPosicao);
        } else {
            while (index < Math.abs(total)) {
                novaPosicao = novaPosicao.getPosicaoAnterior();
                index++;
            }

            jogador.setPosicaoAtual(novaPosicao);
        }
        executarAcaoPosicao(jogador, novaPosicao);
    }

    private void executarAcaoPosicao(Jogador jogador, PosicaoTabuleiro novaPosicao) {
        if (novaPosicao instanceof PosicaoTabuleiroImovel) {
            System.out.println("Você parou em: " + novaPosicao.getNome());
            PosicaoTabuleiroImovel propriedade = (PosicaoTabuleiroImovel) novaPosicao;

            if (propriedade.getProprietario() == null) {
                System.out.println("Este imóvel não tem proprietário.");
                System.out.println("Preço de Compra: " + propriedade.getPreco());
                System.out.println("--- O que você deseja fazer? ---");

                System.out.println("1. Comprar Imóvel");
                System.out.println("2. Passar a vez");

                System.out.print(">> Escolha uma opção: ");
                int opcaoCompra = Funcoes.lerEntradaInt();

                if (opcaoCompra == 1) {
                    if (jogador.getSaldo() >= propriedade.getPreco()) {
                        jogador.adicionarSaldo(-propriedade.getPreco());
                        propriedade.setProprietario(jogador);
                        jogador.adicionarPropriedade(propriedade);
                        System.out.println("Parabéns! Você comprou '" + propriedade.getNome() + "' por "
                                + propriedade.getPreco() + ".");
                    } else {
                        System.out.println("Saldo insuficiente para comprar esta propriedade.");
                    }
                } else if (opcaoCompra == 2) {
                    System.out.println("Você optou por passar a vez.");
                } else {
                    System.out.println("Opção inválida. Você perdeu a vez.");
                }

            } else {
                pagarAluguel(jogador, propriedade);
            }

            System.out.println("Pressione Enter para continuar...");
            Funcoes.lerEntradaString();
        } else if (novaPosicao instanceof PosicaoTabuleiroImposto) {
            System.out.println("Você parou em: " + novaPosicao.getNome());

            int patrimonioTotal = jogador.getSaldo() + jogador.getPropriedades().stream()
                    .mapToInt(PosicaoTabuleiroImovel::getPreco).sum();
            int valorImposto = patrimonioTotal * 5 / 100;

            if (jogador.getSaldo() >= valorImposto) {
                jogador.adicionarSaldo(-valorImposto);
                System.out.println("Você pagou o imposto de: " + valorImposto + ". Seu novo saldo é: "
                        + jogador.getSaldo());
            } else {
                System.out.println("Saldo insuficiente para pagar o imposto. Você foi enviado à falência.");
                jogador.setFalido(true);
            }

            System.out.println("Pressione Enter para continuar...");
            Funcoes.lerEntradaString();
        } else if (novaPosicao instanceof PosicaoTabuleiroPrisao) {
            System.out.println("Você parou em: " + novaPosicao.getNome());
            System.out.println("Você foi enviado para a prisão!");
            jogador.setEmPrisao(true);
            jogador.setRodadasPrisao(0);
        } else if (novaPosicao instanceof PosicaoTabuleiroRestituicao) {
            int valorRestituicao = salarioPorVolta * 10 / 100;
            System.out.println("Você parou em: " + novaPosicao.getNome());
            System.out.println("Você recebeu uma restituição de: " + valorRestituicao);
            jogador.adicionarSaldo(valorRestituicao);
            System.out.println("Seu novo saldo é: " + jogador.getSaldo());
            System.out.println("Pressione Enter para continuar...");
            Funcoes.lerEntradaString();
        } else if (novaPosicao instanceof PosicaoTabuleiroSorteReves) {
            System.out.println("Você parou em: " + novaPosicao.getNome());

            CartaSorteReves carta = tabuleiro.getBaralhoSorteReves().puxarCarta();
            System.out.println("Você puxou a carta: " + carta.getDescricao());

            switch (carta.getAcao()) {
                case Ganhe500:
                    jogador.adicionarSaldo(500);
                    break;
                case Receba1000:
                    jogador.adicionarSaldo(1000);
                    break;
                case Pague500MultaAmbiental:
                    jogador.adicionarSaldo(-500);
                    break;
                case PerdeuApostaPague600:
                    jogador.adicionarSaldo(-600);
                    break;
                case Avance3Casas:
                    System.out.println("Avançando 3 casas...");
                    movimentarJogador(jogador, 3);
                    break;
                case Volte2Casas:
                    System.out.println("Voltando 2 casas...");
                    movimentarJogador(jogador, -2);
                    break;
                case VaParaPrisao:
                    jogador.setEmPrisao(true);
                    jogador.setRodadasPrisao(0);
                    jogador.setPosicaoAtual(tabuleiro.getPosicaoPrisao());
                    System.out.println("Você foi enviado para a prisão!");
                    break;
            }

            System.out.println("Pressione Enter para continuar...");
            Funcoes.lerEntradaString();
        }
    }

    private void pagarAluguel(Jogador jogador, PosicaoTabuleiroImovel propriedade) {
        System.out.println("Você parou em uma propriedade de outro jogador. Pague o aluguel de: "
                + propriedade.getAluguel() + " para " + propriedade.getProprietario().getNome() + ".");
        if (jogador.getSaldo() >= propriedade.getAluguel()) {
            jogador.adicionarSaldo(-propriedade.getAluguel());
            propriedade.getProprietario().adicionarSaldo(propriedade.getAluguel());
            System.out.println("Você pagou o aluguel. Seu novo saldo é: " + jogador.getSaldo());
        } else {
            System.out.println("Saldo insuficiente para pagar o aluguel. Você foi enviado à falência.");
            jogador.setFalido(true);
        }
    }

    private void exibirPainelGeral() {
        System.out.println("=== Painel Geral da Partida ===");
        System.out.println("Rodada Atual: " + rodadaAtual);

        System.out.println("Jogadores na Partida:");

        for (Jogador jogador : jogadores) {
            jogador.exibirStatusCompleto();
        }

        System.out.println("=========================================");
        System.out.println("Pressione Enter para continuar...");
        Funcoes.lerEntradaString();
    }

    private void exibirRanking() {
        System.out.println("=== Ranking de Jogadores ===");
        jogadores.sort((j1, j2) -> Integer.compare(j2.getSaldo(), j1.getSaldo()));

        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            System.out.println((i + 1) + ". " + jogador.getNome() + " - Saldo: " + jogador.getSaldo());
        }
        System.out.println("=========================================");
        System.out.println("Pressione Enter para continuar...");
        Funcoes.lerEntradaString();
    }

    private void exibirPainelFinalizacao() {
        System.out.println("=== Fim da Partida ===");
        System.out.println("Jogadores na Partida:");

        for (Jogador jogador : jogadores) {
            jogador.exibirStatusCompleto();
        }


        Jogador vencedor = jogadores.stream()
                .max((j1, j2) -> Integer.compare(j1.getSaldo() + j1.getPropriedades().stream()
                        .mapToInt(PosicaoTabuleiroImovel::getPreco).sum(),
                        j2.getSaldo() + j2.getPropriedades().stream()
                                .mapToInt(PosicaoTabuleiroImovel::getPreco).sum()))
                .orElse(null);

        if (vencedor != null) {
            System.out.println("Parabéns " + vencedor.getNome() + "! Você é o vencedor da partida!");
            System.out.println("Saldo Final: " + vencedor.getSaldo());
            System.out.println("Valor Total em Propriedades: "
                    + vencedor.getPropriedades().stream().mapToInt(PosicaoTabuleiroImovel::getPreco).sum());
        } else {
            System.out.println("Nenhum jogador venceu a partida.");
        }

        System.out.println("=========================================");
        System.out.println("Pressione Enter para sair...");
        Funcoes.lerEntradaString();
    }
}

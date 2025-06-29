package main;

import java.util.ArrayList;
import model.configuracao.ConfiguracaoPartidaImovel;
import model.configuracao.ConfiguracaoPartidaJogador;
import model.configuracao.ConfiguracaoPartidaValoresPadrao;
import model.jogo.Partida;

public class Menu {

    private ArrayList<ConfiguracaoPartidaJogador> jogadores;
    private ArrayList<ConfiguracaoPartidaImovel> imoveis;
    private ConfiguracaoPartidaValoresPadrao configuracoesPartida;

    public Menu() {
        this.jogadores = new ArrayList<>();
        this.imoveis = new ArrayList<>();
        this.configuracoesPartida = new ConfiguracaoPartidaValoresPadrao(25000, 2000, 20);
    }

    public void exibirMenuInicial() {
        boolean sairDoMenu = false;

        do {
            System.out.println("=========================================");
            System.out.println("===  SIMULADOR DE JOGO DE TABULEIRO   ===");
            System.out.println("=========================================");

            System.out.println("Seja bem-vindo! Antes de começar, vamos configurar a partida");
            System.out.println("--- MENU DE CONFIGURAÇÃO ---");
            System.out.println("1. Gerenciar Jogadores");
            System.out.println("2. Gerenciar Imóveis");
            System.out.println("3. Definir Configurações da Partida");
            System.out.println("4. Iniciar Jogo");
            System.out.println("5. Configuração Rápida");
            System.out.println("0. Sair");

            System.out.print(">> Escolha uma opção: ");

            int opcao = Funcoes.lerEntradaInt();

            switch (opcao) {
                case 1:
                    Funcoes.darEspacoNoConsole();
                    gerenciarJogadores();
                    break;
                case 2:
                    Funcoes.darEspacoNoConsole();
                    gerenciarImoveis();
                    break;
                case 3:
                    Funcoes.darEspacoNoConsole();
                    definirConfiguracoesPartida();
                    break;
                case 4:
                    Funcoes.darEspacoNoConsole();
                    iniciarPartida();
                    break;

                case 5:
                    Funcoes.darEspacoNoConsole();
                    configuracaoRapida();
                    System.out.println("\n");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    sairDoMenu = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println("\n");
                    break;
            }
        } while (!sairDoMenu);
    }

    private void iniciarPartida() {
        Funcoes.darEspacoNoConsole();

        if (jogadores.size() < 2) {
            System.out.println("É necessário pelo menos 2 jogadores para iniciar a partida.");
            return;
        }

        if (imoveis.size() < 10) {
            System.out.println("É necessário pelo menos 10 imóveis cadastrados para iniciar a partida.");
            return;
        }

        Partida partida = new Partida();
        partida.configurarPartida(jogadores, imoveis, configuracoesPartida);
        partida.iniciarPartida();
    }

    private void gerenciarJogadores() {
        boolean sairDoMenu = false;

        do {
            System.out.println("--- Menu de Jogadores ---");
            System.out.println("\n");
            System.out.println("(Atualmente: " + jogadores.size() + "/6 jogadores cadastrados)");

            System.out.println("1. Adicionar Jogador");
            System.out.println("2. Listar Jogadores");
            System.out.println("3. Remover Jogador");
            System.out.println("4. Voltar ao Menu Principal");

            System.out.print(">> Escolha uma opção: ");

            int opcao = Funcoes.lerEntradaInt();

            switch (opcao) {
                case 1:
                    if (jogadores.size() < 6) {
                        System.out.print("Digite o nome do novo jogador: ");
                        String nomeJogador = Funcoes.lerEntradaString();
                        ConfiguracaoPartidaJogador novoJogador = new ConfiguracaoPartidaJogador(nomeJogador);
                        jogadores.add(novoJogador);
                        System.out.println("Jogador " + nomeJogador + " adicionado com sucesso!");
                    } else {
                        System.out.println(
                                "Número máximo de jogadores atingido (6). Não é possível adicionar mais jogadores.");
                    }
                    break;
                case 2:
                    System.out.println("Lista de Jogadores:");
                    for (int i = 0; i < jogadores.size(); i++) {
                        System.out.println((i + 1) + ". " + jogadores.get(i).getNome());
                    }
                    break;
                case 3:
                    if (jogadores.isEmpty()) {
                        System.out.println("Nenhum jogador cadastrado para remover.");
                    } else {
                        System.out.print("Digite o número do jogador a ser removido: ");
                        int numeroJogador = Funcoes.lerEntradaInt();
                        if (numeroJogador > 0 && numeroJogador <= jogadores.size()) {
                            ConfiguracaoPartidaJogador jogadorRemovido = jogadores.remove(numeroJogador - 1);
                            System.out.println("Jogador " + jogadorRemovido.getNome() + " removido com sucesso!");
                        } else {
                            System.out.println("Número inválido. Nenhum jogador removido.");
                        }
                    }
                    break;
                case 4:
                    Funcoes.darEspacoNoConsole();
                    sairDoMenu = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println("\n");
                    break;
            }
        } while (!sairDoMenu);

    }

    private void gerenciarImoveis() {
        boolean sairDoMenu = false;

        do {
            System.out.println("--- Menu de Imóveis ---");
            System.out.println("\n");
            System.out.println("(Atualmente: " + imoveis.size() + "/40 imóveis cadastrados)");

            System.out.println("1. Cadastrar Novo Imóvel");
            System.out.println("2. Listar Imóveis Cadastrados");
            System.out.println("3. Remover Imóvel");
            System.out.println("4. Voltar ao Menu Principal");

            System.out.print(">> Escolha uma opção: ");

            int opcao = Funcoes.lerEntradaInt();

            switch (opcao) {
                case 1:
                    if (imoveis.size() < 40) {
                        System.out.print("Digite o nome do novo imóvel: ");
                        String nomeImovel = Funcoes.lerEntradaString();
                        System.out.print("Digite o preço do imóvel: ");
                        int precoImovel = Funcoes.lerEntradaInt();
                        System.out.print("Digite o valor do aluguel: ");
                        int aluguelImovel = Funcoes.lerEntradaInt();

                        ConfiguracaoPartidaImovel novoImovel = new ConfiguracaoPartidaImovel(nomeImovel, precoImovel,
                                aluguelImovel);
                        imoveis.add(novoImovel);

                        System.out.println("Imóvel " + nomeImovel + " cadastrado com sucesso!");
                    } else {
                        System.out.println(
                                "Número máximo de imóveis atingido (40). Não é possível adicionar mais imóveis.");
                    }
                    break;

                case 2:
                    System.out.println("Lista de Imóveis Cadastrados:");
                    for (int i = 0; i < imoveis.size(); i++) {
                        ConfiguracaoPartidaImovel imovel = imoveis.get(i);
                        System.out.println((i + 1) + ". " + imovel.getNome() + " - Preço: R$ " + imovel.getPreco()
                                + ", Aluguel: R$ " + imovel.getAluguel());
                    }
                    break;

                case 3:
                    if (imoveis.isEmpty()) {
                        System.out.println("Nenhum imóvel cadastrado para remover.");
                    } else {
                        System.out.print("Digite o número do imóvel a ser removido: ");
                        int numeroImovel = Funcoes.lerEntradaInt();
                        if (numeroImovel > 0 && numeroImovel <= imoveis.size()) {
                            ConfiguracaoPartidaImovel imovelRemovido = imoveis.remove(numeroImovel - 1);
                            System.out.println("Imóvel " + imovelRemovido.getNome() + " removido com sucesso!");
                        } else {
                            System.out.println("Número inválido. Nenhum imóvel removido.");
                        }
                    }
                    break;

                case 4:
                    Funcoes.darEspacoNoConsole();
                    sairDoMenu = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println("\n");
                    break;
            }
        } while (!sairDoMenu);
    }

    private void configuracaoRapida() {

        jogadores.clear();
        imoveis.clear();

        System.out.println("Configuração rápida iniciada...");

        String[] nomesJogadores = { "João", "Maria", "Paulo", "Ana", "Carlos", "Fernanda"
        };

        for (String nome : nomesJogadores) {
            ConfiguracaoPartidaJogador novoJogador = new ConfiguracaoPartidaJogador(nome);
            jogadores.add(novoJogador);
        }

        System.out.println("Jogadores configurados: " + jogadores.size() + " jogadores.");

        configuracoesPartida.setSaldoInicial(25000);
        configuracoesPartida.setSalarioPorVolta(2000);
        configuracoesPartida.setNumeroMaximoRodadas(20);

        System.out.println(
                "Definido saldo inicial para R$ 25000, salário por volta R$ 2000 e número máximo de rodadas 20.");

        imoveis.add(new ConfiguracaoPartidaImovel("Rua da Alegria", 800, 80));
        imoveis.add(new ConfiguracaoPartidaImovel("Viela da Paz", 1000, 100));
        imoveis.add(new ConfiguracaoPartidaImovel("Travessa do Sol", 1200, 120));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua dos Cravos", 1300, 130));
        imoveis.add(new ConfiguracaoPartidaImovel("Beco das Flores", 1500, 150));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua das Acácias", 1000, 100));
        imoveis.add(new ConfiguracaoPartidaImovel("Travessa da Lua", 1400, 140));
        imoveis.add(new ConfiguracaoPartidaImovel("Viela do Vento", 1300, 130));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua do Campo", 1100, 110));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua das Andorinhas", 1200, 120));
        imoveis.add(new ConfiguracaoPartidaImovel("Travessa das Águas", 1300, 130));
        imoveis.add(new ConfiguracaoPartidaImovel("Beco da Lapa", 1500, 150));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua das Palmeiras", 3500, 350));
        imoveis.add(new ConfiguracaoPartidaImovel("Avenida Brasil", 3000, 300));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua do Cedro", 2500, 250));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua das Cerejeiras", 3200, 320));
        imoveis.add(new ConfiguracaoPartidaImovel("Vila Harmonia", 2800, 280));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua das Rosas", 3600, 360));
        imoveis.add(new ConfiguracaoPartidaImovel("Travessa da Colina", 2700, 270));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua da Amizade", 2600, 260));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua das Violetas", 3400, 340));
        imoveis.add(new ConfiguracaoPartidaImovel("Avenida Esperança", 3800, 380));
        imoveis.add(new ConfiguracaoPartidaImovel("Alameda dos Ipês", 2900, 290));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua do Comércio", 3100, 310));
        imoveis.add(new ConfiguracaoPartidaImovel("Avenida Central", 7500, 900));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua Ouro Verde", 6500, 800));
        imoveis.add(new ConfiguracaoPartidaImovel("Alameda Imperial", 6000, 700));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua Bela Vista", 5500, 650));
        imoveis.add(new ConfiguracaoPartidaImovel("Boulevard das Nações", 7000, 850));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua do Lago Azul", 5800, 680));
        imoveis.add(new ConfiguracaoPartidaImovel("Avenida Europa", 6800, 820));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua da Independência", 6200, 750));
        imoveis.add(new ConfiguracaoPartidaImovel("Shopping Center", 12000, 2400));
        imoveis.add(new ConfiguracaoPartidaImovel("Estádio Municipal", 10000, 2000));
        imoveis.add(new ConfiguracaoPartidaImovel("Hotel Palace", 13500, 2700));
        imoveis.add(new ConfiguracaoPartidaImovel("Banco Central", 15000, 3000));
        imoveis.add(new ConfiguracaoPartidaImovel("Parque das Águas", 4000, 400));
        imoveis.add(new ConfiguracaoPartidaImovel("Rua da Liberdade", 5200, 600));
        imoveis.add(new ConfiguracaoPartidaImovel("Avenida Atlântica", 8000, 1000));
        imoveis.add(new ConfiguracaoPartidaImovel("Centro Tecnológico", 14000, 2800));

        System.out.println("Imóveis configurados: " + imoveis.size() + " imóveis.");

        System.out.println("Configuração rápida concluída!");
    }

    private void definirConfiguracoesPartida() {

        boolean sairDoMenu = false;

        do {
            System.out.println("--- Configurações da Partida ---");
            System.out.println("\n");
            System.out.println("1. Definir Saldo Inicial (Atual: " + configuracoesPartida.getSaldoInicial() + ")");
            System.out.println(
                    "2. Definir Salário por volta (Atual: R$ " + configuracoesPartida.getSalarioPorVolta() + ")");
            System.out.println(
                    "3. Definir Nº Máximo de Rodadas (Atual: " + configuracoesPartida.getNumeroMaximoRodadas() + ")");
            System.out.println("4. Voltar ao Menu Anterior");

            System.out.print(">> Escolha uma opção: ");

            int opcao = Funcoes.lerEntradaInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o novo saldo inicial: ");
                    int novoSaldoInicial = Funcoes.lerEntradaInt();
                    configuracoesPartida.setSaldoInicial(novoSaldoInicial);
                    System.out.println("Saldo inicial atualizado para: R$ " + configuracoesPartida.getSaldoInicial());
                    break;
                case 2:
                    System.out.print("Digite o novo salário por volta: ");
                    int novoSalarioPorVolta = Funcoes.lerEntradaInt();
                    configuracoesPartida.setSalarioPorVolta(novoSalarioPorVolta);
                    System.out.println(
                            "Salário por volta atualizado para: R$ " + configuracoesPartida.getSalarioPorVolta());
                    break;
                case 3:
                    System.out.print("Digite o novo número máximo de rodadas: ");
                    int novoNumeroMaximoVoltas = Funcoes.lerEntradaInt();
                    configuracoesPartida.setNumeroMaximoRodadas(novoNumeroMaximoVoltas);
                    System.out.println("Número máximo de rodadas atualizado para: "
                            + configuracoesPartida.getNumeroMaximoRodadas());
                    break;
                case 4:
                    Funcoes.darEspacoNoConsole();
                    sairDoMenu = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println("\n");
                    break;
            }
        } while (!sairDoMenu);
    }
}

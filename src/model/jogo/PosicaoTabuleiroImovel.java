package model.jogo;

public class PosicaoTabuleiroImovel extends PosicaoTabuleiro {
    private String nome;
    private int preco;
    private int aluguel;
    private Jogador proprietario;

    public PosicaoTabuleiroImovel(String nome, int preco, int aluguel) {
        super("Imóvel: " + nome);

        this.nome = nome;
        this.preco = preco;
        this.aluguel = aluguel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getAluguel() {
        return aluguel;
    }

    public void setAluguel(int aluguel) {
        this.aluguel = aluguel;
    }

    public Jogador getProprietario() {
        return proprietario;
    }

    public void setProprietario(Jogador proprietario) {
        this.proprietario = proprietario;
    }
}

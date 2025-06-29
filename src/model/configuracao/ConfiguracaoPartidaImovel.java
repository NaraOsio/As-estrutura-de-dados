package model.configuracao;

public class ConfiguracaoPartidaImovel {
    public String nome;
    public int preco;
    public int aluguel;

    public ConfiguracaoPartidaImovel(String nome, int preco, int aluguel) {
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
}

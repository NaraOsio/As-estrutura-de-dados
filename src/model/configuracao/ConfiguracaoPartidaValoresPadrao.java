package model.configuracao;

public class ConfiguracaoPartidaValoresPadrao {
    private int saldoInicial;
    private int salarioPorVolta;
    private int numeroMaximoRodadas;

    public ConfiguracaoPartidaValoresPadrao(int saldoInicial, int salarioPorVolta, int numeroMaximoRodadas) {
        this.saldoInicial = saldoInicial;
        this.salarioPorVolta = salarioPorVolta;
        this.numeroMaximoRodadas = numeroMaximoRodadas;
    }

    public int getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(int saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public int getSalarioPorVolta() {
        return salarioPorVolta;
    }

    public void setSalarioPorVolta(int salarioPorVolta) {
        this.salarioPorVolta = salarioPorVolta;
    }

    public int getNumeroMaximoRodadas() {
        return numeroMaximoRodadas;
    }

    public void setNumeroMaximoRodadas(int numeroMaximoRodadas) {
        this.numeroMaximoRodadas = numeroMaximoRodadas;
    }
}

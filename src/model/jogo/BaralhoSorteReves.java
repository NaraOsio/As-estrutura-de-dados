package model.jogo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BaralhoSorteReves {
    private Stack<CartaSorteReves> pilha;
    private List<CartaSorteReves> todasAsCartas;

    public BaralhoSorteReves() {
        pilha = new Stack<>();
        todasAsCartas = new ArrayList<>();
        carregarCartas();
        embaralhar();
    }

    private void carregarCartas() {
        todasAsCartas.add(new CartaSorteReves("Ganhe R$ 500", AcaoSorteReves.Ganhe500));
        todasAsCartas.add(new CartaSorteReves("Receba R$ 1000", AcaoSorteReves.Receba1000));
        todasAsCartas
                .add(new CartaSorteReves("Pague R$ 500 de multa ambiental", AcaoSorteReves.Pague500MultaAmbiental));
        todasAsCartas.add(new CartaSorteReves("Perdeu a aposta, pague R$ 600", AcaoSorteReves.PerdeuApostaPague600));
        todasAsCartas.add(new CartaSorteReves("Avance 3 casas", AcaoSorteReves.Avance3Casas));
        todasAsCartas.add(new CartaSorteReves("Volte 2 casas", AcaoSorteReves.Volte2Casas));
        todasAsCartas.add(new CartaSorteReves("Vá para a prisão", AcaoSorteReves.VaParaPrisao));
    }

    private void embaralhar() {
        pilha.clear();
        ArrayList<CartaSorteReves> cartasEmbaralhadas = new ArrayList<>(todasAsCartas);
        Collections.shuffle(cartasEmbaralhadas);
        pilha.addAll(cartasEmbaralhadas);
    }

    public CartaSorteReves puxarCarta() {
        if (pilha.isEmpty()) {
            embaralhar();
        }
        return pilha.pop();
    }
}

package jogo;

import java.util.Arrays;
import java.util.List;

public class Jogo {
    String pergunta;
    List<Resposta> respostas;

    public Jogo(String pergunta, List<Resposta> respostas) {
        this.pergunta = pergunta;
        this.respostas = respostas;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "pergunta='" + pergunta + '\'' +
                ", respostas=" + respostas +
                '}';
    }
}

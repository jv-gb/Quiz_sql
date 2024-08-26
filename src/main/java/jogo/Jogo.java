package jogo;

import java.util.Arrays;

public class Jogo {
    String pergunta;
    String[] respostas;

    public Jogo(String pergunta, String[] respostas) {
        this.pergunta = pergunta;
        this.respostas = respostas;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "pergunta='" + pergunta + '\'' +
                ", respostas=" + Arrays.toString(respostas) +
                '}';
    }
}

package exemplo.jogo;

import exemplo.connection.ConnectionFactory;

public class Jogador {
     public String nome;
     public int pontuacao;

    ConnectionFactory connection;

     public Jogador(String nome,int pontuacao) {
         this.nome = nome;
         this.pontuacao = 0;
     }

}

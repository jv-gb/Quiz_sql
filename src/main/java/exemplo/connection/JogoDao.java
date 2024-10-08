package exemplo.connection;


import exemplo.jogo.Jogador;
import exemplo.jogo.Jogo;
import exemplo.jogo.Resposta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JogoDao {

    private Connection conn;

    public JogoDao(Connection connection) {
        this.conn = connection;
    }

    public void salvar(Jogador jogador) {

        String sql = "INSERT INTO jogador (nome)" +
                "VALUES (?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, jogador.nome);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public List<Jogo> visualizarPerguntas() {

        String sql = "SELECT DISTINCT pergunta.Texto_pergunta, resposta.Texto_Resposta,resposta.correta FROM pergunta\n" +
                "join resposta\n" +
                "on pergunta.ID_pergunta = resposta.ID_pergunta;";

        List<Jogo> jogos = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                List<Resposta> respostas = new ArrayList<>();
                String pergunta = resultSet.getString("pergunta.Texto_pergunta");

                // Coleta as quatro respostas referentes à pergunta atual
                for (int i = 0; i < 4; i++) {
                    Resposta resposta = new Resposta(resultSet.getString("resposta.Texto_Resposta"),
                            resultSet.getBoolean("resposta.correta"));
                    respostas.add(resposta);

                    // Somente avança para o próximo registro se não for a última resposta
                    if (i < 3) {
                        resultSet.next();
                    }
                }

                // Embaralha as respostas para adicionar um elemento de aleatoriedade
                Collections.shuffle(respostas);

                // Cria um novo Jogo com a pergunta e suas respostas, e adiciona à lista de jogos
                jogos.add(new Jogo(pergunta, respostas));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Collections.shuffle(jogos);
        List<Jogo> subList = jogos.stream()
                .limit(10)
                .collect(Collectors.toList());

        subList.forEach(System.out::println);
        return jogos;
    }

}

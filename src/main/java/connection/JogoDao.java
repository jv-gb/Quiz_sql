package connection;

import jogo.Jogador;
import jogo.Jogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import connection.JogoDao;

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

        String sql = "SELECT DISTINCT pergunta.Texto_pergunta, resposta.Texto_Resposta FROM pergunta\n" +
                "join resposta\n" +
                "on pergunta.ID_pergunta = resposta.ID_pergunta;";

        List<Jogo> jogos = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                List<String> respostas = new ArrayList<>();

                String pergunta = resultSet.getString("pergunta.Texto_pergunta");

                for (int i = 0; i < 4; i++) {
                    String resposta = resultSet.getString("resposta.Texto_Resposta");

                    respostas.add(resposta);
                    resultSet.next();
                }

                Collections.shuffle(respostas); // Embaralha as respostas
                jogos.add(new Jogo(pergunta,respostas));
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

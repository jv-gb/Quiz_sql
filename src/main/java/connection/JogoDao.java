package connection;

import jogo.Jogador;
import jogo.Jogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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

    public Set<Jogo> visualizarPerguntas() {

        String sql = "SELECT DISTINCT pergunta.Texto_pergunta, resposta.Texto_Resposta FROM pergunta\n" +
                "join resposta\n" +
                "on pergunta.ID_pergunta = resposta.ID_pergunta;";

        Set<Jogo> jogos = new HashSet<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String[] respostas = new String[4];

                String pergunta = resultSet.getString("pergunta.Texto_pergunta");

                for (int i = 0; i < 4; i++) {
                    String resposta = resultSet.getString("resposta.Texto_Resposta");

                    respostas[i] = resposta;
                    resultSet.next();
                }
                jogos.add(new Jogo(pergunta, respostas));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        jogos.forEach(System.out::println);
        return jogos;
    }
}

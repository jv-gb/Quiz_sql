package connection;

import jogo.Jogador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void visualizarPerguntas() {

        String sql = "SELECT * FROM perguntas";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String pergunta = resultSet.getString("pergunta");
                String resposta = resultSet.getString("resposta");

                System.out.println("Pergunta: " + pergunta + ", Resposta: " + resposta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}

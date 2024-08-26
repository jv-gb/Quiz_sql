import connection.ConnectionFactory;
import connection.JogoDao;
import jogo.Jogo;

public class Main {
    public static void main(String[] args) {
ConnectionFactory connectionFactory = new ConnectionFactory();
        JogoDao jogoDao = new JogoDao(connectionFactory.recuperarConexao());

        jogoDao.visualizarPerguntas();



    }
}

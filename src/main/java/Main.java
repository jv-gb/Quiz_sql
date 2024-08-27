import exemplo.connection.ConnectionFactory;
import exemplo.connection.JogoDao;

public class Main {
    public static void main(String[] args) {
ConnectionFactory connectionFactory = new ConnectionFactory();
        JogoDao jogoDao = new JogoDao(connectionFactory.recuperarConexao());

        jogoDao.visualizarPerguntas();

    }
}

import connection.ConnectionFactory;
import connection.JogoDao;

public class Main {
    public static void main(String[] args) {
        ConnectionFactory conn = new ConnectionFactory();
        JogoDao jogoDao = new JogoDao(conn.recuperarConexao());

        jogoDao.salvar();


    }
}

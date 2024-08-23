import connection.ConnectionFactory;
import connection.JogoDao;

public class Main {
    public static void main(String[] args) {
        ConnectionFactory con = new ConnectionFactory();
        JogoDao jogo = new JogoDao(con.recuperarConexao());

        jogo.salvar();

    }
}

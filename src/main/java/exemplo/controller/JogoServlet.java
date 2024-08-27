package exemplo.controller;



import exemplo.connection.ConnectionFactory;
import exemplo.connection.JogoDao;
import exemplo.jogo.Jogador;
import exemplo.jogo.Jogo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/jogo")
public class JogoServlet extends HttpServlet {

    private JogoDao jogoDao;

    @Override
    public void init() throws ServletException {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.jogoDao = new JogoDao(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Jogo> perguntas = jogoDao.visualizarPerguntas();
        req.setAttribute("perguntas", perguntas);
        req.getRequestDispatcher("/jogo.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeJogador = req.getParameter("nome-jogador");
        Jogador jogador = new Jogador(nomeJogador, 0);
        jogoDao.salvar(jogador);

        resp.sendRedirect(req.getContextPath() + "/jogo");
    }
}

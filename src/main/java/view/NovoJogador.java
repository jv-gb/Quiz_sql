package view;

import connection.ConnectionFactory;
import connection.JogoDao;
import jogo.Jogador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NovoJogador {
    private JButton jogadorButton;
    private JPanel Main;
    private JTextField jogadorInput;

    public NovoJogador() {
        jogadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Salva o conteúdo do JTextField em uma String
                String nomeJogador = jogadorInput.getText();
                // Aqui você pode utilizar a string 'nomeJogador' conforme necessário

                Jogador jogador = new Jogador(nomeJogador);
                ConnectionFactory conn = new ConnectionFactory();

                JogoDao jogoDao = new JogoDao(conn.recuperarConexao());
                jogoDao.salvar(jogador);

                System.out.println("Nome do jogador: " + nomeJogador);
            }
        });
    }

    public static void main(String[] args) {
        // Criar a janela (JFrame)
        JFrame frame = new JFrame("Novo Jogador");
        frame.setContentPane(new NovoJogador().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // Ajusta o tamanho da janela ao tamanho dos componentes
        frame.setVisible(true); // Torna a janela visível
    }
}

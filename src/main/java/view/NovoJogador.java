package view;

import connection.ConnectionFactory;
import connection.JogoDao;
import jogo.Jogador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NovoJogador extends JFrame {
    private JButton jogarButton;
    private JPanel Main;
    private JTextField jogadorInput;


    public NovoJogador() {
        // Configurações da janela
        setTitle("Novo Jogador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Inicializar componentes
        jogarButton = new JButton("Salvar");
        jogadorInput = new JTextField(20);
        Main = new JPanel();
        Main.add(new JLabel("Nome do Jogador:"));
        Main.add(jogadorInput);
        Main.add(jogarButton);
        setContentPane(Main);


        // Adicionar o ActionListener ao botão
        jogarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Salva o conteúdo do JTextField em uma String
                String nomeJogador = jogadorInput.getText();
                // Aqui você pode utilizar a string 'nomeJogador' conforme necessário

                Jogador jogador = new Jogador(nomeJogador, 0);
                ConnectionFactory conn = new ConnectionFactory();

                JogoDao jogoDao = new JogoDao(conn.recuperarConexao());
                jogoDao.salvar(jogador);

                JOptionPane.showMessageDialog(NovoJogador.this, "Jogador salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                dispose();
                // Abrir a TelaJogo ao clicar no botão "Jogar"
                TelaJogo telaJogo = new TelaJogo();
                telaJogo.setVisible(true);

            }
        });
    }


    public static void main(String[] args) {
        // Cria e exibe a janela
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NovoJogador().setVisible(true);
            }
        });
    }
}

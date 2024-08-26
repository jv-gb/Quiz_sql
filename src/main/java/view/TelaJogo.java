package view;

import connection.ConnectionFactory;
import connection.JogoDao;
import jogo.Jogo;
import jogo.Resposta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class TelaJogo extends JFrame {
    private JogoDao jogoDao;
    private List<Jogo> perguntas;
    private int perguntaAtual = 0;
    private JRadioButton[] opcoes;
    private JLabel questionLabel;
    private ButtonGroup group;

    public TelaJogo() {
        // Inicializa a conexão e carrega as perguntas
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();
        jogoDao = new JogoDao(connection);
        perguntas = jogoDao.visualizarPerguntas();

        // Configurações da janela
        setTitle("Jogo - Perguntas e Respostas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Pergunta Fácil", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Painel para pergunta e respostas
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Pergunta numerada
        questionLabel = new JLabel("", JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.add(questionLabel);

        // Espaçamento entre a pergunta e as respostas
        questionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Grupo de botões de rádio para as respostas
        group = new ButtonGroup();
        opcoes = new JRadioButton[4];

        for (int i = 0; i < opcoes.length; i++) {
            opcoes[i] = new JRadioButton();
            opcoes[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            group.add(opcoes[i]);
            questionPanel.add(opcoes[i]);
        }

        // Adicionar painel de perguntas ao centro do painel principal
        mainPanel.add(questionPanel, BorderLayout.CENTER);

        // Painel de botões para verificar resposta e próxima pergunta
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Botão de confirmar resposta
        JButton confirmar = new JButton("Confirmar ➡️");
        confirmar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona ação ao botão
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarResposta();
            }
        });

        // Adicionar botão ao painel de botões
        buttonPanel.add(confirmar);

        // Adicionar painel de botões ao painel principal
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adicionar painel principal à janela
        add(mainPanel);

        // Carrega a primeira pergunta
        carregarPergunta();
    }

    private void carregarPergunta() {
        if (perguntaAtual < perguntas.size()) {
            Jogo jogo = perguntas.get(perguntaAtual);
            questionLabel.setText((perguntaAtual + 1) + ". " + jogo.pergunta);
            List<Resposta> respostas = jogo.respostas;

            for (int i = 0; i < opcoes.length; i++) {
                opcoes[i].setText(respostas.get(i).resposta);
                opcoes[i].putClientProperty("correta", respostas.get(i).correta);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Fim do jogo!", "Fim", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Fecha a janela quando o jogo termina
        }
    }

    private void verificarResposta() {
        for (JRadioButton opcao : opcoes) {
            if (opcao.isSelected()) {
                boolean correta = (boolean) opcao.getClientProperty("correta");
                if (correta) {
                    JOptionPane.showMessageDialog(this, "Resposta correta!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Resposta incorreta!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            }
        }

        // Avança para a próxima pergunta
        perguntaAtual++;
        carregarPergunta();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaJogo().setVisible(true);
            }
        });
    }
}

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaJogo extends JFrame {

    public TelaJogo() {
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
        JLabel questionLabel = new JLabel("1. O que significa a sigla SQL?", JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.add(questionLabel);

        // Espaçamento entre a pergunta e as respostas
        questionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Grupo de botões de radio para as respostas
        ButtonGroup group = new ButtonGroup();

        // Respostas
        JRadioButton option1 = new JRadioButton("Structured Question List.");
        JRadioButton option2 = new JRadioButton("Simple Query Language.");
        JRadioButton option3 = new JRadioButton("Sequential Query List.");
        JRadioButton correctOption = new JRadioButton("Structured Query Language.");

        // Centralizar opções de resposta
        option1.setAlignmentX(Component.CENTER_ALIGNMENT);
        option2.setAlignmentX(Component.CENTER_ALIGNMENT);
        option3.setAlignmentX(Component.CENTER_ALIGNMENT);
        correctOption.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adicionar opções ao grupo de botões
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(correctOption);

        // Adicionar as opções ao painel de perguntas
        questionPanel.add(option1);
        questionPanel.add(option2);
        questionPanel.add(option3);
        questionPanel.add(correctOption);

        // Adicionar painel de perguntas ao centro do painel principal
        mainPanel.add(questionPanel, BorderLayout.CENTER);

        // Painel de botões para verificar resposta e próxima pergunta
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());



        // Botão de próxima pergunta
        JButton confirmar = new JButton("Confirmar ➡️");
        confirmar.setAlignmentX(Component.CENTER_ALIGNMENT);


        //adiciona açao ao botao
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TelaJogo.this, "Carregando próxima pergunta...", "Próxima Pergunta", JOptionPane.INFORMATION_MESSAGE);
                // Aqui você pode implementar a lógica para carregar a próxima pergunta
            }
        });

        // Adicionar botões ao painel de botões
        buttonPanel.add(confirmar);

        // Adicionar painel de botões ao painel principal
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adicionar painel principal à janela
        add(mainPanel);
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
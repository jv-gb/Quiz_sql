package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenu extends JFrame {

    public TelaMenu() {
        // Configurações da janela
        setTitle("Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Menu", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        // Botões do menu
        JButton playButton = new JButton("Jogar");
        JButton rankingButton = new JButton("Ranking");

        // Adicionar listeners aos botões
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                NovoJogador novoJogador = new NovoJogador();
                novoJogador.setVisible(true);
            }
        });

        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TelaMenu.this, "Mostrando o ranking...", "Ranking", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Adicionar botões ao painel
        buttonPanel.add(playButton);
        buttonPanel.add(rankingButton);

        // Adicionar painel de botões ao painel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Adicionar painel principal à janela
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaMenu().setVisible(true);
            }
        });
    }
}
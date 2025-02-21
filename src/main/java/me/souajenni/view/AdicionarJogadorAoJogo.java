package me.souajenni.view;

import javax.swing.*;

public class AdicionarJogadorAoJogo extends JFrame {
    private JTextField txtUsuario;
    private JTextField txtVitorias;
    private JTextField txtDerrotas;
    private JTextField txtJogo;
    private JButton btSalvar;
    private JButton btVoltar;
    private JPanel painelAdicionarJogador;

    public AdicionarJogadorAoJogo() {
        setContentPane(painelAdicionarJogador);
        setTitle("Adicionar Jogador ao jogo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

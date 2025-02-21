package me.souajenni.view;

import javax.swing.*;

public class ListarJogadoresPorJogo extends JFrame {
    private JPanel painelListarJogadores;
    private JTable tabelaJogadores;
    private JButton btVoltar;

    public ListarJogadoresPorJogo() {
        setContentPane(painelListarJogadores);
        setTitle("Lista de jogadores");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

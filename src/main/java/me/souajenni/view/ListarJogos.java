package me.souajenni.view;

import javax.swing.*;

public class ListarJogos extends JFrame {
    private JTable tabelaJogos;
    private JPanel painelListarJogos;
    private JButton btVoltar;

    public ListarJogos() {
        setContentPane(painelListarJogos);
        setTitle("Lista de jogos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

package me.souajenni.view;

import javax.swing.*;

public class Menu extends JFrame {
    private JPanel painelMenu;
    private JButton btCadastrarJogo;
    private JButton btListarJogos;
    private JButton btAdicionarJogador;
    private JButton btListarJogadoresPorJogo;
    private JButton btAtualizarJogo;
    private JButton btDeletarJogador;
    private JButton btDeletarJogo;

    public Menu() {
        setContentPane(painelMenu);
        setTitle("Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

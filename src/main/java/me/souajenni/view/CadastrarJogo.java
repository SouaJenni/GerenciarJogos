package me.souajenni.view;

import javax.swing.*;

public class CadastrarJogo extends JFrame {
    private JTextField txtCategoria;
    private JTextField txtNome;
    private JTextField txtNota;
    private JButton btSalvar;
    private JButton btVoltar;
    private JPanel painelCadastrar;

    public CadastrarJogo() {
        setContentPane(painelCadastrar);
        setTitle("Cadastrar um jogo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

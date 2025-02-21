package me.souajenni.view;

import javax.swing.*;
import java.awt.*;

public class Buscar extends JFrame {
    private JPanel painelBuscar;
    private JTextField txtBuscar;
    private JButton btBuscar;
    private JButton btVoltar;

    public Buscar() {
        setContentPane(painelBuscar);
        setTitle("Buscar");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

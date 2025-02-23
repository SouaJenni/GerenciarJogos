package me.souajenni.view;

import me.souajenni.controller.Conector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Menu extends JFrame {
    private JPanel painelMenu;
    private JButton btCadastrarJogo;
    private JButton btListarJogos;
    private JButton btAdicionarJogador;
    private JButton btListarJogadoresPorJogo;
    private JButton btAtualizarJogo;
    private JButton btDeletarJogador;
    private JButton btDeletarJogo;
    private Conector conector;
    private Connection conexao;
    private Utils utils;

    public Menu() {
        setContentPane(painelMenu);
        setTitle("Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        utils = new Utils();
        try {
            conector = new Conector();
            conexao = conector.getConexao();
        } catch (SQLException e) {
            utils.mostrarErro(e.getMessage());
        }

        btCadastrarJogo.addActionListener(this::cadastrarJogo);
        btListarJogos.addActionListener(this::listarJogos);
        btAdicionarJogador.addActionListener(this::adicionarJogadorAoJogo);
        btListarJogadoresPorJogo.addActionListener(this::listarJogadoresPorJogo);
        btAtualizarJogo.addActionListener(this::atualizarJogo);
        btDeletarJogador.addActionListener(this::deletarJogador);
        btDeletarJogo.addActionListener(this::deletarJogo);
    }

    public void cadastrarJogo(ActionEvent e) {
        new CadastrarJogo(this);
        dispose();
    }

    public void listarJogos(ActionEvent e) {
        new ListarJogos(this);
        dispose();
    }

    public void adicionarJogadorAoJogo(ActionEvent e) {

    }

    public void listarJogadoresPorJogo(ActionEvent e) {

    }

    public void atualizarJogo(ActionEvent e) {

    }

    public void deletarJogador(ActionEvent e) {

    }

    public void deletarJogo(ActionEvent e) {

    }

    public Connection getConexao() {
        return conexao;
    }

    public static void main(String[] args) {
        new Menu();
    }
}

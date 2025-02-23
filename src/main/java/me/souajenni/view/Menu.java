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
        btAtualizarJogo.addActionListener(this::buscarJogoParaAtualizar);
        btDeletarJogador.addActionListener(this::buscarJogadorParaDeletar);
        btDeletarJogo.addActionListener(this::buscarJogoParaDeletar);
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
        new AdicionarJogadorAoJogo(this);
        dispose();
    }

    public void listarJogadoresPorJogo(ActionEvent e) {
        new Buscar(this, "listarJogadores");
        setVisible(false);
    }

    public void buscarJogoParaAtualizar(ActionEvent e) {
        new Buscar(this, "atualizarJogo");
        setVisible(false);
    }

    public void buscarJogadorParaDeletar(ActionEvent e) {
        new Buscar(this, "deletarJogador");
    }

    public void buscarJogoParaDeletar(ActionEvent e) {
        new Buscar(this, "deletarJogo");
    }

    public Connection getConexao() {
        return conexao;
    }

    public static void main(String[] args) {
        new Menu();
    }
}

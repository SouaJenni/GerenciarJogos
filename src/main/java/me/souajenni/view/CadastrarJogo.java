package me.souajenni.view;

import me.souajenni.DAO.JogoDAO;
import me.souajenni.model.Jogo;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CadastrarJogo extends JFrame {
    private JTextField txtCategoria;
    private JTextField txtNome;
    private JButton btSalvar;
    private JButton btVoltar;
    private JPanel painelCadastrar;
    private JRadioButton rb0;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JRadioButton rb3;
    private JRadioButton rb4;
    private JRadioButton rb5;
    private ButtonGroup selecionarNota;
    private Menu parent;
    private Utils utils;
    private int idJogo = -1;


    public CadastrarJogo(Menu parent) {
        setContentPane(painelCadastrar);
        setTitle("Cadastrar um jogo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.parent = parent;
        utils = new Utils();
        selecionarNota = new ButtonGroup();

        selecionarNota.add(rb0);
        selecionarNota.add(rb1);
        selecionarNota.add(rb2);
        selecionarNota.add(rb3);
        selecionarNota.add(rb4);
        selecionarNota.add(rb5);

        rb0.setActionCommand("0");
        rb1.setActionCommand("1");
        rb2.setActionCommand("2");
        rb3.setActionCommand("3");
        rb4.setActionCommand("4");
        rb5.setActionCommand("5");

        btSalvar.addActionListener(this::salvar);
        btSalvar.setText("Salvar");
        btVoltar.addActionListener(this::voltar);
    }

    public CadastrarJogo(Menu parent, int idJogo) {
        setContentPane(painelCadastrar);
        setTitle("Atualizar um jogo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.parent = parent;
        utils = new Utils();
        selecionarNota = new ButtonGroup();
        this.idJogo = idJogo;

        JogoDAO jogoDAO = new JogoDAO(parent.getConexao());
        try{
            Jogo jogo = jogoDAO.buscarJogoPorId(idJogo);
            txtNome.setText(jogo.getNome());
            txtCategoria.setText(jogo.getCategoria());
            switch (jogo.getNota()) {
                case 0:
                    rb0.setSelected(true);
                    break;
                case 1:
                    rb1.setSelected(true);
                    break;
                case 2:
                    rb2.setSelected(true);
                    break;
                case 3:
                    rb3.setSelected(true);
                    break;
                case 4:
                    rb4.setSelected(true);
                    break;
                case 5:
                    rb5.setSelected(true);
                    break;
            }
            }catch (Exception e){
            utils.mostrarErro(e.getMessage());
        }

        selecionarNota.add(rb0);
        selecionarNota.add(rb1);
        selecionarNota.add(rb2);
        selecionarNota.add(rb3);
        selecionarNota.add(rb4);
        selecionarNota.add(rb5);

        rb0.setActionCommand("0");
        rb1.setActionCommand("1");
        rb2.setActionCommand("2");
        rb3.setActionCommand("3");
        rb4.setActionCommand("4");
        rb5.setActionCommand("5");

        btSalvar.addActionListener(this::atualizar);
        btSalvar.setText("Atualizar");
        btVoltar.addActionListener(this::voltar);
    }

    public void salvar(ActionEvent e) {
        Jogo jogo = new Jogo();
        String nome = txtNome.getText();
        if (nome.isEmpty()) {
            utils.mostrarAlerta("O nome não pode ser vazio!");
            return;
        }
        jogo.setNome(nome);

        String categoria = txtCategoria.getText();
        if (categoria.isEmpty()) {
            utils.mostrarAlerta("A categoria não pode ser vazia!");
            return;
        }
        jogo.setCategoria(categoria);

        int nota = notaSelecionada();
        if (nota == -1) {
            return;
        }
        jogo.setNota(nota);

        JogoDAO jogoDAO = new JogoDAO(parent.getConexao());
        try{
            jogoDAO.inserirJogo(jogo);
            utils.mostrarInformacao("Jogo inserido com sucesso!");
            parent.setVisible(true);
            dispose();
        }catch(Exception ex){
            utils.mostrarAlerta(ex.getMessage());
        }
    }

    public void atualizar(ActionEvent e) {
        Jogo jogo = new Jogo();
        String nome = txtNome.getText();
        if (nome.isEmpty()) {
            utils.mostrarAlerta("O nome não pode ser vazio!");
            return;
        }
        jogo.setNome(nome);


        String categoria = txtCategoria.getText();
        if (categoria.isEmpty()) {
            utils.mostrarAlerta("A categoria não pode ser vazia!");
            return;
        }
        jogo.setCategoria(categoria);

        int nota = notaSelecionada();
        if (nota == -1) {
            return;
        }
        jogo.setNota(nota);

        jogo.setIdJogo(idJogo);
        JogoDAO jogoDAO = new JogoDAO(parent.getConexao());
        try{
            jogoDAO.atualizarJogo(jogo);
            utils.mostrarInformacao("Jogo atualizado com sucesso!");
            parent.setVisible(true);
            dispose();
        }catch(Exception ex){
            utils.mostrarAlerta(ex.getMessage());
        }
    }

    public int notaSelecionada() {
        if (selecionarNota.getSelection() != null) {
            return Integer.parseInt(selecionarNota.getSelection().getActionCommand());
        } else {
            utils.mostrarAlerta("Selecione uma nota.");
            return -1;
        }
    }

    public void voltar(ActionEvent e) {
        parent.setVisible(true);
        dispose();
    }
}

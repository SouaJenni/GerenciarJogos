package me.souajenni.view;

import me.souajenni.DAO.JogoDAO;
import me.souajenni.model.Jogo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CadastrarJogo extends JFrame {
    private JTextField txtCategoria;
    private JTextField txtNome;
    private JTextField txtNota;
    private JButton btSalvar;
    private JButton btVoltar;
    private JPanel painelCadastrar;
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

        btSalvar.addActionListener(this::salvar);
        btSalvar.setText("Salvar");
        btVoltar.addActionListener(this::voltar);
    }

    public CadastrarJogo(Menu parent, int idJogo) {
        setContentPane(painelCadastrar);
        setTitle("Cadastrar um jogo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.parent = parent;
        utils = new Utils();
        this.idJogo = idJogo;

        JogoDAO jogoDAO = new JogoDAO(parent.getConexao());
        try{
            Jogo jogo = jogoDAO.buscarJogoPorId(idJogo);
            txtNome.setText(jogo.getNome());
            txtCategoria.setText(jogo.getCategoria());
            txtNota.setText(jogo.getNota()+"");
        }catch (Exception e){
            utils.mostrarErro(e.getMessage());
        }

        btSalvar.addActionListener(this::salvar);
        btSalvar.setText("Atualizar");
        btVoltar.addActionListener(this::voltar);
    }

    public void salvar(ActionEvent e) {
        Jogo jogo = new Jogo();
        String nome = txtNome.getText();
        if (nome.isEmpty()) {
            utils.mostrarAlerta("O nome não pode ser vazio!");
            return;
        }else{
            jogo.setNome(nome);
        }
        String categoria = txtCategoria.getText();
        if (categoria.isEmpty()) {
            utils.mostrarAlerta("A categoria não pode ser vazia!");
            return;
        }else{
            jogo.setCategoria(categoria);
        }
        try {
            int nota = Integer.parseInt(txtNota.getText());
            jogo.setNota(nota);
        }catch(NumberFormatException ex){
            utils.mostrarAlerta("Nota deve ser um número intero!");
            return;
        }

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

    public void voltar(ActionEvent e) {
        parent.setVisible(true);
        dispose();
    }
}

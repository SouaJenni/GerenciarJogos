package me.souajenni.view;

import me.souajenni.DAO.JogadorDAO;
import me.souajenni.DAO.JogoDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Buscar extends JFrame {
    private JPanel painelBuscar;
    private JTextField txtBuscar;
    private JButton btBuscar;
    private JButton btVoltar;
    private JLabel labelBuscar;
    private Menu parent;
    private Utils utils;

    public Buscar(Menu parent, String opcao) {
        setContentPane(painelBuscar);
        setTitle("Buscar");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.parent = parent;
        this.utils = new Utils();

        switch (opcao) {
            case "listarJogadores":
                labelBuscar.setText("Digite o nome do jogo:");
                btBuscar.setText("Buscar");
                btBuscar.addActionListener(this::listarJogadores);
                break;

            case "atualizarJogo":
                labelBuscar.setText("Digite o nome do jogo:");
                btBuscar.setText("Buscar");
                btBuscar.addActionListener(this::atualizarJogo);
                break;

            case "deletarJogador":
                labelBuscar.setText("Digite o nome de usuário:");
                btBuscar.setText("Excluir");
                btBuscar.addActionListener(this::deletarJogador);
                break;

            default:
                labelBuscar.setText("Digite o nome do jogo:");
                btBuscar.setText("Excluir");
                btBuscar.addActionListener(this::deletarJogo);
                break;
        }

        btVoltar.addActionListener(this::voltar);
    }

    public void listarJogadores(ActionEvent e){
        JogoDAO jogoDAO = new JogoDAO(parent.getConexao());
        try {
            int idJogo = jogoDAO.buscarJogoPorNome(txtBuscar.getText());
            if(idJogo == -1){
                utils.mostrarAlerta("Curso não encontrado.");
                return;
            }
            new ListarJogadoresPorJogo(parent, idJogo);
            dispose();
        } catch (Exception ex) {
            utils.mostrarErro(ex.getMessage());
            return;
        }
    }

    public void atualizarJogo(ActionEvent e){
        JogoDAO jogoDAO1 = new JogoDAO(parent.getConexao());
        try{
            int idJogo = jogoDAO1.buscarJogoPorNome(txtBuscar.getText());
            if(idJogo == -1){
                utils.mostrarAlerta("Jogo não encontrado!");
                return;
            }
            new CadastrarJogo(parent, idJogo);
            dispose();

        }catch(Exception ex){
            utils.mostrarErro(ex.getMessage());
            return;
        }
    }

    public void deletarJogador(ActionEvent e){
        JogadorDAO jogadorDAO = new JogadorDAO(parent.getConexao());
        int resposta = utils.mostrarConfirmacao("Deseja realmente excluir este jogador?");
        if(resposta == JOptionPane.NO_OPTION){
            return;
        }
        try{
            if(jogadorDAO.excluirJogador(txtBuscar.getText())){
                utils.mostrarInformacao("Jogador excluido com sucesso!");
                parent.setVisible(true);
                dispose();
            }else{
                utils.mostrarAlerta("Aluno não enocntrado.");
                return;
            }
        }catch(Exception ex){
            utils.mostrarErro(ex.getMessage());
            return;
        }
    }

    public void deletarJogo(ActionEvent e){
        JogoDAO jogoDAO2 = new JogoDAO(parent.getConexao());
        try {
            int idJogo = jogoDAO2.buscarJogoPorNome(txtBuscar.getText());
            if(idJogo == -1){
                utils.mostrarAlerta("Jogo não encontrado!");
                return;
            }
            int resposta1 = utils.mostrarConfirmacao("Deseja realmente excluir este jogo e todos os seus jogadores?");
            if(resposta1 == JOptionPane.NO_OPTION){
                return;
            }
            if(jogoDAO2.excluirJogoESeusJogadores(idJogo)){
                utils.mostrarInformacao("Jogo excluido com sucesso!");
                parent.setVisible(true);
                dispose();
            }else {
                utils.mostrarAlerta("Erro ao excluir!");
            }
        } catch (SQLException ex) {
            utils.mostrarErro(ex.getMessage());
            return;
        }
    }

    public void voltar(ActionEvent e) {
        parent.setVisible(true);
        dispose();
    }
}

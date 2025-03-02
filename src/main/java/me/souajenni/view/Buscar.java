package me.souajenni.view;

import me.souajenni.DAO.JogadorDAO;
import me.souajenni.DAO.JogoDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class Buscar extends JFrame {
    private JPanel painelBuscar;
    private JButton btBuscar;
    private JButton btVoltar;
    private JLabel labelBuscar;
    private JComboBox selecionarItem;
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
                labelBuscar.setText("Selecione o jogo:");
                btBuscar.setText("Buscar");
                btBuscar.addActionListener(this::listarJogadores);
                break;

            case "atualizarJogador":
                labelBuscar.setText("Selecione o usuário:");
                btBuscar.setText("Buscar");
                btBuscar.addActionListener(this::atualizarJogador);
                break;

            case "atualizarJogo":
                labelBuscar.setText("Selecione o jogo:");
                btBuscar.setText("Buscar");
                btBuscar.addActionListener(this::atualizarJogo);
                break;

            case "deletarJogador":
                labelBuscar.setText("Selecione o usuário:");
                btBuscar.setText("Excluir");
                btBuscar.addActionListener(this::deletarJogador);
                break;

            default:
                labelBuscar.setText("Selecione o jogo:");
                btBuscar.setText("Excluir");
                btBuscar.addActionListener(this::deletarJogo);
                break;
        }

        try {
            carregarItens(opcao);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        btVoltar.addActionListener(this::voltar);
    }

    public void carregarItens(String opcao) throws SQLException {
        selecionarItem.removeAllItems();

        if (opcao.equals("deletarJogador") || opcao.equals("atualizarJogador")) {
            JogadorDAO jogadorDAO = new JogadorDAO(parent.getConexao());
            List<String> jogadores = jogadorDAO.nomesDeUsuario();
            for (String jogador : jogadores) {
                selecionarItem.addItem(jogador);
            }
        } else {
            JogoDAO jogoDAO = new JogoDAO(parent.getConexao());
            List<String> jogos = jogoDAO.NomesDosJogos();
            for (String jogo : jogos) {
                selecionarItem.addItem(jogo);
            }
        }
    }


    public void listarJogadores(ActionEvent e){
        String jogoSelecionado = (String) selecionarItem.getSelectedItem();
        if (jogoSelecionado == null) {
            utils.mostrarAlerta("Selecione um jogo!");
            return;
        }

        JogoDAO jogoDAO = new JogoDAO(parent.getConexao());
        try {
            int idJogo = jogoDAO.buscarJogoPorNome(jogoSelecionado);
            if(idJogo == -1){
                utils.mostrarAlerta("Jogo não encontrado.");
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
        String jogoSelecionado = (String) selecionarItem.getSelectedItem();
        if (jogoSelecionado == null) {
            utils.mostrarAlerta("Selecione um jogo!");
            return;
        }

        JogoDAO jogoDAO1 = new JogoDAO(parent.getConexao());
        try{
            int idJogo = jogoDAO1.buscarJogoPorNome(jogoSelecionado);
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

    public void atualizarJogador(ActionEvent e){
        String jogadorSelecionado = (String) selecionarItem.getSelectedItem();
        if (jogadorSelecionado == null) {
            utils.mostrarAlerta("Selecione um jogador!");
            return;
        }

        JogadorDAO jogadorDAO = new JogadorDAO(parent.getConexao());
        try{
            int idJogador = jogadorDAO.buscarJogadorPorUsuario(jogadorSelecionado);
            if(idJogador == -1){
                utils.mostrarAlerta("Jogo não encontrado!");
                return;
            }
            new AdicionarJogadorAoJogo(parent, idJogador);
            dispose();

        }catch(Exception ex){
            utils.mostrarErro(ex.getMessage());
            return;
        }

    }

    public void deletarJogador(ActionEvent e){
        String jogadorSelecionado = (String) selecionarItem.getSelectedItem();
        if (jogadorSelecionado == null) {
            utils.mostrarAlerta("Selecione um jogador!");
            return;
        }

        JogadorDAO jogadorDAO = new JogadorDAO(parent.getConexao());
        int resposta = utils.mostrarConfirmacao("Deseja realmente excluir este jogador?");
        if(resposta == JOptionPane.NO_OPTION){
            return;
        }
        try{
            if(jogadorDAO.excluirJogador(jogadorSelecionado)){
                utils.mostrarInformacao("Jogador excluído com sucesso!");
                parent.setVisible(true);
                dispose();
            }else{
                utils.mostrarAlerta("Jogador não enocntrado.");
                return;
            }
        }catch(Exception ex){
            utils.mostrarErro(ex.getMessage());
            return;
        }
    }

    public void deletarJogo(ActionEvent e){
        String jogoSelecionado = (String) selecionarItem.getSelectedItem();
        if (jogoSelecionado == null) {
            utils.mostrarAlerta("Selecione um jogo!");
            return;
        }

        JogoDAO jogoDAO2 = new JogoDAO(parent.getConexao());
        try {
            int idJogo = jogoDAO2.buscarJogoPorNome(jogoSelecionado);
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

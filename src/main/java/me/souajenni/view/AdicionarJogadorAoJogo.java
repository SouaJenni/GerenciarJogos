package me.souajenni.view;

import me.souajenni.DAO.JogadorDAO;
import me.souajenni.DAO.JogoDAO;
import me.souajenni.model.Jogador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class AdicionarJogadorAoJogo extends JFrame {
    private JTextField txtUsuario;
    private JTextField txtVitorias;
    private JTextField txtDerrotas;
    private JButton btSalvar;
    private JButton btVoltar;
    private JPanel painelAdicionarJogador;
    private JComboBox selecionarJogo;
    private Menu parent;
    private Utils utils;
    private int idJogador = -1;

    public AdicionarJogadorAoJogo(Menu parent) {
        setContentPane(painelAdicionarJogador);
        setTitle("Adicionar jogador ao jogo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.parent = parent;
        this.utils = new Utils();

        try {
            carregarJogos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        btSalvar.addActionListener(this::salvar);
        btSalvar.setText("Salvar");
        btVoltar.addActionListener(this::voltar);
    }

    public AdicionarJogadorAoJogo(Menu parent, int idJogador) throws SQLException {
        setContentPane(painelAdicionarJogador);
        setTitle("Atualizar jogador");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.parent = parent;
        this.utils = new Utils();
        this.idJogador = idJogador;

        try {
            carregarJogos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JogadorDAO jogadorDAO = new JogadorDAO(parent.getConexao());
        JogoDAO jogoDAO = new JogoDAO(parent.getConexao());
        try {
            Jogador jogador = jogadorDAO.buscarJogadorPorId(idJogador);
            txtUsuario.setText(jogador.getUsuario());
            txtVitorias.setText(jogador.getVitorias()+"");
            txtDerrotas.setText(jogador.getDerrotas()+"");
            jogador.setElo(jogador.getElo());
            jogador.setIdJogoDoJogador(jogador.getIdJogoDoJogador());

            for (int i = 0; i < selecionarJogo.getItemCount(); i++) {
                if (jogoDAO.buscarJogoPorNome((String) selecionarJogo.getItemAt(i)) == jogador.getIdJogoDoJogador()) {
                    selecionarJogo.setSelectedIndex(i);
                    selecionarJogo.setEnabled(false);
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        btSalvar.addActionListener(this::atualizar);
        btSalvar.setText("Atualizar");
        btVoltar.addActionListener(this::voltar);
    }

    public void carregarJogos() throws SQLException {
        JogoDAO jogoDAO = new JogoDAO(parent.getConexao());
        List<String> jogos = jogoDAO.nomesDosJogos();
        selecionarJogo.removeAllItems();
        for (String jogo : jogos) {
            selecionarJogo.addItem(jogo);
        }
    }

    public void salvar(ActionEvent e){
        String usuario = txtUsuario.getText();
        if(usuario.isEmpty()){
            utils.mostrarAlerta("Usuário não pode ser vazio!");
            return;
        }

        int vitorias = 0;
        try{
            vitorias = Integer.parseInt(txtVitorias.getText());
        }catch(NumberFormatException ex){
            utils.mostrarAlerta("Vitórias deve ser um número inteiro.");
            return;
        }

        int derrotas = 0;
        try {
            derrotas = Integer.parseInt(txtDerrotas.getText());
        }catch(NumberFormatException ex){
            utils.mostrarAlerta("Derrotas deve ser um número inteiro.");
            return;
        }

        String jogoSelecionado = (String) selecionarJogo.getSelectedItem();
        if (jogoSelecionado == null) {
            utils.mostrarAlerta("Selecione um jogo!");
            return;
        }

        JogoDAO jogoDAO = new JogoDAO(parent.getConexao());
        int idJogo;
        try{
            idJogo = jogoDAO.buscarJogoPorNome(jogoSelecionado);
            if(idJogo == -1){
                utils.mostrarAlerta("Jogo não encontrado!");
                return;
            }
        }catch(Exception ex){
            utils.mostrarAlerta(ex.getMessage());
            return;
        }

        Jogador jogador = new Jogador();
        jogador.setUsuario(usuario);
        jogador.setVitorias(vitorias);
        jogador.setDerrotas(derrotas);
        jogador.setElo(definirElo());

        JogadorDAO jogadorDAO = new JogadorDAO(parent.getConexao());
        try{
            jogadorDAO.inserirJogador(jogador, idJogo);
            utils.mostrarInformacao("Jogador adicionado com sucesso!");
            parent.setVisible(true);
            dispose();
        }catch(Exception ex){
            utils.mostrarErro(ex.getMessage());
            return;
        }
    }

    public void atualizar(ActionEvent e){
        String usuario = txtUsuario.getText();
        if(usuario.isEmpty()){
            utils.mostrarAlerta("Usuário não pode ser vazio!");
            return;
        }

        int vitorias = 0;
        try{
            vitorias = Integer.parseInt(txtVitorias.getText());
        }catch(NumberFormatException ex){
            utils.mostrarAlerta("Vitórias deve ser um número inteiro.");
            return;
        }

        int derrotas = 0;
        try {
            derrotas = Integer.parseInt(txtDerrotas.getText());
        }catch(NumberFormatException ex){
            utils.mostrarAlerta("Derrotas deve ser um número inteiro.");
            return;
        }

        Jogador jogador = new Jogador();
        jogador.setUsuario(usuario);
        jogador.setVitorias(vitorias);
        jogador.setDerrotas(derrotas);
        jogador.setElo(definirElo());
        jogador.setIdJogador(idJogador);

        JogadorDAO jogadorDAO = new JogadorDAO(parent.getConexao());
        try{
            jogadorDAO.atualizarJogador(jogador);
            utils.mostrarInformacao("Jogador atualizado com sucesso!");
            parent.setVisible(true);
            dispose();
        }catch(Exception ex){
            utils.mostrarErro(ex.getMessage());
            return;
        }
    }

    public String definirElo() {
        int vitorias = Integer.parseInt(txtVitorias.getText());
        int derrotas = Integer.parseInt(txtDerrotas.getText());

        if (vitorias < 10) {
            return "Ainda sem elo";
        } else if (vitorias >= 50 && derrotas <= 3) {
            return "Mestre";
        } else if (vitorias >= 50 && derrotas <= 10) {
            return "Diamante";
        } else if (vitorias >= 40 && derrotas <= 15) {
            return "Platina";
        } else if (vitorias >= 30 && derrotas <= 20) {
            return "Ouro";
        } else if (vitorias >= 20 && derrotas <= 25) {
            return "Ferro";
        } else {
            return "Bronze";
        }
    }

    public void voltar(ActionEvent e){
        parent.setVisible(true);
        dispose();
    }
}

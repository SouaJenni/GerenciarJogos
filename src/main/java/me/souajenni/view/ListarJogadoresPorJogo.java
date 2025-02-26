package me.souajenni.view;

import me.souajenni.DAO.JogadorDAO;
import me.souajenni.model.Jogador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListarJogadoresPorJogo extends JFrame {
    private JPanel painelListarJogadores;
    private JTable tabelaJogadores;
    private JButton btVoltar;
    private Menu parent;

    public ListarJogadoresPorJogo(Menu parent, int idJogo) {
        setContentPane(painelListarJogadores);
        setTitle("Lista de jogadores");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.parent = parent;

        String[] colunas = {"Usuario", "Vitorias", "Derrotas", "Elo"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JogadorDAO jogadorDAO = new JogadorDAO(parent.getConexao());
        try{
            List<Jogador> jogadores = jogadorDAO.listarJogadoresPorJogo(idJogo);
            for(Jogador jogador : jogadores){
                Object[] linha = {jogador.getUsuario(), jogador.getVitorias(), jogador.getDerrotas(), jogador.getElo()};
                modelo.addRow(linha);
            }
        }catch (Exception e){
            Utils utils = new Utils();
            utils.mostrarErro(e.getMessage());
            return;
        }
        tabelaJogadores.setModel(modelo);

        btVoltar.addActionListener(this::voltar);
    }

    public void voltar(ActionEvent e) {
        parent.setVisible(true);
        dispose();
    }
}

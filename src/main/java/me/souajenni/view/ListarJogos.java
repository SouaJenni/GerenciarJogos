package me.souajenni.view;

import com.sun.jdi.connect.Connector;
import me.souajenni.DAO.JogoDAO;
import me.souajenni.model.Jogo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListarJogos extends JFrame {
    private JTable tabelaJogos;
    private JPanel painelListarJogos;
    private JButton btVoltar;
    private Menu parent;

    public ListarJogos(Menu parent) {
        setContentPane(painelListarJogos);
        setTitle("Lista de jogos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.parent = parent;

        String[] colunas = {"Nome", "Categoria", "Nota"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JogoDAO jogoDAO = new JogoDAO(parent.getConexao());

        try {
            List<Jogo> jogos = jogoDAO.listarJogos();
            for (Jogo jogo : jogos) {
                Object[] linha = {jogo.getNome(), jogo.getCategoria(), jogo.getNota()};
                modelo.addRow(linha);
            }
        }catch(Exception e) {
            Utils utils = new Utils();
            utils.mostrarErro(e.getMessage());
        }
        tabelaJogos.setModel(modelo);

        btVoltar.addActionListener(this::voltar);
    }

    public void voltar(ActionEvent e) {
        parent.setVisible(true);
        dispose();
    }
}

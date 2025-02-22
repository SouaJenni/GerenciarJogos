package me.souajenni.DAO;

import me.souajenni.model.Jogador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JogadorDAO {
    private Connection conexao;

    public JogadorDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public boolean inserirJogador(Jogador jogador, int idJogo) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_idJogo) VALUES " + "(\""+jogador.getUsuario()+"\", "+jogador.getVitorias()+", "+jogador.getDerrotas()+", \""+jogador.getElo()+"\", "+idJogo+")";
        int linhas = statement.executeUpdate(query);

        return linhas > 0;
    }

    public List<Jogador> listarJogadoresPorJogo(int idJogo) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "SELECT * FROM jogador WHERE Jogo_idJogo = "+idJogo;
        ResultSet resultado = statement.executeQuery(query);

        List<Jogador> jogadores = new ArrayList<Jogador>();
        while (resultado.next()) {
            Jogador jogador = new Jogador();
            jogador.setUsuario(resultado.getString("usuario"));
            jogador.setVitorias(resultado.getInt("vitorias"));
            jogador.setDerrotas(resultado.getInt("derrotas"));
            jogador.setElo(resultado.getString("elo"));
            jogadores.add(jogador);
        }
        return jogadores;
    }

    public boolean excluirJogador(String usuario) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "DELETE FROM jogador WHERE usuario = \""+usuario+"\"";
        int linhas = statement.executeUpdate(query);

        return linhas > 0;
    }

    public boolean excluirJogadoresDoJogo(int idJogo) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "DELETE FROM jogador WHERE Jogo_idJogo = " + idJogo;;
        int linhas = statement.executeUpdate(query);

        return linhas > 0;
    }
}

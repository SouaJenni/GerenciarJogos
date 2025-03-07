package me.souajenni.DAO;

import me.souajenni.model.Jogador;
import me.souajenni.model.Jogo;

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

        String query = "INSERT INTO jogador (usuario, vitorias, derrotas, elo, Jogo_id) VALUES " + "(\""+jogador.getUsuario()+"\", "+jogador.getVitorias()+", "+jogador.getDerrotas()+", \""+jogador.getElo()+"\", "+idJogo+")";
        int linhas = statement.executeUpdate(query);

        return linhas > 0;
    }

    public List<Jogador> listarJogadoresPorJogo(int idJogo) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "SELECT * FROM jogador WHERE Jogo_id = "+idJogo;
        ResultSet resultado = statement.executeQuery(query);

        List<Jogador> jogadores = new ArrayList();
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

        String query = "DELETE FROM jogador WHERE Jogo_id = " + idJogo;;
        int linhas = statement.executeUpdate(query);

        return linhas > 0;
    }

    public List<String> nomesDeUsuario() throws SQLException {
        Statement statement = this.conexao.createStatement();

        List<String> jogadores = new ArrayList<>();
        String query = "SELECT usuario FROM jogador";
        ResultSet resultado = statement.executeQuery(query);
        while (resultado.next()) {
            jogadores.add(resultado.getString("usuario"));
        }

        return jogadores;
    }

    public int buscarJogadorPorUsuario(String usuario) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "SELECT * FROM jogador WHERE usuario = \"" + usuario + "\"";
        ResultSet resultado = statement.executeQuery(query);
        int idJogador = -1;
        while (resultado.next()) {
            idJogador = resultado.getInt("id");
        }
        return idJogador;
    }

    public Jogador buscarJogadorPorId(int id) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "SELECT * FROM jogador WHERE id = " + id;
        ResultSet resultado = statement.executeQuery(query);
        Jogador jogador = new Jogador();
        while (resultado.next()) {
            jogador.setUsuario(resultado.getString("usuario"));
            jogador.setVitorias(resultado.getInt("vitorias"));
            jogador.setDerrotas(resultado.getInt("derrotas"));
            jogador.setElo(resultado.getString("elo"));
            jogador.setIdJogoDoJogador(resultado.getInt("Jogo_id"));
        }
        return jogador;
    }

    public int atualizarJogador(Jogador jogador) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "UPDATE jogador SET usuario = \"" + jogador.getUsuario() + "\", vitorias = " + jogador.getVitorias() +
                ", derrotas = " + jogador.getDerrotas() + ", elo = \"" + jogador.getElo() + "\" WHERE id = \"" + jogador.getIdJogador() + "\"";
        int linhas = statement.executeUpdate(query);

        return linhas;
    }
}

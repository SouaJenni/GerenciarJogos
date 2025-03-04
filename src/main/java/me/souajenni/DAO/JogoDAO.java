package me.souajenni.DAO;

import me.souajenni.model.Jogo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JogoDAO {
    private Connection conexao;

    public JogoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public List<Jogo> listarJogos() throws SQLException {
        Statement statement = this.conexao.createStatement();
        String query = "SELECT * FROM jogo";
        ResultSet resultado = statement.executeQuery(query);
        List<Jogo> jogos = new ArrayList<Jogo>();
        while (resultado.next()) {
            Jogo jogo = new Jogo();
            jogo.setNome(resultado.getString("nome"));
            jogo.setCategoria(resultado.getString("categoria"));
            jogo.setNota(resultado.getInt("nota"));
            jogos.add(jogo);
        }
        return jogos;
    }

    public boolean inserirJogo(Jogo jogo) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "INSERT INTO jogo (nome, categoria, nota) VALUES " + "(\""+jogo.getNome()+"\", \""+jogo.getCategoria()+"\", "+jogo.getNota()+")";
        int linhas = statement.executeUpdate(query);

        return linhas > 0;
    }

    public int atualizarJogo(Jogo jogo) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "UPDATE jogo SET nome = \""+jogo.getNome()+"\", categoria = \""+jogo.getCategoria()+"\", nota = "+jogo.getNota()+" WHERE id =" + jogo.getIdJogo();
        int linhas = statement.executeUpdate(query);

        return linhas;
    }

    public boolean excluirJogoESeusJogadores(int idJogo) throws SQLException {
        Statement statement = this.conexao.createStatement();
        JogadorDAO jogadorDAO = new JogadorDAO(conexao);
        jogadorDAO.excluirJogadoresDoJogo(idJogo);

        String query = "DELETE FROM jogo WHERE id = " + idJogo;
        int linhas = statement.executeUpdate(query);

        return linhas > 0;
    }

    public int buscarJogoPorNome(String nome) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "SELECT * FROM jogo WHERE nome = \"" + nome + "\"";
        ResultSet resultado = statement.executeQuery(query);
        int idJogo = -1;
        while (resultado.next()) {
            idJogo = resultado.getInt("id");
        }
        return idJogo;
    }

    public Jogo buscarJogoPorId(int id) throws SQLException {
        Statement statement = this.conexao.createStatement();

        String query = "SELECT * FROM jogo WHERE id = " + id;
        ResultSet resultado = statement.executeQuery(query);
        Jogo jogo = new Jogo();
        while (resultado.next()) {
            jogo.setNome(resultado.getString("nome"));
            jogo.setCategoria(resultado.getString("categoria"));
            jogo.setNota(resultado.getInt("nota"));
        }
        return jogo;
    }

    public List<String> nomesDosJogos() throws SQLException {
        Statement statement = this.conexao.createStatement();

        List<String> jogos = new ArrayList<>();
        String query = "SELECT nome FROM jogo";
        ResultSet resultado = statement.executeQuery(query);
        while (resultado.next()) {
            jogos.add(resultado.getString("nome"));
        }

        return jogos;
    }
}


package me.souajenni.model;

public class Jogador {
    private String usuario;
    private int vitorias;
    private int derrotas;
    private String elo;
    private int idJogador;
    private int idJogoDoJogador;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getElo() {
        return elo;
    }

    public void setElo(String elo) {
        this.elo = elo;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public int getIdJogoDoJogador() {
        return idJogoDoJogador;
    }

    public void setIdJogoDoJogador(int idJogoDoJogador) {
        this.idJogoDoJogador = idJogoDoJogador;
    }
}

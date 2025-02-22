package me.souajenni.view;

import javax.swing.*;

public class Utils {
    public void mostrarErro(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, "Erro no formulário", JOptionPane.ERROR_MESSAGE);

    }

    public void mostrarAlerta(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, "Confira a informção", JOptionPane.WARNING_MESSAGE);

    }

    public void mostrarInformacao(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, "Informção", JOptionPane.INFORMATION_MESSAGE);

    }

    public int mostarConfirmacao(String mensagem){
        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Escolha uma opção", JOptionPane.YES_NO_OPTION);
        return opcao;
    }
}

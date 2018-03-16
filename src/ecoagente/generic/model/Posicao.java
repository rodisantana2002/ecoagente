/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.generic.model;

/**
 *
 * @author rodolfosmac
 */
public class Posicao {
    private int linha;
    private int coluna;
    private String descricao;

    public Posicao(int linha, int coluna){
        setLinha(linha);
        setColuna(coluna);
    }

    public int getLinha() {
        return linha;
    }
    public void setLinha(int inicio) {
        this.linha = inicio;
    }

    public int getColuna() {
        return coluna;
    }
    public void setColuna(int termino) {
        this.coluna = termino;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

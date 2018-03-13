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

    /**
     * @return the inicio
     */
    public int getLinha() {
        return linha;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setLinha(int inicio) {
        this.linha = inicio;
    }

    /**
     * @return the termino
     */
    public int getColuna() {
        return coluna;
    }

    /**
     * @param termino the termino to set
     */
    public void setColuna(int termino) {
        this.coluna = termino;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

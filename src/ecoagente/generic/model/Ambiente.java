/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.generic.model;

import java.util.List;

/**
 *
 * @author rodolfosmac
 */
public abstract class Ambiente {
    private List<Agente> situacaoAtual;
    private List<Agente> situacaoFinal;
    private Estado estado;
    /**
     * @return the situacaoAtual
     */
    public List<Agente> getSituacaoAtual() {
        return situacaoAtual;
    }

    /**
     * @param situacaoAtual the situacaoAtual to set
     */
    public void setSituacaoAtual(List<Agente> situacaoAtual) {
        this.situacaoAtual = situacaoAtual;
    }

    /**
     * @return the situacaoFinal
     */
    public List<Agente> getSituacaoFinal() {
        return situacaoFinal;
    }

    /**
     * @param situacaoFinal the situacaoFinal to set
     */
    public void setSituacaoFinal(List<Agente> situacaoFinal) {
        this.situacaoFinal = situacaoFinal;
    }

    /**
     * @return the estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }   
}

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
public abstract class Ambiente {
    private Agente situacaoInicial;
    private Agente situacaoAtual;
    private Agente situacaoFinal;
    private Estado estado;
        
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

    
    /**
     * exibe a situacao conforme o Agente repassado
     * @param situacao
     */
    public abstract void exibirSituacao(Agente situacao);

    /**
     * @return the situacaoInicial
     */
    public Agente getSituacaoInicial() {
        return situacaoInicial;
    }

    /**
     * @param situacaoInicial the situacaoInicial to set
     */
    public void setSituacaoInicial(Agente situacaoInicial) {
        this.situacaoInicial = situacaoInicial;
    }

    /**
     * @return the situacaoAtual
     */
    public Agente getSituacaoAtual() {
        return situacaoAtual;
    }

    /**
     * @param situacaoAtual the situacaoAtual to set
     */
    public void setSituacaoAtual(Agente situacaoAtual) {
        this.situacaoAtual = situacaoAtual;
    }

    /**
     * @return the situacaoFinal
     */
    public Agente getSituacaoFinal() {
        return situacaoFinal;
    }

    /**
     * @param situacaoFinal the situacaoFinal to set
     */
    public void setSituacaoFinal(Agente situacaoFinal) {
        this.situacaoFinal = situacaoFinal;
    }
          
}

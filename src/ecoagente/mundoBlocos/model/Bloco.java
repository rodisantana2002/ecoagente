/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.core.itfAcaoAgente;
import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.helpers.mensagens.clsPSR;
import ecoagente.generic.model.Agente;
import ecoagente.generic.model.Estado;
import ecoagente.generic.model.Posicao;

/**
 *
 * @author rodolfosmac
 */
public class Bloco extends Agente implements itfAcaoAgente, itfSaidaTerminal{
    private Posicao objetivo;
    private Posicao posicaoAtual;
        
    public Bloco(int id, char alias){
        super();
        setId(id);
        setDescricao("livre");
        setAlias(alias);
    }
    
    public Bloco(int id, String descricao, char alias, Estado estado){
        super();
        setId(id);
        setDescricao(descricao);
        setEstado(estado);
        setAlias(alias);
    }   
    
    /**
     * @return the objetivo
     */
    public Posicao getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(Posicao objetivo) {
        this.objetivo = objetivo;
    }

    /**
     * @return the posicaoAtual
     */
    public Posicao getPosicaoAtual() {
        return posicaoAtual;
    }

    /**
     * @param posicaoAtual the posicaoAtual to set
     */
    public void setPosicaoAtual(Posicao posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }    
    
    @Override
    public boolean atacar(int posicao) {return true;}

    @Override
    public boolean fugir(int posicao) {return true;}

    @Override
    public void desenharTerminal() {
        clsPSR.prt(desenharBloco());        
    }  

    private String desenharBloco() {
        StringBuilder strBloco = new StringBuilder("");
        strBloco.append("  +---+\n");
        strBloco.append("  | " + getAlias() + " |\n");        
        strBloco.append("  +---+");        
        return strBloco.toString();
    }
}

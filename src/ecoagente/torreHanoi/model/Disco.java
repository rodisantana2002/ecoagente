/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.torreHanoi.model;

import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.model.Agente;
import ecoagente.generic.model.Posicao;

/**
 *
 * @author rodolfosmac
 */
public class Disco extends Agente implements itfSaidaTerminal{
   
    private Posicao posicaoAtual;
    private Posicao objetivo;

    private Disco agressor;
        
    public static final String PROP_ID = "id";

    public Disco(int id, Posicao posicaoAtual, Posicao objetivo) {
        super();      
        setPosicaoAtual(posicaoAtual);
        setObjetivo(objetivo);
        
    }
   
    public Posicao getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Posicao ondeEstou) {
        this.posicaoAtual = ondeEstou;
    }

    public Posicao getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Posicao destino) {
        this.objetivo = destino;
    }

    public Disco getAgressor() {
        return agressor;
    }

    public void setAgressor(Disco agressor) {
        this.agressor = agressor;
    }
    
    
    @Override
    public void desenharTerminal() {}
    
}

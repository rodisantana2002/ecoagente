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
    private Posicao posicao;
    private Bloco agressor;
    private Bloco agredido;
        
    public Bloco(int id, String alias){
        super();
        setId(id);
        setDescricao("livre");
        setAlias(alias);
        setPosicao(posicao);
    }
    
    public Bloco(int id, String descricao, String alias, Estado estado, Posicao posicao, Posicao objetivo){
        super();
        setId(id);
        setDescricao(descricao);
        setEstado(estado);
        setAlias(alias);
        setPosicao(posicao);
        setObjetivo(objetivo);
    }   
    
    public Posicao getObjetivo() {
        return objetivo;
    }

    public Bloco getAgressor() {
        return agressor;
    }

    public void setAgressor(Bloco agressor) {
        this.agressor = agressor;
    }

    public Bloco getAgredido() {
        return agredido;
    }

    public void setAgredido(Bloco agredido) {
        this.agredido = agredido;
    }

    public void setObjetivo(Posicao objetivo) {
        this.objetivo = objetivo;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicaoAtual) {
        this.posicao = posicaoAtual;
    }    
        
    @Override
    public void desenharTerminal() {
        clsPSR.prt(desenharBloco());        
    }  

    public String desenharBloco() {
        StringBuilder strBloco = new StringBuilder("");
        strBloco.append("  +---+\n");
        strBloco.append("  | " + getAlias() + " |\n");        
        strBloco.append("  +---+");        
        return strBloco.toString();
    }    

    @Override
    public void atacar(Agente agente) {
        Bloco bloco = (Bloco) agente;
        bloco.setAgredido(bloco);
    }
    
    @Override
    public void fugir(Posicao posicao) {
        setPosicao(posicao);
    }

    @Override
    public void atualizarEstado(Estado estado) {
        setEstado(estado);
    }
}

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

/**
 *
 * @author rodolfosmac
 */
public class Bloco extends Agente implements itfAcaoAgente, itfSaidaTerminal{
    
    public Bloco(int id, String descricao, char alias, Estado estado){
        super();
        setId(id);
        setDescricao(descricao);
        setEstado(estado);
        setAlias(alias);
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

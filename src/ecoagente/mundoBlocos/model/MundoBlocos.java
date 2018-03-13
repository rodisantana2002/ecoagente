/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.model.Agente;
import ecoagente.generic.model.Ambiente;
import ecoagente.generic.model.Estado;

/**
 *
 * @author rodolfosmac
 */
public class MundoBlocos extends Ambiente{
    
    public MundoBlocos(Agente situacaoAtual, Agente situacaoFinal){
        super();
        setSituacaoInicial(situacaoAtual);
        setSituacaoFinal(situacaoAtual);
        setEstado(Estado.INSATISFEITO);
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.generic.core;

import ecoagente.generic.model.Agente;
import ecoagente.generic.model.Estado;
import ecoagente.generic.model.Posicao;

/**
 *
 * @author rodolfosmac
 */
public interface itfAcaoAgente {
    public void atacar(Agente agente);    
    public void fugir(Posicao posicao);
    public void atualizarEstado(Estado estado);
}

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
    public String atacar(Agente agente);    
    public Posicao fugir(Posicao posicao);
    public Posicao movimentar(Posicao posicao);
    public Posicao validarObjetivo(Posicao posicao);
    public void atualizarEstado(Estado estado);
}

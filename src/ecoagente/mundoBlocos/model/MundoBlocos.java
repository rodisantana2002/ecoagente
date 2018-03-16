/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.model.Agente;
import ecoagente.generic.model.Ambiente;
import java.util.List;

/**
 *
 * @author rodolfosmac
 */
public class MundoBlocos extends Ambiente{
    private itfSaidaTerminal prtSaida;    
    private List<Bloco> blocos;
    private List<Mesa> mesas;
    private Mesa mesa;
    private int linhas, colunas;
    
    public MundoBlocos(List<Bloco> blocos, int linhas, int colunas){
        super();
        this.blocos = blocos;   
        this.linhas = linhas;
        this.colunas = colunas;
    }   
    
    public void iniciar(){        
        mesa = new Mesa(01, "Situação Inicial", blocos, linhas, colunas);
        exibirSituacao(mesa);
    }

    @Override
    public void exibirSituacao(Agente agente) {      
        prtSaida = (itfSaidaTerminal) agente;
        prtSaida.desenharTerminal();
    }
}


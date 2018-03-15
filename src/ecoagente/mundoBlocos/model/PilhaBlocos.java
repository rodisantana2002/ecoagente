/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.model.Agente;
import java.util.List;

/**
 *
 * @author rodolfosantana
 */
public class PilhaBlocos extends Agente implements itfSaidaTerminal{
    private int linhas;
    private int colunas;
    private Bloco[][] matrixBlocos;
    private List<Bloco> blocos; 

    public PilhaBlocos(int id, String descricao, int linhas, int colunas, List<Bloco> blocos){        
        super();
        setId(id);
        setDescricao(descricao);        
        this.linhas = linhas;
        this.colunas = colunas;
        this.blocos = blocos;
        
        popularMatrixBlocos();
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }
        
    @Override
    public void desenharTerminal() {}

    private void popularMatrixBlocos() {
        matrixBlocos = new Bloco[linhas][colunas];
        for (Bloco bloco : blocos){
            matrixBlocos[bloco.getObjetivo().getLinha()][bloco.getObjetivo().getColuna()] = bloco;
        }
    }
}

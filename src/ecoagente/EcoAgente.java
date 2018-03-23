/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente;

import ecoagente.generic.model.Estado;
import ecoagente.generic.model.Posicao;
import ecoagente.mundoBlocos.control.MundoBlocos;
import ecoagente.mundoBlocos.model.Bloco;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodolfosmac
 */
public class EcoAgente {

    public static void main(String[] args) {
        Posicao objetivo, posInicial;

        //---------------------------------------------------------------------------------------------------
        //  CONFIGURAÇÃO EXEMPLO 01
        //---------------------------------------------------------------------------------------------------        
        posInicial = new Posicao(0, 0);
        objetivo = new Posicao(4, 1);
        Bloco bloco1 = new Bloco(1, "Bloco de Teste1", 'A', Estado.RS, posInicial, objetivo);       
        
        posInicial = new Posicao(1, 0);       
        objetivo = new Posicao(3, 1);       
        Bloco bloco2 = new Bloco(2, "Bloco de Teste2", 'B', Estado.RS, posInicial, objetivo);
        
        posInicial = new Posicao(1,3);        
        objetivo = new Posicao(2, 1);        
        Bloco bloco3 = new Bloco(3, "Bloco de Teste3", 'C', Estado.RS, posInicial, objetivo);
        
        posInicial = new Posicao(0, 3);        
        objetivo = new Posicao(1, 1);        
        Bloco bloco4 = new Bloco(4, "Bloco de Teste4", 'D', Estado.RS, posInicial, objetivo);
        
        posInicial = new Posicao(0, 1);        
        objetivo = new Posicao(0, 1);        
        Bloco bloco5 = new Bloco(5, "Bloco de Teste5", 'E', Estado.RS, posInicial, objetivo);

        
        //---------------------------------------------------------------------------------------------------
        //  CONFIGURAÇÃO EXEMPLO 02
        //---------------------------------------------------------------------------------------------------        
//        posInicial = new Posicao(0, 0);
//        objetivo = new Posicao(4, 1);
//        Bloco bloco1 = new Bloco(1, "Bloco de Teste1", 'A', Estado.RS, posInicial, objetivo);       
//        
//        posInicial = new Posicao(1, 0);       
//        objetivo = new Posicao(3, 1);       
//        Bloco bloco2 = new Bloco(2, "Bloco de Teste2", 'B', Estado.RS, posInicial, objetivo);
//        
//        posInicial = new Posicao(1,3);        
//        objetivo = new Posicao(2, 1);        
//        Bloco bloco3 = new Bloco(3, "Bloco de Teste3", 'C', Estado.RS, posInicial, objetivo);
//        
//        posInicial = new Posicao(0, 3);        
//        objetivo = new Posicao(1, 1);        
//        Bloco bloco4 = new Bloco(4, "Bloco de Teste4", 'D', Estado.RS, posInicial, objetivo);
//        
//        posInicial = new Posicao(0, 1);        
//        objetivo = new Posicao(0, 1);        
//        Bloco bloco5 = new Bloco(5, "Bloco de Teste5", 'E', Estado.RS, posInicial, objetivo);

        //---------------------------------------------------------------------------------------------------
        //  CONFIGURAÇÃO EXEMPLO 03
        //---------------------------------------------------------------------------------------------------        
//        posInicial = new Posicao(0, 0);
//        objetivo = new Posicao(4, 1);
//        Bloco bloco1 = new Bloco(1, "Bloco de Teste1", 'A', Estado.RS, posInicial, objetivo);       
//        
//        posInicial = new Posicao(1, 0);       
//        objetivo = new Posicao(3, 1);       
//        Bloco bloco2 = new Bloco(2, "Bloco de Teste2", 'B', Estado.RS, posInicial, objetivo);
//        
//        posInicial = new Posicao(1,3);        
//        objetivo = new Posicao(2, 1);        
//        Bloco bloco3 = new Bloco(3, "Bloco de Teste3", 'C', Estado.RS, posInicial, objetivo);
//        
//        posInicial = new Posicao(0, 3);        
//        objetivo = new Posicao(1, 1);        
//        Bloco bloco4 = new Bloco(4, "Bloco de Teste4", 'D', Estado.RS, posInicial, objetivo);
//        
//        posInicial = new Posicao(0, 1);        
//        objetivo = new Posicao(0, 1);        
//        Bloco bloco5 = new Bloco(5, "Bloco de Teste5", 'E', Estado.RS, posInicial, objetivo);
        
        
        //adiciona configuração de blocos na lista que será passado ao processamento do Mundo dos Blocos
        List<Bloco> lstBlocos = new ArrayList<Bloco>();
        lstBlocos.add(bloco1);
        lstBlocos.add(bloco2);
        lstBlocos.add(bloco3);
        lstBlocos.add(bloco4);
        lstBlocos.add(bloco5);
               
        MundoBlocos mundoBlocos = new MundoBlocos(lstBlocos, 5, 4);   
        mundoBlocos.setEstado(Estado.RS);
        mundoBlocos.processar();
    }
}
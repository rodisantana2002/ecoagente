/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente;

import ecoagente.generic.model.Estado;
import ecoagente.mundoBlocos.model.Bloco;
import ecoagente.mundoBlocos.model.LinhaPilha;
import ecoagente.mundoBlocos.model.Mesa;
import ecoagente.mundoBlocos.model.MundoBlocos;
import java.util.LinkedList;

/**
 *
 * @author rodolfosmac
 */
public class EcoAgente {

    public static void main(String[] args) {
        Bloco bloco0 = new Bloco(0, ' ');        
        Bloco bloco1 = new Bloco(1, "Bloco de Teste1", 'A', Estado.INSATISFEITO);       
        Bloco bloco2 = new Bloco(2, "Bloco de Teste2", 'B', Estado.INSATISFEITO);
        Bloco bloco3 = new Bloco(3, "Bloco de Teste3", 'C', Estado.INSATISFEITO);
        Bloco bloco4 = new Bloco(4, "Bloco de Teste4", 'D', Estado.INSATISFEITO);
        Bloco bloco5 = new Bloco(5, "Bloco de Teste5", 'E', Estado.INSATISFEITO);

        LinhaPilha linhaPilha01 = new LinhaPilha(1, "Linha01");
        linhaPilha01.adicionarBloco(bloco1);
        linhaPilha01.adicionarBloco(bloco0);                                        
        linhaPilha01.adicionarBloco(bloco2);
        linhaPilha01.adicionarBloco(bloco3);        

        LinhaPilha linhaPilha02 = new LinhaPilha(2, "Linha02");        
        linhaPilha02.adicionarBloco(bloco4);        
        linhaPilha02.adicionarBloco(bloco0);
        linhaPilha02.adicionarBloco(bloco0);                                                
        linhaPilha02.adicionarBloco(bloco5);                
        
        LinkedList<LinhaPilha> linhas = new LinkedList<LinhaPilha>();
        linhas.addLast(linhaPilha01);
        linhas.addFirst(linhaPilha02);
        
        Mesa mesa = new Mesa(1, "Situação Atual", linhas);
        
        //situacao desejada
        LinhaPilha linhaPilhades01 = new LinhaPilha(1, "Linha01");        
        linhaPilhades01.adicionarBloco(bloco0);        
        linhaPilhades01.adicionarBloco(bloco0);
        linhaPilhades01.adicionarBloco(bloco0);                                                
        linhaPilhades01.adicionarBloco(bloco1);                

        LinhaPilha linhaPilhades02 = new LinhaPilha(1, "Linha02");        
        linhaPilhades02.adicionarBloco(bloco0);        
        linhaPilhades02.adicionarBloco(bloco0);
        linhaPilhades02.adicionarBloco(bloco0);                                                
        linhaPilhades02.adicionarBloco(bloco2);                
        
        LinhaPilha linhaPilhades03 = new LinhaPilha(1, "Linha03");        
        linhaPilhades03.adicionarBloco(bloco0);        
        linhaPilhades03.adicionarBloco(bloco0);
        linhaPilhades03.adicionarBloco(bloco0);                                                
        linhaPilhades03.adicionarBloco(bloco3);                
                
        LinhaPilha linhaPilhades04 = new LinhaPilha(1, "Linha04");        
        linhaPilhades04.adicionarBloco(bloco0);        
        linhaPilhades04.adicionarBloco(bloco0);
        linhaPilhades04.adicionarBloco(bloco0);                                                
        linhaPilhades04.adicionarBloco(bloco4);                
        
        LinhaPilha linhaPilhades05 = new LinhaPilha(1, "Linha05");        
        linhaPilhades05.adicionarBloco(bloco0);        
        linhaPilhades05.adicionarBloco(bloco0);
        linhaPilhades05.adicionarBloco(bloco0);                                                
        linhaPilhades05.adicionarBloco(bloco5);                
                
        LinkedList<LinhaPilha> linhasdes = new LinkedList<LinhaPilha>();
        linhasdes.addLast(linhaPilhades05);
        linhasdes.addLast(linhaPilhades04);
        linhasdes.addLast(linhaPilhades03);
        linhasdes.addLast(linhaPilhades02);
        linhasdes.addLast(linhaPilhades01);
        
        Mesa mesades = new Mesa(2, "Situação Desejada", linhasdes);
        
        
        MundoBlocos mundoBlocos = new MundoBlocos(mesa, mesades);
        mundoBlocos.exibirSituacao(mesades);
        
    }
}

//        new Agente("Quadrado 01").start();
//        new Agente("Quadrado 02").start();

//
//class Agente extends Thread {
//    public Agente(String str) {
//        super(str);
//    }
//    public void run() {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i + " " + getName());
//            try {
//                sleep((long)(Math.random() * 1000));
//            } catch (InterruptedException e) {}
//        }
//        System.out.println("DONE! " + getName());
//    }
//}
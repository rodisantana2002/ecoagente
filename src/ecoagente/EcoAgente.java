/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente;

import ecoagente.generic.model.Estado;
import ecoagente.mundoBlocos.model.Bloco;
import ecoagente.mundoBlocos.model.Mesa;

/**
 *
 * @author rodolfosmac
 */
public class EcoAgente {

    public static void main(String[] args) {
        Bloco bloco = new Bloco(1, "Bloco de Teste", 'A', Estado.INSATISFEITO);
        bloco.desenharTerminal();
        
        Mesa mesa = new Mesa(3);
        mesa.desenharTerminal();
        
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
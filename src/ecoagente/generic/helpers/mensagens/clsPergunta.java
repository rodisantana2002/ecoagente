/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.generic.helpers.mensagens;

import java.util.Scanner;

/**
 *
 * @author Rodolfo
 */
public class clsPergunta {
    
    public static boolean confirmar(String operacao) {
        String conf;
        Scanner sc = new Scanner(System.in);                         
        
        do {
            System.out.print(" Confirma " + operacao + " (S/N) --> ");
            conf = sc.nextLine();
        } while ((!conf.toUpperCase().equals("S")) && (!conf.toUpperCase().equals("N")));

        if (conf.toUpperCase().equals("S")){
            return true;
        }
        else{
            return false;}
    }      
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.helpers.formatacao.clsTrataDatas;
import ecoagente.generic.helpers.mensagens.clsPSR;
import ecoagente.generic.helpers.mensagens.clsPergunta;
import ecoagente.generic.model.Ambiente;
import ecoagente.mundoBlocos.control.EngineMundoBlocos;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rodolfosmac
 */
public class MundoBlocos extends Ambiente implements itfSaidaTerminal{
    private EngineMundoBlocos engineMundoBlocos;
    private List<Bloco> blocos;
    private StringBuilder logs;
    private clsTrataDatas trataDatas;
    
    private Mesa mesa;
    private int linhas, colunas;
    
    public MundoBlocos(List<Bloco> blocos, int linhas, int colunas){
        super();
        this.blocos = blocos;   
        this.linhas = linhas;
        this.colunas = colunas;
        this.logs = new StringBuilder("");
        trataDatas = new clsTrataDatas();
    }   
    
    public void iniciar(){      
        Scanner sc = new Scanner(System.in);
        
        logs.append("--> Olá Rodolfo ...vamos iniciar o jogo!\n\n");
        logs.append("--> identifiquei que vc preparou uma configuração para que eu possa analisar... \n\n");        
        logs.append("--> vou apresentar a sua configuração... \n\n");        
        
        mesa = new Mesa(01, "Situação Inicial", blocos, linhas, colunas);
        logs.append(mesa.desenharMesa() + "\n\n");
        desenharTerminal();

        //Valida        
        if(clsPergunta.confirmar("os dados")){
            if(mesa.getTokenMesa().equals(mesa.getTokenObjetivo())){
                logs.append("--> Identifiqueique os objetivos gerais foram atingidos,\n");
                logs.append("--> sendo assim o jogo terminou\n");
            }
            else{
                logs.delete(1,200);     
                logs.append("--> Aguarde enquando processo as jogadas...\n");
                desenharTerminal();
            }
        }
        else{
            clsPSR.prt("\n\n--> certo...então realize os ajuste e inicie novamento o jogo.");
        }        
    }

    @Override
    public void desenharTerminal() {
        clsPSR.prt(logs.toString());        
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.helpers.formatacao.clsTrataDatas;
import ecoagente.generic.helpers.mensagens.clsPSR;
import ecoagente.generic.model.Ambiente;
import ecoagente.generic.model.Estado;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rodolfosmac
 */
public class MundoBlocos extends Ambiente implements itfSaidaTerminal{
    private List<Bloco> blocos;
    private StringBuilder logs;
    private clsTrataDatas trataDatas;
    
    private Mesa mesa;
    private int linhas, colunas;
    private int idMovimento;
    
    public MundoBlocos(List<Bloco> blocos, int linhas, int colunas){
        super();
        this.blocos = blocos;   
        this.linhas = linhas;
        this.colunas = colunas;
        this.logs = new StringBuilder("");
        trataDatas = new clsTrataDatas();
        idMovimento = 1;
        iniciar();
    }   
    
    private void iniciar(){      
        Scanner sc = new Scanner(System.in);
        
        logs.append("** Processando Movimento - " + String.valueOf(idMovimento) + "\n");                        
        for (Bloco bloco : blocos){
            logs.append("----> Bloco: " + bloco.getAlias() + "  -  criado e adicionado na mesa."+  "\n");
        }
        
        mesa = new Mesa(01, "Situação Inicial", blocos, linhas, colunas);        
        //logs.append(mesa.desenharMesa() + "\n\n\n\n\n");
    }

    public void processarMovimentos() {      
        idMovimento++;        
        
        PilhaBlocos pilhaBlocos = mesa.getPilhaBlocos();      
        logs.append("\n\n** Processando Movimento - " + String.valueOf(idMovimento) + "\n");               
        
        for (int linha=0; linha<linhas; linha++){
            for (int coluna=0; coluna<colunas; coluna++){
                if (pilhaBlocos.getMatrixBlocos()[linha][coluna].getAlias() != ' '){                                        
                    Bloco bloco = pilhaBlocos.getMatrixBlocos()[linha][coluna];                    
                        processarBloco(bloco, pilhaBlocos);
                }
            }    
        }        
        mesa = new Mesa(idMovimento, "Movimento" + String.valueOf(idMovimento), blocos, linhas, colunas);
        //logs.append(mesa.desenharMesa() + "\n");
        desenharTerminal();                        
    }
    
    private void processarBloco(Bloco bloco, PilhaBlocos pilhaBlocos){
        if(bloco.getPosicao().getValor()!=(bloco.getObjetivo().getValor())){
            bloco.atualizarEstado(Estado.RS);
            bloco.setHistorico("bloco irá tentar satisfazer seu objetivo.");
            logs.append("----> Bloco: " + bloco.getAlias() + "  Estado: (" + bloco.getEstado() + ") - " + bloco.getEstado().getDescricao() + " |" + bloco.getHistorico() + "|\n");            

            //existe alguem impedindo o movimento
            if (bloco.getPosicao().getLinha()+1<=linhas){ //valia se passou do limite da mesa)
                if(pilhaBlocos.getMatrixBlocos()[bloco.getPosicao().getLinha()+1][bloco.getPosicao().getColuna()].getAlias() != ' '){
                    bloco.atualizarEstado(Estado.RF);                    
                    bloco.setHistorico("mas esta impedido de se movimentar.");
                    logs.append("----> Bloco: " + bloco.getAlias() + "  Estado: (" + bloco.getEstado() + ") - " + bloco.getEstado().getDescricao() + " |" + bloco.getHistorico() + "|\n");            

                    bloco.setHistorico(" atacar bloco " + pilhaBlocos.getMatrixBlocos()[bloco.getPosicao().getLinha()+1][bloco.getPosicao().getColuna()].getAlias());
                    logs.append("----> Bloco: " + bloco.getAlias() + "  Estado: (" + bloco.getEstado() + ") - " + bloco.getEstado().getDescricao() + " |" + bloco.getHistorico() + "|\n");            
                    
                    pilhaBlocos.getMatrixBlocos()[bloco.getPosicao().getLinha()+1][bloco.getPosicao().getColuna()].atualizarEstado(Estado.RF);
                }
                else{
//                    //posicao já ocupada por outro bloco
//                    if (pilhaBlocos.getMatrixBlocos()[posicao.getLinha()][posicao.getColuna()].getAlias() != ' '){
//                        //verifica se a posicaonão esta flutuando (existe algum outro bloco abaixo)
//                        if (posicao.getLinha()-1>=0){ //valia se passou do limite da mesa
//                            if(pilhaBlocos.getMatrixBlocos()[posicao.getLinha()-1][posicao.getColuna()].getAlias() != ' '){
//                                return false;                    
//                            }
//                        }
//                        else{
//                            return false;
//                        }
//                    }                
                }
            }
            
            
//            if(validarImpedimentos(bloco.getPosicao(), pilhaBlocos)){
//               bloco.atualizarEstado(Estado.RF);
//               bloco.setHistorico("bloco se movimenta para seu objetivo");
//               logs.append("----> Bloco: " + bloco.getAlias() + "  Estado: (" + bloco.getEstado() + ") - " + bloco.getEstado().getDescricao() + " |" + bloco.getHistorico() + "|\n");            
//            }   
//            else{
//                    
//            }
        }
        else{
            bloco.atualizarEstado(Estado.S);
            logs.append("----> Bloco: " + bloco.getAlias() + "  Estado: (" + bloco.getEstado() + ") - " + bloco.getEstado().getDescricao() + "\n");
        }
    }
    
    
    @Override
    public void desenharTerminal() {
        clsPSR.prt(logs.toString());        
    }       
}


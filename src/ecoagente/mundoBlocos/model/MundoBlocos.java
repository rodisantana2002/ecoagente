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
import ecoagente.generic.model.Posicao;
import java.util.List;
import java.util.Random;
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
            logs.append("   --> Bloco: " + bloco.getAlias() + "  -  criado e adicionado na mesa."+  "\n");
        }
        
        mesa = new Mesa(01, "Situação Inicial", blocos, linhas, colunas);        
        //logs.append(mesa.desenharMesa() + "\n\n\n\n\n");
    }

    public void processarMovimentos() {      
        idMovimento++;        
        
        PilhaBlocos pilhaBlocos = mesa.getPilhaBlocos();      
        logs.append("\n\n** Processando Movimento - " + String.valueOf(idMovimento) + "\n");               
        
        for(Bloco bloco: blocos){
            logs.append("   --> Lendo bloco: |" + bloco.getAlias() + "|\n");
            processarBloco(bloco, pilhaBlocos);
        }
  
        //mesa = new Mesa(idMovimento, "Movimento" + String.valueOf(idMovimento), blocos, linhas, colunas);
        //logs.append(mesa.desenharMesa() + "\n");
        desenharTerminal();                        
    }

    private Bloco getBlocoPilha(Posicao posicao, PilhaBlocos pilhaBlocos){
        Bloco bloco = new Bloco(Integer.valueOf(new Random(999).nextInt()), ' ');
        
        if(pilhaBlocos.getMatrixBlocos()[posicao.getLinha()][posicao.getColuna()].getAlias() != ' '){
            bloco = pilhaBlocos.getMatrixBlocos()[posicao.getLinha()][posicao.getColuna()];
        }                
        
        return bloco;
    }
        
    private void processarBloco(Bloco bloco, PilhaBlocos pilhaBlocos){
        //primaeira validação - identificar se o Bloco esta Satisfeito em sua posicaão inicial
        if(!bloco.getPosicao().getValor().equals(bloco.getObjetivo().getValor())){
            
            //processa tratamento para os blocos que estão com o Estado RS
            if (bloco.getEstado()==Estado.RS){
                //existe alguem impedindo o movimento
                if (bloco.getPosicao().getLinha()+1<=linhas){ //valida se passou do limite da mesa)
                    //tenta obter infornações da posição de destino                    
                    //existe alguem impedindo o bloco de buscar seu objetivo               
                    Posicao posicao = new Posicao(bloco.getPosicao().getLinha()+1, bloco.getPosicao().getColuna());
                    Bloco blocoPosicao = getBlocoPilha(posicao, pilhaBlocos);
                    
                    //existe um bloco acima impedindo a realização do objetivo
                    if(blocoPosicao.getAlias() != ' '){
                        logs.append("       --> O estado do bloco foi alterado para ");logs.append(Estado.RS); logs.append(" - "); logs.append(Estado.RS.getDescricao()); logs.append("\n");            
                        logs.append("       --> bloco irá tentar satisfazer seu objetivo.\n");
                        //----
                        logs.append("       --> bloco identificou que esta impedido de atingir seu objetivo por causa do Bloco |" + blocoPosicao.getAlias() + "|\n");
                        logs.append("       --> bloco inicia ataque contra Bloco |" + blocoPosicao.getAlias() + "|\n");
                        blocos.get(blocos.indexOf(blocoPosicao)).atualizarEstado(Estado.RF);
                        logs.append("       --> bloco " + blocoPosicao.getAlias() + " alterou seu Estado |" + Estado.RF + " - " + Estado.RF.getDescricao() + "|\n");
                    }                
                    else{
                        //valida se o objetivo pode ser alcançado (posicao destino)
                        logs.append("       --> O estado do bloco foi alterado para " +  Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                                    
                        logs.append("       --> bloco irá tentar satisfazer seu objetivo.\n");
                        
                        //tenta obter infornações da posição de destino
                        posicao = new Posicao(bloco.getObjetivo().getLinha(), bloco.getObjetivo().getColuna());
                        blocoPosicao = getBlocoPilha(posicao, pilhaBlocos);
                        
                        //o local já esta ocupado
                        if(blocoPosicao.getAlias() != ' '){
                            logs.append("       --> O estado do bloco foi alterado para " +  Estado.RS + " - " + Estado.RS.getDescricao() + "\n");            
                            logs.append("       --> bloco irá tentar satisfazer seu objetivo.\n");
                            //----
                            logs.append("       --> bloco identificou que esta impedido de atingir seu objetivo por causa do Bloco |" + blocoPosicao.getAlias() + "|\n");
                            logs.append("       --> bloco inicia ataque contra Bloco |" + blocoPosicao.getAlias() + "|\n");
                            blocos.get(blocos.indexOf(blocoPosicao)).atualizarEstado(Estado.RF);
                            logs.append("       --> bloco " + blocoPosicao.getAlias() + " alterou seu Estado |" + Estado.RF + " - " + Estado.RF.getDescricao() + "|\n");
                        }
                        else{ //o local é válido e esta livre, pode tentar processar a realização do seu objetivo                            
                        }
                    }
                }
            }
            //processa tratamento para os blocos com o estado em RF
            
            
            //processa tratamento para os blocos com o estado em F
            
        }
        else{
            bloco.atualizarEstado(Estado.S);
            logs.append("       --> O Estado do bloco foi alterado para " +  Estado.S + " - " + Estado.S.getDescricao() + "\n\n");
        }
    }
    
    @Override
    public void desenharTerminal() {
        clsPSR.prt(logs.toString());        
    }       
}



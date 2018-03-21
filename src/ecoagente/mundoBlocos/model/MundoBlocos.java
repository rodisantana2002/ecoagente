/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.core.itfEngine;
import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.helpers.formatacao.clsTrataDatas;
import ecoagente.generic.helpers.mensagens.clsPSR;
import ecoagente.generic.model.Ambiente;
import ecoagente.generic.model.Estado;
import ecoagente.generic.model.Posicao;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rodolfosmac
 */
public class MundoBlocos extends Ambiente implements itfSaidaTerminal, itfEngine{
    private final List<Bloco> blocos;
    private final StringBuilder logs;
    private final clsTrataDatas trataDatas;
    
    private Mesa mesa;
    private final int linhas;
    private final int colunas;
    
    public MundoBlocos(List<Bloco> blocos, int linhas, int colunas){
        super();
        this.blocos = blocos;   
        this.linhas = linhas;
        this.colunas = colunas;
        this.logs = new StringBuilder("");
        trataDatas = new clsTrataDatas();
        iniciar();
    }   
    
    private void iniciar(){      
        logs.append("** Iniciando Movimentos. (" + trataDatas.getDataAtual() + ")\n");                        
        for (Bloco bloco : blocos){
            logs.append("   --> Bloco: " + bloco.getAlias() + "  -  criado e adicionado na mesa."+  "\n");
            //atualiza Estado do bloco para buscando Satistação                
            blocos.get(blocos.indexOf(bloco)).atualizarEstado(Estado.RS);                                            
            logs.append("       --> o estado do bloco foi alterado para " + Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                                        
         }
        
        mesa = new Mesa(0, "Situação Inicial", blocos, linhas, colunas);        
        logs.append(mesa.desenharMesa() + "\n");
        logs.append("------------------------------------------------------------------\n\n");
    }

    @Override
    public void processar() {                              
        //percore loop em busca da solução do probelma
        for (int idMovimento=1;idMovimento<=50;idMovimento++){            
            logs.append("\n\n** Processando Movimento - " + String.valueOf(idMovimento) + "\n");               
            for(Bloco bloco: blocos){                
                logs.append("   --> Lendo bloco: |" + bloco.getAlias() + "|\n");
                processarAcaoBloco(bloco, mesa.getPilhaBlocos());
            }

            mesa = new Mesa(idMovimento, "Movimento" + String.valueOf(idMovimento), blocos, linhas, colunas);            
            logs.append(mesa.desenharMesa() + "\n");
            logs.append("------------------------------------------------------------------\n\n");            
            
            //regra de valida se o objetivo geral foi atingido 
            if(mesa.isSatisfeito()){
                //Ambiente muda seu estado para satisfeito
                setEstado(Estado.S);
                break;
            }                       
        }
        //objetivo atingindo finaliza o jogo
        logs.append("\n\n------------------------------------------------------------------\n");
        logs.append("           **  O objetivo do jogo foi atingido! **");
        logs.append("\n------------------------------------------------------------------\n");        
        
        //apenas lista o Estado final dos Blocos
        for(Bloco bloco: blocos){                
            logs.append("--> Lendo bloco: |" + bloco.getAlias() + "|\n");
            processarAcaoBloco(bloco, mesa.getPilhaBlocos());
        }
        
        desenharTerminal();                                                    
    }
       
    private void processarAcaoBloco(Bloco bloco, PilhaBlocos pilhaBlocos){

        if(!bloco.getPosicao().getValor().equals(bloco.getObjetivo().getValor())){                    
            if(blocos.get(blocos.indexOf(bloco)).getEstado() != Estado.RF){                
                logs.append("       --> bloco irá tentar satisfazer seu objetivo.\n");                
            }
            else{
                logs.append("       --> bloco encontra-se sob ataque!\n");
                logs.append("       --> bloco irá procurar um local para fugir \n");                
            }
            
            //o bloco consegue se movimentar
            if(validarMovimento(bloco.getPosicao(), pilhaBlocos)){                        
                //identificar se o Bloco esta Satisfeito em sua posicaão inicial
                if(obterSatsfacao(bloco.getObjetivo(), pilhaBlocos)){
                    logs.append("       --> bloco consegue realizar seu objetivo " + "\n");            
                    logs.append("       --> o estado do bloco foi alterado para " + Estado.F + " - " + Estado.F.getDescricao() + "\n");            
                    
                    blocos.get(blocos.indexOf(bloco)).fugir(bloco.getObjetivo());
                    blocos.get(blocos.indexOf(bloco)).atualizarEstado(Estado.S);            
                }
                else{
                    if(bloco.getEstado()==Estado.RS){
                        logs.append("       --> bloco tentou satisfazer seu objetivo, mas não teve sucesso! \n");                      
                        blocos.get(blocos.indexOf(bloco)).fugir(obterPosicaoDisponivel(pilhaBlocos));
                        logs.append("       --> o estado do bloco foi alterado para " + Estado.F + " - " + Estado.F.getDescricao() + "\n");                             
                        blocos.get(blocos.indexOf(bloco)).atualizarEstado(Estado.F);                                    
                        logs.append("       --> bloco realiza um movimento de fuga para a primeira posicao livre encontrada! \n");                                              
                        blocos.get(blocos.indexOf(bloco)).atualizarEstado(Estado.RS);                               
                        logs.append("       --> o estado do bloco foi alterado para " + Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                             
                    }
                    else{
                        logs.append("       --> bloco tentou satisfazer seu objetivo, mas não teve sucesso! Pois a gravidade não permitiu!\n");                      
                    }
                }
            }
            //efetua log e atualiza Estado do Bloco            
            else{
                logs.append("       --> bloco não consegue realizar seu objetivo. Pois existe um outro bloco impedindo seu movimento!\n"); 
                Bloco blocoImpedimento = obterBlocoImpedimento(bloco.getPosicao(), pilhaBlocos);
                logs.append("       --> bloco inicia ataque ao bloco que esta impedindo. |" + blocoImpedimento.getAlias() +"|\n");
                blocos.get(blocos.indexOf(blocoImpedimento)).atualizarEstado(Estado.RF);            
                logs.append("       --> o estado do bloco que esta o impedindo, foi alterado para " + Estado.RF + " - " + Estado.RF.getDescricao() + "\n");     
            }
        }
        else{
             blocos.get(blocos.indexOf(bloco)).atualizarEstado(Estado.S);            
             logs.append("       --> O Bloco já esta em Estado de Satisfação: " +  Estado.S + " - " + Estado.S.getDescricao() + "\n\n");
        }                    
        pilhaBlocos.popularMatrixBlocos();
    }
    
    
    private boolean validarMovimento(Posicao posicao, PilhaBlocos pilhaBlocos){
        //existe um outro bloco impedido o movimento (acima)
        if(pilhaBlocos.getMatrixBlocos()[posicao.getLinha()+1][posicao.getColuna()].getAlias() == ' '){            
            return true;
        }                                
        return false;
    }
    
    private boolean obterSatsfacao(Posicao posicao, PilhaBlocos pilhaBlocos){               
        //o objetivo esta disponivel        
        if(pilhaBlocos.getMatrixBlocos()[posicao.getLinha()][posicao.getColuna()].getAlias() == ' '){
            //o objetivo pode ser utilizado (leis da fisica)
            if (posicao.getLinha()==0){  //valida se passou do limite da mesa)                  
                return true;
            }
            else{
                if (pilhaBlocos.getMatrixBlocos()[posicao.getLinha()-1][posicao.getColuna()].getAlias() != ' '){
                    return true;
                }
                else{
                    return false;                
                }                                
            }
        }                
        
        return false;
    }
    
    //identificar qual é o bloco que esta impedindo o movimento
    private Bloco obterBlocoImpedimento(Posicao posicao, PilhaBlocos pilhaBlocos){
        Bloco bloco = new Bloco(new Random(999).nextInt(), ' ');
        
        if(pilhaBlocos.getMatrixBlocos()[posicao.getLinha()+1][posicao.getColuna()].getAlias() != ' '){
            bloco = pilhaBlocos.getMatrixBlocos()[posicao.getLinha()+1][posicao.getColuna()];
        }                
        
        return bloco;
    }        
    
    //percorre a pilha de blocos em busca do primeiro local disponivel para realizar o movimento
    private Posicao obterPosicaoDisponivel(PilhaBlocos pilhaBlocos) {        
        for (int linha=0; linha<linhas; linha++){
            for (int coluna=0; coluna<colunas; coluna++){
                if (pilhaBlocos.getMatrixBlocos()[linha][coluna].getAlias() == ' ') {
                    if (linha==0){
                        return new Posicao(linha, coluna);
                    }
                    else{
                        if(pilhaBlocos.getMatrixBlocos()[linha-1][coluna].getAlias() != ' '){
                            return new Posicao(linha, coluna);
                        }
                        else{
                            break;
                        }
                    }
                }    
                else {
                   break; 
                }
            }
        }          
        return null;
    }
    
    @Override
    public void desenharTerminal() {
        clsPSR.prt(logs.toString());        
    }       
}



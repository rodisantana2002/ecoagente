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
        
        logs.append("** Iniciando Movimentos. \n");                        
        for (Bloco bloco : blocos){
            logs.append("   --> Bloco: " + bloco.getAlias() + "  -  criado e adicionado na mesa."+  "\n");
            //atualiza Estado do bloco para buscando Satistação                
            blocos.get(blocos.indexOf(bloco)).atualizarEstado(Estado.RS);                                            
            logs.append("       --> o estado do bloco foi alterado para " + Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                                        
         }
        
        mesa = new Mesa(01, "Situação Inicial", blocos, linhas, colunas);        
        logs.append(mesa.desenharMesa() + "\n");
        logs.append("--------------------------------------------------------------\n\n");
    }

    public void processarMovimentos() {                              
        //percore loop em busca da solução do probelma
        for (int i=1;i<50;i++){            
            logs.append("\n\n** Processando Movimento - " + String.valueOf(i) + "\n");               
            for(Bloco bloco: blocos){                
                logs.append("   --> Lendo bloco: |" + bloco.getAlias() + "|\n");
                processarBloco(bloco, mesa.getPilhaBlocos());
            }

            mesa = new Mesa(idMovimento, "Movimento" + String.valueOf(i), blocos, linhas, colunas);            
            logs.append(mesa.desenharMesa() + "\n");
            logs.append("--------------------------------------------------------------\n\n");            
            
            if(mesa.getTokenMesa().equals(mesa.getTokenObjetivo())){
                break;
            }                       
        }
        logs.append("\n\n--------------------------------\n");
        logs.append("O objetivo do jogo foi atingido!");
        desenharTerminal();                                                    
    }
       
    private void processarBloco(Bloco bloco, PilhaBlocos pilhaBlocos){

        if(!bloco.getPosicao().getValor().equals(bloco.getObjetivo().getValor())){                    
            if(blocos.get(blocos.indexOf(bloco)).getEstado() != Estado.RF){                
                logs.append("       --> bloco irá tentar satisfazer seu objetivo.\n");                
            }
            else{
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
                    logs.append("       --> o estado do bloco foi alterado para " + Estado.RF + " - " + Estado.RF.getDescricao() + "\n");                                                    
                }
            }
            //efetua log e atualiza Estado do Bloco            
            else{
                logs.append("       --> bloco não consegue realizar seu objetivo.\n"); 
                Bloco blocoImpedimento = getBlocoImpedimento(bloco.getPosicao(), pilhaBlocos);
                logs.append("       --> bloco irá atacar o bloco. |" + blocoImpedimento.getAlias() +"|\n");
                blocos.get(blocos.indexOf(blocoImpedimento)).atualizarEstado(Estado.RF);            
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
    
    private Bloco getBlocoImpedimento(Posicao posicao, PilhaBlocos pilhaBlocos){
        Bloco bloco = new Bloco(new Random(999).nextInt(), ' ');
        
        if(pilhaBlocos.getMatrixBlocos()[posicao.getLinha()+1][posicao.getColuna()].getAlias() != ' '){
            bloco = pilhaBlocos.getMatrixBlocos()[posicao.getLinha()+1][posicao.getColuna()];
        }                
        
        return bloco;
    }
        
//    
//    private Posicao getLocalFuga(Posicao posicao, PilhaBlocos pilhaBlocos){
//        //percorre a lista de bloco em busca de um local disponivel       
//        for (int linha=0; linha<linhas; linha++){
//            for (int coluna=0; coluna<colunas; coluna++){
//                //busca um espaço em branco
//                if (pilhaBlocos.getMatrixBlocos()[linha][coluna].getAlias() == ' '){
//                    //valida se esta na base da mesa
//                    if (linha==0){
//                        return new Posicao(linha, coluna);                                            
//                    }
//                    //encontrou outro bloco
//                    else{
//                        if (pilhaBlocos.getMatrixBlocos()[linha-1][coluna].getAlias() != ' '){
//                            if(posicao.getLinha()!=linha-1 && posicao.getColuna()!=coluna) {
//                                return new Posicao(linha, coluna);                                                                            
//                            }
//                        }
//                    }
//                }
//                //busca outro bloco para ficar em cima
//                else{
//                    //verifica se pode ir para cima do bloco - esta livre
//                    if (pilhaBlocos.getMatrixBlocos()[linha+1][coluna].getAlias() == ' '){
//                        //não pode fugur para a sua posicao ou para cida dele mesmo
//                        if (posicao.getLinha()!=linha && posicao.getColuna()!=coluna){
//                            return new Posicao(linha+1, coluna);                                                    
//                        }
//                    }
//                }
//            }
//        }         
//        return null;
//    }
    
//    private void processarBloco(Bloco bloco, PilhaBlocos pilhaBlocos){
//        //primaeira validação - identificar se o Bloco esta Satisfeito em sua posicaão inicial
//        if(!bloco.getPosicao().getValor().equals(bloco.getObjetivo().getValor())){
//            
//            if (bloco.getEstado()==Estado.F){
//                //procura primeiro espaco vazio na mesa
//                bloco.fugir(getLocalFuga(bloco.getPosicao(), pilhaBlocos));
//                logs.append("       --> bloco consegui realizar a fuga para um lugar disponível. (" + bloco.getPosicao().getValor() +")\n");                                                            
//                logs.append("       --> O estado do bloco foi alterado para " +  Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                                                                                                    
//            }
//            
//            //processa tratamento para os blocos que estão com o Estado RS            
//            if (bloco.getEstado()==Estado.RS){
//                //existe alguem impedindo o movimento de satisfaçao
//                if (bloco.getPosicao().getLinha()+1<=linhas){ //valida se passou do limite da mesa)
//                    //tenta obter infornações da posição de destino                    
//                    //existe alguem impedindo o bloco de buscar seu objetivo               
//                    Posicao posicao = new Posicao(bloco.getPosicao().getLinha() + 1, bloco.getPosicao().getColuna());
//                    Bloco blocoPosicao = getBlocoPilha(posicao, pilhaBlocos);
//                    
//                    //existe um bloco acima impedindo a realização do objetivo
//                    if(blocoPosicao.getAlias() != ' '){
//                        logs.append("       --> O estado do bloco foi alterado para " + Estado.RS + " - " + Estado.RS.getDescricao() + "\n");            
//                        logs.append("       --> bloco irá tentar satisfazer seu objetivo.\n");
//                        //----
//                        logs.append("       --> bloco identificou que esta impedido de atingir seu objetivo por causa do Bloco |" + blocoPosicao.getAlias() + "|\n");
//                        logs.append("       --> bloco inicia ataque contra Bloco |" + blocoPosicao.getAlias() + "|\n");
//                        blocos.get(blocos.indexOf(blocoPosicao)).atualizarEstado(Estado.RF);
//                        logs.append("       --> bloco " + blocoPosicao.getAlias() + " alterou seu Estado |" + Estado.RF + " - " + Estado.RF.getDescricao() + "|\n");
//                    }                
//                    else{
//                        //valida se o objetivo pode ser alcançado (posicao destino)
//                        logs.append("       --> O estado do bloco foi alterado para " +  Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                                    
//                        logs.append("       --> bloco irá tentar satisfazer seu objetivo.\n");
//                        
//                        //tenta obter infornações da posição de destino
//                        posicao = new Posicao(bloco.getObjetivo().getLinha(), bloco.getObjetivo().getColuna());
//                        blocoPosicao = getBlocoPilha(posicao, pilhaBlocos);
//                        
//                        //o local já esta ocupado
//                        if(blocoPosicao.getAlias() != ' '){
//                            logs.append("       --> bloco identificou que esta impedido de atingir seu objetivo por causa do Bloco |" + blocoPosicao.getAlias() + "|\n");
//                            logs.append("       --> bloco inicia ataque contra Bloco |" + blocoPosicao.getAlias() + "|\n");
//                            blocos.get(blocos.indexOf(blocoPosicao)).atualizarEstado(Estado.RF);
//                            logs.append("       --> bloco " + blocoPosicao.getAlias() + " alterou seu Estado |" + Estado.RF + " - " + Estado.RF.getDescricao() + "|\n");
//                        }
//                        else{ //o local é válido e esta livre, pode tentar processar a realização do seu objetivo          
//                            //local é valdo (respeita as regras da fisica - não pode flutuar)
//                            if (bloco.getObjetivo().getLinha()-1>=0){ //valida se passou do limite da mesa)                                
//                                //tenta obter infornações do bloco abaixo 
//                                //posicao = new Posicao(bloco.getObjetivo().getLinha(), bloco.getObjetivo().getColuna());
//                                //blocoPosicao = getBlocoPilha(posicao, pilhaBlocos);
//
//                                //o bloco abaixo existe                                    
//                                //if(blocoPosicao.getAlias() != ' '){
//                                    logs.append("       --> bloco consegue realizar seu objetivo.\n");                                        
//                                    bloco.atualizarEstado(Estado.F);
//                                    logs.append("       --> bloco inicia processo de fuga para seu objetivo.\n");                                        
//                                    logs.append("       --> O Estado do bloco foi alterado para " +  Estado.F + " - " + Estado.F.getDescricao() + "\n");
//                                    bloco.atualizarEstado(Estado.S);
//                                    logs.append("       --> bloco atinge seu objetivo.\n");                                                                                                                
//                                    logs.append("       --> O Estado do bloco foi alterado para " +  Estado.S + " - " + Estado.S.getDescricao() + "\n");                                    
//                                    bloco.fugir(posicao);
//                                    //pilhaBlocos.popularMatrixBlocos();
//                                //}
//                                //else{
//                                //    logs.append("       --> bloco esta impedido de satisfazer seu objetivo pois ficará flutuando." + "\n");            
//                                //    logs.append("       --> bloco irá tentar fugur para outro local.\n");     
//                                //    //procura primeiro espaco vazio na mesa
//                                //    bloco.fugir(getLocalFuga(bloco.getPosicao(), pilhaBlocos));
//                                //    //pilhaBlocos.popularMatrixBlocos();
//                                //    logs.append("       --> bloco consegui realizar a fuga para um lugar disponível. (" + bloco.getPosicao().getValor() +")\n");                            
//                                //    logs.append("       --> O estado do bloco foi alterado para " +  Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                                                                                                                                        
//                                //}                                
//                            }    
//                            else{
//                            //    logs.append("       --> bloco esta impedido de satisfazer seu objetivo pois ficará flutuando." + "\n");            
//                            //    logs.append("       --> bloco irá tentar fugur para outro local.\n");     
//                            //    //procura primeiro espaco vazio na mesa
//                            //    bloco.fugir(getLocalFuga(bloco.getPosicao(), pilhaBlocos));
//                                //pilhaBlocos.popularMatrixBlocos();
//                            //    logs.append("       --> bloco consegui realizar a fuga para um lugar disponível. (" + bloco.getPosicao().getValor() +")\n");                            
//                            //    logs.append("       --> O estado do bloco foi alterado para " +  Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                                                                                                                                        
//                            }
//                        }
//                    }
//                }
//            }
//            //processa tratamento para os blocos com o estado em RF
//            //Bloco em fuga tentara encontrar o primeiro local disponivel para fuga, caso consiga.
//            //1° satisfazendo seu objetivo
//            //2° lugar vago na mesa
//            //3º sobre outro bloco            
//            if (bloco.getEstado()==Estado.RF){
//                //existe alguem impedindo o movimento de fuga
//                if (bloco.getPosicao().getLinha()+1<=linhas){ //valida se passou do limite da mesa)
//                    //tenta obter infornações da posição de destino                    
//                    //existe alguem impedindo o bloco de realizar a fuga
//                    Posicao posicao = new Posicao(bloco.getPosicao().getLinha()+1, bloco.getPosicao().getColuna());
//                    Bloco blocoPosicao = getBlocoPilha(posicao, pilhaBlocos);
//                    
//                    //existe um bloco acima impedindo a realização da fuga
//                    if(blocoPosicao.getAlias() != ' '){
//                        logs.append("       --> bloco irá tentar realizar a fuga.\n");
//                        //----
//                        logs.append("       --> bloco identificou que esta impedido de fugur por causa do Bloco |" + blocoPosicao.getAlias() + "|\n");
//                        logs.append("       --> bloco inicia ataque contra Bloco |" + blocoPosicao.getAlias() + "|\n");
//                        blocos.get(blocos.indexOf(blocoPosicao)).atualizarEstado(Estado.RF);
//                        logs.append("       --> bloco " + blocoPosicao.getAlias() + " alterou seu Estado |" + Estado.RF + " - " + Estado.RF.getDescricao() + "|\n");
//                    }                
//                    else{
//                        //procura primeiro espaco vazio na mesa
//                        logs.append("       --> bloco irá realizar a fuga para um lugar disponível.\n");                            
//                        logs.append("       --> O estado do bloco foi alterado para " +  Estado.F + " - " + Estado.F.getDescricao() + "\n");                                                                    
//                        bloco.atualizarEstado(Estado.F);
//                        
//                        //procura primeiro espaco vazio na mesa
//                        bloco.fugir(getLocalFuga(bloco.getPosicao(), pilhaBlocos));
//                        logs.append("       --> bloco consegui realizar a fuga para um lugar disponível. (" + bloco.getPosicao().getValor() +")\n");                                                            
//                        logs.append("       --> O estado do bloco foi alterado para " +  Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                                                                                                    
//                        
////                        //valida se o objetivo pode ser alcançado (posicao destino), juntamente com a fuga
////                        logs.append("       --> O estado do bloco foi alterado para " +  Estado.F + " - " + Estado.F.getDescricao() + "\n");                                    
////                        logs.append("       --> bloco irá tentar satisfazer seu objetivo, juntamente com a fuga.\n");
////                        
////                        //tenta obter infornações da posição de destino
////                        posicao = new Posicao(bloco.getObjetivo().getLinha(), bloco.getObjetivo().getColuna());
////                        blocoPosicao = getBlocoPilha(posicao, pilhaBlocos);
////                        
////                        //o local já esta ocupado
////                        if(blocoPosicao.getAlias() != ' '){
////                            logs.append("       --> bloco identificou que esta impedido de atingir seu objetivo por causa do Bloco |" + blocoPosicao.getAlias() + "|\n");
////                            logs.append("       --> bloco irá tentar fugur para outro local.\n");                            
////                            
////                            //procura primeiro espaco vazio na mesa
////                            bloco.fugir(getLocalFuga(bloco.getPosicao(), pilhaBlocos));
////                            logs.append("       --> bloco consegui realizar a fuga para um lugar disponível.\n");                            
////                            logs.append("       --> O estado do bloco foi alterado para " +  Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                                                                    
////                        }
////                        else{ //o local é válido e esta livre, pode tentar processar a realização do seu objetivo          
////                            //local é valdo (respeita as regras da fisica - não pode flutuar)
////                            if (bloco.getObjetivo().getLinha()-1>=0){ //valida se passou do limite da mesa)                                
////                                //tenta obter infornações do bloco abaixo 
////                                posicao = new Posicao(bloco.getObjetivo().getLinha()-1, bloco.getObjetivo().getColuna());
////                                blocoPosicao = getBlocoPilha(posicao, pilhaBlocos);
////
////                                //o bloco abaixo existe                                    
////                                if(blocoPosicao.getAlias() != ' '){
////                                    logs.append("       --> O estado do bloco foi alterado para " +  Estado.RS + " - " + Estado.RS.getDescricao() + "\n");            
////                                    logs.append("       --> bloco irá tentar satisfazer seu objetivo.\n");                                        
////                                    logs.append("       --> bloco consegue realizar seu objetivo.\n");                                        
////                                    bloco.atualizarEstado(Estado.F);
////                                    logs.append("       --> bloco inicia processo de fuga para seu objetivo.\n");                                        
////                                    logs.append("       --> O Estado do bloco foi alterado para " +  Estado.F + " - " + Estado.F.getDescricao() + "\n\n");
////                                    bloco.atualizarEstado(Estado.S);
////                                    logs.append("       --> bloco atinge seu objetivo.\n");                                                                                                                
////                                    logs.append("       --> O Estado do bloco foi alterado para " +  Estado.S + " - " + Estado.S.getDescricao() + "\n\n"); 
////                                }
////                                else{
////                                    logs.append("       --> bloco esta impedido de satisfazer seu objetivo pois ficará flutuando." + "\n");            
////                                    logs.append("       --> bloco irá tentar fugur para outro local.\n");     
////                                    //procura primeiro espaco vazio na mesa
////                                    logs.append("       --> bloco consegui realizar a fuga para um lugar disponível. (" + bloco.getPosicao().getValor() +")\n");                            
////                                    logs.append("       --> O estado do bloco foi alterado para " +  Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                                                                                                                                        
////                                }
////                            }    
////                            else{
////                                logs.append("       --> bloco esta impedido de satisfazer seu objetivo pois ficará flutuando." + "\n");            
////                                logs.append("       --> bloco irá tentar fugur para outro local.\n");     
////                                //procura primeiro espaco vazio na mesa
////                                logs.append("       --> bloco consegui realizar a fuga para um lugar disponível. (" + bloco.getPosicao().getValor() +")\n");                                                            
////                                logs.append("       --> O estado do bloco foi alterado para " +  Estado.RS + " - " + Estado.RS.getDescricao() + "\n");                                                                                                    
////                            }
////                        }
//                    }
//                }                
//               
//            }
//            
//            
//            //processa tratamento para os blocos com o estado em F
//                        
//            pilhaBlocos.popularMatrixBlocos();
//        }
//        else{
//            bloco.atualizarEstado(Estado.S);
//            logs.append("       --> O Bloco já esta em Estado de Satisfação: " +  Estado.S + " - " + Estado.S.getDescricao() + "\n\n");
//        }
//    }
    
    @Override
    public void desenharTerminal() {
        clsPSR.prt(logs.toString());        
    }       
}



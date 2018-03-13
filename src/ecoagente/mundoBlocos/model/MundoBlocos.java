/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.model.Agente;
import ecoagente.generic.model.Ambiente;
import ecoagente.generic.model.Estado;
import java.util.List;

/**
 *
 * @author rodolfosmac
 */
public class MundoBlocos extends Ambiente{
    private itfSaidaTerminal prtSaida;    
    private List<Bloco> blocos;
    
    public MundoBlocos(Agente SituacaoInicial){
        super();
        this.blocos = blocos;
        setEstado(Estado.RS);
        setSituacaoAtual(SituacaoInicial);
    }   
    

    @Override
    public void exibirSituacao(Agente situacao) {      
        prtSaida = (itfSaidaTerminal) situacao;
        prtSaida.desenharTerminal();
    }
}



//    private Mesa gerarMesainicial(List<Bloco> listaBlocos){
//        Mesa mesa;         
//        int maxLinha, MaxCol;
//                       
//        LinkedList<LinhaPilha> lstLinhasMesa = new LinkedList<LinhaPilha>();        
//        
//        for (int linha=1; linha<=5; linha++){            
//            LinhaPilha linhaPilha = new LinhaPilha(linha, "Linha" + String.valueOf(linha));                                                         
//            
//            for (Bloco bloco: listaBlocos){                            
//                if (linha==bloco.getPosicao().getLinha()){
//                    for (int coluna=0; coluna<=5; coluna++){
//
//                        if((coluna == bloco.getPosicao().getColuna())){
//                            linhaPilha.adicionarBloco(bloco);
//                        }
//                        else{
//                            Posicao posicao = new Posicao(linha, coluna);
//                            Bloco blocoVazio = new Bloco(Integer.valueOf(String.valueOf(linha)+String.valueOf(coluna)), ' ', posicao);
//                            linhaPilha.adicionarBloco(blocoVazio);
//                        }
//                    }    
//                }
//            }            
//            lstLinhasMesa.addLast(linhaPilha);                                                        
//        }
//        
//        mesa = new Mesa(2, "Situação Desejada", lstLinhasMesa);
//        mesa.desenharTerminal();        
//        return mesa;
//    }

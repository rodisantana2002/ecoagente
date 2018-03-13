/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.helpers.mensagens.clsPSR;
import ecoagente.generic.model.Agente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodolfosmac
 */
public class LinhaPilha extends Agente implements itfSaidaTerminal{
    List<Bloco> blocosLinha;
    
    public LinhaPilha(int id, String descricao){
        super();
        setId(id);
        setDescricao(descricao);
        blocosLinha = new ArrayList();
    }
    
    public int getCount(){
        return blocosLinha.size();
    }
    
    public boolean adicionarBloco(Bloco bloco){
        return blocosLinha.add(bloco);
    }
    
    public boolean removerBloco(Bloco bloco){
        return blocosLinha.remove(bloco);
    }
    
    @Override
    public void desenharTerminal() {
        clsPSR.prt(desenharLinhaPila());
    }    

    public String desenharLinhaPila() {
        StringBuilder strLinha = new StringBuilder("");
        for (Bloco bloco : blocosLinha){
            if(bloco.getAlias() != ' '){
                strLinha.append("  +---+ " );                
            }
            else{
                strLinha.append("        " );                                
            }
        }
        strLinha.append("\n");
        
        for (Bloco bloco : blocosLinha){
            if(bloco.getAlias() != ' '){            
                strLinha.append("  | " + bloco.getAlias() + " | ");
            }
            else{
                strLinha.append("        " );                                                
            }
        }        
        strLinha.append("\n");
        
        for (Bloco bloco : blocosLinha){
            if(bloco.getAlias() != ' '){            
                strLinha.append("  +---+ " );
            }
            else{
                strLinha.append("        " );                                                
            }
        }
        
        return strLinha.toString();
    }
}

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
public class Mesa extends Agente implements itfSaidaTerminal{
    private int numEspacos;

    public Mesa(int numEspacos){
        super();
        this.numEspacos = numEspacos;        
    }
    /**
     * @return the numEspacos
     */
    public int getNumEspacos() {
        return numEspacos;
    }

    /**
     * @param numEspacos the numEspacos to set
     */
    public void setNumEspacos(int numEspacos) {
        this.numEspacos = numEspacos;
    }
    
    @Override
    public void desenharTerminal() {
        clsPSR.prt(desenharMesa());
    }    
    
    private String desenharMesa(){
        StringBuilder strMesa = new StringBuilder("");
        String strBaseBloco = "-------|";        

        //desenha base
        strMesa.append("|");
        for (int i=0; i<numEspacos; i++){
            strMesa.append(strBaseBloco);
        }        

        strMesa.append("\n|" );
        for (int i=1; i<=(numEspacos); i++){
            String strDecimal = "";
            if (i<=9){
                strDecimal = "0";
            }
            if (i<numEspacos){                
                strMesa.append("  E" + strDecimal + String.valueOf(i) + "   ");                                            
            }
            else{
                strMesa.append("  E" + strDecimal + String.valueOf(i) + "  |");                            
            }            
        }
        
        //desenha os pes
        for (int i=0; i<=3; i++){
            strMesa.append("\n|" + repeat(" ", ((numEspacos * 8) - 1)) + "|");
        }                       
        
        strMesa.append("\n\n" + "** Desenvolvido by aluno Rodolfo Santana ");
        
        return strMesa.toString();
    }
 
    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }    
}

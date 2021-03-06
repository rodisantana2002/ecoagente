/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.helpers.mensagens.clsPSR;
import ecoagente.generic.model.Agente;
import ecoagente.generic.model.Estado;
import java.util.List;

/**
 *
 * @author rodolfosmac
 */
public class Mesa extends Agente implements itfSaidaTerminal{
    private final int numColunas;
    private final List<Bloco> blocos;
    private PilhaBlocos pilhaBlocos;
    private String tokenMesa, tokenObjetivo;

    public Mesa(int id, String descricao, List blocos, int linhas, int colunas){
        super();
        setId(id);
        setDescricao(descricao);
        this.numColunas = colunas;
        this.blocos = blocos;
        setEstado(Estado.RS);
        popularPilhaBlocos(linhas, colunas);
    }

    public PilhaBlocos getPilhaBlocos() {
        return pilhaBlocos;
    }

    public void setPilhaBlocos(PilhaBlocos pilhaBlocos) {
        this.pilhaBlocos = pilhaBlocos;
    }

    public String getTokenMesa() {
        return tokenMesa;
    }

    public String getTokenObjetivo() {
        return tokenObjetivo;
    }   
        
    public boolean validarToken(){
        if (this.tokenMesa.equals(this.tokenObjetivo)){
            return true;
        }
        return false;
    }
    
    //distribui os blocos nas pilhas da mesa
    private void popularPilhaBlocos(int linhas, int colunas){        
        setPilhaBlocos(new PilhaBlocos(getId(), "Pilha --> " + getDescricao(), linhas, colunas, blocos));
        StringBuilder strToken = new StringBuilder("");
        StringBuilder strTokenObjetivo = new StringBuilder("");

        for (Bloco bloco : blocos){
            strToken.append(String.valueOf(bloco.getPosicao().getLinha()) +  
                            String.valueOf(bloco.getPosicao().getColuna()) + "|");          
            
            strTokenObjetivo.append(String.valueOf(bloco.getObjetivo().getLinha()) +  
                                    String.valueOf(bloco.getObjetivo().getColuna()) + "|");           
        }
        
        this.tokenMesa = strToken.toString();
        this.tokenObjetivo = strTokenObjetivo.toString();
    }
    
    @Override
    public void desenharTerminal() {
        clsPSR.prt(desenharMesa());
    }    
    
    public String desenharMesa(){
        ///desenha o titulo da mesa
        StringBuilder strMesa = new StringBuilder("");
        
        //desnha as linhas com os blocos
        strMesa.append(desenharLinhas());
        
        //desenha base
        String strBaseBloco = "-------|";                
        strMesa.append("|");
        for (int i=0; i<numColunas; i++){
            strMesa.append(strBaseBloco);
        }        

        strMesa.append("\n|" );
        for (int i=1; i<=(numColunas); i++){
            String strDecimal = "";
            if (i<=9){
                strDecimal = "0";
            }
            if (i<numColunas){                
                strMesa.append("  E" + strDecimal + String.valueOf(i) + "   ");                                            
            }
            else{
                strMesa.append("  E" + strDecimal + String.valueOf(i) + "  |");                            
            }            
        }
        
        //desenha os pes da mesa
        for (int i=0; i<=3; i++){
            strMesa.append("\n|" + repeat(" ", ((numColunas * 8) - 1)) + "|");
        }                               
        strMesa.append("\n* Mesa: " + String.valueOf(getId()) + " - " + getDescricao());
        strMesa.append("\n------------------------------------------------------------------");                    
        strMesa.append("\n* ...Token Atual...: " + getTokenMesa());
        strMesa.append("\n* ...Token Objetivo: " + getTokenObjetivo());
        
        return strMesa.toString();
    }
 
    private String desenharLinhas(){
        StringBuilder strLinha = new StringBuilder("");        
        strLinha.append(getPilhaBlocos().desenharPilhaBlocos());
        
        return strLinha.toString();
    }
    
    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }    
}
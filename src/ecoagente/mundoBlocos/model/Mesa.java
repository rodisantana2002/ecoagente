/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.helpers.mensagens.clsPSR;
import ecoagente.generic.model.Agente;
import ecoagente.generic.model.Posicao;
import java.util.List;

/**
 *
 * @author rodolfosmac
 */
public class Mesa extends Agente implements itfSaidaTerminal{
    private int numEspacos;
    private String token=""; 
    private List<LinhaPilha> linhasPilha;

    public Mesa(int id, String descricao, List linhasPilhas){
        super();
        setId(id);
        setDescricao(descricao);
        this.linhasPilha = linhasPilhas;
        this.numEspacos = this.linhasPilha.get(0).getCount();        
        popularPosicaoBloco();
    }

    public String getToken(){
        return this.token;
    }
    
    private void popularPosicaoBloco(){        
        StringBuilder strToken = new StringBuilder("");
        
        for (LinhaPilha linhaPilha : linhasPilha){
            for (int col = 0; col<linhaPilha.getCount(); col++){
                Posicao posicao = new Posicao(linhaPilha.getId(), col);
                clsPSR.prt(linhaPilha.getBlocosLinha().get(col).getAlias()+ " - lInha:" + linhaPilha.getId() + " Coluna:" + String.valueOf(col));                                
                linhaPilha.getBlocosLinha().get(col).setPosicao(posicao);
                
                strToken.append(linhaPilha.getId()+ String.valueOf(col) + "|");
            }            
        }    
        this.token = strToken.toString();
    }
    
    @Override
    public void desenharTerminal() {
        clsPSR.prt(desenharMesa());
    }    
    
    private String desenharMesa(){
        ///desenha o titulo da mesa
        StringBuilder strMesa = new StringBuilder("* Mesa: " + String.valueOf(getId()) + " - " + getDescricao() + "\n\n");
        
        //desnha as linhas com os blocos
        strMesa.append(desenharLinhas());
        
        //desenha base
        String strBaseBloco = "-------|";                
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
 
    private String desenharLinhas(){
        StringBuilder strLinha = new StringBuilder("");        
        
        for (LinhaPilha linhaPilha : linhasPilha){
            strLinha.append(linhaPilha.desenharLinhaPila() + "\n");
        }
        
        return strLinha.toString();
    }
    
    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }    
}

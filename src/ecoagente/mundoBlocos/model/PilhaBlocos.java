/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.model;

import ecoagente.generic.core.itfSaidaTerminal;
import ecoagente.generic.helpers.mensagens.clsPSR;
import ecoagente.generic.model.Agente;
import java.util.List;

/**
 *
 * @author rodolfosantana
 */
public class PilhaBlocos extends Agente implements itfSaidaTerminal{
    private int linhas;
    private int colunas;
    private Bloco[][] matrixBlocos;
    private List<Bloco> blocos; 

    public PilhaBlocos(int id, String descricao, int linhas, int colunas, List<Bloco> blocos){        
        super();
        setId(id);
        setDescricao(descricao);        
        this.linhas = linhas;
        this.colunas = colunas;
        this.blocos = blocos;
        
        popularMatrixBlocos();
    }

    public Bloco[][] getMatrixBlocos() {
        return matrixBlocos;
    }

    public void setMatrixBlocos(Bloco[][] matrixBlocos) {
        this.matrixBlocos = matrixBlocos;
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }
        
    @Override
    public void desenharTerminal() {
        clsPSR.prt(desenharPilhaBlocos());
    }

    public String desenharPilhaBlocos(){
        StringBuilder strPilha = new StringBuilder("");

        int linha = linhas-1;
        while (linha>=0){
            for (int coluna=0; coluna<colunas; coluna++){
                if(getMatrixBlocos()[linha][coluna].getAlias() != ' '){
                    strPilha.append("  +---+ " );                
                  }
                else{
                    strPilha.append("        " );                                
                }                
            }
            strPilha.append("\n");
            
            for (int coluna=0; coluna<colunas; coluna++){
                if(getMatrixBlocos()[linha][coluna].getAlias() != ' '){
                    strPilha.append("  | " + getMatrixBlocos()[linha][coluna].getAlias() + " | ");
                }
                else{
                    strPilha.append("        " );                                                
                }                
            }            
            strPilha.append("\n");
            
            for (int coluna=0; coluna<colunas; coluna++){
                if(getMatrixBlocos()[linha][coluna].getAlias() != ' '){
                    strPilha.append("  +---+ " );                
                }
                else{
                    strPilha.append("        " );                                
                }                
            }
            strPilha.append("\n");            
            linha --;
        }            
        return strPilha.toString();        
    }
    
    private void popularMatrixBlocos() {
        setMatrixBlocos(new Bloco[linhas][colunas]);
        
        for (Bloco bloco : blocos){
            getMatrixBlocos()[bloco.getPosicao().getLinha()][bloco.getPosicao().getColuna()] = bloco;
        }
        
        for (int linha=0; linha<linhas; linha++){
            for (int coluna=0; coluna<colunas; coluna++){
                if (getMatrixBlocos()[linha][coluna]==null){
                    getMatrixBlocos()[linha][coluna] = new Bloco(Integer.valueOf(String.valueOf("11"+coluna)), ' ');
                }
            }
        }                
    }
}

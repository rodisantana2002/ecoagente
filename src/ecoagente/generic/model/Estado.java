/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.generic.model;

/**
 *
 * @author rodolfosmac
 */
public enum Estado {
    S("Satisfeito"),
    RS("Buscando Satisfação"),    
    RF("Buscando Local p/ Fuga"),
    F("Fugindo");

    private String descricao;
 
    Estado(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
}    

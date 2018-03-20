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
public abstract class Agente {
    private int id;
    private String descricao;
    private char alias;
    private Estado estado;   
       

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public char getAlias() {
        return alias;
    }
    public void setAlias(char alias) {
        this.alias = alias;
    }
}

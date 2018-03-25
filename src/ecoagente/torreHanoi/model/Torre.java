/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.torreHanoi.model;

import ecoagente.generic.core.itfSaidaTerminal;
import java.util.Stack;

/**
 *
 * @author rodolfosmac
 */
public class Torre extends Stack<Disco> implements itfSaidaTerminal {
    private int index;

    public Torre(int index) {
        super();
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    public int getId() {
        return index+1;
    }
    public String getNome(){
        return "Torre"+getId();
    }
    @Override
    public void desenharTerminal() {}
    
}

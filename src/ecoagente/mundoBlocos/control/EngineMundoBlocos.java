/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.control;

import ecoagente.generic.core.itfEngine;
import ecoagente.generic.model.Estado;

/**
 *
 * @author rodolfosmac
 */
public class EngineMundoBlocos implements itfEngine{

    public EngineMundoBlocos(){ }
    
    @Override
    public Estado processar() {
        return Estado.RF;
    }   
}

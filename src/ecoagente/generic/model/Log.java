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
public class Log {
    private int id;
    private String dataHora;
    private String log;
    
    public Log(int id, String log, String dataHora){
        setId(id);
        setLog(log);
        setDataHora(dataHora);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}

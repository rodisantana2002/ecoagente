/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoagente.mundoBlocos.view;

import ecoagente.generic.model.Estado;
import ecoagente.generic.model.Posicao;
import ecoagente.mundoBlocos.control.MundoBlocos;
import ecoagente.mundoBlocos.model.Bloco;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rodolfosmac
 */
public class viewPrincipal extends javax.swing.JFrame {
    private List<Bloco> lstBlocos;
    private StringBuilder strBlocos, strLogs;

    /**
     * Creates new form viewPrincipal
     */
    public viewPrincipal() {
        initComponents();
        iniciarConfiguracoes();
    }
            
    private void gerarCabecalhoLista(){        
        strBlocos.append(" ----------------------------------------------\n");
        strBlocos.append("  Alias     Descricao      Posicao   Objetivo \n");
        strBlocos.append(" ----------------------------------------------\n");        
        txtBlocos.setText(strBlocos.toString());                
    }
    
    private void iniciarConfiguracoes(){
        strBlocos = new StringBuilder("");
        strLogs = new StringBuilder("");        
        lstBlocos = new ArrayList<Bloco>();
        
        limparDadosBloco();
        txtBlocos.setText("");
        txtVisualizador.setText(" Aguardando configurações...");
        spinEspacos.setValue(0);
        gerarCabecalhoLista();
    }
        
    private void adicionarBloco(){
        Bloco bloco;
        Posicao posicao = new Posicao(Integer.valueOf(spinLinhaPosi.getValue().toString()), Integer.valueOf(spinColunaPosi.getValue().toString()));
        Posicao objetivo = new Posicao(Integer.valueOf(spinLinhaObj.getValue().toString()), Integer.valueOf(spinColunaObj.getValue().toString()));
    
        if(validarDados()){
            bloco = new Bloco(lstBlocos.size() + 1, 
                                txtDescricao.getText().trim().toUpperCase(), 
                                txtAlias.getText().toUpperCase(),
                                Estado.RS, 
                                posicao, 
                                objetivo);        
            lstBlocos.add(bloco);
            strBlocos.append("    " + bloco.getAlias().toUpperCase() + "       " + 
                                      bloco.getDescricao().toUpperCase()  + repeat(" ", 4 + (13 - bloco.getDescricao().trim().length()))    + 
                                      String.valueOf(bloco.getPosicao().getLinha())  + "|" + String.valueOf(bloco.getPosicao().getColuna())  + "       " + 
                                      String.valueOf(bloco.getObjetivo().getLinha()) + "|" + String.valueOf(bloco.getObjetivo().getColuna()) +  "\n"); 
            txtBlocos.setText(strBlocos.toString());
            limparDadosBloco();
        }
    }
    
    private boolean validarDados(){
        if(txtAlias.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "O campo Alias deve ser informado!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtAlias.requestFocus();            
            return false;
        }
        if(txtDescricao.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "O campo Descrição deve ser informado!", "Atenção", JOptionPane.WARNING_MESSAGE);            
            txtDescricao.requestFocus();
            return false;
        }
        //-----------------------------------------------
        for(Bloco bloco : lstBlocos){
            if(bloco.getAlias().equals(txtAlias.getText().toUpperCase())){
                JOptionPane.showMessageDialog(null, "Já existe um bloco com o Alias informado!", "Atenção", JOptionPane.WARNING_MESSAGE);            
                txtAlias.requestFocus();
                return false;                
            }
            
            if(bloco.getDescricao().trim().equals(txtDescricao.getText().toUpperCase().trim())){
                JOptionPane.showMessageDialog(null, "Já existe um bloco com a Descrição informada!", "Atenção", JOptionPane.WARNING_MESSAGE);            
                txtDescricao.requestFocus();
                return false;                                
            }
            
            if(bloco.getPosicao().getValor().equals(String.valueOf(spinLinhaPosi.getValue() + String.valueOf(spinColunaPosi.getValue())))){
                JOptionPane.showMessageDialog(null, "Já existe um bloco na Posição informada!", "Atenção", JOptionPane.WARNING_MESSAGE);            
                spinLinhaPosi.requestFocus();
                return false;                                                
            }
            
            if(bloco.getObjetivo().getValor().equals(String.valueOf(spinLinhaObj.getValue() + String.valueOf(spinColunaObj.getValue())))){
                JOptionPane.showMessageDialog(null, "Já existe um bloco com o Objetivo informado!", "Atenção", JOptionPane.WARNING_MESSAGE);            
                spinLinhaObj.requestFocus();
                return false;                                                                
            }                        
        }
        
        return true;
    }    
    
    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }    
        
    private void limparDadosBloco(){
        txtAlias.setText("");
        txtDescricao.setText("");
        spinLinhaPosi.setValue(0);
        spinColunaPosi.setValue(0);
        spinLinhaObj.setValue(0);
        spinColunaObj.setValue(0);                
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneBancada = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtBlocos = new javax.swing.JEditorPane();
        panePrincipal = new javax.swing.JPanel();
        lblDescricao = new javax.swing.JLabel();
        lblAlias = new javax.swing.JLabel();
        panePosicao1 = new javax.swing.JPanel();
        lblLinhaPosi = new javax.swing.JLabel();
        lblColunaPosi = new javax.swing.JLabel();
        spinLinhaPosi = new javax.swing.JSpinner();
        spinColunaPosi = new javax.swing.JSpinner();
        paneObjetivo = new javax.swing.JPanel();
        lblLinhaObj = new javax.swing.JLabel();
        lblColunaObj = new javax.swing.JLabel();
        spinLinhaObj = new javax.swing.JSpinner();
        spinColunaObj = new javax.swing.JSpinner();
        txtAlias = new javax.swing.JFormattedTextField();
        cmdAdicionar = new java.awt.Button();
        cmdLimpar = new java.awt.Button();
        txtDescricao = new javax.swing.JFormattedTextField();
        paneVisualizador = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtVisualizador = new javax.swing.JEditorPane();
        paneTitleBancada = new javax.swing.JPanel();
        lblEspacos = new java.awt.Label();
        spinEspacos = new javax.swing.JSpinner();
        cmdInicial = new javax.swing.JButton();
        cmdProcessar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mundo dos Blocos");
        setLocation(new java.awt.Point(100, 100));

        paneBancada.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtBlocos.setEditable(false);
        txtBlocos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtBlocos.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        txtBlocos.setCaretColor(new java.awt.Color(255, 255, 255));
        txtBlocos.setDisabledTextColor(new java.awt.Color(0, 102, 204));
        txtBlocos.setEnabled(false);
        txtBlocos.setFocusable(false);
        txtBlocos.setMargin(new java.awt.Insets(4, 4, 4, 4));
        txtBlocos.setMaximumSize(new java.awt.Dimension(104, 2147483647));
        txtBlocos.setOpaque(false);
        jScrollPane1.setViewportView(txtBlocos);

        panePrincipal.setBorder(javax.swing.BorderFactory.createTitledBorder("Bloco"));

        lblDescricao.setText("Descrição:");

        lblAlias.setText("Alias:");
        lblAlias.setToolTipText("");

        panePosicao1.setBorder(javax.swing.BorderFactory.createTitledBorder("Posição:"));

        lblLinhaPosi.setText("Linha:");
        lblLinhaPosi.setToolTipText("");

        lblColunaPosi.setText("Coluna:");
        lblColunaPosi.setToolTipText("");

        spinLinhaPosi.setModel(new javax.swing.SpinnerNumberModel(0, 0, 15, 1));

        spinColunaPosi.setModel(new javax.swing.SpinnerNumberModel(0, 0, 15, 1));

        javax.swing.GroupLayout panePosicao1Layout = new javax.swing.GroupLayout(panePosicao1);
        panePosicao1.setLayout(panePosicao1Layout);
        panePosicao1Layout.setHorizontalGroup(
            panePosicao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panePosicao1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLinhaPosi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinLinhaPosi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblColunaPosi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinColunaPosi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panePosicao1Layout.setVerticalGroup(
            panePosicao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panePosicao1Layout.createSequentialGroup()
                .addGroup(panePosicao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(spinLinhaPosi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLinhaPosi, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblColunaPosi, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinColunaPosi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paneObjetivo.setBorder(javax.swing.BorderFactory.createTitledBorder("Objetivo:"));

        lblLinhaObj.setText("Linha:");
        lblLinhaObj.setToolTipText("");

        lblColunaObj.setText("Coluna:");
        lblColunaObj.setToolTipText("");

        spinLinhaObj.setModel(new javax.swing.SpinnerNumberModel(0, 0, 15, 1));

        spinColunaObj.setModel(new javax.swing.SpinnerNumberModel(0, 0, 15, 1));

        javax.swing.GroupLayout paneObjetivoLayout = new javax.swing.GroupLayout(paneObjetivo);
        paneObjetivo.setLayout(paneObjetivoLayout);
        paneObjetivoLayout.setHorizontalGroup(
            paneObjetivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneObjetivoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLinhaObj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinLinhaObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblColunaObj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinColunaObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        paneObjetivoLayout.setVerticalGroup(
            paneObjetivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneObjetivoLayout.createSequentialGroup()
                .addGroup(paneObjetivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLinhaObj, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblColunaObj, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinLinhaObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinColunaObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtAlias.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtAlias.setColumns(1);
        try {
            txtAlias.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("*")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtAlias.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cmdAdicionar.setLabel("adicionar");
        cmdAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAdicionarActionPerformed(evt);
            }
        });

        cmdLimpar.setFocusable(false);
        cmdLimpar.setLabel("limpar");
        cmdLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLimparActionPerformed(evt);
            }
        });

        txtDescricao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtDescricao.setColumns(13);
        try {
            txtDescricao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("*************")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDescricao.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        javax.swing.GroupLayout panePrincipalLayout = new javax.swing.GroupLayout(panePrincipal);
        panePrincipal.setLayout(panePrincipalLayout);
        panePrincipalLayout.setHorizontalGroup(
            panePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneObjetivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panePosicao1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panePrincipalLayout.createSequentialGroup()
                .addGroup(panePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panePrincipalLayout.createSequentialGroup()
                        .addComponent(cmdLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panePrincipalLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(panePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescricao)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(2, 2, 2))
        );
        panePrincipalLayout.setVerticalGroup(
            panePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panePrincipalLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(panePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao)
                    .addComponent(lblAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(panePosicao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(panePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout paneBancadaLayout = new javax.swing.GroupLayout(paneBancada);
        paneBancada.setLayout(paneBancadaLayout);
        paneBancadaLayout.setHorizontalGroup(
            paneBancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(panePrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        paneBancadaLayout.setVerticalGroup(
            paneBancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneBancadaLayout.createSequentialGroup()
                .addComponent(panePrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE))
        );

        paneVisualizador.setBorder(javax.swing.BorderFactory.createTitledBorder("Bancada"));

        txtVisualizador.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        txtVisualizador.setDisabledTextColor(new java.awt.Color(0, 0, 153));
        txtVisualizador.setEnabled(false);
        txtVisualizador.setFocusable(false);
        txtVisualizador.setMargin(new java.awt.Insets(4, 4, 4, 4));
        jScrollPane2.setViewportView(txtVisualizador);

        lblEspacos.setText("Número de Espaços:");

        spinEspacos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        cmdInicial.setToolTipText("");
        cmdInicial.setLabel("Estado Inicial");
        cmdInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdInicialActionPerformed(evt);
            }
        });

        cmdProcessar.setText("Processar");
        cmdProcessar.setToolTipText("");
        cmdProcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdProcessarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneTitleBancadaLayout = new javax.swing.GroupLayout(paneTitleBancada);
        paneTitleBancada.setLayout(paneTitleBancadaLayout);
        paneTitleBancadaLayout.setHorizontalGroup(
            paneTitleBancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneTitleBancadaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEspacos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(spinEspacos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(cmdInicial)
                .addGap(18, 18, 18)
                .addComponent(cmdProcessar)
                .addContainerGap())
        );

        paneTitleBancadaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmdInicial, cmdProcessar});

        paneTitleBancadaLayout.setVerticalGroup(
            paneTitleBancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneTitleBancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(lblEspacos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(spinEspacos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmdInicial)
                .addComponent(cmdProcessar))
        );

        javax.swing.GroupLayout paneVisualizadorLayout = new javax.swing.GroupLayout(paneVisualizador);
        paneVisualizador.setLayout(paneVisualizadorLayout);
        paneVisualizadorLayout.setHorizontalGroup(
            paneVisualizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(paneTitleBancada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        paneVisualizadorLayout.setVerticalGroup(
            paneVisualizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneVisualizadorLayout.createSequentialGroup()
                .addComponent(paneTitleBancada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneBancada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneVisualizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paneVisualizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneBancada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAdicionarActionPerformed
        // TODO add your handling code here:
        adicionarBloco();
        txtAlias.requestFocus();
    }//GEN-LAST:event_cmdAdicionarActionPerformed

    private void cmdLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLimparActionPerformed
        // TODO add your handling code here:
        iniciarConfiguracoes();
        txtAlias.requestFocus();
    }//GEN-LAST:event_cmdLimparActionPerformed

    private void cmdInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdInicialActionPerformed
        // TODO add your handling code here:
        if(String.valueOf(spinEspacos.getValue()).equals("0")){
            JOptionPane.showMessageDialog(null, "Favor configurar o número de espaços para a bancada!", "Atenção", JOptionPane.WARNING_MESSAGE);            
            spinEspacos.requestFocus();            
        }
        else{
            MundoBlocos mundoBlocos = new MundoBlocos(lstBlocos, lstBlocos.size()+1, Integer.valueOf(String.valueOf(spinEspacos.getValue())));
            mundoBlocos.setEstado(Estado.RS);
            txtVisualizador.setText(mundoBlocos.getLogs());            
        }                
    }//GEN-LAST:event_cmdInicialActionPerformed

    private void cmdProcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdProcessarActionPerformed
        // TODO add your handling code here:
        if(String.valueOf(spinEspacos.getValue()).equals("0")){
            JOptionPane.showMessageDialog(null, "Favor configurar o número de espaços para a bancada!", "Atenção", JOptionPane.WARNING_MESSAGE);            
            spinEspacos.requestFocus();            
        }
        else{
            MundoBlocos mundoBlocos = new MundoBlocos(lstBlocos, lstBlocos.size()+1, Integer.valueOf(String.valueOf(spinEspacos.getValue())));
            mundoBlocos.setEstado(Estado.RS);
            mundoBlocos.processar();
            txtVisualizador.setText(mundoBlocos.getLogs());            
        }        
    }//GEN-LAST:event_cmdProcessarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("MacOS".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button cmdAdicionar;
    private javax.swing.JButton cmdInicial;
    private java.awt.Button cmdLimpar;
    private javax.swing.JButton cmdProcessar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAlias;
    private javax.swing.JLabel lblColunaObj;
    private javax.swing.JLabel lblColunaPosi;
    private javax.swing.JLabel lblDescricao;
    private java.awt.Label lblEspacos;
    private javax.swing.JLabel lblLinhaObj;
    private javax.swing.JLabel lblLinhaPosi;
    private javax.swing.JPanel paneBancada;
    private javax.swing.JPanel paneObjetivo;
    private javax.swing.JPanel panePosicao1;
    private javax.swing.JPanel panePrincipal;
    private javax.swing.JPanel paneTitleBancada;
    private javax.swing.JPanel paneVisualizador;
    private javax.swing.JSpinner spinColunaObj;
    private javax.swing.JSpinner spinColunaPosi;
    private javax.swing.JSpinner spinEspacos;
    private javax.swing.JSpinner spinLinhaObj;
    private javax.swing.JSpinner spinLinhaPosi;
    private javax.swing.JFormattedTextField txtAlias;
    private javax.swing.JEditorPane txtBlocos;
    private javax.swing.JFormattedTextField txtDescricao;
    private javax.swing.JEditorPane txtVisualizador;
    // End of variables declaration//GEN-END:variables

}


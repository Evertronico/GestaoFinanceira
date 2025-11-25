package view;

import controller.Cliente;
import controller.MembroGrupo;
import controller.Objetivo;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ClienteModel;
import model.MembroGrupoModel;
import model.ObjetivoModel;

public class JFMembroGrupo extends javax.swing.JFrame {
    
    private int linhaSelecionada = -1;
    private ArrayList<MembroGrupo> listaMembros = new ArrayList<>();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JFMembroGrupo.class.getName());

    public JFMembroGrupo() {
        initComponents();
        carregarCombos();
        atualizarTabela(); 
        controlarBotoes(); 
    }

    private void carregarCombos() {
        // Carrega Clientes
        ClienteModel cModel = new ClienteModel();
        DefaultComboBoxModel<Object> comboCli = new DefaultComboBoxModel<>();
        for(Cliente c : cModel.liste()) {
            comboCli.addElement(c);
        }
        cbCliente.setModel(comboCli);
        cbCliente.setSelectedIndex(-1);

        // Carrega Objetivos
        ObjetivoModel oModel = new ObjetivoModel();
        DefaultComboBoxModel<Object> comboObj = new DefaultComboBoxModel<>();
        for(Objetivo o : oModel.liste()) {
            comboObj.addElement(o);
        }
        cbObjetivo.setModel(comboObj);
        cbObjetivo.setSelectedIndex(-1); 
    }

    // Método objetivo selecionado, filtra
    private void atualizarTabela() {
        MembroGrupoModel model = new MembroGrupoModel();
        if (cbObjetivo.getSelectedIndex() != -1) {
            Objetivo obj = (Objetivo) cbObjetivo.getSelectedItem();
            listaMembros = model.listePorObjetivo(obj.getObjetivoId());
        } else {
            listaMembros = model.liste();
        }
        
        carregarTabela(listaMembros);
    }

    private void carregarTabela(ArrayList<MembroGrupo> lista) {
        DefaultTableModel table = (DefaultTableModel) tblMembro.getModel();
        table.setRowCount(0);
        
        for (MembroGrupo m : lista) {
            table.addRow(new Object[]{
                m.getObjetivo().getNome(),
                m.getCliente().getNome(),
                m.getGestor() == 1 ? "Sim" : "Não"
            });
        }
    }

    private void limparFormulario() {
        linhaSelecionada = -1;
        cbCliente.setSelectedIndex(-1);
        cbObjetivo.setSelectedIndex(-1);
        
        controlarBotoes();
        atualizarTabela();
    }
    
    private void controlarBotoes() {
        btnRemover.setEnabled(linhaSelecionada != -1);
    }
    
    private void selecionarComboPorId(javax.swing.JComboBox cb, int id) {
        for (int i = 0; i < cb.getItemCount(); i++) {
            Object item = cb.getItemAt(i);
            if (item instanceof Cliente) {
                if (((Cliente) item).getClienteId() == id) {
                    cb.setSelectedIndex(i);
                    return;
                }
            } else if (item instanceof Objetivo) {
                if (((Objetivo) item).getObjetivoId() == id) {
                    cb.setSelectedIndex(i);
                    return;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMembro = new javax.swing.JTable();
        btnGravar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbObjetivo = new javax.swing.JComboBox<>();
        cbCliente = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Objetivo");

        jLabel2.setText("Cliente");

        tblMembro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Objetivo", "Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMembro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMembroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMembro);

        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover");
        btnRemover.setEnabled(false);
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        cbObjetivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbObjetivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbObjetivoActionPerformed(evt);
            }
        });

        cbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar)
                    .addComponent(btnRemover)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        if (cbCliente.getSelectedIndex() == -1 || cbObjetivo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um Cliente e um Objetivo.");
            return;
        }

        Cliente cli = (Cliente) cbCliente.getSelectedItem();
        Objetivo obj = (Objetivo) cbObjetivo.getSelectedItem();

        int id = (linhaSelecionada == -1) ? -1 : listaMembros.get(linhaSelecionada).getMembroGrupoId();
        
        MembroGrupo membro = new MembroGrupo(id, cli, obj, 0);
        MembroGrupoModel model = new MembroGrupoModel();
        String msg = model.grave(membro);
        
        JOptionPane.showMessageDialog(null, msg);
        
        if (!msg.toLowerCase().contains("erro")) {
            limparFormulario();
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbObjetivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbObjetivoActionPerformed
        atualizarTabela();
    }//GEN-LAST:event_cbObjetivoActionPerformed

    private void tblMembroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMembroMouseClicked
        linhaSelecionada = tblMembro.getSelectedRow();
        if (linhaSelecionada != -1) {
            MembroGrupo m = listaMembros.get(linhaSelecionada);
            
            selecionarComboPorId(cbCliente, m.getCliente().getClienteId());
            selecionarComboPorId(cbObjetivo, m.getObjetivo().getObjetivoId());
            
            controlarBotoes();
        }
    }//GEN-LAST:event_tblMembroMouseClicked

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        if (linhaSelecionada == -1) return;

                MembroGrupo m = listaMembros.get(linhaSelecionada);

                if (JOptionPane.showConfirmDialog(null, "Remover membro do grupo?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    MembroGrupoModel model = new MembroGrupoModel();
                    String msg = model.remova(m.getMembroGrupoId());
                    JOptionPane.showMessageDialog(null, msg);
                    limparFormulario();
                }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void cbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClienteActionPerformed

    }//GEN-LAST:event_cbClienteActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new JFMembroGrupo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<Object> cbCliente;
    private javax.swing.JComboBox<Object> cbObjetivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMembro;
    // End of variables declaration//GEN-END:variables
}

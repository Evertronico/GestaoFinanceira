package view;

import controller.FormaPagamento;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.FormaPagamentoModel;

public class JFFormaPagamento extends javax.swing.JFrame {

    // determina o modo de operação do formulário
    private int linha = -1;

    // declara coleção de objetos da classe FormaPagamento
    ArrayList<FormaPagamento> formasPagamento = new ArrayList<>();

    public JFFormaPagamento() {
        initComponents();
        atualizeTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        btnGravar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFormaPagamento = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestão de formas de pagamento");

        jLabel1.setText("Forma de pagamento");

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

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tblFormaPagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Forma de pagamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFormaPagamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFormaPagamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFormaPagamento);
        if (tblFormaPagamento.getColumnModel().getColumnCount() > 0) {
            tblFormaPagamento.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(txtDescricao)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar)
                    .addComponent(btnRemover)
                    .addComponent(btnBuscar)
                    .addComponent(btnCancelar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        try {
            FormaPagamento formaPagamento = new FormaPagamento(
                this.linha == -1 ? -1 : this.formasPagamento.get(this.linha).getFormaPagamentoId(),
                txtDescricao.getText()
            );

            FormaPagamentoModel model = new FormaPagamentoModel();
            String msg = model.grave(formaPagamento);

            refreshUI(msg);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void tblFormaPagamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormaPagamentoMouseClicked
        // recupera a linha clicada pelo usuário
        this.linha = tblFormaPagamento.rowAtPoint(evt.getPoint());
        // cria um objeto da forma de pagamento selecionada
        FormaPagamento f = this.formasPagamento.get(this.linha);
        // preenche o formulário com as informações do objeto selecionado
        txtDescricao.setText(f.getDescricao());
        // habilita o botão excluir
        this.controleBotoes();
    }//GEN-LAST:event_tblFormaPagamentoMouseClicked

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // valida a remoção
        if (this.linha == -1) {
            JOptionPane.showMessageDialog(null, "Nenhuma forma de pagamento selecionada");
            return;
        }

        // valida a ação de exclusão
        if (JOptionPane.showConfirmDialog(null, "Realmente deseja remover?", 
            "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // cria um objeto da classe model
            FormaPagamentoModel model = new FormaPagamentoModel();
            // remove a forma de pagamento e recupera a mensagem do banco de dados
            String msg = model.remova(
                this.formasPagamento.get(this.linha).getFormaPagamentoId()
            );
            this.refreshUI(msg);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // valida o termo de busca
        if (txtDescricao.getText().isEmpty()) {
            // exibe uma mensagem ao usuário
            JOptionPane.showMessageDialog(null, "Informe um termo de busca.");
            // realiza o reset do formulário
            btnCancelarActionPerformed(evt);
            return;
        }

        // cria um objeto da classe model
        FormaPagamentoModel model = new FormaPagamentoModel();
        this.formasPagamento = model.pesquise(txtDescricao.getText());
        carregarTabela(this.formasPagamento);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpeFormulario();
        this.atualizeTabela();
        this.controleBotoes();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // atualiza a interface com o usuário
    private void refreshUI(String msg) {
        // atualiza a lista de formas de pagamento na tabela
        this.atualizeTabela();
        // limpa o formulário e o coloca em modo de insersão (default)
        this.limpeFormulario();
        // habilita/desabilita o botão de remoção
        this.controleBotoes();
        // quando há uma mensagem oriunda do banco de dados a exibe na tela
        if (msg != null) {
            JOptionPane.showMessageDialog(null, msg);
        }
    }

    private void controleBotoes() {
        btnRemover.setEnabled(this.linha != -1);
    }

    private void limpeFormulario() {
        this.linha = -1;
        txtDescricao.setText("");
    }

    private void atualizeTabela() {
        // cria um objeto da classe model
        FormaPagamentoModel model = new FormaPagamentoModel();
        // lista para a variável global formasPagamento todos os registros do banco de dados
        this.formasPagamento = model.liste();
        this.carregarTabela(this.formasPagamento);
    }

    private void carregarTabela(ArrayList<FormaPagamento> lista) {
        // captura o componente JTable e converte em um DefaultTableModel
        DefaultTableModel table = (DefaultTableModel) tblFormaPagamento.getModel();
        // zera as linhas da tabela
        table.setRowCount(0);
        // para cada objeto de FormaPagamento existe na coleção de objetos cria-se uma cópia "f"
        for (FormaPagamento f : lista) {
            // adiciona uma linha na tabela imprimindo a descrição da Forma de pagamento
            table.addRow(new Object[]{f.getDescricao()});
        }
    }

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFFormaPagamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFormaPagamento;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables
}

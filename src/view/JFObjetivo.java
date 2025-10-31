package view;

import controller.Objetivo;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.ObjetivoModel;

public class JFObjetivo extends javax.swing.JFrame {

    // armazena a linha selecionada no componente JTable (o modo de operação do formulário)
    private int linhaSelecionada = -1;

    // coleção de objetos de Objetivo
    private ArrayList<Objetivo> objetivos = new ArrayList<>();

    // método construtor
    public JFObjetivo() {
        // cria e posiciona todos os componentes do formulário na tela
        initComponents();
        // atualiza a tabela
        atualizarTabela();
        // controle dos botões de acordo com o modo de operação do formulário
        controlarBotoes();
    }

    // declara o método atualizarTabela
    private void atualizarTabela() {
        // cria objeto da classe model e lista a coleção de objetos
        ObjetivoModel model = new ObjetivoModel();
        objetivos = model.liste();
        // carrega a tabela (JTable)
        carregarTabela(objetivos);
    }

    // preenche o componente JTable com os objetos da coleção Objetivos
    private void carregarTabela(ArrayList<Objetivo> lista) {
        // captura a estrutura da tabela (JTable)
        DefaultTableModel table = (DefaultTableModel) tblObjetivo.getModel();
        // limpa todas as linhas da tabela
        table.setRowCount(0);
        // percorre a coleção de objetos incluindo linha por linha na tabela
        for (Objetivo o : lista) {
            // adiciona uma nova linha na tabela
            table.addRow(new Object[]{
                o.getNome(), o.getCotas(), o.getValorIntegralizacao()
            });
        }
    }

    private void controlarBotoes() {
        btnRemover.setEnabled(linhaSelecionada != -1);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtValorIntegralizacao = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDataRealizacao = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtJurosAtrasoDiario = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMultaAtraso = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNumeroParcelas = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCotas = new javax.swing.JFormattedTextField();
        btnGravar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblObjetivo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestão de objetivos");

        jLabel1.setText("Objetivo");

        jLabel2.setText("Valor integralização");

        txtValorIntegralizacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel3.setText("Data realização");

        try {
            txtDataRealizacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel4.setText("Juros atraso diário");

        txtJurosAtrasoDiario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel5.setText("Multa atraso");

        txtMultaAtraso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel6.setText("Número parcelas");

        txtNumeroParcelas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel7.setText("Cotas");

        txtCotas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

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

        tblObjetivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Objetivo", "Cotas", "Valor integralização"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblObjetivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblObjetivoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblObjetivo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtValorIntegralizacao)
                                    .addComponent(jLabel6)
                                    .addComponent(txtNumeroParcelas))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtCotas, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtDataRealizacao, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtJurosAtrasoDiario))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(txtMultaAtraso, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGravar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemover)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar)))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorIntegralizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataRealizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJurosAtrasoDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMultaAtraso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar)
                    .addComponent(btnRemover)
                    .addComponent(btnBuscar)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        try {
            // recupera a data no padrão DD/MM/AAAA como String
            String dataStr = txtDataRealizacao.getText();
            java.sql.Date dataRealizacao = null;

            // recupera todos os valores do formulário
            String nome = txtNome.getText();
            BigDecimal valorIntegralizacao = new BigDecimal(
                txtValorIntegralizacao.getText().replace(",", ".")
            );

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date parsed = sdf.parse(dataStr);
                dataRealizacao = new java.sql.Date(parsed.getTime());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Data inválida: " + ex.getMessage());
            }

            BigDecimal jurosAtraso = new BigDecimal(
                txtJurosAtrasoDiario.getText().replace(",", ".")
            );

            BigDecimal multaAtraso = new BigDecimal(
                txtMultaAtraso.getText().replace(",", ".")
            );
            int numeroParcelas = Integer.parseInt(txtNumeroParcelas.getText());
            int cotas = Integer.parseInt(txtCotas.getText());

            // objeto da classe Objetivo
            Objetivo o = new Objetivo(
                this.linhaSelecionada == -1 
                    ? -1 
                    : this.objetivos.get(this.linhaSelecionada).getObjetivoId(),
                nome,
                valorIntegralizacao,
                dataRealizacao, 
                jurosAtraso,
                multaAtraso,
                numeroParcelas,
                cotas
            );

            ObjetivoModel model = new ObjetivoModel();
            String msg = model.grave(o);
            JOptionPane.showMessageDialog(null, msg);

            atualizarTabela();
            limparFormulario();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void tblObjetivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblObjetivoMouseClicked
        linhaSelecionada = tblObjetivo.getSelectedRow();
        // cria uma cópia do objeto selecionado
        Objetivo o = objetivos.get(linhaSelecionada);

        // preenche todos os campos do formulário
        txtNome.setText(o.getNome());
        txtValorIntegralizacao.setText( o.getValorIntegralizacao().toString());

        // formata a data para DD/MM/YYYY
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtDataRealizacao.setText( sdf.format( o.getDataRealizacao() ) );

        txtJurosAtrasoDiario.setText( o.getJurosAtrasoDiario().toString() );
        txtMultaAtraso.setText( o.getMultaAtraso().toString() );
        txtNumeroParcelas.setText( String.valueOf( o.getNumeroParcelas() ) );
        txtCotas.setText( String.valueOf( o.getCotas() ) );

        controlarBotoes();
    }//GEN-LAST:event_tblObjetivoMouseClicked

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum objetivo selecionado.");
            return;
        }

        if (JOptionPane.showConfirmDialog(
                null, "Deseja realmente remover?", "Confirmação", JOptionPane.YES_NO_OPTION
            ) == JOptionPane.YES_OPTION) {
            // cria um objeto da classe model
            ObjetivoModel model = new ObjetivoModel();
            // chama o método de remoção de registro no banco de dados
            String msg = model.remova( objetivos.get(linhaSelecionada).getObjetivoId() );
            // exibe a mensagem retornada pelo banco de dados 
            JOptionPane.showMessageDialog(null, msg);
            atualizarTabela();
            limparFormulario();
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // valida a busca pelo termo
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe um termo de busca.");
            return;
        }

        // cria um objeto da classe Model
        ObjetivoModel model = new ObjetivoModel();
        objetivos = model.pesquise(txtNome.getText());
        carregarTabela(objetivos);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparFormulario();
        atualizarTabela();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // reseta o formulário
    private void limparFormulario() {
        linhaSelecionada = -1;
        txtNome.setText("");
        txtValorIntegralizacao.setText("");
        txtDataRealizacao.setText("");
        txtJurosAtrasoDiario.setText("");
        txtMultaAtraso.setText("");
        txtNumeroParcelas.setText("");
        txtCotas.setText("");
        controlarBotoes();
    }

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
            java.util.logging.Logger.getLogger(JFObjetivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFObjetivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFObjetivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFObjetivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFObjetivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblObjetivo;
    private javax.swing.JFormattedTextField txtCotas;
    private javax.swing.JFormattedTextField txtDataRealizacao;
    private javax.swing.JFormattedTextField txtJurosAtrasoDiario;
    private javax.swing.JFormattedTextField txtMultaAtraso;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtNumeroParcelas;
    private javax.swing.JFormattedTextField txtValorIntegralizacao;
    // End of variables declaration//GEN-END:variables
}

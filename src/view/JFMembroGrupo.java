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

    // armazena o modo de operação do formulário [-1 = inserção | !=-1 edição/remoção]
    private int linha = -1;

    // coleção de objetos provenientes da tabela membro_grupo
    ArrayList<MembroGrupo> listaMembros = new ArrayList<>();

    // variáveis para armazenar clientes e objetivos já cadastrados
    ArrayList<Cliente> listaClientes;
    ArrayList<Objetivo> listaObjetivos;

    // método construtor
    public JFMembroGrupo() {
        // inicializa os componentes do formulário
        initComponents();
        // carrega os registros (cliente, objetivo) para os JComboBox
        carregarCombos();
        atualizeTabela();
    }

    private void carregarCombos() {
        try {
            // lista todos os clientes
            ClienteModel clienteModel = new ClienteModel();
            listaClientes = clienteModel.liste();

            // manipula o JComboBox de Membro/Cliente
            jcbMembro.removeAllItems();
            DefaultComboBoxModel modelCli = (DefaultComboBoxModel) jcbMembro.getModel();
            for (Cliente c : listaClientes) {
                modelCli.addElement(c);
            }

            // lista todos os objetivos
            ObjetivoModel objetivoModel = new ObjetivoModel();
            listaObjetivos = objetivoModel.liste();

            // manipula o JComboBox de Objetivo
            jcbObjetivo.removeAllItems();
            DefaultComboBoxModel modelObj = (DefaultComboBoxModel) jcbObjetivo.getModel();
            for (Objetivo o : listaObjetivos) {
                modelObj.addElement(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar informações");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbMembro = new javax.swing.JComboBox<>();
        jcbObjetivo = new javax.swing.JComboBox<>();
        btnGravar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMembroGrupo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestão de Membros");

        jLabel1.setText("Membro");

        jLabel2.setText("Objetivo");

        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover");
        btnRemover.setEnabled(false);

        btnCancelar.setText("Cancelar");

        tblMembroGrupo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Membro", "Objetivo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMembroGrupo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcbMembro, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbObjetivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(226, 226, 226)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGravar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRemover)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbMembro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar)
                    .addComponent(btnRemover)
                    .addComponent(btnCancelar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        try {
            // valida formulário antes de se comunicar com o banco de dados
            if (jcbMembro.getSelectedIndex() == -1 || jcbObjetivo.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um membro e um objetivo");
                return;
            }

            // recupera membro e objetivo na prática
            Cliente clienteSelecionado = (Cliente) jcbMembro.getSelectedItem();
            Objetivo objetivoSelecionado = (Objetivo) jcbObjetivo.getSelectedItem();

            // objeto da classe MembroGrupo
            MembroGrupo membro = new MembroGrupo(
                this.linha == -1 ? -1 : listaMembros.get(this.linha).getMembroGrupoId(),
                clienteSelecionado,
                objetivoSelecionado,
                0 // gestor padrão = 0 (temporariamente)
            );

            MembroGrupoModel model = new MembroGrupoModel();
            String msg = model.Grave(membro);

            refreshUI(msg);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void refreshUI(String msg) {
        atualizeTabela();
        //limpeFormulario();
        //controleBotoes();
    }

    private void atualizeTabela() {
        MembroGrupoModel model = new MembroGrupoModel();
        listaMembros = model.liste();
        carregarTabela(listaMembros);
    }

    private void carregarTabela(ArrayList<MembroGrupo> lista) {
        DefaultTableModel table = (DefaultTableModel) tblMembroGrupo.getModel();
        table.setRowCount(0);

        for (MembroGrupo m : lista) {
            table.addRow(new Object[]{
                m.getCliente().getNome(),
                m.getObjetivo().getNome()
            });
        }
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
            java.util.logging.Logger.getLogger(JFMembroGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFMembroGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFMembroGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFMembroGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFMembroGrupo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbMembro;
    private javax.swing.JComboBox<String> jcbObjetivo;
    private javax.swing.JTable tblMembroGrupo;
    // End of variables declaration//GEN-END:variables
}

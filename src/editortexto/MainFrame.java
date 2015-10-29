/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editortexto;

import java.awt.Color;
import static java.awt.Color.RED;
import javax.swing.text.StyledEditorKit;
import java.awt.GraphicsEnvironment;  
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

/**
 *
 * @author Fabio
 */
public class MainFrame extends javax.swing.JFrame {
    

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        rtfEditorKit = new RTFEditorKit();                      //tipo do texto
        editorPane.setEditorKit(rtfEditorKit);
        this.editorPane.setContentType("text/rtf"); 
        
        //teclas de atalho 
        editorPane.getInputMap(2).put(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK), "negrito");      //ctrl + b = negrito
        editorPane.getInputMap(2).put(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK), "italico");      //ctrl + i = italico
        editorPane.getInputMap(2).put(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK), "sublinhado");   //ctrl + u = sublinhado
        editorPane.getInputMap(2).put(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK), "alinhadoEsquerda");         //ctrl + l = alinhado à esquerda
        editorPane.getInputMap(2).put(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK), "centralizado");             //ctrl + e = centralizado
        editorPane.getInputMap(2).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK), "alinhadoDireita");          //ctrl + r = alinhado à direita
        editorPane.getInputMap(2).put(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_MASK), "justificado");              //ctrl + j = justificado
        
        
        
        //funcoes das teclas de atalho
        editorPane.getActionMap().put("alinhadoEsquerda", new AbstractAction(){
            @Override  
            public void actionPerformed(ActionEvent e) {  
                AlinharEsquerda.doClick();                          //quando precionada a tecla de atalho simula o click no botao em questao
            }  
        });
        
        
        editorPane.getActionMap().put("centralizado", new AbstractAction(){
            @Override  
            public void actionPerformed(ActionEvent e) {  
                Centralizar.doClick();  
            }  
        });
        
        editorPane.getActionMap().put("alinhadoDireita", new AbstractAction(){
            @Override  
            public void actionPerformed(ActionEvent e) {  
                AlinhaDireita.doClick();  
            }  
        });
        
        editorPane.getActionMap().put("justificado", new AbstractAction(){
            @Override  
            public void actionPerformed(ActionEvent e) {  
                Justificar.doClick();  
            }  
        });
        
        editorPane.getActionMap().put("negrito", new AbstractAction(){
            @Override  
            public void actionPerformed(ActionEvent e) {  
                ButtonNegrito.doClick();  
            }  
        });
        
        editorPane.getActionMap().put("italico", new AbstractAction(){
            @Override  
            public void actionPerformed(ActionEvent e) {  
                ButtonItalico.doClick();  
            }  
        });
        
        editorPane.getActionMap().put("italico", new AbstractAction(){
            @Override  
            public void actionPerformed(ActionEvent e) {  
                ButtonSublinhado.doClick();
            }  
        });
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jPanel1 = new javax.swing.JPanel();
        ButtonNegrito = new javax.swing.JButton();
        ButtonItalico = new javax.swing.JButton();
        ButtonSublinhado = new javax.swing.JButton();
        String[] fontes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        ComboFonte = new javax.swing.JComboBox();
        ComboTam = new javax.swing.JComboBox();
        AlinhaDireita = new javax.swing.JButton();
        AlinharEsquerda = new javax.swing.JButton();
        Centralizar = new javax.swing.JButton();
        CorFonte = new javax.swing.JButton();
        Justificar = new javax.swing.JButton();
        Recortar = new javax.swing.JButton();
        Copiar = new javax.swing.JButton();
        Colar = new javax.swing.JButton();
        Undo = new javax.swing.JButton();
        Redo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        editorPane = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        ButtonNegrito.setText("Negrito");
        ButtonNegrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonNegritoActionPerformed(evt);
            }
        });

        ButtonItalico.setText("Italico");
        ButtonItalico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonItalicoActionPerformed(evt);
            }
        });

        ButtonSublinhado.setText("Sublinhado");
        ButtonSublinhado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSublinhadoActionPerformed(evt);
            }
        });

        ComboFonte.setModel(new javax.swing.DefaultComboBoxModel(fontes));
        ComboFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboFonteActionPerformed(evt);
            }
        });

        ComboTam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30", "32", "34", "36", "38", "40", "42", "44", "46", "48", "50", "52", "54", "56" }));
        ComboTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTamActionPerformed(evt);
            }
        });

        AlinhaDireita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editortexto/right.png"))); // NOI18N
        AlinhaDireita.setAlignmentY(0.0F);
        AlinhaDireita.setMargin(new java.awt.Insets(0, 0, 0, 0));
        AlinhaDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlinhaDireitaActionPerformed(evt);
            }
        });

        AlinharEsquerda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editortexto/left.png"))); // NOI18N
        AlinharEsquerda.setMargin(new java.awt.Insets(0, 0, 0, 0));
        AlinharEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlinharEsquerdaActionPerformed(evt);
            }
        });

        Centralizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editortexto/center.png"))); // NOI18N
        Centralizar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Centralizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CentralizarActionPerformed(evt);
            }
        });

        CorFonte.setText("Cor da Fonte");
        CorFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorFonteActionPerformed(evt);
            }
        });

        Justificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editortexto/justify.png"))); // NOI18N
        Justificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JustificarActionPerformed(evt);
            }
        });

        Recortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editortexto/Cut-256.png"))); // NOI18N
        Recortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecortarActionPerformed(evt);
            }
        });

        Copiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editortexto/Copy.png"))); // NOI18N
        Copiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopiarActionPerformed(evt);
            }
        });

        Colar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editortexto/Paste-256.png"))); // NOI18N
        Colar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColarActionPerformed(evt);
            }
        });

        Undo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editortexto/Gnome-Edit-Undo-64.png"))); // NOI18N

        Redo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editortexto/Gnome-Edit-Redo-64.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ButtonNegrito, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(ButtonItalico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(ButtonSublinhado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CorFonte)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Undo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Redo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Recortar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Copiar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Colar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ComboFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboTam, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlinharEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(AlinhaDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Centralizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Justificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonNegrito, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonItalico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonSublinhado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CorFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AlinhaDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Centralizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboTam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(AlinharEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Justificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Copiar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Colar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Recortar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Undo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Redo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(editorPane);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonNegritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNegritoActionPerformed
        new StyledEditorKit.BoldAction().actionPerformed(null);
    }//GEN-LAST:event_ButtonNegritoActionPerformed

    private void ComboFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboFonteActionPerformed
        new StyledEditorKit.FontFamilyAction("font", ComboFonte.getSelectedItem().toString()).actionPerformed(null);
    }//GEN-LAST:event_ComboFonteActionPerformed

    private void ComboTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTamActionPerformed
        new StyledEditorKit.FontSizeAction("size-", Integer.parseInt(ComboTam.getSelectedItem().toString())).actionPerformed(null);
    }//GEN-LAST:event_ComboTamActionPerformed

    private void CorFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorFonteActionPerformed
        cor = JColorChooser.showDialog(null, "selecione uma cor", Color.BLACK);         //abre a paleta de cores para o usuario escolher a cor da fonte
        new StyledEditorKit.ForegroundAction("CorFonte", cor).actionPerformed(null);        //muda a cor da fonte para a cor escolhida
        
        
    }//GEN-LAST:event_CorFonteActionPerformed

    private void CentralizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CentralizarActionPerformed
        new StyledEditorKit.AlignmentAction("Centrazado", 1).actionPerformed(null);
    }//GEN-LAST:event_CentralizarActionPerformed

    private void ButtonItalicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonItalicoActionPerformed
        new StyledEditorKit.ItalicAction().actionPerformed(null);         
    }//GEN-LAST:event_ButtonItalicoActionPerformed

    private void ButtonSublinhadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSublinhadoActionPerformed
        new StyledEditorKit.UnderlineAction().actionPerformed(null);
        
    }//GEN-LAST:event_ButtonSublinhadoActionPerformed

    private void AlinharEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlinharEsquerdaActionPerformed
        new StyledEditorKit.AlignmentAction("AlinhadoEsquerda", 0).actionPerformed(null);
    }//GEN-LAST:event_AlinharEsquerdaActionPerformed

    private void AlinhaDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlinhaDireitaActionPerformed
        new StyledEditorKit.AlignmentAction("AlinhadoDireita", 2).actionPerformed(null);
    }//GEN-LAST:event_AlinhaDireitaActionPerformed

    private void JustificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JustificarActionPerformed
        new StyledEditorKit.AlignmentAction("Justificado", 3).actionPerformed(null);
    }//GEN-LAST:event_JustificarActionPerformed

    private void RecortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecortarActionPerformed
        JEditorPane recortar = editorPane;  
        Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();
        ClipboardOwner selection = new StringSelection(recortar.getText());
        board.setContents((Transferable)selection, selection);
        recortar.cut();  
    }//GEN-LAST:event_RecortarActionPerformed

    private void CopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopiarActionPerformed
        JEditorPane copiar = editorPane;  
        Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();
        ClipboardOwner selection = new StringSelection(copiar.getText());
        board.setContents((Transferable)selection, selection);
        copiar.copy();   
    }//GEN-LAST:event_CopiarActionPerformed

    private void ColarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColarActionPerformed
        JEditorPane colar = editorPane;  
       colar.paste();
    }//GEN-LAST:event_ColarActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AlinhaDireita;
    private javax.swing.JButton AlinharEsquerda;
    private javax.swing.JButton ButtonItalico;
    private javax.swing.JButton ButtonNegrito;
    private javax.swing.JButton ButtonSublinhado;
    private javax.swing.JButton Centralizar;
    private javax.swing.JButton Colar;
    private javax.swing.JComboBox ComboFonte;
    private javax.swing.JComboBox ComboTam;
    private javax.swing.JButton Copiar;
    private javax.swing.JButton CorFonte;
    private javax.swing.JButton Justificar;
    private javax.swing.JButton Recortar;
    private javax.swing.JButton Redo;
    private javax.swing.JButton Undo;
    private javax.swing.JEditorPane editorPane;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private RTFEditorKit rtfEditorKit;  
    private Color cor = null;  
    private String mem;

    private Color getChooserPanels() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

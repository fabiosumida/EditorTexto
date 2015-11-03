/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editortexto;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;


/**
 *
 * @author Leonardo
 */
public class Folha extends JEditorPane {
    /**
     * variaveis que fazem o Undo e o Redo
     */
    private final UndoManager undo = new UndoManager();
    Action undoAction = new AbstractAction("Undo") {
        public void actionPerformed(ActionEvent evt) {
            try {
                if (undo.canUndo()) {
                    undo.undo();
                }
            }
            catch (CannotUndoException e) {}
        }
    };
    Action redoAction = new AbstractAction("Redo") {
        public void actionPerformed(ActionEvent evt) {
            try {
                if (undo.canRedo()) {
                    undo.redo();
                }
            } catch (CannotRedoException e) {}
        }
    };
    
    /**
     * Janela do pai
     */
    private MainFrame mainFrame;
    
    private HTMLEditorKit htmlEditorKit;

    
    public Folha(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        

        /**
         * Inserindo o modo de editor como HTML
         */
        htmlEditorKit = new HTMLEditorKit(); //tipo do texto
        this.setEditorKit(htmlEditorKit);
        this.setContentType("text/html");
        
        //teclas de atalho 
        this.getInputMap(2).put(KeyStroke.getKeyStroke("control B"), "negrito");          //ctrl + b = negrito
        this.getInputMap(2).put(KeyStroke.getKeyStroke("control I"), "italico");          //ctrl + i = italico
        this.getInputMap(2).put(KeyStroke.getKeyStroke("control U"), "sublinhado");       //ctrl + u = sublinhado
        this.getInputMap(2).put(KeyStroke.getKeyStroke("control L"), "alinhadoEsquerda"); //ctrl + l = alinhado à esquerda
        this.getInputMap(2).put(KeyStroke.getKeyStroke("control E"), "centralizado");     //ctrl + e = centralizado
        this.getInputMap(2).put(KeyStroke.getKeyStroke("control R"), "alinhadoDireita");  //ctrl + r = alinhado à direita
        this.getInputMap(2).put(KeyStroke.getKeyStroke("control J"), "justificado");      //ctrl + j = justificado
        //funcoes das teclas de atalho
        this.getActionMap().put("alinhadoEsquerda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.AlinharEsquerda.doClick(); //quando precionada a tecla de atalho simula o click no botao em questao
            }
        });
        this.getActionMap().put("centralizado", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.Centralizar.doClick();
            }
        });
        this.getActionMap().put("alinhadoDireita", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.AlinhaDireita.doClick();
            }
        });
        this.getActionMap().put("justificado", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.Justificar.doClick();
            }
        });
        this.getActionMap().put("negrito", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ButtonNegrito.doClick();
            }
        });
        this.getActionMap().put("italico", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ButtonItalico.doClick();
            }
        });
        this.getActionMap().put("italico", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.ButtonSublinhado.doClick();
            }
        });
        
        
        /**
         * Inserindo as funcionalidades Undo e Redo
         */
        Document document = this.getDocument();
        // adicionando Listener
        document.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                undo.addEdit(evt.getEdit());
            }
        });
        // adicionando funcionalidade Undo e CTRL-Z
        this.getActionMap().put("Undo",
            undoAction
        );
        // Bind em CTRL-Z
        this.getInputMap(2).put(KeyStroke.getKeyStroke("control Z"), "Undo");
        
        // adicionando funcionalidade Redo e CTRL-Y
        this.getActionMap().put("Redo",
            redoAction
        );
        // Bind em CTRL-Y
        this.getInputMap(2).put(KeyStroke.getKeyStroke("control Y"), "Redo");
    }
}

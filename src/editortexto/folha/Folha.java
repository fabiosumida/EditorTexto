/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editortexto.folha;

import editortexto.MainFrame;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;


/**
 *
 * @author Leonardo
 * 
 * Classe que representa a Folha no nosso trabalho,
 * extende o JEditorPane e configura-o para ser editável
 */
public class Folha extends JEditorPane {
    /**
     * variaveis que fazem o Undo e o Redo
     */
    private final UndoManager undo = new UndoManager();
    private Action undoAction = new AbstractAction("Undo") {
        public void actionPerformed(ActionEvent evt) {
            try {
                if (undo.canUndo()) {
                    undo.undo();
                }
            }
            catch (CannotUndoException e) {}
        }
    };
    private Action redoAction = new AbstractAction("Redo") {
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

    
    public Folha(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        

        /**
         * Inserindo o modo de editor como HTML
         */
        this.setEditorKit(new ListEditorKit());
        // this.setContentType("text/html");;
        
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
        this.getActionMap().put("sublinhado", new AbstractAction() {
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
    
    /**
     * Funções que criam bullets/numerção
     */
    public static void makeList(Folha app, int type) {
        int start=app.getSelectionStart();
        int end=app.getSelectionEnd();
        if (start==end) {
            return;
        }
        if (start>end) {
            int tmp=start;
            start=end;
            end=tmp;
        }
        ListDocument doc = (ListDocument) app.getDocument();
        doc.makeList(start, end, type);
        app.setSelectionStart(start);
        app.setSelectionEnd(end);
    }
    
    public static void clearList(Folha app) {
        int start=app.getSelectionStart();
        int end=app.getSelectionEnd();
        if (start==end) {
            return;
        }
        if (start>end) {
            int tmp=start;
            start=end;
            end=tmp;
        }
        ListDocument doc=(ListDocument)app.getDocument();
        doc.clearLists(start, end);
        app.setSelectionStart(start);
        app.setSelectionEnd(end);
    }
    
    private void insertNumberedList(SimpleAttributeSet attrs, Document doc) throws BadLocationException {
        DefaultStyledDocument.ElementSpec[] specs = new DefaultStyledDocument.ElementSpec[10];
        DefaultStyledDocument.ElementSpec closePar = new DefaultStyledDocument.ElementSpec(attrs, DefaultStyledDocument.ElementSpec.EndTagType);
        specs[0]=closePar;

        attrs.addAttribute(DefaultStyledDocument.ElementNameAttribute, ListEditorKit.LIST_ELEMENT);
        attrs.addAttribute(ListDocument.TYPE_ATTRIBUTE_NAME, ListDocument.TYPE_NUMBERING);
        attrs.addAttribute(ListDocument.START_ATTRIBUTE_NAME, new Integer(5));
        DefaultStyledDocument.ElementSpec areaStart = new DefaultStyledDocument.ElementSpec(attrs, DefaultStyledDocument.ElementSpec.StartTagType);
        specs[1]=areaStart;

        DefaultStyledDocument.ElementSpec parStart = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.StartTagType);
        specs[2]=parStart;
        String text="List member 1.\n";
        DefaultStyledDocument.ElementSpec parContent = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.ContentType, text.toCharArray(), 0, text.length() );
        specs[3]=parContent;
        DefaultStyledDocument.ElementSpec parEnd = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.EndTagType);
        specs[4]=parEnd;
        parStart = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.StartTagType);
        specs[5]=parStart;
        text="List member 2.\n";
        parContent = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.ContentType, text.toCharArray(), 0, text.length());
        specs[6]=parContent;
        parEnd = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.EndTagType);
        specs[7]=parEnd;

        DefaultStyledDocument.ElementSpec areaEnd = new DefaultStyledDocument.ElementSpec(attrs, DefaultStyledDocument.ElementSpec.EndTagType);
        specs[8]=areaEnd;

        DefaultStyledDocument.ElementSpec openPar = new DefaultStyledDocument.ElementSpec(attrs, DefaultStyledDocument.ElementSpec.StartTagType);
        specs[9]=openPar;

        ((ListDocument)doc).insert(29, specs);
    }
    private void insertBulletedList(SimpleAttributeSet attrs, Document doc) throws BadLocationException {
        DefaultStyledDocument.ElementSpec[] specs = new DefaultStyledDocument.ElementSpec[10];
        DefaultStyledDocument.ElementSpec closePar = new DefaultStyledDocument.ElementSpec(attrs, DefaultStyledDocument.ElementSpec.EndTagType);
        specs[0]=closePar;

        attrs.addAttribute(DefaultStyledDocument.ElementNameAttribute, ListEditorKit.LIST_ELEMENT);
        attrs.addAttribute(ListDocument.TYPE_ATTRIBUTE_NAME, ListDocument.TYPE_BULLET);
        DefaultStyledDocument.ElementSpec areaStart = new DefaultStyledDocument.ElementSpec(attrs, DefaultStyledDocument.ElementSpec.StartTagType);
        specs[1]=areaStart;

        DefaultStyledDocument.ElementSpec parStart = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.StartTagType);
        specs[2]=parStart;
        String text="List member 1.\n";
        DefaultStyledDocument.ElementSpec parContent = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.ContentType, text.toCharArray(), 0, text.length() );
        specs[3]=parContent;
        DefaultStyledDocument.ElementSpec parEnd = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.EndTagType);
        specs[4]=parEnd;
        parStart = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.StartTagType);
        specs[5]=parStart;
        text="List member 2.\n";
        parContent = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.ContentType, text.toCharArray(), 0, text.length());
        specs[6]=parContent;
        parEnd = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.EndTagType);
        specs[7]=parEnd;

        DefaultStyledDocument.ElementSpec areaEnd = new DefaultStyledDocument.ElementSpec(attrs, DefaultStyledDocument.ElementSpec.EndTagType);
        specs[8]=areaEnd;

        DefaultStyledDocument.ElementSpec openPar = new DefaultStyledDocument.ElementSpec(attrs, DefaultStyledDocument.ElementSpec.StartTagType);
        specs[9]=openPar;

        ((ListDocument)doc).insert(doc.getLength(), specs);
    }
    /**
     * Fim das funções de bullets e numeração
     */
    
    
    /**
     * Obter ações que são private
     */
    public Action getUndoAction() {
        return undoAction;
    }

    public Action getRedoAction() {
        return redoAction;
    }
}

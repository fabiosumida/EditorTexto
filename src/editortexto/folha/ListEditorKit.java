/**
 * Código presente para habilitar os bullets/numerics
 * Retirado deste site:
 * http://java-sl.com/bullets_numberings.html
 */

package editortexto.folha;

import editortexto.folha.ListDocument;
import javax.swing.text.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ListEditorKit extends StyledEditorKit {
    public static String LIST_ELEMENT ="list_element";
    public ViewFactory getViewFactory() {
        return new ListEditorKit.StyledViewFactory();
    }
    public void install(JEditorPane c) {
        super.install(c);
    }
    public void deinstall(JEditorPane c) {
        super.deinstall(c);
    }
    public Document createDefaultDocument() {
        return new ListDocument();
    }

    protected static Shape getAllocation(View v, JEditorPane edit) {
        Insets ins=edit.getInsets();
        View vParent=v.getParent();
        int x=ins.left;
        int y=ins.top;
        while(vParent!=null) {
            int i=vParent.getViewIndex(v.getStartOffset(), Position.Bias.Forward);
            Shape alloc=vParent.getChildAllocation(i, new Rectangle(0,0, Short.MAX_VALUE, Short.MAX_VALUE));
            x+=alloc.getBounds().x;
            y+=alloc.getBounds().y;

            vParent=vParent.getParent();
        }

        if (v instanceof BoxView) {
            int ind=v.getParent().getViewIndex(v.getStartOffset(), Position.Bias.Forward);
            Rectangle r2=v.getParent().getChildAllocation(ind, new Rectangle(0,0,Integer.MAX_VALUE,Integer.MAX_VALUE)).getBounds();

            return new Rectangle(x,y, r2.width, r2.height);
        }

        return new Rectangle(x,y, (int)v.getPreferredSpan(View.X_AXIS), (int)v.getPreferredSpan(View.Y_AXIS));
    }

    public Action[] getActions() {
        Action[] res=super.getActions();
        for (int i=0; i<res.length; i++) {
            if (insertBreakAction.equals(res[i].getValue(Action.NAME)) ) {
                res[i]=new StyledInsertBreakAction();
            }
        }
        return res;
    }

    class StyledViewFactory implements ViewFactory {

        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new LabelView(elem);
                }
                else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                }
                else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elem, View.Y_AXIS);
                }
                else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                }
                else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
                else if (kind.equals(LIST_ELEMENT)) {
                    return new ListView(elem);
                }
            }

            // default to text display
            return new LabelView(elem);
        }
    }

    static class StyledInsertBreakAction extends StyledTextAction {
        private SimpleAttributeSet tempSet;

        StyledInsertBreakAction() {
            super(insertBreakAction);
        }

        public void actionPerformed(ActionEvent e) {
            JEditorPane target = getEditor(e);

            if (target != null) {
                if ((!target.isEditable()) || (!target.isEnabled())) {
                    UIManager.getLookAndFeel().provideErrorFeedback(target);
                    return;
                }
                StyledEditorKit sek = getStyledEditorKit(target);

                if (tempSet != null) {
                    tempSet.removeAttributes(tempSet);
                }
                else {
                    tempSet = new SimpleAttributeSet();
                }
                tempSet.addAttributes(sek.getInputAttributes());

                ListDocument doc=(ListDocument)target.getDocument();
                int offs=target.getCaretPosition();
                ListDocument.ListElement list=doc.getListElement(offs);
                if (list!=null && offs==list.getEndOffset()-1) {
                    Element last=list.getElement(list.getElementCount()-1);
                    if (last.getEndOffset()-last.getStartOffset()==1) {
                        //empty par
                        try {
                            doc.remove(offs-1, 1);

                            doc.insertString(offs, "\n", last.getAttributes());
                            doc.insertString(offs, "\n", last.getAttributes());
                            target.setCaretPosition(target.getCaretPosition()+2);
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else {
                        target.replaceSelection("\n");
                    }
                }
                else {
                    target.replaceSelection("\n");
                }

                MutableAttributeSet ia = sek.getInputAttributes();

                ia.removeAttributes(ia);
                ia.addAttributes(tempSet);
                tempSet.removeAttributes(tempSet);
            }
            else {
                // See if we are in a JTextComponent.
                JTextComponent text = getTextComponent(e);

                if (text != null) {
                    if ((!text.isEditable()) || (!text.isEnabled())) {
                        UIManager.getLookAndFeel().provideErrorFeedback(target);
                        return;
                    }
                    text.replaceSelection("\n");
                }
            }
        }
    }
}

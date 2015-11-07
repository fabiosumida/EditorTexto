/**
 * Código presente para habilitar os bullets/numerics
 * Retirado deste site:
 * http://java-sl.com/bullets_numberings.html
 */

package editortexto.folha;

import javax.swing.text.*;
import java.util.ArrayList;

public class ListDocument extends DefaultStyledDocument {
    public static int TYPE_NONE=0;
    public static int TYPE_BULLET=1;
    public static int TYPE_NUMBERING=2;

    public static String TYPE_ATTRIBUTE_NAME="type_attribute";
    public static String START_ATTRIBUTE_NAME="start_attribute";

    public ListDocument() {
    }
    
    public void insert(int offset, ElementSpec[] data) throws BadLocationException {
        super.insert(offset, data);
    }

    protected Element createBranchElement(Element parent, AttributeSet a) {
        Object name=a.getAttribute(DefaultStyledDocument.ElementNameAttribute);
        if (name !=null && name.equals(ListEditorKit.LIST_ELEMENT)) {
            return new ListElement(parent, a);
        }
        return super.createBranchElement(parent, a);
    }

    protected void clearLists(int start, int end) {
        start=getParagraphElement(start).getStartOffset();
        end=getParagraphElement(end).getEndOffset();
        if (end>getLength()) {
            end=getLength();
        }
        try {
            Element root=getDefaultRootElement();
            for (int i=0; i<root.getElementCount(); i++) {
                Element elem=root.getElement(i);
                if (ListEditorKit.LIST_ELEMENT.equals(elem.getName()) &&
                        start>=elem.getStartOffset() &&
                        start<=elem.getEndOffset()) {
                    ArrayList<ElementSpec> specs=new ArrayList<ElementSpec>();
                    ElementSpec spec;
                    for (int j=0; j<elem.getElementCount(); j++) {
                        Element par=elem.getElement(j);
                        spec=new ElementSpec(par.getAttributes(), ElementSpec.StartTagType);
                        specs.add(spec);
                        for (int k=0; k<par.getElementCount(); k++) {
                            Element leaf=par.getElement(k);
                            String text=getText(leaf.getStartOffset(), leaf.getEndOffset()-leaf.getStartOffset());
                            spec=new ElementSpec(leaf.getAttributes(),ElementSpec.ContentType, text.toCharArray(), 0, text.length());
                            specs.add(spec);
                        }
                        spec=new ElementSpec(par.getAttributes(), ElementSpec.EndTagType);
                        specs.add(spec);
                    }

                    int elStart=elem.getStartOffset();
                    remove(elStart, elem.getEndOffset()-elStart);

                    if (elStart==0) {
                        insertString(0,"\n", new SimpleAttributeSet());
                    }
                    ElementSpec[] specArray = new ElementSpec[specs.size()+2];
                    ElementSpec closePar = new ElementSpec(new SimpleAttributeSet(), ElementSpec.EndTagType);
                    specArray[0]=closePar;

                    for (int j=0; j<specs.size(); j++) {
                        specArray[j+1]=specs.get(j);
                    }

                    ElementSpec openPar = new ElementSpec(new SimpleAttributeSet(), ElementSpec.StartTagType);
                    specArray[specArray.length-1]=openPar;
                    insert(Math.max(elStart, 1), specArray);
                    if (elStart==0) {
                        remove(0,1);
                    }
                }
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    protected void makeList(int start, int end, int type) {
        try {
            start=getParagraphElement(start).getStartOffset();
            end=getParagraphElement(end).getEndOffset();
            if (end>getLength()) {
                end=getLength();
            }
            clearLists(start, end-1);

            ArrayList<ElementSpec> specs=new ArrayList<ElementSpec>();
            ElementSpec spec;
            int offs=start;
            while (offs<end) {
                Element par=getParagraphElement(offs);

                spec=new ElementSpec(par.getAttributes(), ElementSpec.StartTagType);
                specs.add(spec);
                for (int i=0; i<par.getElementCount(); i++) {
                    Element leaf=par.getElement(i);
                    String text=getText(leaf.getStartOffset(), leaf.getEndOffset()-leaf.getStartOffset());
                    spec=new ElementSpec(leaf.getAttributes(),ElementSpec.ContentType, text.toCharArray(), 0, text.length());
                    specs.add(spec);
                }
                spec=new ElementSpec(par.getAttributes(), ElementSpec.EndTagType);
                specs.add(spec);

                offs=par.getEndOffset();
            }

            remove(start, end-start);
            ElementSpec[] specArray = new ElementSpec[specs.size()+4];
            ElementSpec closePar = new ElementSpec(new SimpleAttributeSet(), ElementSpec.EndTagType);
            specArray[0]=closePar;

            SimpleAttributeSet attrs=new SimpleAttributeSet();
            attrs.addAttribute(ElementNameAttribute, ListEditorKit.LIST_ELEMENT);
            attrs.addAttribute(ElementNameAttribute, ListEditorKit.LIST_ELEMENT);
            attrs.addAttribute(ListDocument.TYPE_ATTRIBUTE_NAME, new Integer(type));
            ElementSpec areaStart = new ElementSpec(attrs, ElementSpec.StartTagType);
            specArray[1]=areaStart;

            for (int i=0; i<specs.size(); i++) {
                specArray[i+2]=specs.get(i);
            }

            ElementSpec areaEnd = new ElementSpec(attrs, ElementSpec.EndTagType);
            specArray[specArray.length-2]=areaEnd;

            ElementSpec openPar = new ElementSpec(attrs, ElementSpec.StartTagType);
            specArray[specArray.length-1]=openPar;

            insert(start, specArray);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public ListElement getListElement(int offset) {
        Element root=getDefaultRootElement();
        while (!root.isLeaf()) {
            int i=root.getElementIndex(offset);
            if (i>=0) {
                root=root.getElement(i);
            }
            else {
                return null;
            }

            if (root instanceof ListElement) {
                return (ListElement)root;
            }
        }

        return null;
    }

    public class ListElement extends BranchElement {
        int type=TYPE_BULLET;
        int start=1;
        
        public ListElement(Element parent, AttributeSet a) {
            super (parent, a);
            Object attr=a.getAttribute(TYPE_ATTRIBUTE_NAME);
            if (attr !=null) {
                type=(Integer)attr;
            }
            attr=a.getAttribute(START_ATTRIBUTE_NAME);
            if (attr !=null) {
                start=(Integer)attr;
            }
        }
    }
}

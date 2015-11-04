package editortexto.folha;

import editortexto.folha.ListDocument;
import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;

public class ListView extends BoxView {
    public static final int AREA_SHIFT=36; //half inch

    public ListView(Element elem) {
        super(elem, View.Y_AXIS);
        setInsets((short)0, (short) AREA_SHIFT, (short)0, (short)0);
    }

    public void paint(Graphics g, Shape alloc) {
        Rectangle a=alloc instanceof Rectangle ? (Rectangle)alloc : alloc.getBounds();
        super.paint(g, a);
        ListDocument.ListElement elem=(ListDocument.ListElement)getElement();
        ListDocument doc=(ListDocument)getDocument();
        int n = getViewCount();
        Rectangle clip = g.getClipBounds();
        View v=getView(0);
        while(v.getViewCount()>0) {
            v=v.getView(0);
        }
        Font f=doc.getFont(v.getAttributes());
        g.setFont(f);
        String s="999.";
        if (elem.type==ListDocument.TYPE_BULLET) {
            s="\u2022 ";
        }
        int w=g.getFontMetrics(f).stringWidth(s);
        int x = a.x + getLeftInset()-w;
        int y = a.y + getTopInset();
        for (int i = 0; i < n; i++) {
            y=a.y + getTopInset()+getOffset(Y_AXIS, i);

            v=getView(i);
            while(v.getViewCount()>0) {
                v=v.getView(0);
            }
            if (v instanceof LabelView) {
                y+=((LabelView)v).getGlyphPainter().getAscent((LabelView)v);
            }

            s=(i+elem.start)+".";
            if (elem.type==ListDocument.TYPE_BULLET) {
                s="\u2022";
            }
            g.drawString(s,x,y);
        }
    }

}

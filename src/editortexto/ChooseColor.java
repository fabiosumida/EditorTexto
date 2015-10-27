/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editortexto;

import static com.sun.javafx.fxml.expression.Expression.add;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Fabio
 */
public class ChooseColor extends JPanel {  
    
    
       public ChooseColor(){
           super(new BorderLayout());
           JLabel banner = new JLabel ("Escolha uma cor", JLabel.CENTER);
           banner.setForeground(Color.YELLOW);
           JColorChooser tcc = new JColorChooser(banner.getForeground());
           tcc = new JColorChooser(banner.getBackground());
           add(tcc, BorderLayout.PAGE_END);
           tcc.getSelectionModel().addChangeListener((ChangeListener) this);
       }
       
     /*  public void stateChanged(ChangeEvent e){
           Color newColor = tcc.getColor();
           banner.setForeground(newColor);
       }*/
}

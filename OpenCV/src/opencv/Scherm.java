/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Sybren
 */
public class Scherm extends JFrame implements ActionListener {
     private JButton inloggen;
     private JTextArea veld;
    public Scherm(){
        
    setTitle("Titel");
		setSize(300, 200);
		setLayout(new FlowLayout());

          inloggen = new JButton("inloggen");
          add(inloggen);
          inloggen.addActionListener(this);
          veld = new JTextArea(15, 30);
          add(veld);
		
		setVisible(true); 
    }
    
        public void actionPerformed(ActionEvent e){ 
	     Dialoog d = new Dialoog(this);
             if(d.getBoolean()){
             veld.setText("Username: " + d.getUsername() + "\n" + "Password: " + d.getPassword());
        

             }
             
    }

}

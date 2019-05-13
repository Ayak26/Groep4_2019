/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Sybren
 */
public class Dialoog extends JDialog implements ActionListener {
    
    private JLabel gebruikersnaam;
    private JLabel wachtwoord;
    private JTextField username;
    private JTextField password;
    private JButton OK;
    private JButton annuleer;
    private boolean bool = false;
    public Dialoog(JFrame frame){
       super(frame, true);
setSize(300, 200);
		setLayout(new FlowLayout());
                
      gebruikersnaam = new JLabel("Gebruikersnaam");
      add(gebruikersnaam);
      username = new JTextField(15);
      add(username);
      wachtwoord = new JLabel("Wachtwoord");
      add(wachtwoord);
      password = new JTextField(15);
      add(password);
      OK = new JButton("OK");
      add(OK);
      OK.addActionListener(this);
      annuleer = new JButton("Annuleer");
            annuleer.addActionListener(this);

      add(annuleer);
       setVisible(true);
    }
    
      public void actionPerformed(ActionEvent e){ 
	     if(e.getSource() == OK){
                 bool = true;
                 JOptionPane.showMessageDialog(this, "U bent succesvol ingelogd!");

                 setVisible(false);
             } 
             if(e.getSource() == annuleer){
                 bool = false;
                 setVisible(false);
             }
    }

    public String getPassword() {
        return password.getText();
    }

    public String getUsername() {
        return username.getText();
    }
    
    public boolean getBoolean(){
        return bool;
    }
      
      
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmi;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

/**
 *
 * @author Alihan
 */
public class HMI {

    public HMI() {
        JFrame hoofdmenu = new JFrame("HMI Applicatie"); //make the frame
        hoofdmenu.setSize(1500, 900);
        hoofdmenu.setLocation(0, 0);
        hoofdmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hoofdmenu.setDefaultLookAndFeelDecorated(true);
        hoofdmenu.setVisible(true);

        JButton sorteerRknop = new JButton("Sorteerrobot"); //make all the buttons
        JButton inpakRknop = new JButton("Inpakrobot");
        JButton orderknop = new JButton("Orders");
        JButton voorraadknop = new JButton("Voorraad");

        hoofdmenu.add(sorteerRknop); //add all buttons to the frame
        hoofdmenu.add(inpakRknop);
        hoofdmenu.add(orderknop);
        hoofdmenu.add(voorraadknop);

        hoofdmenu.add(new JLabel(new ImageIcon("C:\\Users\\Alihan\\Pictures\\icoontjes\\Afbeelding1.png")));

        sorteerRknop.setBounds(300, 500, 200, 30); //set position for buttons
        inpakRknop.setBounds(550, 500, 200, 30);
        orderknop.setBounds(800, 500, 200, 30);
        voorraadknop.setBounds(1050, 500, 200, 30);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new HMI();

    }
}
    

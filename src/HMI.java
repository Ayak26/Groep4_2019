/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Alihan
 */
public class HMI {

    boolean variabel_hoofdmenu = true;
    private JButton sorteerRknop;
    private JButton inpakRknop;
    private JButton orderknop;
    private JButton voorraadknop;

    public HMI() {
        JFrame hoofdmenu = new JFrame("HMI Applicatie"); //make the frame
        hoofdmenu.setSize(1500, 900);
        hoofdmenu.setLocation(0, 0);
        hoofdmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hoofdmenu.setDefaultLookAndFeelDecorated(true);
        hoofdmenu.setVisible(variabel_hoofdmenu);

        JPanel panel = new JPanel();
        JButton menuknop = new JButton("Menu");

        panel.setLayout(null);
        menuknop.setBounds(0, 0, 100, 60);
        panel.add(menuknop);
        hoofdmenu.add(panel);

        Image image = null;
        try {
            URL url = new URL("https://image.flaticon.com/icons/png/512/17/17969.png");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel labelI = new JLabel(new ImageIcon(image));

        sorteerRknop = new JButton("Sorteerrobot"); //make all the buttons
        inpakRknop = new JButton("Inpakrobot");
        orderknop = new JButton("Orders");
        voorraadknop = new JButton("Voorraad");

        hoofdmenu.add(sorteerRknop); //add all buttons to the frame
        hoofdmenu.add(inpakRknop);
        hoofdmenu.add(orderknop);
        hoofdmenu.add(voorraadknop);

        sorteerRknop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hoofdmenu.dispose();
                new Sorteerrobot();
            }
        });
        inpakRknop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hoofdmenu.dispose();
                new Inpakrobot();
            }
        });
        sorteerRknop.setBounds(300, 500, 200, 30); //set position for buttons
        inpakRknop.setBounds(550, 500, 200, 30);
        orderknop.setBounds(800, 500, 200, 30);
        voorraadknop.setBounds(1050, 500, 200, 30);
    }

}

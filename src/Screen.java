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
public class Screen extends JFrame implements ActionListener {
    private JButton sorteerRknop;
    private JButton inpakRknop;
    private JButton orderknop;
    private JButton voorraadknop;
    private JButton menuknop;

    private JPanel panel;

    private JLabel labelI;

    private Image image;

    public Screen() {
        JFrame hoofdmenu = new JFrame("Screen Applicatie"); //make the frame
        setSize(1500, 900);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);

        menuknop = new JButton("Menu");
        menuknop.setBounds(0, 0, 100, 60);

        panel = new JPanel();
        panel.setLayout(null);
        panel.add(menuknop);
        hoofdmenu.add(panel);


        try {
            URL url = new URL("https://image.flaticon.com/icons/png/512/17/17969.png");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        labelI = new JLabel(new ImageIcon(image));

        sorteerRknop = new JButton("Sorteerrobot"); //make all the buttons
        add(sorteerRknop);
        sorteerRknop.setBounds(300, 500, 200, 30); //set position for buttons
        sorteerRknop.addActionListener(this);

        inpakRknop = new JButton("Inpakrobot");
        add(inpakRknop);
        inpakRknop.setBounds(550, 500, 200, 30);
        inpakRknop.addActionListener(this);

        orderknop = new JButton("Orders");
        add(orderknop);
        orderknop.setBounds(800, 500, 200, 30);

        voorraadknop = new JButton("Voorraad");
        add(voorraadknop);
        voorraadknop.setBounds(1050, 500, 200, 30);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sorteerRknop) {
            this.dispose();
//            new Sorteerrobot();
        } else if (e.getSource() == inpakRknop) {
            this.dispose();
//            new Inpakrobot();
        }
    }
}

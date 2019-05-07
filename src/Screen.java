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
    private JButton sortBotButton;
    private JButton packBotButton;
    private JButton orderButton;
    private JButton stockButton;
    private JButton menuButton;

    private JPanel panel;

    private JLabel labelI;

    private Image image;

    public Screen() {
        setSize(1500, 900);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);

        menuButton = new JButton("Menu");
        menuButton.setBounds(0, 0, 100, 60);

        panel = new JPanel();
        panel.setLayout(null);
        panel.add(menuButton);
        add(panel);


        try {
            URL url = new URL("https://image.flaticon.com/icons/png/512/17/17969.png");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        labelI = new JLabel(new ImageIcon(image));

        sortBotButton = new JButton("Sorteerrobot"); //make all the buttons
        add(sortBotButton);
        sortBotButton.setBounds(300, 500, 200, 30); //set position for buttons
        sortBotButton.addActionListener(this);

        packBotButton = new JButton("Inpakrobot");
        add(packBotButton);
        packBotButton.setBounds(550, 500, 200, 30);
        packBotButton.addActionListener(this);

        orderButton = new JButton("Orders");
        add(orderButton);
        orderButton.setBounds(800, 500, 200, 30);

        stockButton = new JButton("Voorraad");
        add(stockButton);
        stockButton.setBounds(1050, 500, 200, 30);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortBotButton) {
            this.dispose();
//            new Sorteerrobot();
        } else if (e.getSource() == packBotButton) {
            this.dispose();
//            new Inpakrobot();
        }
    }
}

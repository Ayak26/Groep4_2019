/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmi;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Alihan
 */
public class Inpakrobot {

    public Inpakrobot() {
        JFrame inpakscherm = new JFrame();
        JPanel panel = new JPanel();
        JButton menuknop = new JButton("Menu");
        panel.setLayout(null);
        menuknop.setBounds(0, 0, 100, 60);
        panel.add(menuknop);

        inpakscherm.add(panel);
        inpakscherm.setDefaultCloseOperation(3);
        inpakscherm.setSize(1500, 900);
        inpakscherm.setLocation(0, 0);
        inpakscherm.setVisible(true);
        menuknop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                inpakscherm.dispose();
                new HMI();

            }
        }
    }

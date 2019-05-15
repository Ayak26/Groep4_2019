import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    //  JFrame hoofdmenu = new JFrame("HMI applicatie");
    JButton sorting_robot_button;
    JButton packing_robot_button;
    JButton order_button;
    JButton stock_button;
    JPanel mainmenu_panel;

    JPanel mainmenu_panel_image;
    JLabel image_sortingrob;
    JLabel image_packingrob;
    JLabel image_order;
    JLabel image_stock;


    public Screen() {
        setLayout(null);
        setSize(800, 480);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        //initializing
        sorting_robot_button = new JButton("Sorteerrobot");
        packing_robot_button = new JButton("Inpakrobot");
        order_button = new JButton("Orders");
        stock_button = new JButton("Voorraad");
        mainmenu_panel = new JPanel();

        //initializing for images
        mainmenu_panel_image = new JPanel();
        image_sortingrob = new JLabel();
        image_packingrob = new JLabel();
        image_order = new JLabel();
        image_stock = new JLabel();

        //adding buttons
        mainmenu_panel.add(sorting_robot_button);
        mainmenu_panel.add(packing_robot_button);
        mainmenu_panel.add(order_button);
        mainmenu_panel.add(stock_button);

        //adding images
        mainmenu_panel_image.add(image_sortingrob);
        mainmenu_panel_image.add(image_packingrob);
        mainmenu_panel_image.add(image_order);
        mainmenu_panel_image.add(image_stock);

        //adding actual images to the labels
        image_sortingrob.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Afbeelding1.png")));
        image_packingrob.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Afbeelding2.png")));
        image_order.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Afbeelding3.png")));
        image_stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Afbeelding4.png")));

        //setting size for buttons
        sorting_robot_button.setPreferredSize(new Dimension(150, 40));
        packing_robot_button.setPreferredSize(new Dimension(150, 40));
        order_button.setPreferredSize(new Dimension(150, 40));
        stock_button.setPreferredSize(new Dimension(150, 40));

        //setting size for images
        image_sortingrob.setPreferredSize(new Dimension(150, 150));
        image_packingrob.setPreferredSize(new Dimension(150, 150));
        image_order.setPreferredSize(new Dimension(150, 150));
        image_stock.setPreferredSize(new Dimension(150, 150));

        //setting size/position for panels
        mainmenu_panel.setBounds(0, 270, 800, 150);
        mainmenu_panel_image.setBounds(22, 130, 800, 200);

        //adding panels to frame
        add(mainmenu_panel);
        add(mainmenu_panel_image);
        setVisible(true);

    }
}

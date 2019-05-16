import javax.swing.*;
import java.awt.*;

public class Packingscreen extends JFrame {
    //set names for all the assets
    JButton home_button;
    JButton return_button;
    JPanel home_button_panel;

    JButton start_button;
    JLabel start_image;
    JPanel start_panel;
    JPanel start_panel_image;

    JLabel title;
    JPanel title_panel;

    JPanel container_panel_0;
    JTextField container_content_textfield_0;
    JButton textfield_0_plus;
    JButton textfield_0_min;

    JPanel container_panel_1;
    JTextField container_content_textfield_1;
    JButton textfield_1_plus;
    JButton textfield_1_min;

    JPanel container_panel_2;
    JTextField container_content_textfield_2;
    JButton textfield_2_plus;
    JButton textfield_2_min;

    //making the JFrame here
    public Packingscreen() {
        setLayout(null);
        setSize(800, 480);//set width and height of the JFrame
        setLocation(0, 0);//set location of JFrame on your screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);

        //making all the assets
        home_button = new JButton("Home");
        return_button = new JButton("Back");
        home_button_panel = new JPanel();

        start_image = new JLabel();
        start_button = new JButton("Start");
        start_panel = new JPanel();
        start_panel_image = new JPanel();

        title = new JLabel("Lijn <>");
        title_panel = new JPanel();

        container_content_textfield_0 = new JTextField("8");
        textfield_0_plus = new JButton("+");
        textfield_0_min = new JButton("-");
        container_panel_0 = new JPanel();

        container_panel_1 = new JPanel();
        container_content_textfield_1 = new JTextField("8");
        textfield_1_plus = new JButton("+");
        textfield_1_min = new JButton("-");

        container_panel_2 = new JPanel();
        container_content_textfield_2 = new JTextField("8");
        textfield_2_plus = new JButton("+");
        textfield_2_min = new JButton("-");

        //setting parameters for all of the assets
        home_button.setPreferredSize(new Dimension(80, 30));
        return_button.setPreferredSize(new Dimension(80, 30));
        start_image.setPreferredSize(new Dimension(150, 150));
        start_button.setPreferredSize(new Dimension(80, 30));
        title.setPreferredSize(new Dimension(100, 30));
        container_content_textfield_0.setPreferredSize(new Dimension(50, 30));
        textfield_0_min.setPreferredSize(new Dimension(50, 30));
        textfield_0_plus.setPreferredSize(new Dimension(50, 30));
        container_content_textfield_1.setPreferredSize(new Dimension(50, 30));
        textfield_1_min.setPreferredSize(new Dimension(50, 30));
        textfield_1_plus.setPreferredSize(new Dimension(50, 30));
        container_content_textfield_2.setPreferredSize(new Dimension(50, 30));
        textfield_2_min.setPreferredSize(new Dimension(50, 30));
        textfield_2_plus.setPreferredSize(new Dimension(50, 30));

        //add image to the label
        start_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/startknop.png")));

        //set location and size for all the panels
        home_button_panel.setBounds(0, 0, 150, 150);
        start_panel.setBounds(0, 400, 150, 300);
        start_panel_image.setBounds(20, 250, 150, 300);
        title_panel.setBounds(350, 0, 200, 100);
        container_panel_0.setBounds(150, 250, 250, 60);
        container_panel_1.setBounds(350, 250, 250, 60);
        container_panel_2.setBounds(550, 250, 250, 60);

        //add assets to the panels
        home_button_panel.add(home_button);
        home_button_panel.add(return_button);

        start_panel_image.add(start_image);
        start_panel.add(start_button);

        title_panel.add(title);

        container_panel_0.add(textfield_0_min);
        container_panel_0.add(container_content_textfield_0);
        container_panel_0.add(textfield_0_plus);

        container_panel_1.add(textfield_1_min);
        container_panel_1.add(container_content_textfield_1);
        container_panel_1.add(textfield_1_plus);

        container_panel_2.add(textfield_2_min);
        container_panel_2.add(container_content_textfield_2);
        container_panel_2.add(textfield_2_plus);

        //add panels to the JFrame
        add(home_button_panel);
        add(start_panel);
        add(start_panel_image);
        add(title_panel);
        add(container_panel_0);
        add(container_panel_1);
        add(container_panel_2);

        //make the JFrame visible
        setVisible(true);
    }
}

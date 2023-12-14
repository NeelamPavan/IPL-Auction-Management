package ipl.ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener{
    JButton view, add, update, remove;
    Home() {
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/ipl3.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 630, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1100, 630);
        add(image);

        JLabel heading = new JLabel("INDIAN PREMIER LEAGUE 2023");
        heading.setBounds(620, 20, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        image.add(heading);

        add = new JButton("Add Player");
        add.setBounds(650, 80, 150, 40);

        add.addActionListener(this);
        image.add(add);

        view = new JButton("Display Player");
        view.setBounds(820, 80, 150, 40);
        view.addActionListener(this);
        image.add(view);

        update = new JButton("Update Player");
        update.setBounds(650, 140, 150, 40);
        update.addActionListener(this);
        image.add(update);

        remove = new JButton("Remove Player");
        remove.setBounds(820, 140, 150, 40);
        remove.addActionListener(this);
        image.add(remove);

        setSize(1120, 630);
        setLocation(250, 100);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            new AddPlayer();
        } else if (ae.getSource() == view) {
            setVisible(false);
            new DisplayPlayer();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new DisplayPlayer();
        } else {
            setVisible(false);
            new RemovePlayer();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
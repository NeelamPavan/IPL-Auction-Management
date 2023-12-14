package ipl.ex;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
public class AddPlayer extends JFrame implements ActionListener{
    JTextField name1,age1,salary1,num;
    JComboBox role;
    JLabel lblempId;
    JButton add, back;

    AddPlayer() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Player Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelname = new JLabel("Cricketer Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);

        name1 = new JTextField();
        name1.setBounds(200, 150, 150, 30);
        add(name1);

        JLabel lage = new JLabel("Age");
        lage.setBounds(400, 150, 150, 30);
        lage.setFont(new Font("serif", Font.PLAIN, 20));
        add(lage);

        age1 = new JTextField();
        age1.setBounds(450, 150, 150, 30);
        add(age1);

        JLabel labelsalary = new JLabel("Bid-Amount");
        labelsalary.setBounds(50, 200, 150, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsalary);

        salary1 = new JTextField();
        salary1.setBounds(200, 200, 150, 30);
        add(salary1);

        JLabel lrole = new JLabel("Role");
        lrole.setBounds(400, 200, 150, 30);
        lrole.setFont(new Font("serif", Font.PLAIN, 20));
        add(lrole);

        String courses[] = {"Batsman","Bowler","Wk-Batsman","Bowling-Allrounder","Batting-Allrounder"};
        role = new JComboBox(courses);
        role.setBackground(Color.WHITE);
        role.setBounds(450, 200, 150, 30);
        add(role);

        JLabel ljersey = new JLabel("Jersey Number");
        ljersey.setBounds(50, 250, 150, 30);
        ljersey.setFont(new Font("serif", Font.PLAIN, 20));
        add(ljersey);

        num = new JTextField();
        num.setBounds(200, 250, 150, 30);
        add(num);

        add = new JButton("Add Details");
        add.setBounds(250, 300, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 300, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = name1.getText();
            String age=age1.getText();
            String salary = salary1.getText();
            String jn = num.getText();
            String rolex = (String) role.getSelectedItem();

            try {
                Conn conn = new Conn();
                String query = "insert into players values('"+name+"','"+age+"','"+salary+"','"+rolex+"','"+jn+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new AddPlayer();
    }
}
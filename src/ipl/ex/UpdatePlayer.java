package ipl.ex;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdatePlayer extends JFrame implements ActionListener
{

    JTextField  tfsalary;
    JComboBox tfrole;
    JLabel lblempId;
    JButton add, back;
    String tjn;

    UpdatePlayer(String tjn) {
        this.tjn = tjn;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Player Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);

        JLabel lblname = new JLabel();
        lblname.setBounds(200, 150, 150, 30);
        add(lblname);

        JLabel labelsalary = new JLabel("Salary");
        labelsalary.setBounds(50, 200, 150, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(200, 200, 150, 30);
        add(tfsalary);



        JLabel labeldesignation = new JLabel("Role");
        labeldesignation.setBounds(50, 250, 150, 30);
        labeldesignation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldesignation);

        String courses[] = {"Batsman","Bowler","Wk-Batsman","Bowling-Allrounder","Batting-Allrounder"};
        tfrole = new JComboBox(courses);
        tfrole.setBackground(Color.WHITE);
        tfrole.setBounds(200, 250, 150, 30);
        add(tfrole);;


        JLabel labelempId = new JLabel("Jersey Number");
        labelempId.setBounds(50, 300, 150, 30);
        labelempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelempId);

        lblempId = new JLabel();
        lblempId.setBounds(200, 300, 150, 30);
        lblempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblempId);

        try {
            Conn c = new Conn();
            String query = "select * from players where jn = '"+tjn+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                lblname.setText(rs.getString("name"));
                tfsalary.setText(rs.getString("salary"));
                lblempId.setText(rs.getString("jn"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        add = new JButton("Update Details");
        add.setBounds(250, 350, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 350, 150, 40);
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
            String salary = tfsalary.getText();
            String designation = tfrole.getSelectedItem().toString();

            try {
                Conn conn = new Conn();
                String query = "update players set salary = '"+salary+"',rolex = '"+designation+"' where jn = '"+tjn+"'";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
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
        new UpdatePlayer("");
    }
}

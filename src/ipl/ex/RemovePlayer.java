package ipl.ex;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class RemovePlayer extends JFrame implements ActionListener {
    Choice rjn;
    JLabel lbempId,lbbid;
    JButton delete, back;

    RemovePlayer() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel heading = new JLabel("Remove Players");
        heading.setBounds(400, 5, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);
        JLabel labelempId = new JLabel("Jersey Number");
        labelempId.setFont(new Font("serif", Font.PLAIN, 20));
        labelempId.setBounds(50, 50, 200, 30);
        add(labelempId);

        rjn = new Choice();
        rjn.setBounds(250, 55, 170, 40);
        add(rjn);

        try {
            Conn c = new Conn();
            String query = "select * from players";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                rjn.add(rs.getString("jn"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 100, 100, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);

        JLabel lblname = new JLabel();
        lblname.setBounds(200, 100, 250, 30);
        lblname.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblname);

        JLabel labelsalary = new JLabel("Bid-Amount");
        labelsalary.setBounds(50, 150, 150, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));

        add(labelsalary);

        lbbid = new JLabel();
        lbbid.setBounds(200, 150, 150, 30);
        lbbid.setFont(new Font("serif", Font.PLAIN, 20));

        add(lbbid);




        try {
            Conn c = new Conn();
            String query = "select * from players where jn = '"+rjn.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                lblname.setText(rs.getString("name"));

                lbbid.setText(rs.getString("salary"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        rjn.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from players where jn = '"+rjn.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        lblname.setText(rs.getString("name"));
                        lbbid.setText(rs.getString("salary"));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100,30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 300, 100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);



        setSize(1000, 400);
        setLocation(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                Conn c = new Conn();
                String query = "delete from players where jn = '"+rjn.getSelectedItem()+"'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Player Information Deleted Sucessfully");
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
        new RemovePlayer();
    }
}

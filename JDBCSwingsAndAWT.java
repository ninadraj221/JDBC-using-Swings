/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.swings.and.awt;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author Ninad
 */
class swing extends JFrame implements ActionListener
{

    JButton b1, b2;
    JTextField t1, t2;
    int a;
    String b;

    public swing()
    {
        setLayout(null);
        setSize(400, 600);
        setVisible(true);
        setDefaultCloseOperation(3);

        b1 = new JButton("Insert");
        b2 = new JButton("Retrieve");
        t1 = new JTextField("EID");
        t2 = new JTextField("ENAME");

        t1.setBounds(95, 100, 200, 30);
        t2.setBounds(95, 150, 200, 30);
        b1.setBounds(100, 200, 90, 30);
        b2.setBounds(190, 200, 90, 30);

        add(t1);
        add(t2);
        add(b1);
        add(t1);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == b2)
        {
            retrieveTable obj = new retrieveTable();
        } else if (ae.getSource() == b1)
        {
            JFrame f = new JFrame();
            a = Integer.parseInt(t1.getText());
            b = t2.getText();
            try
            {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "emp", "abc");
                Statement stmt = con.createStatement();
                String s = "insert into emp_details(eid,ename) values(" + a + ",'" + b + "')";
                int sure = JOptionPane.showConfirmDialog(f, "Are you sure?");
                if (sure == JOptionPane.YES_OPTION)
                {
                    int res = stmt.executeUpdate(s);
                    if (res == 1)
                    {
                        JOptionPane.showMessageDialog(f, "Values updated");
                    } else if (res == 0)
                    {
                        JOptionPane.showMessageDialog(f, "Values not updated");
                    }
                } else if (sure == JOptionPane.NO_OPTION)
                {
                    JOptionPane.showMessageDialog(f, "Error Try again", "Alert", JOptionPane.WARNING_MESSAGE);
                    //JOptionPane.showMessageDialog(f,"Try again");
                }
            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(f, "Error" + e, "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

public class JDBCSwingsAndAWT
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        swing ob = new swing();
    }

}

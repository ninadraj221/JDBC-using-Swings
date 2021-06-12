/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.swings.and.awt;

import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

/**
 *
 * @author Ninad
 */
public class retrieveTable extends JFrame
{

    JFrame jf;
    JTable jt;

    public retrieveTable()
    {
        try
        {
            jf = new JFrame("RETRIEVE TABLE");

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "emp", "abc");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String s = "select * from emp_details";
            ResultSet rs = stmt.executeQuery(s);

            int count = 0;
            while (rs.next())
            {
                count += 1;
            }
            JOptionPane.showMessageDialog(jf, "COUNT = " + count);

            String[][] data = new String[count][2];
            int i;
            int j;

            for (i = 0; i < data.length; i++)
            {
                rs.absolute(i + 1);
                for (j = 0; j < data[i].length; j++)
                {
                    data[i][j] = rs.getString(j + 1);
                }
            }
            System.out.println(Arrays.deepToString(data));

            String[] coloumn =
            {
                "EID", "ENAME"
            };

            jt = new JTable(data, coloumn);
            jt.setBounds(30, 40, 200, 300);
            JScrollPane sp = new JScrollPane(jt);

            jf.add(sp);

            System.out.println("WORKING");
            jf.setSize(300, 500);
            jf.setVisible(true);
            validate();
            con.close();
            stmt.close();
        } catch (Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(jf, "Error" + e, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

}

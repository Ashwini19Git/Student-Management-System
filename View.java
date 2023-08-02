import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ViewFrame extends JFrame
{
    Container c ;
    TextArea taData;
        JButton btnBack;

    ViewFrame()
    {
        c = getContentPane();
        c.setLayout(null);
         Font f = new Font("Arial",Font.BOLD, 15);
	c.setBackground(Color.GRAY);

        taData = new TextArea(30,50);
        btnBack = new JButton("Back");
      
        taData.setFont(f);
        btnBack.setFont(f);

        taData.setBounds(170, 30, 400, 300);
        btnBack.setBounds(200, 400, 200, 50);

        c.add(taData); 
        c.add(btnBack);
 
       String data="";
 try
{
DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
String url = "jdbc:mysql://localhost:3306/smsapp";
String username = "root";
String password = "abc456";
Connection con = DriverManager.getConnection(url,username,password);
String sql = "select * from student";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(sql);
while(rs.next())
    data=data + "rno = " + rs.getInt(1) + " name = " + rs.getString(2) + " S1 = " + rs.getInt(3) + " S2 = " + rs.getInt(4) + " S3 = " + rs.getInt(5) + "\n";
    taData.setText(data);
rs.close();
con.close();
}
catch(SQLException e){
        System.out.println("issue ==> "+ e);
}

        ActionListener a1 = (ae) -> {
              Home h = new Home();
              dispose();
         };
        btnBack.addActionListener(a1);
     
      setSize(700,500);
      setLocationRelativeTo(null);
      setTitle("S.M.S.");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
}
}
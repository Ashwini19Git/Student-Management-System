import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.sql.*;


class AddFrame extends JFrame
{
Container c;
JLabel rno,name,S1,S2,S3;
JTextField txtrno,txtname,txtS1,txtS2,txtS3;
JButton Savebtn,Backbtn;
AddFrame()
{
c=getContentPane();
c.setLayout(null);
c.setBackground(Color.GRAY);
rno=new JLabel("Enter Roll No :");
txtrno=new JTextField(20);
name=new JLabel("Enter Name :");
txtname=new JTextField(20);
S1=new JLabel("Enter Sub marks 1 :");
txtS1=new JTextField(20);
S2=new JLabel("Enter Sub marks 2 :");
txtS2=new JTextField(20);
S3=new JLabel("Enter Sub marks 3 :");
txtS3=new JTextField(20);
Savebtn=new JButton("Save");
Backbtn=new JButton("Back");

Font f=new Font("Arial",Font.BOLD,30);
rno.setFont(f);
txtrno.setFont(f);
name.setFont(f);
txtname.setFont(f);
S1.setFont(f);
txtS1.setFont(f);
S2.setFont(f);
txtS2.setFont(f);
S3.setFont(f);
txtS3.setFont(f);
		
Savebtn.setFont(f);
Backbtn.setFont(f);
		
rno.setBounds(249,50,300,30);
txtrno.setBounds(200,90,300,30);
name.setBounds(242,140,300,30);
txtname.setBounds(200,180,300,40);
S1.setBounds(210,230,300,30);
txtS1.setBounds(200,270,300,30);
S2.setBounds(210,320,300,30);
txtS2.setBounds(200,360,300,30);
S3.setBounds(210,410,300,30);
txtS3.setBounds(200,450,300,30);
Savebtn.setBounds(240,520,165,30);
Backbtn.setBounds(240,600,165,30);
	
c.add(rno);
c.add(txtrno);
c.add(name);
c.add(txtname);
c.add(S1);
c.add(txtS1);
c.add(S2);
c.add(txtS2);
c.add(S3);
c.add(txtS3);
c.add(Savebtn);
c.add(Backbtn);
ActionListener a2=(ae) ->{
 try
 {
	if(txtrno.getText().equals(""))
	throw new Exception("roll no should not be empty");
	int rno=Integer.parseInt(txtrno.getText());
			
        if(rno<1)
	  {
	txtrno.setText("");
	txtrno.requestFocus();
			
	throw new Exception("rollno can not be zero!");
	}
		
	 DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
       String url="jdbc:mysql://localhost:3306/smsapp";	
       String un="root";
       String pw="abc456";

	 Connection con=DriverManager.getConnection(url,un,pw);
	 String sq="select *from student where rno=?";
	 PreparedStatement ps=con.prepareStatement(sq);
	 ps.setInt(1,rno);
	 ResultSet rs=ps.executeQuery();
	if(rs.next())
				throw new Exception(rno+"  existed ");
			else
				{

				String name=txtname.getText();
			if (name.trim().isEmpty()) 
				{
    				txtname.setText("");
   				txtname.requestFocus();
    				throw new Exception("name field cannot be blank");
				}
			if(!name.matches("[a-zA-Z ]+"))
				{
				txtname.setText("");
				txtname.requestFocus();
				throw new Exception("name should contain letters only !");			
				}
			if(name.length() < 2)
				{
				throw new Exception("name should not be one letter");
				}
	if(txtS1.getText().equals("") || txtS2.getText().equals("") || txtS3.getText().equals("") )
	throw new Exception("marks field should not be empty");
	int S1=Integer.parseInt(txtS1.getText());
	 if(S1<0 || S1>100)
	{
	txtS1.setText("");
	txtS1.requestFocus();
	throw new Exception("marks of subject 1 should be in the range of 0-100");
	}
	int S2=Integer.parseInt(txtS2.getText());
	if(S2<0 || S2>100)
	{
	txtS2.setText("");
	txtS2.requestFocus();
	throw new Exception("marks of subject 2 should be in the range of 0-100");
	}
	int S3=Integer.parseInt(txtS3.getText());
	if(S3<0 || S3>100)
	{
	txtS3.setText("");
	txtS3.requestFocus();
	throw new Exception("marks of subject 3 should be in the range of 0-100");
      }
	String sql="insert into student values(?,?,?,?,?)";
	PreparedStatement pst=con.prepareStatement(sql);
			
	pst.setInt(1,rno);
	pst.setString(2,name);
	pst.setDouble(3,S1);
	pst.setDouble(4,S2);
	pst.setDouble(5,S3);
	pst.executeUpdate();
	con.close();
	JOptionPane.showMessageDialog(c,"record added");
	 txtrno.setText("");
         txtname.setText("");
         txtS1.setText("");
         txtS2.setText("");
         txtS3.setText("");

                    
        txtrno.requestFocus();
      }
	con.close();

	}
      catch(SQLException e)
	{
       JOptionPane.showMessageDialog(c,"issue = "+e);
       }
	catch(NumberFormatException e)
	{
      JOptionPane.showMessageDialog(c,"roll no and marks should be integer");
       }
	catch(Exception e)
      {
       JOptionPane.showMessageDialog(c,"issue = "+e.getMessage());
				
	}
   };		

      ActionListener a1=(ae) -> {
      Home h = new Home();
      dispose();
      };
      Savebtn.addActionListener(a2);
      Backbtn.addActionListener(a1);
      setTitle("Add Frame");
      setSize(650,800);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      }
      }
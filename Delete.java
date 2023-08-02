import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DeleteFrame extends JFrame {
    private Container c;
    private JLabel labRno;
    private JTextField txtRno;
    private JButton btnSave, btnBack;

    DeleteFrame() {
        c = getContentPane();
        c.setLayout(null);
        Font f = new Font("Arial", Font.BOLD, 30);
	c.setBackground(Color.GRAY);

        labRno = new JLabel("Enter Roll No");
        txtRno = new JTextField(20);
        btnSave = new JButton("Save");
        btnBack = new JButton("Back");

        labRno.setFont(f);
        txtRno.setFont(f);
        btnSave.setFont(f);
        btnBack.setFont(f);

        labRno.setBounds(50, 30, 200, 50);
        txtRno.setBounds(250, 30, 200, 50);
        btnSave.setBounds(100, 200, 200, 50);
        btnBack.setBounds(100, 300, 200, 50);

        c.add(labRno);
        c.add(txtRno);
        c.add(btnSave);
        c.add(btnBack);

        ActionListener backAction = (ae) -> {
            Home h = new Home();
            dispose();
        };
        btnBack.addActionListener(backAction);

       ActionListener saveAction = (ae) -> {
    String rnoText = txtRno.getText();
    
    if (rnoText.isEmpty()) {
        JOptionPane.showMessageDialog(c, "Roll number cannot be empty");
        return;
    }

    // Check if the input is a valid integer
    if (!rnoText.matches("\\d+")) {
        JOptionPane.showMessageDialog(c, "Invalid rno input. Roll number must be a valid number.");
        return;
    }

    int rno = Integer.parseInt(rnoText);

    if (rno <= 0) {
        JOptionPane.showMessageDialog(c, "Roll number should be a non-zero positive integer");
        return;
    }


            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/smsapp";
                String username = "root";
                String password = "abc456";
                Connection con = DriverManager.getConnection(url, username, password);
                String sql = "DELETE FROM student WHERE rno=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, rno);
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(c, "Record deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(c, "No record found with the given rno");
                }

                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(c, "Issue: " + e.getMessage());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(c, "Invalid rno input");
            }
        };
        btnSave.addActionListener(saveAction);

        setTitle("Delete Frame");
        setSize(650, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

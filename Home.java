import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class Home extends JFrame
{
    Container c ;
        JButton btnAdd, btnView, btnUpdate, btnDelete;

    Home()
    {
        c = getContentPane();
        c.setLayout(null);
         Font f = new Font("Arial",Font.BOLD, 30);

        btnAdd = new JButton("Add");
        btnView = new JButton("View");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        
        btnAdd.setFont(f);
        btnView.setFont(f);
        btnUpdate.setFont(f);
        btnDelete.setFont(f);
        btnAdd.setBounds(50, 30, 150, 50);
        btnView.setBounds(300, 30, 150, 50);
        btnUpdate.setBounds(50, 90, 150, 50);
        btnDelete.setBounds(300, 90, 150, 50);
        c.add(btnAdd); 
        c.add(btnView);
        c.add(btnUpdate);
        c.add(btnDelete);
       

        ActionListener a1 = (ae) -> {
              AddFrame a = new AddFrame();
              dispose();
         };
        btnAdd.addActionListener(a1);

        ActionListener a2 = (ae) -> {
              ViewFrame v = new ViewFrame();
              dispose();
         };
        btnView.addActionListener(a2);
     
        ActionListener a3 = (ae) -> {
              UpdateFrame u = new UpdateFrame();
              dispose();
         };
        btnUpdate.addActionListener(a3);

        ActionListener a4 = (ae) -> {
              DeleteFrame d = new DeleteFrame();
              dispose();
         };
        btnDelete.addActionListener(a4);
     
     
      setSize(500,500);
      setLocationRelativeTo(null);
      setTitle("S.M.S.");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
}
}
package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class AFrame extends JFrame implements ActionListener {

    private JButton confirm;
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;
    private JPanel formPanel;
    private JPanel body;
    public AFrame(){
        ImageIcon logo = new ImageIcon("logo.png");
         body = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(56, 0, 54);
                Color color2 = new Color(12, 186, 186);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, 1000, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
    
            }
    
        };
        body.setLayout(null);
        body.setBounds(0,0,1080,720);

        Border border = BorderFactory.createLineBorder(Color.BLACK,2);

        JLabel formTitle = new JLabel("Arrangement Form                  ");
        JLabel enterName = new JLabel("    Enter title:            ");
        JLabel enterStaffNum = new JLabel("Enter Number of Staff:");
        JLabel enterTime = new JLabel("Enter Time Frame:        ");
        JLabel enterDaysOff = new JLabel("Enter Days Off:");

        this.confirm = new JButton("Confirm");
        confirm.setBackground(new Color(12, 186, 186));
        confirm.setForeground(Color.WHITE);
        confirm.setBounds(70,0,100,30);
        confirm.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        confirm.addActionListener(this);
        
        formTitle.setBounds(0,0,650,60);
        formTitle.setForeground(Color.WHITE);
        formTitle.setHorizontalTextPosition(JLabel.CENTER);
        formTitle.setFont(new Font("Sans-Serif", Font.BOLD, 36));

        enterName.setForeground(Color.WHITE);
        enterName.setHorizontalTextPosition(JLabel.CENTER);
        enterName.setFont(new Font("Sans-Serif", Font.BOLD, 25));

        enterStaffNum.setForeground(Color.WHITE);
        enterStaffNum.setHorizontalTextPosition(JLabel.CENTER);
        enterStaffNum.setFont(new Font("Sans-Serif", Font.BOLD, 25));

        enterTime.setForeground(Color.WHITE);
        enterTime.setHorizontalTextPosition(JLabel.CENTER);
        enterTime.setFont(new Font("Sans-Serif", Font.BOLD, 25));

        enterDaysOff.setForeground(Color.WHITE);
        enterDaysOff.setHorizontalTextPosition(JLabel.CENTER);
        enterDaysOff.setFont(new Font("Sans-Serif", Font.BOLD, 25));


         field1 = new JTextField("");           //A loop didn't want to work for this
        field1.setPreferredSize(new Dimension(250,40));
        field1.setFont(new Font("Consolas",Font.BOLD,15));
         field2 = new JTextField("");
        field2.setPreferredSize(new Dimension(250,40));
        field2.setFont(new Font("Consolas",Font.BOLD,15));
         field3 = new JTextField("");
        field3.setPreferredSize(new Dimension(250,40));
        field3.setFont(new Font("Consolas",Font.BOLD,15));
         field4 = new JTextField("");
        field4.setPreferredSize(new Dimension(250,40));
        field4.setFont(new Font("Consolas",Font.BOLD,15));

         formPanel = new JPanel();
        formPanel.setBounds(215,210,650,300);
        formPanel.setBackground(new Color(0,0,0,51));
        formPanel.setLayout(new BorderLayout());
        formPanel.setBorder(border);
        
        JPanel formBodyPanel = new JPanel();
        formBodyPanel.setLayout(new FlowLayout());
        formBodyPanel.setBackground(new Color(0,0,0,51));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0,0,0,51));
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(0,0,0,51));
        footerPanel.setPreferredSize(new Dimension(650,50));
        footerPanel.setLayout(null);

        
        headerPanel.add(formTitle);
        formBodyPanel.add(enterName);
        formBodyPanel.add(enterStaffNum);
        formBodyPanel.add(field1);
        formBodyPanel.add(field2);
        formBodyPanel.add(enterTime);
        formBodyPanel.add(enterDaysOff);
        formBodyPanel.add(field3);
        formBodyPanel.add(field4);
        footerPanel.add(confirm);

        formPanel.add(headerPanel,BorderLayout.NORTH);
        formPanel.add(formBodyPanel,BorderLayout.CENTER);
        formPanel.add(footerPanel,BorderLayout.SOUTH);
        

        
        
        body.add(formPanel);

        this.setTitle("Shifts Arrangement");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1080,720);
        
        this.setIconImage(logo.getImage());

        this.add(body);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==confirm){
            Boolean valid;
            String errorString=new String("Type error only enter numbers in the number field");
            valid = !(field1.getText().isEmpty())
                &&(Util.isNumeric(field2.getText()))
                &&(Util.isNumeric(field3.getText()))
                &&(Util.isNumeric(field4.getText()));
            
            if(!valid)
            {
                JOptionPane.showMessageDialog(null, errorString, "Type error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String name = field1.getText();
                int staff = Integer.parseInt(field2.getText());
                int days = Integer.parseInt(field3.getText());
                int daysOff=Integer.parseInt(field4.getText());
                formPanel.removeAll();
                this.formPanel.setVisible(false);
                this.body.remove(formPanel);
                
                body.add(new FormPanel2(name,staff,days,daysOff,this));
            }

        }
        
    }
    
}

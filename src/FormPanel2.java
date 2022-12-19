package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class FormPanel2 extends JPanel implements ActionListener {

    private String name;
    private int staff,days,daysOff;
    private JButton submit;
    private JTextField staffFieldArr[];
    private JTextField daysFieldArr[];
    private JFrame frame;
    private static class MyJlabel extends JLabel {
        MyJlabel(String text,int size)
        {
            this.setText(text);
            this.setFont(new Font("Sans-Serif", Font.BOLD, size));
            this.setForeground(Color.WHITE);
        }
    
    }

    public FormPanel2(String name,int staff,int days, int daysOff,JFrame frame)
    {   
        Border border = BorderFactory.createLineBorder(Color.BLACK,1);
        this.frame = frame;
        this.name=name;
        this.staff=staff;
        this.days=days;
        this.daysOff=daysOff;

        this.setBounds(215,211,650,300);
        this.setBackground(new Color(0,0,0,52));
        this.setLayout(new BorderLayout());
        this.setBorder(border);

        JLabel title2 = new JLabel("Arrangement Form");
        title2.setBounds(0,0,650,60);
        title2.setForeground(Color.WHITE);
        title2.setHorizontalTextPosition(JLabel.CENTER);
        title2.setFont(new Font("Sans-Serif", Font.BOLD, 36));

        int week = 7;
        if (days<=7) 
            week = days;
         staffFieldArr = new JTextField[staff];
         daysFieldArr = new JTextField[week];

        for(int i=0;i<staff;i++)
        {
            staffFieldArr[i] = new JTextField();
            staffFieldArr[i].setPreferredSize(new Dimension(100,30));
        }
        for(int i=0;i<week;i++)
        {
            daysFieldArr[i] = new JTextField();
            daysFieldArr[i].setPreferredSize(new Dimension(100,30));
        }
        JLabel countLabel = new JLabel();
        countLabel.setForeground(Color.WHITE);
        countLabel.setHorizontalTextPosition(JLabel.CENTER);
        countLabel.setFont(new Font("Sans-Serif", Font.BOLD, 36));

         submit = new JButton("Submit");
        submit.setBackground(new Color(12, 186, 186));
        submit.setForeground(Color.WHITE);
        submit.setBounds(70,0,100,30);
        submit.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        submit.setFocusable(false);
        submit.addActionListener(this);

        JPanel bodyPanel2 = new JPanel();
        bodyPanel2.setLayout(new FlowLayout());
        bodyPanel2.setBackground(new Color(0,0,0,0));
        

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(325,200));
        rightPanel.setLayout(new GridLayout(staff,2));
        rightPanel.setBackground(new Color(0,0,0,0));

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(325,200));
        leftPanel.setLayout(new GridLayout(week,2));
        leftPanel.setBackground(new Color(0,0,0,0));

        JPanel footerPanel2 = new JPanel(new FlowLayout());
        footerPanel2.setPreferredSize(new Dimension(650,60));
        footerPanel2.setBackground(new Color(0,0,0,0));

        JPanel headerPanel2 = new JPanel();
        headerPanel2.setBackground(new Color(0,0,0,0));

        headerPanel2.add(title2);
        
        for(int i=0;i<staff;i++)
        {
            rightPanel.add(new MyJlabel("|     Staff "+(i+1)+" Name: ",15 ));
            rightPanel.add(staffFieldArr[i]);
        }
        String dayNames[] = new DateFormatSymbols().getWeekdays();  
        for(int i=0;i<week;i++)
        {
            leftPanel.add(new MyJlabel("Staff required  "+dayNames[1+(i+1)%7]+": ", 12));
            leftPanel.add(daysFieldArr[i]);
        }
        
        footerPanel2.add(submit);

        this.add(headerPanel2,BorderLayout.NORTH);
        this.add(leftPanel,BorderLayout.WEST);
        this.add(rightPanel,BorderLayout.EAST);
        this.add(bodyPanel2,BorderLayout.CENTER);
        this.add(footerPanel2,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==submit)
        {
            Queue<Staff> staffs = new LinkedList<Staff>();
            Boolean valid2=true;
            for(int i=0;i<staff;i++)
            {
                if(staffFieldArr[i].getText().isEmpty())
                    {
                        valid2=false;
                        break;
                    }
            }
            if(valid2)
            {
                for(JTextField field:daysFieldArr)
                {
                    if(!Util.isNumeric(field.getText()))
                    {
                        valid2=false;
                        break;
                    }
                }
            }
            if(!valid2)
                {JOptionPane.showMessageDialog(null, "Input invalid", "Type error", JOptionPane.ERROR_MESSAGE);}
            else if(valid2)
            {
                int finalDaysArray[] = new int[days];
                for(int i=0;i<days;i++)
                {
                    finalDaysArray[i] =Integer.parseInt(daysFieldArr[i%7].getText());
                }
               
                Arrangement theArrangement = new Arrangement(finalDaysArray, daysOff);
                theArrangement.setTitle(this.name);
                for(JTextField staffField:staffFieldArr)
                {
                    theArrangement.addStaff(new Staff(staffField.getText()));
                }
                new ArrangementTable(theArrangement);
                this.frame.dispose();
            }
        }
        
    }
    
}

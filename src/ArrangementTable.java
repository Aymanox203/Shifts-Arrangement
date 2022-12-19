package src;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ArrangementTable extends JFrame implements ActionListener  {
    JPanel body;
    private static class ALabel extends JLabel {
        ALabel(String text,int size)
        {
            Border border = BorderFactory.createLineBorder(Color.GRAY,1);
            this.setText(text);
            this.setFont(new Font("Sans-Serif", Font.BOLD, size));
            this.setForeground(Color.WHITE);
            this.setBorder(border);
            this.setHorizontalAlignment(JLabel.CENTER);
        }
    
    }
    ArrangementTable(Arrangement arr)
    {
        boolean displayMsg = arr.is_satisfied();
        Day arrangedDays[] =  arr.arrange();

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
        body.setBounds(0,0,1440,900);

        JLabel title = new JLabel(arr.getTitle());
        title.setFont(new Font("Sans-Serif", Font.BOLD, 25));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(225,10,1000,100);

        JLabel message = new JLabel("*note: The minimum Staff required is not satisfied for this arrangement, requirements may not be satisfied");
        message.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        message.setForeground(Color.WHITE);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setBounds(225,775,1000,100);
        JLabel arrTable[][] = new JLabel[arr.getDayCount()+1][3];
        arrTable[0][0]= new ALabel("Day",15);
        arrTable[0][1]= new ALabel("Staff",15);
        arrTable[0][2]= new ALabel("Staff Count",15);
        for(int i=0;i<arr.getDayCount();i++)
        {
            arrTable[i+1][0] = new ALabel("Day "+(i+1)+"",15);
            arrTable[i+1][1] = new ALabel(arrangedDays[i].getNames(),15);
            arrTable[i+1][2] = new ALabel(""+arrangedDays[i].getStaffCount(),15);
        }
        

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(arr.getDayCount()+1,3));
        tablePanel.setBounds(270, 100, 900, 700);
        tablePanel.setBackground(new Color(0,0,0,52));
        for(int i=0;i<arr.getDayCount()+1;i++)
        {
            for(int j=0;j<3;j++)
            {
                tablePanel.add(arrTable[i][j]);
            }
        }
        body.add(title);
        body.add(tablePanel);
        if(!displayMsg)
            body.add(message);

    
        this.setTitle("Shifts Arrangement");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1440,900);
        
        this.setIconImage(logo.getImage());

        this.add(body);

        this.setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
    
}

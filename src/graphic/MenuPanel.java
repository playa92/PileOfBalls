package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel{
	
	private PanelSwitcher switcher;
	
	public MenuPanel(int W, int H, PanelSwitcher switcher) {
		this.setSwitcher(switcher);
    	this.setPreferredSize(new Dimension(W, H));
    	this.setLayout(null);
    	this.setBackground(Color.BLUE);
    	
    	JLabel title = new JLabel("Pile of balls");
    	title.setFont(new Font("TimesRoman", Font.PLAIN, 80));
    	title.setBounds(WIDTH/2+330, 50, 500, 80);
    	title.setForeground(Color.PINK);
//    	title.setBackground(Color.BLUE);
    	this.add(title);
    	
    	 JButton start = new JButton("Start");
    	 start.setForeground(Color.PINK);
    	 start.setBackground(Color.BLUE);
  
    	 start.setBounds(W/2-40,H/2-100, 80, 40);
    	 
         start.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		getSwitcher().switchToGame();
 			}
         });
         this.add(start);
         
         JButton exit = new JButton("Exit");
    	 exit.setForeground(Color.PINK);
    	 exit.setBackground(Color.BLUE);
  
    	 exit.setBounds(W/2-40,H/2-40, 80, 40);
    	 
         exit.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		System.exit(0);
 			}
         });
         this.add(exit);

	}

	public PanelSwitcher getSwitcher() {
		return switcher;
	}

	public void setSwitcher(PanelSwitcher switcher) {
		this.switcher = switcher;
	}
	
}

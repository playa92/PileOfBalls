package graphic;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements PanelSwitcher{
	
	private final int WIDTH = 1200;
	private final int HEIGHT = 800;
	public GamePanel game;
	private MenuPanel start;

	public MainFrame() { 
		
		start = new MenuPanel(WIDTH, HEIGHT, this);

		this.setLayout(new BorderLayout());
		this.add(start);
	    this.setPreferredSize(new Dimension(1200, 800));  
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.pack();
	    this.setVisible(true);
	} 
	
	 public void switchTo(JComponent component) {   
			
		 this.getContentPane().removeAll();
		 component.setLayout(new BorderLayout());
		 this.add(component);
		 this.validate();
		 this.repaint();
		 component.transferFocus();
		 component.requestFocus();
	}
	
	public void switchToGame() {
		
		game = new GamePanel(WIDTH, HEIGHT, this);
		switchTo(game);
	}
	
	public void switchToMenu() {
		
		start = new MenuPanel(WIDTH, HEIGHT, this);
		switchTo(start);
	}
	
	public static void main(String[] args) {
		new MainFrame();

	}
}



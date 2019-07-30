package graphic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.*;
import logic.*;

@SuppressWarnings({ "unused", "serial" })
public class GamePanel  extends JPanel {

    private int WIDTH;
    private int HEIGHT;
    private int delay = 200;
   	private Color backgroundGP = Color.blue;
	private Color forgroundGP = Color.pink;
    private FontMetrics metrics;
    private GameManager game;
    private PanelSwitcher switcher;
    private int cont = 0;
    private boolean paused = false;
    private boolean paused2 = false;
    private boolean rotate = false;
    private KeyEvent tmpkey = null;
    private boolean speed = false;
    private boolean draw = false;
    private boolean risolvi = false;
    
    //////////////////
    
    private int idTMP = 0;
    
    /////////////////
    
    LinkedList<String> list = new LinkedList<String>();
    
    public GamePanel(int W, int H, final PanelSwitcher switcher) {
    	
    	this.switcher=switcher;
    	this.setPreferredSize(new Dimension(W, H));
    	   	
    	this.setBackground(backgroundGP);
    	this.setForeground(forgroundGP);
    	
    	WIDTH=W;
    	HEIGHT=H;
    
        game = new GameManager(W,H);
      
        JLabel tris_ = new JLabel("NEXT TRIS:");
        tris_.setBounds(60,30,150,30);
     	tris_.setBackground(backgroundGP);
    	tris_.setForeground(forgroundGP);
        this.add(tris_);
           
        //BUTTON RESTART
        JButton restart = new JButton("RESTART");
        restart.setBounds(W-215, 90, 100, 40);
        restart.setForeground(forgroundGP);
        restart.setBackground(backgroundGP);
        restart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		restart();
			}
        });
        this.add(restart);
        JLabel restart_ = new JLabel("OPPURE PREMI <R>");
        restart_.setBounds(W-220, 120, 250, 40);
        restart_.setForeground(forgroundGP);
        restart_.setBackground(backgroundGP);
        this.add(restart_);
        
        //BUTTON SOLVE
        JButton solve = new JButton("SOLVE");
        solve.setBounds(W-215, 170, 100, 40);
        solve.setForeground(forgroundGP);
        solve.setBackground(backgroundGP);
        solve.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        				
        			risolvi = true;
        			((MainFrame)switcher).game.requestFocusInWindow();
			}
        });
        this.add(solve);
        JLabel solve_ = new JLabel("OPPURE PREMI <S>");
        solve_.setBounds(W-220, 200, 250, 40);
        solve_.setForeground(forgroundGP);
        solve_.setBackground(backgroundGP);
        this.add(solve_);
           
        //BUTTON PAUSE
        JButton pause = new JButton("PAUSE");
        pause.setBounds(W-215, 250, 100, 40);
        pause.setForeground(forgroundGP);
        pause.setBackground(backgroundGP);
        pause.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		SoundsProvider.playPaused();
        		if(!paused)
        			paused=true;
        		else {
        			paused=false;
        			delay = 800;
        		}
        		((MainFrame)switcher).game.requestFocusInWindow();
			}
        });
        this.add(pause);
        JLabel pause_ = new JLabel("OPPURE PREMI <INVIA>");
        pause_.setBounds(W-230, 280, 260, 40);
        pause_.setForeground(forgroundGP);
        pause_.setBackground(backgroundGP);
        this.add(pause_);
        
     	this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(final KeyEvent event) {
				
				if(event.getKeyCode() == KeyEvent.VK_ESCAPE)
					switcher.switchToGame();
				else if(event.getKeyCode() == KeyEvent.VK_ENTER){
					SoundsProvider.playPaused();
	        		if(!paused)
	        			paused=true;
	        		else {
	        			paused=false;
	        			delay = 800;
	        		}
				}
				else if(event.getKeyCode() == KeyEvent.VK_S){
					solve();
				}
				else if(event.getKeyCode() == KeyEvent.VK_R){
					restart();
				}
				else if(event.getKeyCode() == KeyEvent.VK_LEFT){
					if(!list.contains("LEFT"))
						list.add("LEFT");
						repaint();
						delay = 100;
				}else if(event.getKeyCode() == KeyEvent.VK_RIGHT){			
						delay = 100;
						if(!list.contains("RIGHT"))
						list.add("RIGHT");
						repaint();
				}

				else if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
					rotate = true;
					tmpkey = event;
				}
				
				else if(event.getKeyCode() == KeyEvent.VK_SPACE){
					//SPEED
					if(!list.contains("SPACE"))
						list.add("SPACE");

				}
			}

			@Override
			public void keyReleased(final KeyEvent event){
				
				if(event.getKeyCode() == KeyEvent.VK_SPACE)
					list.remove("SPACE");
				else if(event.getKeyCode() == KeyEvent.VK_UP){
					list.remove("UP");
				}else if(event.getKeyCode() == KeyEvent.VK_DOWN){
					list.remove("DOWN");
				}
				
				if(game.getTris().size()>0){
					game.getTris().get(0).setDirection(Direction.DOWN);
					game.getTris().get(1).setDirection(Direction.DOWN);
					game.getTris().get(2).setDirection(Direction.DOWN);
					game.getTris().get(0).setBorder(false);
					game.getTris().get(1).setBorder(false);
					game.getTris().get(2).setBorder(false);
				}
				speed = false;
			
			}
		
     	});
     	
		new Thread() {

			@Override
			public void run() {
				
				SoundsProvider.playMusic();
				while (!game.exit) {        
		
					if(!paused && !paused2 && !draw)
						GameLoop();
		
						repaint();
						try {
							sleep(delay);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
				
				}
				if(!game.restart){
					repaint();
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		switcher.switchToMenu();
				}
			}
		}.start();
		
    }

    public void GameLoop(){
    	
    	for(int i=0;i<list.size();i++){
    		if(list.get(i).equals("LEFT"))
    			game.changeDir(Direction.LEFT);
    		else if(list.get(i).equals("RIGHT")){
    			game.changeDir(Direction.RIGHT);
    		}else if(list.get(i).equals("SPACE")){
    			speed = true;
				delay = 10;
    		}
    		list.remove(i--);
    	}
    	
    	//GENERA TRIS
    		game.generateTris();
    	
        //RUOTA TRIS
	    	if(rotate){
	    		game.rotate(tmpkey);
	    		rotate = false;
	    	}
    	
    	//AGGIORNA TRIS    	
	    	if(cont++ == 4){
				game.updateTris("godown");
	    		cont=0;
			}
	    	else
				game.updateTris("");
	    	  	
	    //EFFETTO URTO
	    	if(!speed){
		       if(game.oneBallDeactivated(game.tris)) {
		    	   delay = 30;
		       }
		       else
		    	   delay= 200;
	    	}
	      		      
		 // CRASH   
		   	   game.crash(game.matrix, game.tris, game.tris_fermi);
		   	   game.verify(game.tris, game.matrix);		
		 
		   	   repaint();
		   	   
		   	   if(risolvi){
		   		   solve();
		   		   risolvi=false;
		   	   }
    }
       
    private void solve(){
    	
    	game.all_matrix.clear();
       	LinkedList<HexagonDLV> all = game.createMatrixAndHexagonForDLV();
    	       	
    	game.solved.clear();
    		
		paused2 = true;
		
    	
		//
		// VERSIONE 1
		//
//		game.solved = game.intelligenza.solve(all);
		
		//
		// VERSIONE 2
		//
		if(game.halfFullMatrix())
			game.solved = game.intelligenza.solve1(all);
		else
			game.solved = game.intelligenza.solve2(all);
		
		
		    
    	if(game.solved != null){
//    		System.out.println(game.solved.size());
    		idTMP = game.solved.get(0).getId();
//        	for(int i=0;i<game.solved.size();i++)
//        		System.out.print(game.solved.get(i).getX() + ":" + game.solved.get(i).getY() + " col:" + game.solved.get(i).getColor() + " id:"+game.solved.get(i).getId() + " | " );
    	}else{ 
    		System.out.println("vuoto");
    	}
    	
    	teletrasport();
		paused2 = false;
    }
    
    private void restart(){
		game.restart = true;
		game.exit=true;
		switcher.switchToGame();
    }
    
    private void teletrasport(){
    	
    	for(int i=0;i<3;i++){
    		game.matrix.map[game.tris.get(i).getX()][game.tris.get(i).getY()].setBall(null);
    		game.matrix.map[game.tris.get(i).getX()][game.tris.get(i).getY()].setContain(false);
    	}
    	game.tris.clear();
        	
    	//sposto il tris ma al momento me lo tengo nella lista
    	for(int i=0;i<game.all_matrix.get(idTMP).trisImpatto.size();i++){
    		game.tris.add(game.all_matrix.get(idTMP).trisImpatto.get(i));
    	}
    		
    	draw = true;
    	
    	new Thread(){
    		public void run() {
    			try {
    				Thread.sleep(3000);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			draw = false;
    			//ora posso aggiungere il tris nella nuova pos nella matrice
    		 	for(int i=0;i<game.all_matrix.get(idTMP).trisImpatto.size();i++){
    	    		game.matrix.map[game.tris.get(i).getX()][game.tris.get(i).getY()].setBall(game.tris.get(i));
    	    		game.matrix.map[game.tris.get(i).getX()][game.tris.get(i).getY()].setContain(true);
    	    	}
    		};
    	}.start();  
    }
    
	private Color convertColor(Hexagon hex){
    	switch(hex.getBall().getBallColor()){
    	case RED:
    		return Color.RED;
    	case BLUE:
    		return Color.BLUE;
    	case GREEN:
    		return Color.decode("#5cd121");
    	case YELLOW:
    		return Color.YELLOW;
    	case ORANGE:
    		return Color.ORANGE;
    	case MAGENTA:
    		return Color.MAGENTA;
    	default:
    		return Color.white;
    	}
    }
 
    private void drawHex(Graphics g) {
    	 Graphics2D g2d = (Graphics2D) g;

        int cols = game.COLUMN;
        for (int i = 0; i < game.ROW; i++) {
            for (int j = 0; j < cols; j++) {
            	
            	Hexagon hex = game.matrix.map[i][j];
            	
                String text = String.format("%s : %s", Integer.toString(i), Integer.toString(j));
                int w = metrics.stringWidth(text);
                int h = metrics.getHeight();
                
                int x = hex.getCenter().x;
                int y = hex.getCenter().y;
                
                
//              //commentare per nascondere gli esagono
//                Color col = Color.white;
//                hex.draw(g2d, x, y, 0, col, true);
//                g.setColor(Color.gray);
//                g.drawString(text, x - w/2, y + h/2);
//  
//                // basta commentare se nn vuoi vedere le palline ma scommentare quello di sopra
                drawBall(hex, g);
                
            }
            cols = (cols == game.COLUMN) ? cols-1 : game.COLUMN;
        }
        
    }
  
    private void drawBall(Hexagon hex, Graphics g){
    	
         if( hex.isContain()){
        	 Color col = convertColor(hex);
             g.setColor(col);
         	 g.fillOval((int)hex.getCenter().getX()-hex.getRadius()/2-12,(int) hex.getCenter().getY()-hex.getRadius()/2-12, hex.getRadius()+24, hex.getRadius()+24);
         }
    }
    
    private void draw_Ball_dlv(Graphics g){
    	
    	if(!game.exit)
    	((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .7f));
  	
    	for(int i=0;i<game.all_matrix.get(idTMP).trisImpatto.size();i++){
    		
    		Ball b = game.all_matrix.get(idTMP).trisImpatto.get(i);
	    	
    		Hexagon tmp = game.matrix.map[b.getX()][b.getY()];
    		Hexagon hex = new Hexagon(tmp.getCenter(), 30, tmp.getA(), tmp.getB());
    		hex.setBall(b);
    		
    		
            Color col = convertColor(hex);
            g.setColor(col);
        	g.fillOval((int)hex.getCenter().getX()-hex.getRadius()/2-12,(int) hex.getCenter().getY()-hex.getRadius()/2-12, hex.getRadius()+24, hex.getRadius()+24);

    	}
    	
    	if(!game.exit)
    	((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
    
    private void draw_next_ball(Graphics g){

    	int x_ = 120;
    	int y_ = 190;
    	for(int i=0;i<game.next_tris.size();i++){
    		
    		if(i==1)
    			x_ += 85;
    		if(i==2){
    			x_ -= 43;
    			y_ -= 73;
    		}
    			Ball b = game.next_tris.get(i);
	    		Hexagon tmp = game.matrix.map[b.getX()][b.getY()];
	    		Hexagon hex = new Hexagon(new Point(x_,y_), 60, 30, 30/*, null*/);
	    		hex.setBall(game.next_tris.get(i));
	            Color col = convertColor(hex);
	            g.setColor(col);
	        	g.fillOval((int)hex.getCenter().getX()-hex.getRadius()/2-12,(int) hex.getCenter().getY()-hex.getRadius()/2-12, hex.getRadius()+24, hex.getRadius()+24);
	    	}
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D g2d = (Graphics2D) g;
    	
    	if(game.exit)
    	((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f));
    	
    	g.setColor(Color.decode("#fcb8c9"));
    	
    	//RIQUADRO GIOCO
    	g.fillRect(330, 28, 538, 713);
    	
    	//RIQUADRO prossimo tris
    	g.fillRect(60, 60, 200, 200);
    	
        metrics = g.getFontMetrics();
   
        drawHex(g);  //DISEGNO LA MATRICE DI ESAGONI
        
        if(draw && (System.currentTimeMillis() / 400) % 2 == 0 ){
        	draw_Ball_dlv(g); // DISEGNO LE PALLINE TEMPORANEE
        }
        
        draw_next_ball(g);
        
    	if (paused) {
			g2d.drawImage(ImageProvider.getPaused(), WIDTH/2-75, HEIGHT/2-55, null);
		}
    	
    	if(game.exit){
    		((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    		g2d.setColor(Color.BLACK);
    		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
	    	if(game.loose){
	    		g2d.drawString("Game Over", WIDTH /2-93, HEIGHT /2-20);
	    	}else{
	    		g2d.drawString("WIN", WIDTH /2-57, HEIGHT /2-20);
	    	}
    	}
    }
    
}
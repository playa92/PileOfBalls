package logic;
import graphic.SoundsProvider;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import dlv.Intelligenza;
 
@SuppressWarnings("unused")
public class GameManager {
	
	private int W;
	private int H;
    public final int ROW = 15;
    public final int COLUMN = 10;
	public Matrix matrix;

	public Intelligenza intelligenza;
    public ArrayList<Ball> tris;
    public ArrayList<Ball> tris_fermi;
    public LinkedList<Ball> next_tris;
    public LinkedList<HexagonDLV> solved;
    public ArrayList<Matrix> all_matrix;
    
	private boolean firstTime;
	public boolean exit;
	public boolean loose;
	public boolean flag;
	public boolean restart;
	private boolean allCrashed = false;	
	public long lastTrueTime;
	private boolean uniti = true;
	
    public GameManager(int W, int H){
    	this.W=W;
    	this.H=H;
    	tris = new ArrayList<Ball>();
    	tris_fermi  = new ArrayList<Ball>();
    	next_tris  = new LinkedList<Ball>();
    	solved  = new LinkedList<HexagonDLV>();
    	all_matrix = new ArrayList<Matrix>();
		matrix=new Matrix(ROW, COLUMN, W, H);
		
		firstTime = true;
		flag=false; // se dopo il verify ci sono palline che devono scoppiare
		loose = exit = false;
		intelligenza = new Intelligenza();
		
		next_tris.add( new Ball(1, 4, "A"));   // pallina A
	    next_tris.add( new Ball(1, 5, "B")); // pallina B
	    next_tris.add( new Ball(0, 5, "C"));   // pallina C		    	
	}
    
    public void generateTris(){
    	
    	if(firstTime || (tris.size() > 0 && allPosizionato(tris) && !flag)){
    		firstTime = false;
   
    		// se ci sono palline nella poszione di spawn partita persa
    		if(matrix.map[1][4].getBall() != null || 
    		   matrix.map[1][5].getBall() != null ||
    		   matrix.map[0][5].getBall() != null ){
    			exit = true;
    			loose = true;
    		}
    		uniti = true;
    		tris.clear();
    		while(!next_tris.isEmpty()){
    			Ball b = next_tris.pop();
    			b.init(matrix);
    			tris.add(b);
    		}
    		
    		next_tris.clear();
    		
    		next_tris.add( new Ball(1, 4, "A"));   // pallina A
    	    next_tris.add( new Ball(1, 5, "B")); // pallina B
    	    next_tris.add( new Ball(0, 5, "C"));   // pallina C	
    	    	    		
    	}
    }
    
    public void rotate(KeyEvent e) {
    	// SE NON è ne down ne up non ha senso ruotare
    	if(e.getKeyCode() !=  KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_UP)
    		return;
    	
    	Ball a = tris.get(0);
    	Ball b = tris.get(1);
    	Ball c = tris.get(2);
    	  	
    	// CONTROLLO DEGLI URTI MENTRE RUOTO
    	if(canRotate(a,b,c,e)){
    
	    	Hexagon ballA = matrix.map[a.getX()][a.getY()];
	    	Hexagon ballB = matrix.map[b.getX()][b.getY()];
	    	Hexagon ballC = matrix.map[c.getX()][c.getY()];
	    	
	    	if(e.getKeyCode() == KeyEvent.VK_DOWN){
	    		
		    			if(c.getX() < b.getX()){    // se "C" si trova sopra rispetto "B" 
				    						
		    				//A rimane fisso B pure ma si prende il colore di C, invece C salta di 2 celle	
				    				BallColor t= b.getBallColor();
				    				b.setBallColor(c.getBallColor());		
				    				c.setX(c.getX()+2);
				    				c.setBallColor(t);
				    				
				    			//aggiorno nuova pos di C
				       				matrix.map[c.getX()][c.getY()].setBall(c);
				    				matrix.map[c.getX()][c.getY()].setContain(true);
				    				
				    			//reset 
				    				ballC.setBall(null);
				    				ballC.setContain(false); 	
				    				
				    		
		    			} else if(c.getX() > b.getX()){    	// se "C" si trova sotto rispetto "B" quindi triangolo capovolto
		    				
		    				
		    				// "A" si sposta di 2 celle verso il basso e si prende il colore di "C" 
		    				// "B" si spota di 2 celle mantiene il suo colore
		    				// "C" rimane fermo ereditando il colore di A
		    				
		    					a.setX(a.getX()+2);
		    					BallColor t= a.getBallColor();
		    					a.setBallColor(c.getBallColor());
		    					b.setX(b.getX()+2);
		    					c.setBallColor(t);
		
		    					//aggiorno nuove pos
			    					matrix.map[a.getX()][a.getY()].setBall(a);
			    					//TODO
				    				matrix.map[a.getX()][a.getY()].setContain(true);
				    				matrix.map[b.getX()][b.getY()].setBall(b);
				    					//TODO
				    				matrix.map[b.getX()][b.getY()].setContain(true);
		    					//reset
				    				ballA.setBall(null);
				    				ballA.setContain(false); 	
				    				ballB.setBall(null);
				    				ballB.setContain(false); 	
		    			}
	    		
	    	}else if(e.getKeyCode() == KeyEvent.VK_UP){
	    		
		    		if(c.getX() < b.getX()){    // se "C" si trova sopra rispetto "B" 
						
						//B rimane fisso  A pure ma si prende il colore di C, C invece si sposta di 2 celle
			    				BallColor t= a.getBallColor();
			    				a.setBallColor(c.getBallColor());		
			    				c.setX(c.getX()+2);
			    				c.setBallColor(t);
			    				
			    			//aggiorno nuova pos di C
			       				matrix.map[c.getX()][c.getY()].setBall(c);
			       				//TODO
			    				matrix.map[c.getX()][c.getY()].setContain(true);
			    				
			    			//reset 
			    				ballC.setBall(null);
			    				ballC.setContain(false); 	
			    				
			    		
					} else if(c.getX() > b.getX()){    	// se "C" si trova sotto rispetto "B" quindi triangolo capovolto
						
						// "A" si sposta di 2 celle verso il basso e si prende il colore di "C" 
						// "B" si spota di 2 celle mantiene il suo colore
						// "C" rimane fermo ereditando il colore di B
						
							a.setX(a.getX()+2);
							BallColor t= b.getBallColor();
							b.setBallColor(c.getBallColor());
							b.setX(b.getX()+2);
							c.setBallColor(t);
		
							//aggiorno nuove pos
		    					matrix.map[a.getX()][a.getY()].setBall(a);
		    					//TODO
			    				matrix.map[a.getX()][a.getY()].setContain(true);
			    				matrix.map[b.getX()][b.getY()].setBall(b);
			    				//TODO
			    				matrix.map[b.getX()][b.getY()].setContain(true);
							//reset
			    				ballA.setBall(null);
			    				ballA.setContain(false); 	
			    				ballB.setBall(null);
			    				ballB.setContain(false); 	
					}
	    				
	    	}
    	}
    }
    
    // ------------- EXPLOSION -------------------
    
    public void crash(Matrix m, ArrayList<Ball> t, ArrayList<Ball> t_fermi){
    	// SCANDISCO TUTTA LA MATRICE DAL BASSO VERSO L'ALTO
    		for(int i = ROW-1; i >0; i--){
	    			for(int j = 0; j<COLUMN; j++){
	    					if( (i % 2 != 0 && j<COLUMN-1) ||  (i %2 ==0 && j<COLUMN) ){
	    							Ball ball = ((Ball)m.map[i][j].getBall());
			    					if( ball != null && ball.isPosizionato()){ 
			    				
			    						if(!t_fermi.contains(ball))
			    							t_fermi.add(ball);
			    						
			    						trigger(m.map[i][j], m, t, t_fermi);
			    					}
	    					}
	    			}
    		}	
}

    private void trigger(Hexagon h, Matrix m, ArrayList<Ball> t,  ArrayList<Ball> t_fermi){
			
		LinkedList<Hexagon> daVisitare = new LinkedList<Hexagon>();
		final LinkedList<Hexagon> visitati = new LinkedList<Hexagon>();
		daVisitare.add(h); // aggiungo nodo di partenza
		
		while(!daVisitare.isEmpty()){
				ArrayList<Hexagon> list =  new ArrayList<Hexagon>();    
				list = adjacent(list, daVisitare.getFirst(), m); // estraggo gli adjacenti della pallina
				if(list.isEmpty()) { return; }
    			for(int i=0;i<list.size();i++){																																	
							if(!presente( list.get(i), visitati) && !presente( list.get(i), daVisitare)){
    								if(((Ball)list.get(i).getBall()).getBallColor() == ((Ball)daVisitare.getFirst() .getBall()).getBallColor() ){
    										daVisitare.add(list.get(i));	
    								}
    					}
    			}	
    			visitati.add(daVisitare.pop());
		}

		if((allPosizionato(t) || flag) && visitati.size()>=4){
				
			for(int i=0;i<visitati.size();i++){   
				Ball b = m.map[visitati.get(i).getA()][visitati.get(i).getB()].getBall();
				t_fermi.remove(b);
    			m.map[visitati.get(i).getA()][visitati.get(i).getB()].setBall(null);
    			m.map[visitati.get(i).getA()][visitati.get(i).getB()].setContain(false); 
    			SoundsProvider.playExplosion();
			}	
		}
}
    
    public void verify(ArrayList<Ball> t, Matrix m){
	    		
    		flag=false;
	    	for(int i = ROW-1; i > 0; i--){
					for(int j = 0; j<COLUMN; j++){
							if( (i % 2 != 0 && j<COLUMN-1) ||  (i %2 ==0) ){	
			    				//	 DOPO L' INNESCO CONTROLLO SE QUALCHE PALLIaNA DEVE CONTIINUARE A CADERE
				    					if(m.map[i][j].isContain() && !t.contains(((Ball)m.map[i][j].getBall())) 
				    							||allPosizionato(t) && m.map[i][j].isContain()){
				    				
					    						if( freeSpaceAfterTriggering( m.map[i][j].getBall(), m)){
					    								update1(((Ball)m.map[i][j].getBall()), t, m);
					    								flag=true;
					    						}
				    					}
							}
					}
	    	}
	    	
	    	if(!flag && allPosizionato(t)){
	    		allCrashed=true;
	    	}
    }
    
    private boolean freeSpaceAfterTriggering(Ball b, Matrix m){	
    	
		int X = b.getX();
		int Y = b.getY();

		//DISPARI PARI
		if((X % 2 != 0 && Y % 2 == 0)) {
	
			if(X + 1 <  m.getRow() ) {
				if(check(m.map[X+1][Y]))
					return true;
			}
			if(X + 1 <  m.getRow() && Y+1 < m.getColumn()) {
				if(check(m.map[X+1][Y+1]))
						return true;
			}
		}
		//PARI PARI 
		else if( X % 2 == 0 && Y % 2 == 0) {
			
			if(X + 1 <  m.getRow() && Y-1 >= 0) {
				if(check(m.map[X+1][Y-1]))
				return true;
			}
			if(X + 1 <  m.getRow()) {
				if(check(m.map[X+1][Y]))
					return true;
			}
		}	
		//PARI DISPARI
		else if((X % 2 == 0 && Y % 2 != 0)) {
			
			if(X + 1 < m.getRow() && Y-1 >= 0 ){
				if(check(m.map[X+1][Y-1]))
					return true;
			}
			if(X + 1 <  m.getRow() && Y < COLUMN-1) {
				if(check(m.map[X+1][Y]))
					return true;
			}
		}
		//DISPARI DISPARI
		else if( X % 2 != 0 && Y % 2 != 0) {

			if(X + 1 <  m.getRow() && Y+1 < m.getColumn()) {
				if(check(m.map[X+1][Y+1]))
				return true;
			}
			if(X + 1 <  m.getRow()) {
				if(check(m.map[X+1][Y]))
				return true;
			}
		}	
		return false;
    
    }
     		
    private boolean presente(Hexagon h, LinkedList<Hexagon> l){

    	for(int i=0;i<l.size();i++)
    		if(l.get(i) == h)
    			return true;
    	return false;
    }
    
    public ArrayList<Hexagon> adjacent(ArrayList<Hexagon> l, Hexagon h, Matrix m){
    		    	
    		int x = h.getA();  
    		int y = h.getB();
    		    		
    		int infX=0, infY=0, supX = x, supY=COLUMN-2;
    		if(x-1 >= 0)
    			infX = x-1;
    		if(y -1 >= 0)
    			infY = y-1;
    		if(x+1 < ROW)
    			supX = x+1;
    		
    		if((x % 2 == 0 && y+1 < COLUMN) || (x % 2 != 0 && y+1 < COLUMN-1))
    			supY = y+1;	
    		for(int i=infX; i<=supX;i++){
	    			for(int j=infY; j<=supY;j++){
	        				if( (( x % 2 == 0 && ( (i != x && j <= y) || (i == x && Math.abs(j-y) == 1)) )  || 
	        				( x % 2 != 0  && ( ( i != x && j >= y) || (i == x && Math.abs(j-y) == 1)) )     ) &&
	        						m.map[i][j].isContain() && ((Ball)m.map[i][j].getBall()).isPosizionato() ){
	        					l.add( m.map[i][j] );
	        				}
	        		}
    		}
    		return l;
    }
     
    // ---------- UPDATE BALLS ---------------------
    
    public void updateTris(String s) {
//    	if(!tris.isEmpty()){
	    	if(tris.get(2).getX() < tris.get(0).getX())
	    		normal(s);
	    	else
	    		inverted(s);    
//    	}
   }
    
    private void normal(String s){
    	
    	if(s.equals("godown")){
        	
	    	if(getTris().get(0).getDirection() == Direction.LEFT){
	    		update1(tris.get(0),tris, matrix);
	    		update1(tris.get(2),tris, matrix);
	    		update1(tris.get(1),tris, matrix);
	    
	    	}
	    	else if(getTris().get(1).getDirection() == Direction.RIGHT){
		        	update1( tris.get(1),tris, matrix);	
		        	update1( tris.get(2),tris, matrix);
		    		update1(tris.get(0),tris, matrix);
	
	        }
	    	else {  // DOWN 
	    		
	    		for(int i=0;i<getTris().size();i++)  
	        		update1( tris.get(i),tris, matrix);
	    	}
	    	
     }else{
    	 
    	 	if(getTris().get(0).getDirection() == Direction.LEFT){
        		update2(tris.get(0),tris, matrix);    		
        		update2(tris.get(1),tris, matrix);
        		update2(tris.get(2),tris, matrix);
        		
        	}
        	else if(getTris().get(1).getDirection() == Direction.RIGHT){
        		
            	update2( tris.get(1),tris, matrix);    		
        		update2( tris.get(0),tris, matrix);
            	update2( tris.get(2),tris, matrix);
        	}
     }
    	
    }
    
    private void inverted(String s){
    	
    	if(s.equals("godown")){
        	
	    	if(getTris().get(0).getDirection() == Direction.LEFT || getTris().get(0).getDirection() == Direction.DOWN){
	    		update1(tris.get(2), tris, matrix);
	    		update1(tris.get(0), tris, matrix);
	    		update1(tris.get(1), tris, matrix);
	    
	    	}
	    	else if(getTris().get(1).getDirection() == Direction.RIGHT){
		        	update1( tris.get(2),tris, matrix);	
		        	update1( tris.get(1),tris, matrix);
		    		update1( tris.get(0),tris, matrix);
	        }
	    	
     }else{
    	 
    	 	if(getTris().get(0).getDirection() == Direction.LEFT){
        		update2(tris.get(2),tris, matrix);    		
        		update2(tris.get(0),tris, matrix);
        		update2(tris.get(1),tris, matrix);
        		
        	}
        	else if(getTris().get(1).getDirection() == Direction.RIGHT){
        		
            	update2( tris.get(2),tris, matrix);    		
        		update2( tris.get(1),tris, matrix);
            	update2( tris.get(0),tris, matrix);
        	}
     }
    	
    }
    
    public void update1(Ball b, ArrayList<Ball> t, Matrix m) {
    	checkBorder(b, t, m);
    	
		//DOWN
				if(b.isBorder() || b.getDirection() == Direction.DOWN || oneBallDeactivated(t)) {
								
						if( b.getX() + 2 < m.getRow()){
								if(m.map[b.getX()+2][b.getY()].getBall() == null){
										m.map[b.getX()][b.getY()].setBall(null);
										m.map[b.getX()][b.getY()].setContain(false);
										b.setX(b.getX()+2);
								} 
								else  if(b.getX() + 1 <  m.getRow()){
									if(!canDropDown(b, t, m)){
										b.setPosizionato(true);	
									}
								}
								else
									b.setPosizionato(true);
								
						}
						else if(b.getX() + 1 <  m.getRow()){
									if(!canDropDown(b, t, m)){
										b.setPosizionato(true);
									}
						}
						else
							b.setPosizionato(true);
				}
				else{
				
				//LEFT
						if(!b.isBorder() && b.getDirection() == Direction.LEFT ) {
								if (b.getX() + 1 < m.getRow()) 
									if(!canDropDown(b,t, m)){
										b.setPosizionato(true);
									}
						}
				//RIGHT
						else if(!b.isBorder() && b.getDirection() == Direction.RIGHT) {
								//DISPARI
								if (b.getX() % 2 != 0 && b.getX() + 1 < m.getRow() &&
										b.getY() + 1 < m.getColumn()){ 
												if(!canDropDown(b, t, m)){
													b.setPosizionato(true);
												}
								}
								//PARI
								else if (b.getX() % 2 == 0 && b.getX() + 1 < m.getRow() && 
										b.getY() < m.getColumn()){
												if(!canDropDown(b, t, m)){
													b.setPosizionato(true);
												}
								}
						
						 }
			 }

			m.map[b.getX()][b.getY()].setBall(b);
			m.map[b.getX()][b.getY()].setContain(true);
			b.setDirection(Direction.DOWN);	
	}
    
  	public void update2(Ball b, ArrayList<Ball> t, Matrix m) {	
			checkBorder(b, t, m);
		
			if( !oneBallDeactivated(tris)){
				//LEFT
					if(!b.isBorder()&& b.getDirection() == Direction.LEFT) {
							if (b.getY() - 1 >= 0 && 
									!(b.getId().equals("C") && b.getY()-1 == 0 && b.getX() % 2 == 0 )) {
									if(matrix.map[b.getX()][b.getY()-1].getBall() == null){
										matrix.map[b.getX()][b.getY()].setBall(null);
										matrix.map[b.getX()][b.getY()].setContain(false);
										b.setY(b.getY()-1);
									}
							}
					}
					
				//RIGHT
					else if(!b.isBorder() && b.getDirection() == Direction.RIGHT) {
							
							//DISPARI
							if (b.getX() % 2 != 0 && b.getY() + 1 < matrix.getColumn()-1 && 
									!(b.getId().equals("C") && b.getY()+1 > COLUMN-1 )){
										if(matrix.map[b.getX()][b.getY()+1].getBall() == null){
											matrix.map[b.getX()][b.getY()].setBall(null);
											matrix.map[b.getX()][b.getY()].setContain(false);
											b.setY(b.getY()+1);
										}
								}
							
							//PARI
							else if (b.getX() % 2 == 0 && b.getY() + 1 < matrix.getColumn() &&
									!(b.getId().equals("C") && b.getY()+1 >= COLUMN-1 )) {
										if(matrix.map[b.getX()][b.getY()+1].getBall() == null){
											matrix.map[b.getX()][b.getY()].setBall(null);
											matrix.map[b.getX()][b.getY()].setContain(false);
											b.setY(b.getY()+1);
										}
							}
					}
			
				matrix.map[b.getX()][b.getY()].setBall(b);
				matrix.map[b.getX()][b.getY()].setContain(true);	
			}
			else
				changeDir(Direction.DOWN);
	}
	
  	//-------------------DLV---------------------------
  	  	
  	public LinkedList<HexagonDLV> createMatrixAndHexagonForDLV(){
  		
  		//contiene tutte le combinazioni
  		LinkedList<HexagonDLV> comb = new LinkedList<HexagonDLV>();
  		
  		Ball b1 = tris.get(0);
		Ball b2 = tris.get(1);
		Ball b3 = tris.get(2);
		
			int x=2,y=0;
			int limit = 9;
			while(y < limit){
				newTris(x, y, x, y+1, x-1, y, b1, b2, b3, comb);
				if(!allSameColor())
					newTris(x, y, x, y+1, x-1, y, b3, b1, b2, comb);
				if(!allSameColor())
					newTris(x, y, x, y+1, x-1, y, b2, b3, b1, comb);
				y++;
			}
			
			x=1;y=0;
			limit=8;
			while(y < limit){
				newTris(x, y, x, y+1, x-1, y+1, b1, b2, b3, comb);
				if(!allSameColor())
					newTris(x, y, x, y+1, x-1, y+1, b3, b1, b2, comb);
				if(!allSameColor())
					newTris(x, y, x, y+1, x-1, y+1, b2, b3, b1, comb);
				y++;
			
			}
			
//			//TODO NON SERVE
//			x=1;y=0;
//			limit=8;
//			while(y < limit){
//				newTris(x, y, x, y+1, x+1, y+1, b1, b2, b3, comb);
//			if(!allSameColor())
//				newTris(x, y, x, y+1, x+1, y+1, b3, b1, b2, comb);
//				if(!allSameColor())
//				newTris(x, y, x, y+1, x+1, y+1, b2, b3, b1, comb);
//				y++;
//			}
//			
//			//TODO NON SERVE
//			x=0;y=0;
//			limit=9;
//			while(y < limit){
//				newTris(x, y, x, y+1, x+1, y, b1, b2, b3, comb);
//				if(!allSameColor())
//					newTris(x, y, x, y+1, x+1, y, b3, b1, b2, comb);
//				if(!allSameColor())
//					newTris(x, y, x, y+1, x+1, y, b2, b3, b1, comb);
//				y++;
//			}
//						
//  		    System.out.println(">>>>>>>>>>> comb.size >>>>>>>>> " + comb.size());
  		return comb;
  	}

  	private void newTris(int x1, int y1, int x2, int y2, int x3, int y3, Ball b1, Ball b2, Ball b3, LinkedList<HexagonDLV> comb){

  		//creo tris temporaneo
  			ArrayList<Ball> tmpTris = new ArrayList<Ball>();

  			tmpTris.add( newBall(b1,x1,y1) );
			tmpTris.add( newBall(b2,x2,y2) );
			tmpTris.add( newBall(b3,x3,y3) );
						
			newMatrix(comb, tmpTris);
			
			tmpTris.clear();
  	}
  
  	private void newMatrix(LinkedList<HexagonDLV> comb, ArrayList<Ball> tmpTris){
  		
			Matrix DLVMatrix = new Matrix(ROW, COLUMN, W, H);
			ArrayList<Ball> t_fermiDLV = new ArrayList<Ball>();
			boolean f = false;
			
			//copio matrice corrente in DLVMatrix  
	        int cols = COLUMN;
	        for (int r = 0; r < ROW; r++) {
	            for (int c = 0; c < cols; c++) {
	            	if(matrix.map[r][c].getBall() != null && matrix.map[r][c].getBall().isPosizionato()){
	            		DLVMatrix.map[r][c].setBall(newBall(matrix.map[r][c].getBall(), r, c));
	            		DLVMatrix.map[r][c].setContain(true);
	            	}
	            }
	            cols = (cols == COLUMN) ? cols-1 : COLUMN;
	        }
	        
		 //aggiungo tris temporaneo nella matrice
			for(Ball b : tmpTris){
				DLVMatrix.map[b.getX()][b.getY()].setBall(b);
				DLVMatrix.map[b.getX()][b.getY()].setContain(true);
			}
			
			while(!allPosizionato(tmpTris) || !allCrashed){
				impatto(DLVMatrix, tmpTris);
					for(int i=0;i<tmpTris.size();i++){
						if(!tmpTris.get(i).isPosizionato())
							update1( tmpTris.get(i), tmpTris, DLVMatrix );
					}
					
			 	crash(DLVMatrix, tmpTris, t_fermiDLV);
		   	   	verify(tmpTris, DLVMatrix);	
			}
			allCrashed=false;
	
			//Conversione oggetto Hexagon in HexagonDLV
			convert(comb, DLVMatrix, all_matrix.size());
			
			all_matrix.add(DLVMatrix);
	
//			System.out.println("------------- Matrice "+ (all_matrix.size()-1));
//			DLVMatrix.stampa();
			
//			System.out.println("IMPATTO");
//			for(Ball b: DLVMatrix.trisImpatto){
//				System.out.println(b.getX() + ":" + b.getY() + " -> "+b.getBallColor());
//			}
			
//			System.out.println("--------");
  	}
  		
  	private Ball newBall(Ball b, int xPos, int yPos){
  		
		Ball ball = new Ball(xPos,yPos,b.getId());
//		ball.setMatrix(newMatrix);
		ball.setBallColor(b.getBallColor());
		ball.setPosizionato(b.isPosizionato());
		ball.setUrtato(b.isUrtato());
		ball.setBorder(b.isBorder());
		ball.setDeactivate(b.isDeactivate());		
		ball.setDirection(b.getDirection());
		
		return ball;
	}

	public void convert(LinkedList<HexagonDLV> list, Matrix m, int cont){
  		//Conversione da Hexagon in HexagonDLV
		  	for(Hexagon h:m.hexagon){
  		  		int x = h.getA();
  				int y = h.getB();
  				int c = 0;	
  				HexagonDLV tmp =  new HexagonDLV( x, y, c, cont);
  				
  				if(h.getBall() != null){
  				 	c = h.getBall().colorInteger(); // è solo il colore
  				 	tmp.setColor(c);
  				 }
  				
  				if(h.isContain()){
  					m.pallineRimaste.add(tmp);
  					list.add(tmp);
  				}
  	  		}
  	}
	
	public void convertIntoJava(LinkedList<HexagonDLV> list, Matrix m, int cont){
  		//Conversione da Hexagon in HexagonDLV
		  	for(Hexagon h:m.hexagon){
  		  		int x = h.getA();
  				int y = h.getB();
  				int c = 0;	
  				HexagonDLV tmp =  new HexagonDLV( x, y, c, cont);
  				
  				if(h.getBall() != null){
  				 	c = h.getBall().colorInteger(); // è solo il colore
  				 	tmp.setColor(c);
//  				 	System.out.println(tmp.getX() + ":" + tmp.getY() + " color="+ tmp.getColor()+ " id=" + tmp.getId());
  				 }
  				
  				if(h.isContain())
  					m.pallineRimaste.add(tmp);
  				list.add(tmp);
  	  		}
  	}
  	
	public boolean halfFullMatrix(){
		int tmp = ROW;
		for(Hexagon h : matrix.hexagon){
			if(h.isContain() && h.getA() < tmp && !tris.contains(h.getBall()))
				tmp = h.getA();
		}
		return (tmp > ROW / 2);
	}
	
	private boolean allSameColor(){
		return (tris.get(0).getBallColor() == tris.get(1).getBallColor() && 
				tris.get(1).getBallColor() == tris.get(2).getBallColor());
	}
  	
	//-------------------------------------------------
  	
  	private void impatto(Matrix m, ArrayList<Ball> t){
  		
  		if(trisAncoraUnito(t) && celleLibere(t, m)){
	  		if(!m.trisImpatto.isEmpty())
	  			m.trisImpatto.clear();
	  		
	  		for(Ball b: t){
	  			m.trisImpatto.add(newBall(b, b.getX(), b.getY()));
	  		}	  		
  		}
  	}
  	
  	private boolean celleLibere(ArrayList<Ball> t, Matrix m){
  		
  		Ball b1 = t.get(0);
  		Ball b2 = t.get(1);
  		Ball b3 = t.get(2);
  		
  		boolean libero = false;
  		
  		if(b3.getX() < b1.getX()){
  			
  			if(b1.getX() + 1 < ROW){
	  			if(b1.getX()%2!=0){
					
	  				if( !m.map[b1.getX()+1][b1.getY()].isContain() &&
						!m.map[b1.getX()+1][b1.getY()+1].isContain() &&
						!m.map[b2.getX()+1][b2.getY()+1].isContain())
							libero = true;
					
	  			}else{
	  				
	  				if( (b1.getY()-1 >=0 && !m.map[b1.getX()+1][b1.getY()-1].isContain() && !m.map[b1.getX()+1][b1.getY()].isContain() &&
	  						b2.getY()< COLUMN-1 && !m.map[b2.getX()+1][b2.getY()].isContain()) || 
	  						(!m.map[b1.getX()+1][b1.getY()].isContain() && b2.getY()< COLUMN-1 && !m.map[b2.getX()+1][b2.getY()].isContain()))
	  							libero = true;
	  			}
  			}
  		}
    	else{
    		
    		if(b3.getX() + 1 < ROW){
    			if(b3.getX()%2 != 0){
					
    				if(!m.map[b3.getX()+1][b3.getY()].isContain() &&
    					!m.map[b3.getX()+1][b3.getY()+1].isContain())
    							libero = true;
				}else{
					if( (b3.getY()-1 >= 0 && !m.map[b3.getX()+1][b3.getY()-1].isContain() && !m.map[b3.getX()+1][b3.getY()].isContain()) ||
							!m.map[b3.getX()+1][b3.getY()].isContain())
    							libero = true;
				}
    		}
		}
   
  		return libero;
  	}
  	
  	private boolean trisAncoraUnito(ArrayList<Ball> t){
  		
  		Ball b1 = t.get(0);
  		Ball b2 = t.get(1);
  		Ball b3 = t.get(2);
  		
  		boolean unito = false;
  		
  		if(b3.getX() < b1.getX()){
  			
  				if(b1.getX() % 2 == 0){
  					
  					if(b2.getX() == b1.getX() && b2.getY() == b1.getY()+1 &&
  						b3.getX() == b1.getX()-1 && b3.getY() == b1.getY()){
  							unito = true;
  					}
  				}else{
  					if(b2.getX() == b1.getX() && b2.getY() == b1.getY()+1 &&
  	  						b3.getX() == b1.getX()-1 && b3.getY() == b1.getY()+1)
  	  						unito = true;
  				}
  		}
    	else{
    		
    			if(b1.getX()%2!=0){
					
					if(b2.getX() == b1.getX() && b2.getY() == b1.getY()+1 &&
						b3.getX() == b1.getX()+1 && b3.getY() == b1.getY()+1)
							unito = true;
				}else{
					if(b2.getX() == b1.getX() && b2.getY() == b1.getY()+1 &&
	  						b3.getX() == b1.getX()+1 && b3.getY() == b1.getY())
	  						unito = true;
				}
		}
    		
  		return unito;
  	}
  	
	public void changeDir(Direction d){
		for(int i=0;i<tris.size();i++)
			tris.get(i).setDirection(d);
	}
	
	private void disableControll(ArrayList<Ball> t){
		for(int i=0;i<t.size();i++)
			t.get(i).deactivate();
	}
	
	public boolean oneBallDeactivated(ArrayList<Ball> t){
		for(int i=0;i<t.size();i++)
			if(t.get(i).isDeactivated())
				return true;
		return false;
	}

	public boolean allPosizionato(ArrayList<Ball> t){
		for(int i=0;i<t.size();i++)
			if(!t.get(i).isPosizionato())
				return false;
		return true;
	}
	    
	private void checkBorder(Ball b, ArrayList<Ball> t, Matrix m){
		if ((b.getY()+1 >= m.getColumn()  && b.getDirection() == Direction.RIGHT)  ||
			 (b.getY() - 1 < 0  && b.getDirection() == Direction.LEFT && b.getX() % 2 == 0)) {

			t.get(0).setBorder(true);
			t.get(1).setBorder(true);
			t.get(2).setBorder(true);
		}
	}
	
	private boolean canRotate(Ball a, Ball b, Ball c, KeyEvent e){
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			if(tris.get(2).getX() < tris.get(0).getX()) {
				
				if(a.getX()+1 < ROW && bothEmpty(a))
					return true;
			
			}else {
				if(c.getX()+1 < ROW && bothEmpty(c))
					return true;
			}
			
		}else {
			
			if(tris.get(2).getX() < tris.get(0).getX()) {
				if(b.getX()+1 < ROW && bothEmpty(b))
					return true;
			}else {
				if(c.getX()+1 < ROW && bothEmpty(c))
					return true;
			}
			
		}

			return false;
	}
	
	private boolean bothEmpty(Ball b) {
		int X = b.getX();
		int Y = b.getY();
		Direction d = b.getDirection();
		
		boolean urtato = false;
	
		//DISPARI PARI
		if((X % 2 != 0 && Y % 2 == 0)) {
	
			if(X + 1 <  matrix.getRow() && (d == Direction.LEFT || d == Direction.DOWN)  ) {
				if(!(check(matrix.map[X+1][Y])))
					urtato = true;
			}
			if(X + 1 <  matrix.getRow() && Y+1 < matrix.getColumn() && (d == Direction.RIGHT || d == Direction.DOWN)) {
				if(!(check(matrix.map[X+1][Y+1])))
					urtato = true;
			}
		}
			
		//PARI PARI 
		else if( X % 2 == 0 && Y % 2 == 0) {
			
			if(X + 1 <  matrix.getRow() && Y-1 >= 0 && (d == Direction.LEFT || d == Direction.DOWN)) {
				if(!(check(matrix.map[X+1][Y-1])))
					urtato = true;
			}
			if(X + 1 <  matrix.getRow() && (d == Direction.RIGHT || d == Direction.DOWN)) {
				if(!(check(matrix.map[X+1][Y])))
					urtato = true;
			}
		}
		
		//PARI DISPARI
		else if((X % 2 == 0 && Y % 2 != 0)) {
			
			if(X + 1 < matrix.getRow() && Y-1 >= 0&& (d == Direction.LEFT || d == Direction.DOWN) ){
				if(!(check(matrix.map[X+1][Y-1])))
					urtato = true;
			}
			
			if(X + 1 <  matrix.getRow() && (d == Direction.RIGHT || d == Direction.DOWN)) {
				if(!(matrix.map[X+1][Y] != null &&  check(matrix.map[X+1][Y])))
					urtato = true;
			}
		}
			
		//DISPARI DISPARI
		else if( X % 2 != 0 && Y % 2 != 0) {

			if(X + 1 <  matrix.getRow() && Y+1 < matrix.getColumn() && (d == Direction.RIGHT || d == Direction.DOWN)) {
				if(!(check(matrix.map[X+1][Y+1])))
					urtato = true;
			}
			if(X + 1 <  matrix.getRow() && (d == Direction.LEFT || d == Direction.DOWN)) {
				if(!(check(matrix.map[X+1][Y])))
					urtato = true;
			}
		}
		if(urtato)
			return false;
		return true;
	}
	
	private boolean canDropDown(Ball b, ArrayList<Ball> t, Matrix m){
		Hexagon h = canDrop(b, t, m);
		if(h != null){
			m.map[b.getX()][b.getY()].setBall(null);
			m.map[b.getX()][b.getY()].setContain(false);
			b.setX(h.getA());
			b.setY(h.getB());
			return true;
		}
		return false;
	}
	
	private Hexagon canDrop(Ball b, ArrayList<Ball> t, Matrix m) {
		
		int X = b.getX();
		int Y = b.getY();
		Direction d = b.getDirection();
		
		boolean urtato = false;
		Hexagon tmp = null;
	
		//DISPARI PARI
		if((X % 2 != 0 && Y % 2 == 0)) {
	
			if(X + 1 <  m.getRow() && (d == Direction.LEFT || d == Direction.DOWN)  ) {
				if(check(m.map[X+1][Y]) && tmp==null)
					tmp = m.map[X+1][Y];
				else
					urtato = true;
			}
			if(X + 1 <  m.getRow() && Y+1 < m.getColumn() && (d == Direction.RIGHT || d == Direction.DOWN)) {
				if(check(m.map[X+1][Y+1]) && tmp==null)
					tmp = m.map[X+1][Y+1];
				else
					urtato = true;
			}
		}
				
		//PARI PARI 
		else if( X % 2 == 0 && Y % 2 == 0) {
			
			if(X + 1 <  m.getRow() && Y-1 >= 0 && (d == Direction.LEFT || d == Direction.DOWN)) {
				if(check(m.map[X+1][Y-1])  && tmp==null)
					tmp = m.map[X+1][Y-1];
				else
					urtato = true;
			}
			if(X + 1 <  m.getRow() && (d == Direction.RIGHT || d == Direction.DOWN)) {
				if(check(m.map[X+1][Y])  && tmp==null)
					tmp = m.map[X+1][Y];
				else
					urtato = true;
			}
		}
		
		//PARI DISPARI
		else if((X % 2 == 0 && Y % 2 != 0)) {
			
			if(X + 1 < m.getRow() && Y-1 >= 0&& (d == Direction.LEFT || d == Direction.DOWN) ){
				if(check(m.map[X+1][Y-1]) && tmp==null)
					tmp =  m.map[X+1][Y-1];
				else 
					urtato = true;
			}
			
			if(X + 1 <  m.getRow() && (d == Direction.RIGHT || d == Direction.DOWN)) {
				if(m.map[X+1][Y] != null &&  check(m.map[X+1][Y])  && tmp==null)
					tmp = m.map[X+1][Y];
				else
					urtato = true;
			}
		}
		
		//DISPARI DISPARI
		else if( X % 2 != 0 && Y % 2 != 0) {

			if(X + 1 <  m.getRow() && Y+1 < m.getColumn() && (d == Direction.RIGHT || d == Direction.DOWN)) {
				if(check(m.map[X+1][Y+1])  && tmp==null)
					tmp =  m.map[X+1][Y+1];
				else
					urtato = true;
			}
			if(X + 1 <  m.getRow() && (d == Direction.LEFT || d == Direction.DOWN)) {
				if(check(m.map[X+1][Y])  && tmp==null)
					tmp = m.map[X+1][Y];
				else
					urtato = true;
			}
		}
		if(uniti && urtato){
			uniti = false;
			SoundsProvider.playDrop();
		}
			
		if(urtato && t.contains(b)){
			disableControll(t);
		}
		return tmp;
	}
	
	private boolean check(Hexagon h) {
		
		if(h.getBall() == null)
			return true;
		return false;
	}

	public ArrayList<Ball> getTris() {
		return tris;
	}
	
	public void setTris(ArrayList<Ball> tris) {
		this.tris = tris;
	}

}

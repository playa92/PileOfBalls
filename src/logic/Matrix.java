 package logic;

import java.awt.Point;
import java.util.LinkedList;

public class Matrix {

	public Hexagon[][] map;
	public LinkedList<Hexagon> hexagon = new LinkedList<Hexagon>();
	public LinkedList<HexagonDLV> pallineRimaste = new LinkedList<HexagonDLV>();
	public LinkedList<Ball> trisImpatto = new LinkedList<Ball>();
	
	private int radius = 30;
	private int padding = 1;
	private int row, column, W, H;
	
	public Matrix(int row,int column, int W, int H){
		this.W=W;
		this.H=H;
		this.row=row;
		this.column=column;
		this.map=new Hexagon[row][column];
		
		createHexMatrix();
	}
	
	public void createHexMatrix(){
	       
	   double ang30 = Math.toRadians(30);
       double xOff = Math.cos(ang30) * (radius + padding);
       double yOff = Math.sin(ang30) * (radius + padding);
        
        int cols = column;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < cols; c++) {
                int x = (int) (W/2 + xOff * (c * 2 + 1 - cols));
                int y = (int) (H/2-15 + yOff * (r - row/2) * 3);
                map[r][c] = new Hexagon(new Point(x, y), radius, r , c/*, null*/);
                hexagon.add(map[r][c]);
            }
            cols = (cols == column) ? cols-1 : column;
        }
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void stampa(){
		
		int cols = column;
		for(int i=0;i<row; i++){
			for(int j=0; j<cols;j++){
				if(map[i][j].getBall() != null)
					System.out.print(" "+map[i][j].getBall().getBallColor()+" ");
				else
					System.out.print(" ... ");
			}
			System.out.println();
			cols = (cols == column) ? cols-1 : column;
		}
	}
	
    public void printIsContain(){

	        int cols = column;
	        for (int i = 0; i < row; i++) {
	            for (int j = 0; j < cols; j++) {
	               
	            	if(map[i][j].isContain()){
	            		System.out.print("true ");
	            	}
	            	else{
	            		System.out.print("false ");
	            	}
	            }
	            System.out.println();
	            cols = (cols == column) ? cols-1 : column;
	        }
	        System.out.println("------------------------------------");
	    }

}

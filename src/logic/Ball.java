package logic;

import java.util.Random;

public class Ball{
	
	private int x;
	private int y;
	private String id;
	protected Matrix matrix;
	private Direction direction;
	private BallColor color;
	private boolean posizionato;
	private boolean urtato;
	private boolean border;
	private boolean deactivate;

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Ball(int x, int y, String id) {
		this.x = x;
		this.y = y;
		this.id = id;
		randomColor();
	}

	public void init(Matrix m){
		this.direction = Direction.DOWN;
		matrix = m;
		deactivate = false;
		posizionato = false;
		matrix.map[getX()][getY()].setBall(this);
		matrix.map[getX()][getY()].setContain(true);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public int colorInteger() {
		
		switch(color){
			case RED:
				return 1;
			
			case YELLOW:
				return 2;
				
			case BLUE:
				return 3;
		
			case GREEN:
				return 4;
				
			case ORANGE:
				return 5;
				
			case MAGENTA:
				return 6;
				
			default:
				return 0;
		}
	}
	
	public void randomColor(){
		int rand = new Random().nextInt(6);
		switch(rand){
			case 0:
				color = BallColor.RED;
				break;
			case 1:
				color = BallColor.YELLOW;
				break;
			case 2:
				color = BallColor.BLUE;
				break;
			case 3:
				color = BallColor.GREEN;
				break;
			case 4:
				color = BallColor.ORANGE;
			case 5:
				color = BallColor.MAGENTA;
				break;
		}
	}

	public BallColor getBallColor() {
		return color;
	}

	public void setBallColor(BallColor color) {
		this.color = color;
	}

	public boolean isPosizionato() {
		return posizionato;
	}

	public void setPosizionato(boolean urtato) {
		this.posizionato = urtato;
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public String getId() {
		return id;
	}
	
	public void setDeactivate(boolean deactivate) {
		this.deactivate = deactivate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDeactivated() {
		return deactivate;
	}

	public void deactivate( ){
		this.deactivate = true;
	}

	public boolean isUrtato() {
		return urtato;
	}

	public void setUrtato(boolean urtato) {
		this.urtato = urtato;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public BallColor getColor() {
		return color;
	}

	public void setColor(BallColor color) {
		this.color = color;
	}

	public boolean isDeactivate() {
		return deactivate;
	}

}

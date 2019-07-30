package logic;

public class HexagonDLV {
	
	private int x;
	private int y;
	private int color;
	private int id;

	public HexagonDLV(){}
	
	public HexagonDLV(int x, int y, int color, int id) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return x+":"+y+" col="+color+" id="+id;
	}
}

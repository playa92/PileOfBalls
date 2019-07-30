package logic;

import java.awt.*;

@SuppressWarnings("serial")
public class Hexagon extends Polygon{

    public static final int SIDES = 6;

    int a;
    int b;
    private Point[] points;
    private Point center;
    private int radius;
    private int rotation;
    private boolean contain;
    private Ball ball;
    private boolean triggering;
    
    public Hexagon(Point center, int radius, int a, int b) {
   
    	points = new Point[SIDES];
    	rotation = 90;
        npoints = SIDES;
        xpoints = new int[SIDES];
        ypoints = new int[SIDES];
        ball=null;
		this.setA(a);
		this.setB(b);
        this.center = center;
        this.radius = radius;
        setContain(false);
        updatePoints();
        triggering = false;
    }

    @Override
    public String toString() {
    	return ".";
    }
    
    public Point getCenter() {
		return center;
	}

	public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;

        updatePoints();
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;

        updatePoints();
    }

    public void setCenter(Point center) {
        this.center = center;

        updatePoints();
    }

    public void setCenter(int x, int y) {
        setCenter(new Point(x, y));
    }

    private double findAngle(double fraction) {
        return fraction * Math.PI * 2 + Math.toRadians((rotation + 180) % 360);
    }

    private Point findPoint(double angle) {
        int x = (int) (center.x + Math.cos(angle) * radius);
        int y = (int) (center.y + Math.sin(angle) * radius);

        return new Point(x, y);
    }

    protected void updatePoints() {
        for (int p = 0; p < SIDES; p++) {
            double angle = findAngle((double) p / SIDES);
            Point point = findPoint(angle);
            xpoints[p] = point.x;
            ypoints[p] = point.y;
            points[p] = point;
        }
    }

    public void draw(Graphics2D g, int x, int y, int lineThickness, Color color, boolean filled) {
        // Store before changing.
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();

        g.setColor(color);
        g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

        if (filled)
            g.fillPolygon(xpoints, ypoints, npoints);
        else
            g.drawPolygon(xpoints, ypoints, npoints);

        // Set values to previous when done.
        g.setColor(tmpC);
        g.setStroke(tmpS);
    }

	public boolean isContain() {
		return contain;
	}

	public void setContain(boolean contain) {
		this.contain = contain;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public boolean isTriggering() {
		return triggering;
	}

	public void setTriggering(boolean triggering) {
		this.triggering = triggering;
	}
}
	
import processing.core.PApplet;
import processing.core.PImage;

public class Bird extends PApplet{
	private int x,y;
	private double v,g;
	
	//constructor
	public Bird() {
		this(0,0,0,0);
	}
	
	//constructor that takes coordinates, velocity, and gravity for the fields.
	public Bird(int xcor, int ycor, double velocity, double gravity) {
		x = xcor;
		y = ycor;	
		v = velocity;
		g = gravity;
	}

	//Getters and Setters for the bird.
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getV() {
		return v;
	}

	public void setV(double v) {
		this.v = v;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}
	
	
}

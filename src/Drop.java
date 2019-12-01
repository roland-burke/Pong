import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class Drop {
	public int xPos;
	public int yPos;
	public double width;
	public int height;
	public double defaultSpeed;
	public double speed;
	public double gravity;
	public Color color;
	
	public Drop(int xPos, int yPos, double width, int height, double speed, double gravity, int r, int g, int b) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.speed = defaultSpeed = speed;
		this.gravity = gravity;
		this.color = new Color(r, g, b);
	}
	
	public void move() {
		speed += gravity;
		this.yPos += speed;
		
		if(yPos > 1500) {
			speed = defaultSpeed;
			yPos = ThreadLocalRandom.current().nextInt(-300, -100); // -300 -100
		}
	}
}

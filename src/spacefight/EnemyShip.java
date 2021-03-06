package spacefight;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class EnemyShip {
	private Image enemyShip;
	private int x;
	private int y;
	public int speed;
	
	public EnemyShip() throws SlickException {
		enemyShip = new Image("res/ShipEnemy.png");
		randomPosition();
	}
	
	public void randomPosition() {
		Random random = new Random();
		int randomX = 51 + random.nextInt(700);
		x = randomX;
		y = 0;
		speed = 1;
	}
	
	public boolean outOfScreen() {
		if(y > 600) return true;
		else return false;
	}
	
	public void draw() {
	    enemyShip.draw(x, y);
	}
	
	public void update() {
		y = y + speed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}

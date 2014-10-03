import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class EnemyShip {
	private Image enemyShip;
	private int x;
	private int y;
	private int HP;
	public int laserLevel;
	public int laserShoot;
	
	public EnemyShip() throws SlickException {
		enemyShip = new Image("res/ship2.png");
		randomPosition();
	}
	
	public void randomPosition() {
		Random random = new Random();
		int randomX = 51 + random.nextInt(700);
		x = 420;
		y = 0;
	}
	
	public void draw() {
	    enemyShip.draw(x, y);
	}
	
	public void update() {
		y = y+10;
	}
}

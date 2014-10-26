package spacefight;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class PlayerShip {
	private Image playerShip;
	private int x;
	private int y;
	public int HP;
	public int laserLevel;
	public int laserShoot;
	
	public PlayerShip(int x, int y) throws SlickException {
		playerShip = new Image("res/ShipPlayer.png");
		this.x = x;
	    this.y = y;
	    HP = 3;
	    laserLevel = 1;
	    laserShoot = 0;
	}
	
	public void draw() {
		playerShip.draw(x, y);
	}

	public void moveLeft() {
		if(x > 0) this.x -= 5;
	}

	public void moveRight() {
		if(x < 740) this.x += 5;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getLaserLevel() {
		return laserLevel;
	}
	
	public int getLaserShoot() {
		return laserShoot;
	}
}
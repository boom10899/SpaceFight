package spacefight;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class PlayerLaser {
	private Image playerLaser;
	private int x;
	private int y;

	public PlayerLaser(int x, int y) throws SlickException {
		playerLaser = new Image("res/laser.png");
		this.x = x;
	    this.y = y;
	}

	public void draw() {
	    playerLaser.draw(x, y);
	}

	public void update() {
		y = y-10;
	}

	public boolean outOfScreen() {
		if(y < -50) return true;
		else return false;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void remove() {
		x = 800;
		y = 600;
	}
}

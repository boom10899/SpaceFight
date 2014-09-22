import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class PlayerShip {
	private Image playerShip;
	private int HP;
	private int x;
	private int y;
	
	public PlayerShip(int x, int y) throws SlickException {
		playerShip = new Image("res/ship.png");
		this.x = x;
	    this.y = y;
	}
	
	public void draw() {
		playerShip.draw(x, y);
	}

	public void moveLeft() {
		// TODO Auto-generated method stub
		this.x--;
	}

	public void moveRight() {
		// TODO Auto-generated method stub
		this.x++;
	}
}
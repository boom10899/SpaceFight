import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SpaceFight extends BasicGame{
	
	private PlayerShip player;
	private PlayerLaser laser;
	Boolean shoot;

	public SpaceFight(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		player.draw();
		if(shoot) {
			laser.draw();
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		player = new PlayerShip(420,500);
		shoot = false;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
	    updateShipMovement(input, delta);
	    if(shoot) {
	    	if(!laser.outOfScreen())
	    		updateLaserMovement();
	    	else {
	    		player.laserShoot--;
	    		shoot = false;
	    	}
	    }
	}

	private void updateShipMovement(Input input, int delta) throws SlickException {
		if (input.isKeyDown(Input.KEY_LEFT)) { 
	    	player.moveLeft();
	    }
	    if (input.isKeyDown(Input.KEY_RIGHT)) {
	    	player.moveRight();
	    }
	    if (input.isKeyDown(Input.KEY_SPACE)) {
	    	System.out.println("Shoot!");
	    	if(player.getLaserShoot() < player.getLaserLevel()) {
	    		player.laserShoot++;
	    		laser = new PlayerLaser(player.getX(), player.getY());
	    		shoot = true;
	    	}
	    }
	}
	
	private void updateLaserMovement() {
		laser.update();
	}

	public static void main(String[] args) {
		try {
			SpaceFight game = new SpaceFight("Space Fight");
			AppGameContainer container = new AppGameContainer(game);
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}

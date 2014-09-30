import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SpaceFight extends BasicGame{
	
	private PlayerShip player;

	public SpaceFight(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		player.draw();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		player = new PlayerShip(420,500);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
	    updateShipMovement(input, delta);
	}

	private void updateShipMovement(Input input, int delta) {
		if (input.isKeyDown(Input.KEY_LEFT)) { 
	    	player.moveLeft();
	    }
	    if (input.isKeyDown(Input.KEY_RIGHT)) {
	    	player.moveRight();
	    }
	    if (input.isKeyDown(Input.KEY_SPACE)) {
	    	System.out.println("Shoot!");
	    }
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

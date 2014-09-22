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
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		player.draw();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		player = new PlayerShip(420,500);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = container.getInput();
	    updateShipMovement(input, delta);
	}

	private void updateShipMovement(Input input, int delta) {
		// TODO Auto-generated method stub
		if (input.isKeyDown(Input.KEY_LEFT)) { 
	    	player.moveLeft();
	    }
	    if (input.isKeyDown(Input.KEY_RIGHT)) {
	    	player.moveRight();
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

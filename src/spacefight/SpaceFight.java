package spacefight;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SpaceFight extends BasicGame{
	
	private PlayerShip player;
	private PlayerLaser laser;
	private EnemyShip enemy;
	Boolean shoot;
	int score;
	int gameLevel;
	Boolean isGameOver;

	public SpaceFight(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		player.draw();
		enemy.draw();
		if(shoot) {
			laser.draw();
		}
		g.drawString("Score : " + score, 700, 0);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		player = new PlayerShip(420,500);
		enemy = new EnemyShip();
		shoot = false;
		score = 0;
		gameLevel = 1;
		isGameOver = false;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
	    updateShipMovement(input, delta);
		updateLaserMovement();
		updateEnemyMovement();
		if(shoot) checkCollision();
	}

	private void checkCollision() {
		int enemyX = enemy.getX();
		int enemyY = enemy.getY();
		int laserX = laser.getX();
		int laserY = laser.getY();
		
//		System.out.println("Enemy " + enemyX + " " + enemyY + " " + "Laser " + laserX + " " + laserY);
		
		if(laserY <= enemyY && laserY+50 >= enemyY) {
//			System.out.println("CollisionY");
			if(laserX >= enemyX && laserX-60 <= enemyX) {	
//				System.out.println("Collision");
				enemy.randomPosition();
				laser.remove();
				player.laserShoot--;
	    		shoot = false;
				score++;
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
	    	if(player.getLaserShoot() < player.getLaserLevel()) {
	    		player.laserShoot++;
	    		laser = new PlayerLaser(player.getX()+25, player.getY());
	    		shoot = true;
	    	}
	    }
	}
	
	private void updateLaserMovement() {
		if(shoot) {
	    	if(!laser.outOfScreen())
	    		laser.update();
	    	else {
	    		player.laserShoot--;
	    		shoot = false;
	    	}
	    }
	}
	
	private void updateEnemyMovement() {
		if(!enemy.outOfScreen())
			enemy.update();
		else {
			enemy.randomPosition();
		}
	}

	public static void main(String[] args) {
		try {
			SpaceFight game = new SpaceFight("Space Fight");
			AppGameContainer container = new AppGameContainer(game);
			container.setDisplayMode(800, 600, false);
			container.setMinimumLogicUpdateInterval(1000 / 60);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}

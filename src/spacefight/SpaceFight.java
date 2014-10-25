package spacefight;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SpaceFight extends BasicGame{
	
	private PlayerShip player;
	private PlayerLaser[] laser;
	private EnemyShip[] enemy;
	int[] shoot;
	int score;
	int gameLevel;
	int enemyCount;
	int newEnemyCount;
	Boolean isGameOver;

	public SpaceFight(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		player.draw();
		for(int i = 0; i < enemyCount; i++) {
			enemy[i].draw();
		}
		for(int i = 0; i < player.getLaserLevel(); i++) {
			if(shoot[i] == 1) {
				laser[i].draw();
			}
		}
		g.drawString("Level : " + gameLevel, 550, 10);
		g.drawString("Score : " + score, 700, 10);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		score = 0;
		isGameOver = false;
		gameLevel = 1;
		
		player = new PlayerShip(420,500);
		
		enemy = new EnemyShip[50];
		enemyCount = gameLevel/5 + 1;
		for(int i = 0; i < enemyCount; i++) {
			enemy[i] = new EnemyShip();
		}
		
		laser = new PlayerLaser[10];
		shoot = new int[10];
		for(int i = 0; i < player.getLaserLevel(); i++) {
			shoot[i] = 0;
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
	    updateShipMovement(input, delta);
		updateLaserMovement();
		for(int i = 0; i < enemyCount; i++) {
			updateEnemyMovement(i);
		}
		for(int i = 0; i < player.getLaserLevel(); i++) {
			for(int j = 0; j < enemyCount; j++) {
				if(shoot[i] == 1) {
					checkCollision(i, j);
				}
			}
		}
	}

	private void checkCollision(int i, int j) {
		int enemyX = enemy[j].getX();
		int enemyY = enemy[j].getY();
		int laserX = laser[i].getX();
		int laserY = laser[i].getY();
		
//		System.out.println("Enemy " + enemyX + " " + enemyY + " " + "Laser " + laserX + " " + laserY);
		
		if(laserY <= enemyY && laserY+50 >= enemyY) {
//			System.out.println("CollisionY");
			if(laserX >= enemyX && laserX-60 <= enemyX) {	
//				System.out.println("Collision");
				enemy[j].randomPosition();
				laser[i].remove();
	    		shoot[i] = 0;
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
	    	System.out.println("Press");
	    	if(player.getLaserShoot() < player.getLaserLevel()) {
	    		for(int i = 0; i < player.getLaserLevel(); i++) {
	    			if(shoot[i] == 0) {
	    				laser[i] = new PlayerLaser(player.getX()+25, player.getY());
	    				shoot[i] = 1;
	    				break;
	    			}
	    		}
	    	}
	    }
	}
	
	private void updateLaserMovement() {
		for(int i = 0; i < player.getLaserLevel(); i++) {
			if(shoot[i] == 1) {
				if(!laser[i].outOfScreen())
					laser[i].update();
				else {
					shoot[i] = 0;
				}
	    	}
	    }
	}
	
	private void updateEnemyMovement(int i) {
		if(!enemy[i].outOfScreen())
			enemy[i].update();
		else {
			enemy[i].randomPosition();
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

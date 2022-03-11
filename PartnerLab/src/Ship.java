import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/* class to represent a Ship object in a game */
public class Ship {
// attributes of a Ship object
	private int x, y, hp;
	private boolean alive;
	private int width, height;
	private Image img;
	private int vx, vy;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	private int e, f;
	Projectile[] lasers = new Projectile[100000]; // creates container
	private boolean hit = false;
	Enemy em1 = new Enemy("pener.png");

// constructor
	public Ship(String fileName) {
		x = 600;
		y = 200;
		vx = 0;
		vy = 5;
		hp = 5;
		width = 172;
		height = 78;
// call the loadLaser method
		loadLasers();
// do not touch
		img = getImage(fileName);
		updateShip();
	}

// method to set up missiles
	public void loadLasers() {
// traverse each spot in the array
// fill it with a Projectile object
		for (int i = 0; i < lasers.length; i++) {
			lasers[i] = new Projectile("bullet.png");
		}
	}

// method for firing our projectiles
	public void fire() {
// visit each projectile in the array
		for (int i = 0; i < lasers.length; i++) {
			if (!lasers[i].active) {
				lasers[i].active = true;
// missiles should appear by the ship
				lasers[i].setX(x + 148);
				lasers[i].setY(y + 36);
// turn on velocity
				lasers[i].setVx(60);
				break; // break will stop searching for more lasers to activate
			}
		}
	}

	public int getLX() {
		for (int i = 0; i < lasers.length; i++) {
			if (lasers[i].active) {
				e = lasers[i].getX();
			}
		}
		return e;
	}

	public void setLX(int e) {
		this.e = e;
	}

	public int getLY() {
		for (int i = 0; i < lasers.length; i++) {
			if (lasers[i].active) {
				f = lasers[i].getY();
			}
		}
		return f;
	}

	public void setLY(int f) {
		this.f = f;
	}

// add a 2nd constructor that takes in the desired position
// of the object
	public Ship(String paramFileName, int paramX, int paramY) {
		this(paramFileName); // calls the first constructor
// the this() call will setup some variables
// the rest is done in here
		x = paramX;
		y = paramY;
// anytime you alter x and y
// you must call updateShip()
		updateShip();
	}

	public void move() {
		y += vy;
		if (y > 502) {
			vy = 0;
		}
		updateShip();
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
// ship will handle telling active missiles to draw themselves
// code to visit all missiles
		for (int i = 0; i < lasers.length; i++) {
			if (lasers[i].active) {
				lasers[i].paint(g);
			}
// later - you should figure out when to deactivate an active
// missile
// (if it's off screen, deactivate it)
		}
// later - you should figure out when to deactivate an active missile
// (if it's off screen, deactivate it)
		for (int i = 0; i < lasers.length; i++) {
			if (lasers[i].getY() == 100000) {
				lasers[i].active = false;
			}
			if (lasers[i].getX() >= 1800) {
				lasers[i].setY(100000);
			}
		}
	}

	public void collision(Ship s, Enemy em1) {
		Rectangle enemy = new Rectangle(em1.getX() + 145, em1.getY() + 26, 249, 218);
		Rectangle ship = new Rectangle(getLX(), getLY(), 50, 50);
		for (int i = 0; i < lasers.length; i++) {
			if (ship.intersects(enemy)) {
				lasers[i].setY(100000);
				lasers[i].active = false;
				hp--;
			}
		}
	}

	void updateShip() {
		tx.setToTranslation(x, y);
	}

// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Ship.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	public static void main(String[] args) {
	}

	public static void PlaySound(File shine) {
	}

	public void laser() {
		File shine = new File(".//res//laser.wav");
		PlaySound(shine);
		try {
			Clip clip = AudioSystem.getClip();
			PlaySound(shine);
			clip.open(AudioSystem.getAudioInputStream(shine));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 10000);
		} catch (Exception e) {
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		updateShip();
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		updateShip();
	}

	public int getW(int w) {
		return w;
	}

	public void setW(int w) {
		this.width = w;
		updateShip();
	}

	public int getH(int h) {
		return h;
	}

	public void setH(int h) {
		this.height = h;
		updateShip();
	}
}

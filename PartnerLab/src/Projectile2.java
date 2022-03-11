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
public class Projectile2 {
//attributes of a Ship object
	private int x, y;
	private double fy;
	private int width, height;
	private Image img;
	private int vx, vy;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
//indicates if the projectile is live
	public boolean active = false;

//constructor
	public Projectile2(String fileName, int paramX, int paramY, int paramVx, int paramVy) {
		x = paramX;
		y = paramY;
		vx = -10;
		vy = 0;
		width = 172;
		height = 78;
//do not touch
		img = getImage(fileName);
		updateProjectile();
	}

//add a 2nd constructor that takes in the desired position
//of the object
	public Projectile2() {
		// calls the first constructor
//the this() call will setup some variables
//the rest is done in here
//anytime you alter x and y
//you must call updateShip()
		updateProjectile();
	}

	public void move() {
		updateProjectile();
	}

	public boolean collision(Ship s, Projectile2 p2) {
		Rectangle ship = new Rectangle(s.getX(), s.getY(), 100, 100);
		Rectangle projectile2 = new Rectangle(getX() + 10, getY() + 10, 30, 100);
		boolean coll = ship.intersects(projectile2);

		return false;
	}

	public void die() {
		y = 1000000;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		x += vx;
		y += vy;
		updateProjectile();
	}

	private void updateProjectile() {
		tx.setToTranslation(x, y);
	}

// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Projectile.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return width;
	}

	public int getH() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
		updateProjectile();
	}

	public void setY(int y) {
		this.y = y;
		updateProjectile();
	}

	public int getW(int w) {
		return w;
	}

	public void setW(int w) {
		this.width = w;
		updateProjectile();
	}

	public int getVy() {
		return vy;
	}

	public int getH(int h) {
		return h;
	}

	public void setH(int h) {
		this.height = h;
		updateProjectile();
	}

	public int getVx() {
		return vx;
	}

}
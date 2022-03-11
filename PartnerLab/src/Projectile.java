import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/* class to represent a Ship object in a game */
public class Projectile {
//attributes of a Ship object
	private int x, y;
	private int width, height;
	private Image img;
	private int vx, vy;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
//indicates if the projectile is live
	public boolean active = false;

//constructor
	public Projectile(String fileName) {
		x = 200;
		y = 200;
		vx = 10;
		vy = 0;
		width = 172;
		height = 78;
//do not touch
		img = getImage(fileName);
		updateShip();
	}

//add a 2nd constructor that takes in the desired position
//of the object
	public Projectile(String paramFileName, int paramX, int paramY) {
		this(paramFileName); // calls the first constructor
//the this() call will setup some variables
//the rest is done in here
		x = paramX;
		y = paramY;
//anytime you alter x and y
//you must call updateShip()
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

// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		x += vx;
		updateShip();
	}

	private void updateShip() {
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
		updateShip();
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

	public static void main(String[] args) {
	}

	public static void PlaySound(File shine) {
	}

	public void keyPressed() {
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
}
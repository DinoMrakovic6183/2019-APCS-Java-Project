import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Enemy3 {
	private int x, e, y, f;
	private boolean active;
	private boolean offscreen = true;
	private int width, height;
	private Image img;
	private int vx, vy;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	private boolean erase;
	public int em_count;
	private int cntr = 0, rate = 100;
	Projectile2[] fireball = new Projectile2[1000];

	public Enemy3(String FileName) {
		x = -800;
		y = 200;
		vx = 50;
		vy = 1;
		erase = false;
		em_count = 20;
		img = getImage(FileName);
		loadFire();
		updateEnemy();
	}

	public void loadFire() {

		for (int i = 0; i < fireball.length; i++) {
			fireball[i] = new Projectile2("fireball.png", 0, 0, 15, 0);
		}

	}

	public void fire() {
		for (int i = 0; i < fireball.length; i++) {
			if (!fireball[i].active) {
				fireball[i].active = true;
				fireball[i].setVx(15);
				break;
			}
		}

		updateEnemy();
	}

	public void rate() {
		cntr++;
		if (cntr >= rate) {
			fire();
			keyPressed();
			cntr = 0;
		}
		updateEnemy();
	}

	public void move() {
		if (x >= -800 && offscreen == true) {
			x += 1;
		}
		if (x >= -275) {
			offscreen = false;
		}
		y += vy;
		if (y >= 201 || y <= 0) {
			vy *= -1;
		}
		for (int i = 0; i < fireball.length; i++) {
			if (fireball[i].active == false) {
				fireball[i].setY(y + 70);
				fireball[i].setX(x + 300);
			}
		}
	}

	public int getFX() {
		for (int i = 0; i < fireball.length; i++) {
			if (fireball[i].active == true) {
				e = fireball[i].getX();
			}
		}
		return e;
	}

	public void setFX(int e) {
		this.e = e;
	}

	public int getFY() {
		for (int i = 0; i < fireball.length; i++) {
			if (fireball[i].active == true) {
				f = (int) fireball[i].getY();
			}
		}
		return f;
	}

	public void setFY(int f) {
		this.f = f;
	}

	public void updateLava() {
		tx.setToTranslation(x, y);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		for (int i = 0; i < fireball.length; i++) {
			if (fireball[i].active == true) {
				fireball[i].paint(g);
			}
		}
		updateEnemy();
	}

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

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW(int w) {
		return w;
	}

	public void setW(int w) {
		this.width = w;
	}

	public int getH(int h) {
		return h;
	}

	public void setH(int h) {
		this.height = h;
	}

	public int getEm_count() {
		return em_count;
	}

	public void setEm_count(int em_count) {
		this.em_count = em_count;
	}

	private void updateEnemy() {
		tx.setToTranslation(x, y);
	}

	public static void main(String[] args) {
	}

	public static void PlaySound(File shine) {
	}

	public void keyPressed() {
		File shine = new File(".//res//firecharge.wav");
		PlaySound(shine);
		try {
			Clip clip = AudioSystem.getClip();
			PlaySound(shine);
			clip.open(AudioSystem.getAudioInputStream(shine));
			clip.start();
		} catch (Exception e) {
		}
	}
}
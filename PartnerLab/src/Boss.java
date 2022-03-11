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
import javax.sound.sampled.FloatControl;

public class Boss {
	private int x, e, y, f, unoX, unoY, emoX, emoY, laserX, laserY;
	private int random;
	private boolean active;
	public int hp = 1500;
	private boolean offscreen = true;
	private int width, height;
	private Image img;
	private int vx, vy;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	private boolean erase;
	public int em_count;
	public int bounce = 0, bouncemax = 8;
	private int cntr = 0, rate = 100;
	private int cntruno = 0, rateuno = 150;
	private int cntrlaser = 0, ratelaser = 200;
	Projectile2[] emoji = new Projectile2[2000];
	Projectile2[] uno = new Projectile2[2000];
	Projectile2[] laser = new Projectile2[1000];

	public Boss(String FileName) {
		x = 2600;
		y = 20;
		vx = 50;
		vy = 1;
		erase = false;
		em_count = 20;
		img = getImage(FileName);
		loadFire();
		loadUno();
		loadlaser();
		updateEnemy();
	}

	public void loadFire() {
		for (int i = 0; i < emoji.length; i++) {
			emoji[i] = new Projectile2("anxious.png", 0, 0, 15, 0);
		}
	}

	public void loadUno() {
		for (int i = 0; i < uno.length; i++) {
			uno[i] = new Projectile2("nou.gif", 0, 0, 15, 0);
		}
	}

	public void loadlaser() {
		for (int i = 0; i < laser.length; i++) {
			laser[i] = new Projectile2("bossLaser.png", 0, 0, -15, 0);
		}
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void fire() {
		for (int i = 0; i < emoji.length; i++) {
			if (!emoji[i].active) {
				emoji[i].active = true;
				random = (int) (Math.random() * 2);
				emoji[i].setVy(-15);
				emoji[i].setVx(-20);
				break;
			}
			updateEnemy();
		}
	}

	public void fireUno() {
		for (int i = 0; i < uno.length; i++) {
			if (!uno[i].active) {
				uno[i].active = true;
				random = (int) (Math.random() * 2);
				uno[i].setVx(-25);
				break;
			}
			updateEnemy();
		}
	}

	public void fireLaser() {
		for (int i = 0; i < laser.length; i++) {
			if (!laser[i].active) {
				laser[i].active = true;
				random = (int) (Math.random() * 2);
				laser[i].setVx(-25);
				break;
			}
			updateEnemy();
		}
	}

	public void collision(Ship s) {
		Rectangle boss = new Rectangle((int) getX() + 50, (int) getY() + 150, 200, 600);
		Rectangle ship = new Rectangle(s.getLX(), s.getLY(), 50, 50);
		if (ship.intersects(boss)) {
			updateEnemy();
			hp--;
		}
	}

	public void rate() {
		cntr++;

		if (cntr >= rate) {
			fire();
			keyPressed();
			cntr = 0;
			rate = 500;
			bounce = 0;
		}
		for (int i = 0; i < emoji.length; i++) {
			if (emoji[i].active) {
				if (emoji[i].getY() <= 0 || emoji[i].getY() >= 400) {
					emoji[i].setVy(emoji[i].getVy() * -1);
					bounce++;
				}
				if (emoji[i].getX() >= 1700) {
					emoji[i].setVx(emoji[i].getVx() * -1);
					bounce++;
				}
				if (emoji[i].getX() <= 0 && bounce < bouncemax) {
					emoji[i].setVx(emoji[i].getVx() * -1);
					bounce++;
				}
				if (emoji[i].getX() <= 0 && bounce > bouncemax) {
					emoji[i].setVx(-1000);
					bounce = 0;
				}
				if (emoji[i].getX() <= -200) {
					emoji[i].active = false;
				}
			}
		}
		updateEnemy();
	}

	public int getEmoX() {
		for (int i = 0; i < emoji.length; i++) {
			if (emoji[i].active == true) {
				emoX = emoji[i].getX();
			}
		}
		return emoX;
	}

	public void setEmoX(int emoX) {
		this.emoX = emoX;
	}

	public int getEmoY() {
		for (int i = 0; i < emoji.length; i++) {
			if (emoji[i].active == true) {
				emoY = (int) emoji[i].getY();
			}
		}
		return emoY;
	}

	public void setEmoY(int emoY) {
		this.emoY = emoY;
	}

	public int getUnoX() {
		for (int i = 0; i < uno.length; i++) {
			if (uno[i].active == true) {
				unoX = uno[i].getX();
			}
		}
		return unoX;
	}

	public void setUnoX(int unoX) {
		this.unoX = unoX;
	}

	public int getUnoY() {
		for (int i = 0; i < uno.length; i++) {
			if (uno[i].active == true) {
				unoY = (int) uno[i].getY();
			}
		}
		return unoY;
	}

	public void setUnoY(int unoY) {
		this.unoY = unoY;
	}

	public int getLaserX() {
		for (int i = 0; i < laser.length; i++) {
			if (laser[i].active == true) {
				laserX = laser[i].getX();
			}
		}
		return laserX;
	}

	public void setLaserX(int laserX) {
		this.laserX = laserX;
	}

	public int getLaserY() {
		for (int i = 0; i < laser.length; i++) {
			if (laser[i].active == true) {
				laserY = (int) laser[i].getY();
			}
		}
		return laserY;
	}

	public void setLaserY(int laserY) {
		this.laserY = laserY;
	}

	public void rateUno() {
		cntruno++;

		if (cntruno >= rateuno) {
			fireUno();
			keyPressed();
			cntruno = 0;
			rateuno = 150;
		}

		for (int i = 0; i < uno.length; i++) {
			if (uno[i].active) {
				if (uno[i].getX() <= 0) {
					uno[i].setVx(25);
				}
				if (uno[i].getX() >= x + 1) {
					uno[i].active = false;
				}
			}
		}
	}

	public void rateLaser() {
		cntrlaser++;

		if (cntrlaser >= ratelaser) {
			fireLaser();
			laserSound();
			cntrlaser = 0;
			ratelaser = 100;
		}

		for (int i = 0; i < laser.length; i++) {
			if (laser[i].active) {
				if (laser[i].getX() <= -300) {
					laser[i].active = false;
				}
			}
		}
	}

	public void move() {
		if (x <= 2600 && offscreen == true) {
			x -= 10;
		}
		if (x <= 1400) {
			offscreen = false;
		}
		for (int i = 0; i < emoji.length; i++) {
			if (emoji[i].active == false) {
				emoji[i].setY(y + 330);
				emoji[i].setX(x);
			}
		}
		for (int i = 0; i < uno.length; i++) {
			if (uno[i].active == false) {
				uno[i].setY(y + 330);
				uno[i].setX(x);
			}
		}
		for (int i = 0; i < laser.length; i++) {
			if (laser[i].active == false) {
				laser[i].setY(y + 185);
				laser[i].setX(x - 20);
			}
		}
	}

	public int getFX() {
		for (int i = 0; i < emoji.length; i++) {
			if (emoji[i].active == true) {
				e = emoji[i].getX();
			}
		}
		return e;
	}

	public void setFX(int e) {
		this.e = e;
	}

	public int getFY() {
		for (int i = 0; i < emoji.length; i++) {
			if (emoji[i].active == true) {
				f = (int) emoji[i].getY();
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
		for (int i = 0; i < emoji.length; i++) {
			if (emoji[i].active == true) {
				emoji[i].paint(g);
			}
			if (cntr >= rate) {
				emoji[i].active = false;
			}
		}
		for (int i = 0; i < uno.length; i++) {
			if (uno[i].active == true) {
				uno[i].paint(g);
			}
			if (cntruno >= rateuno) {
				uno[i].active = false;
			}
		}
		for (int i = 0; i < laser.length; i++) {
			if (laser[i].active == true) {
				laser[i].paint(g);
			}
			if (cntrlaser >= ratelaser) {
				laser[i].active = false;
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
		File shine = new File(".//res//wind.wav");
		PlaySound(shine);
		try {
			Clip clip = AudioSystem.getClip();
			PlaySound(shine);
			clip.open(AudioSystem.getAudioInputStream(shine));
			clip.start();
		} catch (Exception e) {
		}
	}

	public void laserSound() {
		File shine = new File(".//res//bosslaser.wav");
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
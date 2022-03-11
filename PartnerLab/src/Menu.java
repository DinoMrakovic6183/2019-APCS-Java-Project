import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Menu {
//attributes of a Background object
	private int x, y;
	private boolean alive;
	private int width, height;
	private Image img;
	private int vx, vy;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

//constructor
	public Menu(String fileName) {
		x = 0;
		y = 0;
		vx = -1;
		vy = 0;
		width = 172;
		height = 78;
//do not touch
		img = getImage(fileName);
		updateBackground();
	}

//add a 2nd constructor that takes in the desired position
//of the object
	public Menu(String paramFileName, int paramX, int paramY) {
		this(paramFileName); // calls the first constructor
//the this() call will setup some variables
//the rest is done in here
		x = paramX;
		y = paramY;
//anytime you alter x and y
//you must call updateBackground()
		updateBackground();
	}

	public void move() {
		x += vx;
		if (x == -1800) {
			x = 0;
		}
		updateBackground();
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
	}

	private void updateBackground() {
		tx.setToTranslation(x, y);
	}

// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		updateBackground();
	}

	public void setY(int y) {
		this.y = y;
		updateBackground();
	}

	public int getW(int w) {
		return w;
	}

	public void setW(int w) {
		this.width = w;
		updateBackground();
	}

	public int getH(int h) {
		return h;
	}

	public void setH(int h) {
		this.height = h;
		updateBackground();
	}

	public static void main(String[] args) {
	}

	public static void PlaySound(File shine) {
	}

	public void keyPressed() {
		File shine = new File(".//res//laugh.wav");
		PlaySound(shine);
		try {
			Clip clip = AudioSystem.getClip();
			PlaySound(shine);
			clip.open(AudioSystem.getAudioInputStream(shine));
			clip.start();
		} catch (Exception e) {
		}
	}

	public void lvl2music() {
		File shine = new File(".//res//lvl2music.wav");
		try {
			Clip clip = AudioSystem.getClip();
			PlaySound(shine);
			clip.open(AudioSystem.getAudioInputStream(shine));
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void silent() {
		File shine = new File(".//res//bosslaser.wav");
		PlaySound(shine);
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(shine));
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10000.0f);
		} catch (Exception e) {
		}
	}
}
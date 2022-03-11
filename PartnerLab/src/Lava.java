import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Lava {
	private int x, y, cntr, e;
	private boolean offscreen = true;
	private int width, height;
	private Image img;
	private int vx, vy;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	public Lava(String fileName) {
		e = 0;
		x = 0;
		y = 890;
		vx = 0;
		vy = 5;
		width = 172;
		height = 78;
// call the loadLaser method
// do not touch
		img = getImage(fileName);
	}

	public void move() {
		if (y <= 890 && offscreen == true) {
			y -= 3;
		}
		if (y <= 490) {
			offscreen = false;
		}
		updateLava();
	}

	public void updateLava() {
		tx.setToTranslation(x, y);
	}

	public void move2() {
		if (y <= 890 && offscreen == true) {
			y -= 9;
		}
		if (y <= 490) {
			offscreen = false;
		}
		updateLava();
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		Rectangle lava = new Rectangle(x, y + 10, 4000, 320);
		// g.drawRect(0,(int) lava.getY(),4000,320);
	}

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

	public void collision(Ship s) {
		Rectangle lava = new Rectangle(x, y + 10, 4000, 320);
		Rectangle ship = new Rectangle(s.getX(), s.getY(), 178, 72);
		if (lava.intersects(ship)) {
			System.out.println("die");
		}
	}

	public void collision(Enemy em) {
		Rectangle lava = new Rectangle(x, y + 10, 4000, 320);
		Rectangle enemy = new Rectangle(em.getX() + 145, em.getY() + 26, 249, 218);
		if (lava.intersects(enemy)) {
			em.setY((int) (Math.random() * 400));
		}
	}
}
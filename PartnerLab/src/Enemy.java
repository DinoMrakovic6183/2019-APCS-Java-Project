import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
public class Enemy {
private int x, y, hp;
private boolean alive;
private int width, height;
private Image img;
private int vx, vy;
private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
private boolean erase;
public int ec;
public int score = 0;

public Enemy(String FileName) {
x = 3000;
y = (int) (Math.random() * 400);
vx = 25;
vy = 0;
erase = false;
hp = 5;
ec = 100;
img = getImage(FileName);
updateEnemy();
}
public void collision(Ship s, Enemy em1) {
Rectangle enemy = new Rectangle(x+145,y+26,249,218);
Rectangle ship = new Rectangle(s.getLX(), s.getLY(), 50, 50);
if (ship.intersects(enemy)) {
 score+=100;
 ec--;
s.setLY(100000);
em1.setX(2500);
em1.setY((int) (Math.random() * 400));
updateEnemy();
}
erase = false;

if(em1.getX()<=-450) {
 updateEnemy();
 s.setLY(100000);
 em1.setX(2500);
 em1.setY((int) (Math.random() * 400));
 updateEnemy();
 ec--;
}
}
public void collision(Lava lava, Enemy em) {
 Rectangle enemy = new Rectangle(x+145,y+26,249,218);
 Rectangle Lava = new Rectangle(0, lava.getY(),4000,320);
 if(Lava.intersects(enemy)) {
  em.setX(2500);
  em.setY((int) (Math.random() * 400));
 }
 updateEnemy();
}
public void paint(Graphics g) {
Graphics2D g2 = (Graphics2D) g;
g2.drawImage(img, tx, null);
if(ec>0) {
 x -= vx;
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
public int getScore() {
 return score;
}
public void setScore(int score) {
 this.score = score;
}
public int getX() {
return x;
}
public void setX(int x) {
this.x = x;
}
public int getY() {
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
public int getEc() {
return ec;
}
public void setEc(int ec) {
this.ec = ec;
}
private void updateEnemy() {
tx.setToTranslation(x, y);
}
}
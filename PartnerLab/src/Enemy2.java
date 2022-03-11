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
public class Enemy2 {
 private int x, y, e, f, g;
 private boolean active = false;
 private boolean offscreen = true;
 private int cntr, cntr2, rate = 10, duration = 120;
 private int firerate = 60;
 private Image img;
 private double vx, vy;
 private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
 private boolean erase;
 public int em_count;
 public int score = 0;
 Projectile2[] fire = new Projectile2[1000];
 public Enemy2(String FileName) {
  x = 1900;
  y = (int) (Math.random() * 349);
  cntr2 = 0;
  vx = 4;
  vy = ((Math.random() * 10) + 5);
  erase = false;
  em_count = 50;
  img = getImage(FileName);
  for (int i = 0; i < fire.length; i++) {
   fire[i] = new Projectile2("fire.gif", x, y, -10, 0);
  }
  updateEnemy();
 }
 public void move() {
  if (x >= 1600) {
   x -= vx;
   vx=4;
  }
  if (x <= 1600) {
   cntr++;
   if (cntr >= rate && cntr <= duration) {
    y += vy;
   }
   if (cntr > duration) {
    cntr = 9;
    fire();
    keyPressed();
    vy = (int) ((Math.random() * 10) + 5);
   }
   if (y >= 350) {
    vy *= -1;
   }
   if (y <= 0) {
	   y=1;
	    vy *= -1;
	   }
   if (y >= 350) {
    y = 349;
   }
   vx = 0;
   offscreen = false;
   updateEnemy();
   System.out.println("Firerate EM2: " + cntr2);
  }
 }
 public void collision(Ship s, Enemy2 em2) {
  Rectangle enemy = new Rectangle(getX()+30,getY()+50,100,75);
  Rectangle ship = new Rectangle(s.getLX(), s.getLY(), 50, 50);
  if (ship.intersects(enemy)) {
   updateEnemy();
   x += 30;
   score+=20;
  }
 }
 public int getScore() {
  return score;
 }
 public void setScore(int score) {
  this.score = score;
 }
 public void fire() {
  for (int i = 0; i < fire.length; i++) {
   if (!fire[i].active) {
    fire[i].active = true;
    fire[i].setX(x);
    fire[i].setY(y);
    break;
   }
  }
 }
 
 
  public int getFX() {
  for (int i = 0; i < fire.length; i++) {
   if (fire[i].active) {
    e = fire[i].getX();
   }
  }
  
  
  return e;
 }
  public void setFX(int e) {
   this.g = e;
  }
  
 
 public int getFY() {
  for (int i = 0; i < fire.length; i++) {
   if (fire[i].active) {
    f = fire[i].getY();
   }
  }
  return f;
 }
 public void setFY(int f) {
  this.f = f;
 }
 public void collision(Lava lava, Enemy2 em) {
  Rectangle enemy = new Rectangle(x + 145, y + 26, 249, 218);
  Rectangle Lava = new Rectangle(0, lava.getY(), 4000, 320);
  if (Lava.intersects(enemy)) {
   em.setX(2500);
   em.setY((int) (Math.random() * 400));
  }
  updateEnemy();
 }
 public void updateLava() {
  tx.setToTranslation(x, y);
 }
 public void paint(Graphics g) {
  Graphics2D g2 = (Graphics2D) g;
  g2.drawImage(img, tx, null);
  for (int i = 0; i < fire.length; i++) {
   if (fire[i] != null && fire[i].active) {
    fire[i].paint(g);
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
  File shine = new File(".//res//fireblast.wav");
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

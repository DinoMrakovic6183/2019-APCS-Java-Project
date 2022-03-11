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
public class Shine {
 private int x, y;
 private int width, height;
 private Image img;
 private int vx, vy;
 private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
 public boolean active = false;
 public Shine(String fileName) {
  x = 600;
  y = 200;
  width = 150;
  height = 150;
//do not touch
  img = getImage(fileName);
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
 public static void main(String[] args) {
 }
 public static void PlaySound(File shine) {
 }
 public void keyPressed() {
  File shine = new File(".//res//shine.wav");
  PlaySound(shine);
  try {
   Clip clip = AudioSystem.getClip();
   PlaySound(shine);
   clip.open(AudioSystem.getAudioInputStream(shine));
   clip.start();
  } catch (Exception e) {
  }
 }
 public void paint(Graphics g) {
  Graphics2D g2 = (Graphics2D) g;
  g2.drawImage(img, x, y, width, height, null);
 }
 public int getX() {
  return x;
 }
 public int getY() {
  return y;
 }
 public void setX(int x) {
  this.x = x;
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
}
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	public static int life = 3;
//attributes for screen
	int screen_width = 1800;
	int screen_height = 600;
	boolean use = true;
	boolean paintUse = false;
	int limit = 10;
	int music = 0;
	boolean game = false;
	boolean gameover = false;
	boolean help = false;
	int laugh = 0;
	int count = 5;
	boolean level2 = false;
	boolean game2 = false;
	private boolean MM = true;
// adding objects
	String src = new File("").getAbsolutePath() + "/src/"; // path to image
	Clip hop;
	Ship s = new Ship("planeSprite1.gif");
	
	Background bg = new Background("fullBackground.png");
	Projectile p = new Projectile("bullet.png");
	Shine shine = new Shine("reflector.gif");
	Menu m = new Menu("TitleScreen.jpg");
	Menu m2 = new Menu("gameover.gif");
	Menu h = new Menu("helpscreen.jpg");
	Menu lvl2 = new Menu("level2.jpg");
	Enemy em1 = new Enemy("pener.png");

	Enemy2 em2 = new Enemy2("enemy2.gif");
	Enemy2 em2_2 = new Enemy2("enemy2.gif");
	Enemy3 em3 = new Enemy3("enemy3.png");
	Projectile2 proj2 = new Projectile2("fire.gif", getX(), getY(), -10, 0);
	Menu orc = new Menu("orc.gif");
	Background bg2 = new Background("background2.jpeg");
	Lava lava = new Lava("lava.gif");
	Lava lava2 = new Lava("lava2.gif");
	Boss chungus = new Boss("boss.gif");
// clip.open(audioInputStream);
	Sequencer sequencer;
// Background bg;
	int my_variable = 0; // example
	int score = (em1.getScore() + em2.getScore());
	String lost = "";

	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setFont(font);
		g.setFont(font2);
		g.setColor(Color.BLACK);
		if(MM == true) {
			m.paint(g);
		}
	
		g.setColor(Color.RED);
		g.drawString("Press G to Start or H for Help Menu", 600, 550);
		if (game == true && level2 == false) {
			collision(s, em3);
			collision(s, em2);
			collision(s, em1);
			collision(s, lava);
		}
		if (gameover == true && game2 == false) {
			level2 = false;
			g.setColor(Color.WHITE);
			g.setFont(font);
			if (em1.getScore() + em2.getScore() > 0) {
				laugh++;
				m2.paint(g);
				g.drawString("You were " + (8000 - (em1.getScore() + em2.getScore())) + " points away from Level 2", 100, 550);
			}
			if (em1.getScore() + em2.getScore() >= 1000) {
				g.drawString("Your Score: " + (em1.getScore() + em2.getScore()), 300, 350);
			}
			if (em1.getScore() + em2.getScore() == 0) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, screen_width, screen_height);
				orc.paint(g);
				g.setColor(Color.WHITE);
				g.drawString("game over", 0, 300);
			}
			if (em1.getScore() + em2.getScore() <= 1000 && em1.getScore() + em2.getScore() > 0) {
				g.drawString("You're absolutely terrible", 300, 350);
			}
		}

		if (game == false && help == true) {
			h.paint(g);
		}

		if (game == true && gameover == false && level2 == false) {
			bg.paint(g);
			s.paint(g);
			if (paintUse == true) {
				shine.paint(g);
			}
			em1.paint(g);
			em3.paint(g);
			em2.paint(g);
			em2_2.paint(g);
			lava.paint(g);

			g.drawString("Shines Left: " + count, 600, 30);
			// g.drawRect(em2.getX()+30,em2.getY()+50,100,75);
			//g.drawRect(em2_2.getX()+30,em2_2.getY()+50,100,75);
			g.drawString("Points: " + (em1.getScore() + em2.getScore()), 1000, 30);
			g.drawString("Petercopters Left: " + em1.getEc(), 100, 30);
			//g.drawRect(em1.getX()+145,em1.getY()+26,249,218);
		 //g.drawRect(s.getX()+20, s.getY()+5, 115, 50);
		//	g.drawRect(em2.getFX()+10,em2.getFY()+10,30,100);
		//	 g.drawRect(em2_2.getFX()+10,em2_2.getFY()+10,30,100);
			// g.drawRect(em3.getFX(),em3.getFY(),160,80);
		}
		if (em1.getScore() + em2.getScore() >= 2000 && game == true) {
			level2 = true;
			lvl2.paint(g);
			g.setColor(Color.WHITE);
			g.drawString("Press K to continue", 600, 500);
		}

		if (game2 == true && gameover == false) {
			bg2.paint(g);
			s.paint(g);
			chungus.paint(g);
			lava2.paint(g);
		g.drawString("Shines Left " + count, 600, 30);
			//g.drawString("Hp: " + chungus.getHp(), 1500, 550);
		//	 g.drawRect(s.getX()+20, s.getY()+5, 115, 50);
			// g.drawRect((int)chungus.getX()+50, (int)chungus.getY()+150,300,600);
			// g.drawRect(chungus.getEmoX(),chungus.getEmoY(),100,100);
		// g.drawRect(chungus.getUnoX()+20,chungus.getUnoY()+20,100,100); 
		//	 g.drawRect(chungus.getLaserX(),chungus.getLaserY(),180,90);
			if (paintUse == true) {
				shine.paint(g);
			}
		}

		if (laugh == 1) {
			m2.keyPressed();
		}
		if (laugh >=2) {
			laugh = 2;
		}

		if (gameover == true && em1.getScore() + em2.getScore() > 0 && game2 == true) {
			level2 = false;
			game = false;
			laugh++;
			m2.paint(g);
		}
			
	}

	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
//

	public void update() {

		if (score > 0 && paintUse == true) {
			score--;
		}

		bg.move();
	
		lava.collision(em1);
		em1.collision(s, em1);
		s.collision(s, em1);
		em2.collision(s, em2);
		em2_2.collision(s, em2_2);
		if (game == true && gameover == false && level2 == false) {
			lava.move();
			em3.move();
			em2.move();
			em2_2.move();
			em3.rate();
		}

		if (game2 == true && gameover == false && MM == false) {
			bg2.setVx(-5);
			bg2.move();
			lava2.move2();
			chungus.move();
			chungus.rate();
			chungus.rateUno();
			chungus.rateLaser();
			collisionL2(s, lava2);
			music++;
			if (music == 1) {
				orc.lvl2music();
			}
		}
		if (game2 == true) {
			chungus.collision(s);
			collision(s, chungus);
			collisionUno(s, chungus);
			collisionEmo(s, chungus);
		}
		if (gameover == true) {
			orc.silent();
		}

		if (count < 0) {
			count = 0;
		}

		//System.out.println("Laser X: " + s.getLX());
		//System.out.println("Laser Y: " + s.getLY());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}


	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Just Another Bullet Hell");
		f.setSize(screen_width, screen_height);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseListener(this);
		f.addMouseMotionListener(this);

		// driver is responsible for setting attributes

// Obtains the default Sequencer connected to a default device.
		try {
			sequencer = MidiSystem.getSequencer();
// Opens the device, indicating that it should now acquire any
// system resources it requires and become operational.
			sequencer.open();
// create a stream from a file
			InputStream is = new BufferedInputStream(
					new FileInputStream(new File("Thelazysong.mid").getAbsoluteFile()));
// Sets the current sequence on which the sequencer operates.
// The stream must point to MIDI file data.
			sequencer.setSequence(is);
// Starts playback of the MIDI data in the currently loaded
// sequence.
		} catch (Exception e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
// player.addMouseListener(this);
// bg = new Background("background.png");
// do not add to frame, call paint on
// these objects in paint method
		f.add(this);
		t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void collision(Ship s, Enemy3 em3) {
		Rectangle ship = new Rectangle(s.getX()+20, s.getY()+5, 115, 50);
		Rectangle proj3 = new Rectangle(em3.getFX(), em3.getFY(), 160, 80);
		if (proj3.intersects(ship) && paintUse == false) {
			gameover = true;
		}
		if (proj3.intersects(ship) && paintUse == true) {

		}
	}

	public void collision(Ship s, Boss boss) {
		Rectangle ship = new Rectangle(s.getX()+20, s.getY()+5, 115, 50);
		Rectangle proj = new Rectangle(boss.getLaserX(), boss.getLaserY(), 180, 90);
		if (proj.intersects(ship) && paintUse == false) {
			gameover = true;
		}
	}

	public void collisionEmo(Ship s, Boss boss) {
		Rectangle ship = new Rectangle(s.getX()+20, s.getY()+5, 115, 50);
		Rectangle proj3 = new Rectangle(boss.getEmoX(), boss.getEmoY(), 100, 100);
		if (proj3.intersects(ship) && paintUse == false) {
			gameover = true;
		}
	}

	public void collisionUno(Ship s, Boss boss) {
		Rectangle ship = new Rectangle(s.getX()+20, s.getY()+5, 115, 50);
		Rectangle proj2 = new Rectangle(boss.getUnoX(), boss.getUnoY(), 100, 100);
		if (proj2.intersects(ship) && paintUse == false) {
			gameover = true;
		}
	}

	public void collision(Ship s, Enemy2 em2) {
		Rectangle ship = new Rectangle(s.getX()+20, s.getY()+5, 115, 50);
		Rectangle project2 = new Rectangle(em2.getFX() + 10, em2.getFY() + 10, 30, 100);
		if (project2.intersects(ship) && paintUse == false) {
			gameover = true;
		}
	}

	public void collision(Ship s, Enemy em) {
		Rectangle ship = new Rectangle(s.getX()+20, s.getY()+5, 115, 50);
		Rectangle em1 = new Rectangle(em.getX() + 145, em.getY() + 26, 249, 218);
		if (em1.intersects(ship) && paintUse == false) {
			gameover = true;
		}
		if (em1.intersects(ship) && paintUse == true) {

		}
	}

	public void collision(Ship s, Lava lava) {
		Rectangle ship = new Rectangle(s.getX()+20, s.getY()+5, 115, 50);
		Rectangle L = new Rectangle(0, (int) lava.getY(), 4000, 320);
		if (L.intersects(ship)) {
			gameover = true;
		}
	}

	public void collisionL2(Ship s, Lava lava2) {
		Rectangle ship = new Rectangle(s.getX()+20, s.getY()+5, 115, 50);
		Rectangle L2 = new Rectangle(0, (int) lava2.getY(), 4000, 320);
		if (L2.intersects(ship)) {
			gameover = true;
		}
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 72 && game == false) { // H
			if (help == false) {
				help = true;
			}
		}

		if (e.getKeyCode() == 75 && game == false) { // K
			if (help == true) {
				help = false;
			}
		}
		if (e.getKeyCode() == 75 && level2 == true && gameover == false) {
			level2 = false;
			game2 = true;
			MM = false;
		}
		if (e.getKeyCode() == 75 && gameover == true) {
			level2 = false;
			game2 = false;
			game = false;
			em1.setScore(0);
			em2.setScore(0);
			MM = true;
		}

		if (e.getKeyCode() == 71 && game == false && help == false) { // G
			game = true;
		}

		if (e.getKeyCode() == 32) { // SPACEBAR
			if (use == true && game == true && gameover == false && count > 0 && level2 == false) {
				paintUse = true;
				shine.keyPressed();
				count--;
				use = false;
			}
			if (game2 == true && use == true && gameover == false && count > 0) {
				paintUse = true;
				count--;
				shine.keyPressed();
				use = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 32) { // SPACEBAR
			paintUse = false;
			use = true;
			if (game2 == true) {
				paintUse = false;
				use = true;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void reset() {
	}

	boolean on = false;

	@Override
	public void mousePressed(MouseEvent e) {
		if (game == true && gameover == false && level2 == false && paintUse == false) {
			s.fire();
			p.keyPressed();
		}

		if (game2 == true && gameover == false) {
			s.fire();
			p.keyPressed();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent m) {
		s.setY(m.getY() - 64);
		shine.setY(m.getY() - 98);
	}

	@Override
	public void mouseMoved(MouseEvent m) {
// TODO Auto-generated method stub
//this method is triggered anytime there is
//mouse movement on the frame
//System.out.println(m.getX());
		shine.setY(m.getY() - 98);
		s.setY(m.getY() - 64);
	}
}

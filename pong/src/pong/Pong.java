package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Pong extends Canvas implements Runnable, KeyListener {
	/**
	 * 
	 * O programa tava pedindo pra colocar um serialID
	 */
	private static final long serialVersionUID = 1L;
		
	/*Pega o tamanho da tela do cara*/
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double screenWidth = screenSize.getWidth();
	static double screenHeight = screenSize.getHeight();
	
	public static int WIDTH = (int) (screenWidth/4); //240;
	public static int HEIGHT = (int) (screenHeight/4); //120;
	
	
	public static int SCALE = 3;
	public Player player;
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	public Pong() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addKeyListener(this);
		player = new Player(100, HEIGHT - 10);
	}

	public static void main(String[] args) {
		Pong game = new Pong();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new Thread(game).start();
	}

	public void tick() {
		player.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		player.render(g);
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		bs.show();
	}

	@Override
	public void run() {
		while (true) {
			tick();
			render();
			try {

				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			player.right = true;
			player.left = false;
		} 
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			player.right = false;
			player.left = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			player.right = false;
		} 
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			player.left = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

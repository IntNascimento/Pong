package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	public boolean right, left;
	public int x, y;
	public int width, heigth;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.heigth = 10;
	}

	public void tick() {
		if (right) 
		{
			x++;
		} 
		else if (left) 
		{
			x--;
		}
		if (x + width > Pong.WIDTH) 
		{
			x = Pong.WIDTH - width;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, heigth);
	}
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Wall {
	private int size;
	private int row;
	private int height;
	//墙的行坐标
	int x = 600;
		
	Random r = new Random();
	
	public Wall(int row, int size) {
		this.row = row;
		this.size = size;
		height = (r.nextInt(5-1)+1)*size;  //产生从一到四的随机数,及随机生成高度
	}
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		//注意墙的纵坐标，不要弄错
		g.fillRect(x, row*size-height, size, height);
		move();
	}
	
	//墙向左运动
	public void move() {
		x = x - size;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, row*size-height, size, height);		
	}
}

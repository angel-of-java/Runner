import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Wall {
	private int size;
	private int row;
	private int height;
	//ǽ��������
	int x = 600;
		
	Random r = new Random();
	
	public Wall(int row, int size) {
		this.row = row;
		this.size = size;
		height = (r.nextInt(5-1)+1)*size;  //������һ���ĵ������,��������ɸ߶�
	}
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		//ע��ǽ�������꣬��ҪŪ��
		g.fillRect(x, row*size-height, size, height);
		move();
	}
	
	//ǽ�����˶�
	public void move() {
		x = x - size;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, row*size-height, size, height);		
	}
}

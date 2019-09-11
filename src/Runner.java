import java.awt.*;
import java.util.List;
	
public class Runner {
	private int size;
	private int row;
	boolean live = true;
	int x,y;
	public int score = 0;

	Font f = new Font("宋体",Font.BOLD,40);
	
	public Runner(int row, int size) {
		this.row = row;
		this.size = size;
		x = size*5;
		y = row*size-20;
	}
	
	public void draw(Graphics g) {		
		g.setColor(Color.RED);
		g.fillRect(x, y, size, size);
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, size, size);		
	}
	
	//碰撞检测
	public void collisions(List<Wall> w, Graphics g) {
		for(int i = 0; i < w.size(); i++) {
			//只检测与到了runner位置的墙的碰撞
			if(w.get(i).x == this.x) {
				collision(w.get(i),g);
			}
			
		}
	}
	
	//检测碰撞
	public boolean collision(Wall w,Graphics g) {
		//如果方法生碰撞，结束游戏
		if(this.getRect().intersects(w.getRect())) {
			g.setFont(f);			
			live = false;
			g.drawString("game over", 190, 100);
			return true;
		}else {
			//如果没有碰撞，分数加一
			score++;
			return false;
		}
	}

	//向上移动躲避障碍
	public void keyPressed() {		
		new Thread(new Up()).start();
	}
	//runner向上移动
	class Up implements Runnable{
		@Override
		public void run() {
			while(y > size * 4) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				y = y - 10;
			}
			//向上移动完了，才能下落
			new Thread(new Down()).start();	
		}		
	}
	
	//runner向下落
	class Down implements Runnable{
		@Override
		public void run() {
			while(y < row*size-20) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				y = y + 10;
			}			
		}		
	}
}

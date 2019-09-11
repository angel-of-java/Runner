import java.awt.*;
import java.util.List;
	
public class Runner {
	private int size;
	private int row;
	boolean live = true;
	int x,y;
	public int score = 0;

	Font f = new Font("����",Font.BOLD,40);
	
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
	
	//��ײ���
	public void collisions(List<Wall> w, Graphics g) {
		for(int i = 0; i < w.size(); i++) {
			//ֻ����뵽��runnerλ�õ�ǽ����ײ
			if(w.get(i).x == this.x) {
				collision(w.get(i),g);
			}
			
		}
	}
	
	//�����ײ
	public boolean collision(Wall w,Graphics g) {
		//�����������ײ��������Ϸ
		if(this.getRect().intersects(w.getRect())) {
			g.setFont(f);			
			live = false;
			g.drawString("game over", 190, 100);
			return true;
		}else {
			//���û����ײ��������һ
			score++;
			return false;
		}
	}

	//�����ƶ�����ϰ�
	public void keyPressed() {		
		new Thread(new Up()).start();
	}
	//runner�����ƶ�
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
			//�����ƶ����ˣ���������
			new Thread(new Down()).start();	
		}		
	}
	
	//runner������
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

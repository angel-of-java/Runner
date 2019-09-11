import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Yard extends Frame{
	
	public static final int ROW = 10;
	public static final int COL = 30;
	public static final int SIZE = 20;
	
	Runner runner = new Runner(ROW, SIZE);	
	
	List<Wall> wall = new ArrayList<Wall>();
	
	public static void main(String[] args) {
		new Yard().launchFrame();
	}
	
	//���в����ô���
	public void launchFrame() {
		setTitle("Runner");
		setSize(COL * SIZE + 4, ROW * SIZE + 4);
		setLocationRelativeTo(null);
		setVisible(true);
		//���������û����ڴ��ڴ�С
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}			
		});
		//��Ӽ��̼���
		addKeyListener(new KeyMonitor());
		new Thread(new Move()).start();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		//��ʾ��񣬷������
//		for(int i = 1; i<=ROW; i++) {
//			g.drawLine(0, i*SIZE, COL*SIZE, i*SIZE);
//		}		
//		for(int i = 1; i<=COL; i++) {
//			g.drawLine(i*SIZE, 0, i*SIZE, ROW*SIZE);
//		}
		
		g.drawString("����: " + runner.score, 20, 50);
		
		runner.draw(g);
		runner.collisions(wall,g);
		
		//���ǽ�ƶ����˽���֮�⣬���ڼ������Ƴ�ǽ
		for(int i = 0; i<wall.size(); i++) {
			if(wall.get(i).x > 0) {
				wall.get(i).draw(g);
			}else {
				wall.remove(wall.get(i));
			}			
		}
		
	}
	
	//��������
	class KeyMonitor extends KeyAdapter{
 		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			//���¿ո����ϰ�
			if(key == KeyEvent.VK_SPACE && runner.live  && runner.y == (ROW*SIZE - 20)) {
				runner.keyPressed();
			} 
			//���¿ո����¿�ʼ
			else if(key == KeyEvent.VK_SPACE && !runner.live){
				setVisible(false);				
				new Yard().launchFrame();
			} 
			//����R����
			else if(!runner.live && key == KeyEvent.VK_R) {
				runner.live = true;
				new Thread(new Move()).start();
			}	
		}		
	}
	
	//����ǽ
	class Move implements Runnable{
		int i = 0;
		
		@Override
		public void run() {			
			while(runner.live) {
				repaint();
				try {
					Thread.sleep(130);
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}
				i++;
				
				//ÿ��ǽ֮��ľ���Ϊ10
				if(i >= 10) {
					Wall w = new Wall(ROW,SIZE);
					wall.add(w);
					i = 0; 					
				}
			}			
		}		
	}
	
}

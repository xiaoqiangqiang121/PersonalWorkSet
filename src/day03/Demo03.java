package day03;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Random;

/**
 * Object == ���� == ���� == ʵ�� == Instance 
 */
public class Demo03 {
	public static void main(String[] args) 
		throws Exception {
		JFrame frame = new JFrame();
		MyPanel panel = new MyPanel();
		frame.add(panel);
		frame.setSize(432, 674);
		//���� Locationλ�� Relative��� To�� ��
		frame.setLocationRelativeTo(null);
		// DefaultĬ�� Close�ر� Operation����
		frame.setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		panel.action();
	}
}//Demo03 ��Ľ���
//�Զ������
class MyPanel extends JPanel{
	BufferedImage background;
	//MyPanel�а�������
	Ground ground;
	//ΪMyPanel�������
	Column column1;
	Column column2;
	//��
	Bird bird;
	
	public MyPanel() throws Exception{
		background = ImageIO.read(
			getClass().getResource("bg.png"));
		//�����������
		ground = new Ground();
		column1 = new Column(1);
		column2 = new Column(2);
		bird = new Bird();
	}
	//��д(�޸�)paint�������Զ����ͼ
	public void paint(Graphics g){
		g.drawImage(background, 0, 0, null);
		g.drawImage(column1.image,
				column1.x-column1.width/2,
				column1.y-column1.height/2,null);
		g.drawImage(column2.image,
				column2.x-column2.width/2,
				column2.y-column2.height/2,null);
		g.drawImage(ground.image, 
				ground.x, ground.y, null);
		g.drawImage(bird.image, 
				bird.x-bird.width/2, 
				bird.y-bird.height/2, null);
	}//paint �����Ľ���
	//��MyPanel������ӷ���
	public void action()throws Exception{
		while(true){
			//������һ��
			ground.step();
			column1.step();
			column2.step();
			bird.step();
			repaint();
			Thread.sleep(1000/30);
		}
	}
}//MyPanel��Ľ���
/** �������ͣ���װ������ص����� �� ����  */
class Ground{
	//�����ͼƬimage
	BufferedImage image;
	//����ͼƬ��λ�ã� �ǵ���ͼƬ�����Ͻ�
	int x;
	int y;
	/** ����������ʼ������ */
	public Ground()throws Exception{
		image = ImageIO.read(
		  getClass().getResource("ground.png"));
		x = 0;
		y = 500;
		
	}//�������Ľ���
	
	//Ground�������,��ӵ�һ������
	//������������ƶ�һ��(step)���ǵ������Ϊ����
	//���������ݼ�����̣����ö������ݼ���ʵ��
	public void step(){
		x--;
		if(x==-109){
			x=0;
		}
	}
}//Ground ��Ľ���
/*** �������� ***/
class Column{
	//���ӵ�ͼƬ
	BufferedImage image;
	//x,y�����ӵķ�϶���ĵ�
	int x, y;
	//���ӵĿ�(width)��(height)
	int width, height;
	//��϶(gap)
	int gap;
	//��������֮��ľ���(distance)
	int distance;
	//���������У���ӹ���������ʼ������
	//n �������ӵı�ţ��磺1,2
	public Column(int n) throws Exception {
		image = ImageIO.read(
		  getClass().getResource("column.png"));
		distance = 245;
		x = 550+(n-1)*distance;
		Random random = new Random();
		// nextInt ���� [0,218) ��Χ�ڵ������
		y = random.nextInt(218) + 132;
		gap = 144;
		width = image.getWidth();
		height = image.getHeight();
	}
	//Column ������� �ƶ�һ�����㷨
	public void step(){
		x--;
		if(x<=-width/2){
			x = distance*2 - width/2;
			Random random = new Random();
			y = random.nextInt(218) + 132;
		}
	}
}//Column ��Ľ���
/** ������  */
class Bird{
	BufferedImage image;
	//���λ�ã�����������ĵ�
	int x, y;
	int width, height;
	//��Ĵ�С���������ײ��ⷶΧ
	int size;
	//�������ٶȣ��Ǹ������ͣ�С�����ͣ�
	double g;
	//���ʱ�䣬�����ƶ��ļ��ʱ��
	double t; 
	//���μ��ʱ�䣬�ƶ��ľ��룺λ��
	double s;
	//��ʼ�ٶ�
	double v0;
	//����ʱ��t�Ժ󣬵��˶��ٶ�
	double speed;
	//������,  �Ի�����Ϊ��λ
	double alpha;
	//���ù���������ʼ������
	public Bird() throws Exception {
		image = ImageIO.read(
			getClass().getResource("0.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 132;
		y = 280;
		size = 40;
		g = 4;
		t = 0.25;
		v0 = 35;
		s = 0;
		speed = v0;
		alpha = 0;
	}
	//���ƶ�һ�������ǰ��մ�ֱ�����˶���ʽ�ƶ�
	// 
	public void step(){
		//��ǰ�����׳�ʼ�ٶ�
		double v0 = speed;
		//����λ��
		s = v0 * t + g * t * t / 2;
		y = y - (int)s;
		//���㾭��ʱ��t�Ժ���ٶȣ����´μ���
		//�ĳ�ʼ�ٶ�
		double v = v0 - g * t;
		//������ʱ��t�Ժ���ٶ���Ϊ�´εĳ�ʼ�ٶ�
		speed = v;
		if(y>=280){
			y = 280;
			speed = 35;
		}
	}
	
}

























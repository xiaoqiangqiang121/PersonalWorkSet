package day02;

import java.awt.Color;//��ɫ Color.class
import javax.swing.JFrame;//���ڿ�
import javax.swing.JPanel;//��� �װ�

import java.awt.Graphics;

import java.awt.image.BufferedImage;//ͼƬ����
import javax.imageio.ImageIO;//��ȡͼƬ�Ĺ���

public class Demo01 {
	public static void main(String[] args) throws Exception {
		// ����һ�����ڿ򣬱�frame��������
		JFrame frame = new JFrame();
		// �������,��panel��������
		// new MyPanel()ִ���˹�������װ����Ƭ
		MyPanel panel = new MyPanel();
		// Background ���������ñ���ɫ=��ɫ
		panel.setBackground(Color.BLUE);
		// ��frame���õĿ������panel���õ����
		// ��������
		frame.add(panel);
		frame.setSize(432, 644 + 30);
		// setVisibleִ�е�ʱ�򣬾����ִ����
		// paint ����
		frame.setVisible(true);
		panel.action();
	}
}// Demo01 ��Ľ���
// JPanel����հ����
// MyPanel ��չ(extends) ��Ϊ�̳�
// MyPanel �ڿհ��������չ�� ����ͼƬ
// "������" ����Ҫ��;�ǳ�ʼ������ı�����

class MyPanel extends JPanel {
	// �����˱���(background)ͼ������û��ͼƬ����
	BufferedImage background;
	BufferedImage bird;
	BufferedImage ground;
	BufferedImage column1;
	BufferedImage column2;
	int x1 = 100;
	int y1 = -300;
	int x2 = 100 + 245;
	int y2 = -280;
	int x = 0;

	// ���á�����������ʼ����������ȡͼƬ
	// ����������������һ��
	public MyPanel() throws Exception {
		// ʹ��ImageIO��read����������
		// ��"bg.png"��ȡΪһ��ͼƬ���͵Ķ���
		// background ���������ͼƬ����
		background = ImageIO.read(getClass().getResource("bg.png"));
		bird = ImageIO.read(getClass().getResource("0.png"));
		ground = ImageIO.read(getClass().getResource("ground.png"));
		column1 = ImageIO.read(getClass().getResource("column.png"));
		column2 = ImageIO.read(getClass().getResource("column.png"));
	}

	// �޸�JPanel�Ļ��Ʒ���
	// Graphics ͼ
	// paint Ϳ��
	// draw �� ImageͼƬ

	public void paint(Graphics g) {
		// ��0,0λ�û���backgroundͼƬ
		g.drawImage(background, 0, 0, null);
		g.drawImage(bird, 100, 300, null);
		g.drawImage(column1, x1, y1, null);
		g.drawImage(column2, x2, y2, null);
		g.drawImage(ground, x, 500, null);
	}

	// action �ж�
	public void action() throws Exception {
		while (true) {
			x--;// x��ֵȥ��1
			if (x == -109) {
				x = 0;
			}
			x1--;
			if (x1 == -column1.getWidth()) {
				x1 = 245 * 2 - column1.getWidth();
			}
			x2--;
			if (x2 == -column2.getWidth()) {
				x2 = 245 * 2 - column2.getWidth();
			}
			repaint();// �������paint()
			Thread.sleep(1000 / 30);// ����
		}
	}
}

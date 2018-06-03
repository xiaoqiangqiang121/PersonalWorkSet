package day02;

import java.awt.Color;//颜色 Color.class
import javax.swing.JFrame;//窗口框
import javax.swing.JPanel;//面板 底板

import java.awt.Graphics;

import java.awt.image.BufferedImage;//图片类型
import javax.imageio.ImageIO;//读取图片的工具

public class Demo01 {
	public static void main(String[] args) throws Exception {
		// 创建一个窗口框，被frame变量引用
		JFrame frame = new JFrame();
		// 创建面板,被panel变量引用
		// new MyPanel()执行了构造器，装载照片
		MyPanel panel = new MyPanel();
		// Background 背景，设置背景色=蓝色
		panel.setBackground(Color.BLUE);
		// 在frame引用的框中添加panel引用的面板
		// 框添加面板
		frame.add(panel);
		frame.setSize(432, 644 + 30);
		// setVisible执行的时候，尽快的执行了
		// paint 方法
		frame.setVisible(true);
		panel.action();
	}
}// Demo01 类的结束
// JPanel代表空白面板
// MyPanel 扩展(extends) 称为继承
// MyPanel 在空白面板上扩展出 背景图片
// "构造器" 的主要用途是初始化对象的变量的

class MyPanel extends JPanel {
	// 声明了背景(background)图变量，没有图片对象
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

	// 利用“构造器”初始化变量，读取图片
	// 构造器名称与类名一致
	public MyPanel() throws Exception {
		// 使用ImageIO的read（读）方法
		// 将"bg.png"读取为一个图片类型的对象
		// background 引用了这个图片对象
		background = ImageIO.read(getClass().getResource("bg.png"));
		bird = ImageIO.read(getClass().getResource("0.png"));
		ground = ImageIO.read(getClass().getResource("ground.png"));
		column1 = ImageIO.read(getClass().getResource("column.png"));
		column2 = ImageIO.read(getClass().getResource("column.png"));
	}

	// 修改JPanel的绘制方法
	// Graphics 图
	// paint 涂画
	// draw 绘 Image图片

	public void paint(Graphics g) {
		// 在0,0位置绘制background图片
		g.drawImage(background, 0, 0, null);
		g.drawImage(bird, 100, 300, null);
		g.drawImage(column1, x1, y1, null);
		g.drawImage(column2, x2, y2, null);
		g.drawImage(ground, x, 500, null);
	}

	// action 行动
	public void action() throws Exception {
		while (true) {
			x--;// x的值去掉1
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
			repaint();// 尽快调用paint()
			Thread.sleep(1000 / 30);// 休眠
		}
	}
}

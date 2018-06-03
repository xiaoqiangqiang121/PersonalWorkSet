package day03;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Random;

/**
 * Object == 对象 == 东西 == 实例 == Instance 
 */
public class Demo03 {
	public static void main(String[] args) 
		throws Exception {
		JFrame frame = new JFrame();
		MyPanel panel = new MyPanel();
		frame.add(panel);
		frame.setSize(432, 674);
		//居中 Location位置 Relative相对 To于 空
		frame.setLocationRelativeTo(null);
		// Default默认 Close关闭 Operation操作
		frame.setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		panel.action();
	}
}//Demo03 类的结束
//自定义面板
class MyPanel extends JPanel{
	BufferedImage background;
	//MyPanel中包含地面
	Ground ground;
	//为MyPanel添加柱子
	Column column1;
	Column column2;
	//鸟
	Bird bird;
	
	public MyPanel() throws Exception{
		background = ImageIO.read(
			getClass().getResource("bg.png"));
		//创建地面对象
		ground = new Ground();
		column1 = new Column(1);
		column2 = new Column(2);
		bird = new Bird();
	}
	//重写(修改)paint方法，自定义绘图
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
	}//paint 方法的结束
	//在MyPanel类中添加方法
	public void action()throws Exception{
		while(true){
			//地面走一步
			ground.step();
			column1.step();
			column2.step();
			bird.step();
			repaint();
			Thread.sleep(1000/30);
		}
	}
}//MyPanel类的结束
/** 地面类型，封装地面相关的数据 和 功能  */
class Ground{
	//地面的图片image
	BufferedImage image;
	//地面图片的位置， 是地面图片的左上角
	int x;
	int y;
	/** 构造器：初始化数据 */
	public Ground()throws Exception{
		image = ImageIO.read(
		  getClass().getResource("ground.png"));
		x = 0;
		y = 500;
		
	}//构造器的结束
	
	//Ground类的里面,添加的一个方法
	//描述地面如何移动一步(step)，是地面的行为动作
	//方法是数据计算过程，利用对象数据计算实现
	public void step(){
		x--;
		if(x==-109){
			x=0;
		}
	}
}//Ground 类的结束
/*** 柱子类型 ***/
class Column{
	//柱子的图片
	BufferedImage image;
	//x,y是柱子的缝隙中心点
	int x, y;
	//柱子的宽(width)高(height)
	int width, height;
	//缝隙(gap)
	int gap;
	//两个柱子之间的距离(distance)
	int distance;
	//在柱子类中，添加构造器，初始化数据
	//n 代表柱子的编号，如：1,2
	public Column(int n) throws Exception {
		image = ImageIO.read(
		  getClass().getResource("column.png"));
		distance = 245;
		x = 550+(n-1)*distance;
		Random random = new Random();
		// nextInt 生成 [0,218) 范围内的随机数
		y = random.nextInt(218) + 132;
		gap = 144;
		width = image.getWidth();
		height = image.getHeight();
	}
	//Column 类中添加 移动一步的算法
	public void step(){
		x--;
		if(x<=-width/2){
			x = distance*2 - width/2;
			Random random = new Random();
			y = random.nextInt(218) + 132;
		}
	}
}//Column 类的结束
/** 鸟类型  */
class Bird{
	BufferedImage image;
	//鸟的位置，按照鸟的中心点
	int x, y;
	int width, height;
	//鸟的大小，是鸟的碰撞检测范围
	int size;
	//重力加速度，是浮点类型（小数类型）
	double g;
	//间隔时间，两次移动的间隔时间
	double t; 
	//两次间隔时间，移动的距离：位移
	double s;
	//初始速度
	double v0;
	//经过时间t以后，的运动速度
	double speed;
	//鸟的倾角,  以弧度制为单位
	double alpha;
	//利用构造器：初始化变量
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
	//鸟移动一步：鸟是按照垂直上抛运动方式移动
	// 
	public void step(){
		//当前的上抛初始速度
		double v0 = speed;
		//计算位移
		s = v0 * t + g * t * t / 2;
		y = y - (int)s;
		//计算经过时间t以后的速度，是下次计算
		//的初始速度
		double v = v0 - g * t;
		//将经过时间t以后的速度作为下次的初始速度
		speed = v;
		if(y>=280){
			y = 280;
			speed = 35;
		}
	}
	
}

























package day01;

import javax.swing.JFrame;//JFrame.class

public class Demo1 {
	public static void main(String[] args) {
		JFrame frame = new JFrame();//创建了一个窗口框
		//Frame 框架 相框 代表窗口框
		//frame 窗口框 .的 set设置 Size大小
		frame.setSize(400,300);
		//       Visible 可见 true 真的
		frame.setVisible(true);
		
		frame = new JFrame();//又创建了一个窗口
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
}





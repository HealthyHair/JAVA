package a;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame{
	
	//初始化变量
	private JTextArea showMessage = null;
	private JTextField inputMESS =  null;
	private JButton sendBtn = null;
	
	private Box boxH = null;
	private Box boxV = null;
	
	void init(){
		showMessage = new JTextArea(10,20);//十行二十列的文本区
		inputMESS = new JTextField(15);//长度15的文本框
		sendBtn = new JButton("发送");//发送按钮
		
		/**
		 * 1.生成水平盒子容器
		 * 2.创建
		 */
		boxH = Box.createHorizontalBox();
		boxH.add(inputMESS);//向boxH中添加输入文本框
		boxH.add(sendBtn);
		
		/**
		 * 1.生成垂直盒子容器
		 * 2.
		 */
		boxV = Box.createVerticalBox();
		boxV.add(new JScrollPane(showMessage));
		boxV.add(boxH);
		
		this.add(boxV);
		
		this.setTitle("聊天室客户端");
		this.setBounds(400,400,800,600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 1.创建一个客户端的窗口对象
		 * 2.窗口对象调用init
		 */
		Client clientwindow = new Client();
		clientwindow.init();

	}

}

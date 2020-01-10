package a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame{
	
	//初始化变量
	private JTextArea showMessage = null;
	private JTextField inputMess =  null;
	private JButton sendBtn = null;
//	private JTextArea inputMess = null;
	
	private Box boxH = null;
	private Box boxV = null;
	
	void init(){
		showMessage = new JTextArea(10,20);//十行二十列的文本区
		inputMess = new JTextField(15);//长度15的文本框
		sendBtn = new JButton("发送");//发送按钮
		
		/**
		 * 1.生成水平盒子容器
		 * 2.创建
		 */
		boxH = Box.createHorizontalBox();
		boxH.add(inputMess);//向boxH中添加输入文本框
		boxH.add(sendBtn);
		
		/**
		 * 1.生成垂直盒子容器
		 * 2.
		 */
		boxV = Box.createVerticalBox();
		boxV.add(new JScrollPane(showMessage));
		boxV.add(boxH);
		
		this.add(boxV);

		//为输入文本框添加事件监听
		inputMess.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//获取文本域的内容并且去前后空格
				String sendContent = inputMess.getText().trim();
System.out.println(sendContent);
				
			}
		});
//		//为输入文本框添加事件监听
//		inputMess.addActionListener(new ActionListener() {
//			
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				//获取文本域的内容并且去前后空格
//				String sendContent = inputMess.getText().trim();
//System.out.println(sendContent);
//				
//			}
//		});
		
		
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

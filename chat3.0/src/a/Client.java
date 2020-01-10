package a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
	
	private MyListener myListener = null;
	
	private Socket socket = null;
	private int serverport = 6666;
	
	void init(){
		showMessage = new JTextArea(10,20);//十行二十列的文本区
		inputMess = new JTextField(15);//长度15的文本框
		sendBtn = new JButton("发送");//发送按钮
		
		myListener = new MyListener();//new 监视对象
		inputMess.addActionListener(myListener);
		sendBtn.addActionListener(myListener);
		
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
		
		this.setTitle("聊天室客户端");
		this.setBounds(400,400,800,600);
		this.setVisible(true);
		//this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		
		try {
			socket = new Socket("127.0.0.1",serverport);
			showMessage.setText("已跟服务器建立连接！");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			showMessage.append("无法跟服务器建立连接！，请确认服务器地址是否正确并重试"+"\n");
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			showMessage.append("无法跟服务器建立连接！，请确定服务器已打开并重试!"+"\n");
//			e.printStackTrace();
		}
		

	}

	private class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String sendContent = inputMess.getText().trim();
			System.out.println(sendContent);
			
			try {
				OutputStream os = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.writeUTF(sendContent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
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

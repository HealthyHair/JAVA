package a;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument.Content;

public class Server extends JFrame {

	private JTextArea showDialog = null;

	private ServerSocket serverSocker = null;
//	private Socket socker = null;
	private int port = 0;

	void init() {
		// this.setLayout(new FlowLayout());
		showDialog = new JTextArea(10, 20);
		this.add(new JScrollPane(showDialog));

		this.setTitle("服务器端");
		this.setBounds(200, 200, 800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();

		port = 6666;
		try {
			serverSocker = new ServerSocket(port);
			showDialog.setText("服务器已就绪，等待客户端的请求。。。" + "\n");

			while (true) {
				Socket socket = serverSocker.accept();
				showDialog.append("连接已建立。" + "\n");
				
				//多线程实现多个客户端同时与服务器连接
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						//没加入一个客户端创建一个新线程
						try {
							InputStream is = socket.getInputStream();
							DataInputStream dis = new DataInputStream(is);
//							OutputStream os = socket.getOutputStream();
//							DataOutputStream dos = new DataOutputStream(os);

							// 利用while循环实现发送多条消息
							while (true) {
								String receiveContent = dis.readUTF();
//System.out.println(receiveContent);
								showDialog.append(receiveContent + "\n");
//								dos.writeUTF(receiveContent);
//								send(Content);
							}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							
							// 客户退出后，服务器异常处理
							showDialog.append("客户端已退出！");
//							e.printStackTrace();
						}
					}
				}).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//将一个客户端发送过来的消息转发给其他客户端
	void send(String content) {
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server serverWindow = new Server();
		serverWindow.init();

	}

}

package a;

import java.awt.FlowLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame {

	private JTextArea showDialog = null;

	private ServerSocket serverSocker = null;
	private Socket socker = null;
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
			socker = serverSocker.accept();
			showDialog.append("连接已建立。" + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			InputStream is = socker.getInputStream();
			DataInputStream dis = new DataInputStream(is);

			// 利用for循环实现发送多条消息
			while (true) {
				String receiveContent = dis.readUTF();
				showDialog.append(receiveContent + "\n");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// 客户退出后，服务器异常处理
			showDialog.append("客户端已退出！");
//			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server serverWindow = new Server();
		serverWindow.init();

	}

}

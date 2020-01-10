package a;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame {

	private JTextArea showDialog = null;

	private ServerSocket serverSocker = null;
//	private Socket socker = null;
	private int port = 0;

	// ����һ���������Ϊÿһ���ͻ����ṩ������̶߳���ļ��ϡ�������������߳�
	List clients = new ArrayList<ForClient>();

	void init() {
		// this.setLayout(new FlowLayout());
		showDialog = new JTextArea(10, 20);
		this.add(new JScrollPane(showDialog));

		this.setTitle("��������");
		this.setBounds(200, 200, 800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();

		port = 6666;
		try {
			serverSocker = new ServerSocket(port);
			showDialog.setText("�������Ѿ������ȴ��ͻ��˵����󡣡���" + "\n");

			// ʵ�ֶ���ͻ�������
			while (true) {
				Socket socket = serverSocker.accept();
				showDialog.append("�����ѽ�����" + "\n");

				// �����߳���Ķ���
				ForClient client = new ForClient(socket);
				clients.add(client);
				new Thread(client).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ��һ���ͻ��˷��͹�������Ϣת���������ͻ���

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		Server serverWindow = new Server();
		serverWindow.init();
	}

	private class ForClient implements Runnable {
		Socket socket = null;
		InputStream is = null;
		DataInputStream dis = null;
		OutputStream os = null;
		DataOutputStream dos = null;

		public ForClient(Socket socket) {
			this.socket = socket;
			try {
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				String message = null;
				try {
					message = dis.readUTF();
					showDialog.append(message + "\n");
					for (int i = 0; i < clients.size(); i++) {
						((ForClient) clients.get(i)).send(message);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		void send(String s) {
			try {
				dos.writeUTF(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}

//
//		private class ForClient implements Runnable{
//		Socket socket;
//		public ForClient (Socket socket) {
//		this.socket=socket;	
//		}
//		public void run() {
//		InputStream is = socket.getInputStream();
//		DataInputStream dis = new DataInputStream(is);
//		while(true) {
//			String message = dis.readUTF();
//			ShowDialog.append(message+"\n");
//			
//		}
//	

package a;

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
//	private Socket socker = null;
	private int port = 0;

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

			while (true) {
				Socket socker = serverSocker.accept();
				showDialog.append("�����ѽ�����" + "\n");
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							InputStream is = socker.getInputStream();
							DataInputStream dis = new DataInputStream(is);

							// ����whileѭ��ʵ�ַ��Ͷ�����Ϣ
							while (true) {
								String receiveContent = dis.readUTF();
								showDialog.append(receiveContent + "\n");
							}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							// �ͻ��˳��󣬷������쳣����
							showDialog.append("�ͻ������˳���");
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server serverWindow = new Server();
		serverWindow.init();

	}

}

package a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame {

	// ��ʼ������
	private JTextArea showMessage = null;
	private JTextField inputMess = null;
	private JButton sendBtn = null;
	// private JTextArea inputMess = null;

	private Box boxH = null;
	private Box boxV = null;

	private MyListener myListener = null;

	private Socket socket = null;
	private int serverport = 6666;

	// ��ʾ�����Ƿ����ı���
	private boolean isConnect = false;

	private DataInputStream dis = null;
	private DataOutputStream dos = null;

	/**
	 * ���ڳ�ʼ��
	 */
	void init() {
		showMessage = new JTextArea(10, 20);// ʮ�ж�ʮ�е��ı���
		inputMess = new JTextField(15);// ����15���ı���
		sendBtn = new JButton("����");// ���Ͱ�ť

		myListener = new MyListener();// new ���Ӷ���
		inputMess.addActionListener(myListener);
		sendBtn.addActionListener(myListener);

		// 1.����ˮƽ�������� 2.����
		boxH = Box.createHorizontalBox();
		boxH.add(inputMess);// ��boxH����������ı���
		boxH.add(sendBtn);

		// 1.���ɴ�ֱ�������� 2.
		boxV = Box.createVerticalBox();
		boxV.add(new JScrollPane(showMessage));
		boxV.add(boxH);

		this.add(boxV);

		this.setTitle("�����ҿͻ���");
		this.setBounds(400, 400, 800, 600);
		this.setVisible(true);
		// this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();

		// �رչܵ�
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				disConnect();
			}
		});

	}

	// ���������������
	void connect() {
		try {
			socket = new Socket("127.0.0.1", serverport);
			showMessage.setText("�Ѹ��������������ӣ�" + "\n");// ����append
			isConnect = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			showMessage.append("�޷����������������ӣ�����ȷ�Ϸ�������ַ�Ƿ���ȷ������" + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			showMessage.append("�޷����������������ӣ�����ȷ���������Ѵ򿪲�����!" + "\n");
		}
	}

	// �Ͽ��������������
	void disConnect() {
		isConnect = false;
		try {

			if (dis != null)
				dis.close();
			if (dos != null)
				dos.close();

			if (socket != null)
				socket.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * 1ʵ��˫���� 2���շ���������Ϣ
	 */
	void receiveMessage() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					dis = new DataInputStream(socket.getInputStream());

					// ���Ͻ��շ��������͵���Ϣ
					while (isConnect) {
						String mess = dis.readUTF();
						showMessage.append(mess + "\n");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	void start() {
		// ���ô��ڽ���
		this.init();

		// ������������������󲢽���������
		this.connect();

		// ���ý�����Ϣ�Ĵ���
		this.receiveMessage();

	}

	// ���ͼ�����
	private class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String sendContent = inputMess.getText().trim();
			System.out.println(sendContent);

			try {
				dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(sendContent);

				// ���ͺ����
				inputMess.setText("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 1.����һ���ͻ��˵Ĵ��ڶ��� 2.���ڶ������init
		 */
		Client clientwindow = new Client();
		clientwindow.start();
	}

}

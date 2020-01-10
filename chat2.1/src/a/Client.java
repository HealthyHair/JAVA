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
	
	//��ʼ������
	private JTextArea showMessage = null;
	private JTextField inputMess =  null;
	private JButton sendBtn = null;
//	private JTextArea inputMess = null;
	
	private Box boxH = null;
	private Box boxV = null;
	
	private MyListener myListener = null;
	
	void init(){
		showMessage = new JTextArea(10,20);//ʮ�ж�ʮ�е��ı���
		inputMess = new JTextField(15);//����15���ı���
		sendBtn = new JButton("����");//���Ͱ�ť
		
		myListener = new MyListener();
		inputMess.addActionListener(myListener);
		sendBtn.addActionListener(myListener);
		
		/**
		 * 1.����ˮƽ��������
		 * 2.����
		 */
		boxH = Box.createHorizontalBox();
		boxH.add(inputMess);//��boxH����������ı���
		boxH.add(sendBtn);
		
		/**
		 * 1.���ɴ�ֱ��������
		 * 2.
		 */
		boxV = Box.createVerticalBox();
		boxV.add(new JScrollPane(showMessage));
		boxV.add(boxH);
		
		this.add(boxV);
		
		this.setTitle("�����ҿͻ���");
		this.setBounds(400,400,800,600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		
	}

	private class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String sendContent = inputMess.getText().trim();
System.out.println(sendContent);
		}
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 1.����һ���ͻ��˵Ĵ��ڶ���
		 * 2.���ڶ������init
		 */
		Client clientwindow = new Client();
		clientwindow.init();

	}

}

package jframe.main;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ChatClient extends JFrame {
	// 채팅방

	JTextField sendTF;
	JLabel la_msg;

	JTextArea ta;
	JScrollPane sp_ta, sp_list;

	JList<String> li_inwon;
	JButton bt_exit;
	JButton bt_perChat;
	JPanel p;
	
	OutputStream out;
	 Socket s = new Socket();
	public ChatClient() throws IOException {
		setTitle("채팅방");
	
		
		
		sendTF = new JTextField(15);
		la_msg = new JLabel("Message");
		la_msg.setFont(new Font("굴림", Font.BOLD, 12));
		
		sp_ta = new JScrollPane();
		sp_list = new JScrollPane();
		bt_exit = new JButton("나가기");
		bt_perChat = new JButton("귓속말");
		p = new JPanel();

		sp_ta.setBounds(12, 6, 213, 390);
		la_msg.setBounds(12, 392, 60, 30);
		sendTF.setBounds(12, 421, 203, 30);

		sp_list.setBounds(269, 10, 87, 239);
		bt_exit.setBounds(255, 332, 87, 30);
		bt_perChat.setBounds(255, 332, 87, 30);
		p.setLayout(null);
		p.setBackground(new Color(192, 192, 192));
		p.add(sp_ta);
		p.add(bt_perChat);
		ta = new JTextArea();
		sp_ta.setViewportView(ta);
		ta.setLineWrap(true); // TextArea 가로길이를 벗어나는 text발생시 자동 줄바꿈
		p.add(la_msg);
		p.add(sendTF);
		p.add(sp_list);

		li_inwon = new JList<String>();
		sp_list.setViewportView(li_inwon);
		p.add(bt_exit);

		getContentPane().add(p);
		setBounds(300, 200, 393, 500);
		sendTF.requestFocus();
		
		
		
	}
	
}

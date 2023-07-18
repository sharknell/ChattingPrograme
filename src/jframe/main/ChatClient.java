package jframe.main;

import java.awt.Color;

import javax.swing.ImageIcon;
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
import javax.swing.SwingConstants;

public class ChatClient extends JFrame {
	// 채팅방
	ImageIcon img = new ImageIcon("./bt_img/cloud.png");
	JTextField sendTF;

	JTextArea ta;
	JScrollPane sp_ta, sp_list;

	JList<String> li_inwon;
	JButton bt_exit; // bt_img 파일ㅇ
	JButton bt_perChat; // bt_img 파일ㅇ
	JPanel p;

	OutputStream out;
	Socket s = new Socket();
	private JLabel lblNewLabel_1;

	public ChatClient() throws IOException {
		setForeground(new Color(64, 128, 128));
		setTitle("채팅방");

		sendTF = new JTextField(15);

		sp_ta = new JScrollPane();
		sp_list = new JScrollPane();
		bt_exit = new JButton("나가기");
		bt_exit.setFont(new Font("777별나라달님", Font.PLAIN, 17));
		bt_exit.setBackground(new Color(255, 255, 255));

		bt_exit.setLayout(null);
		
		bt_exit.setBorderPainted(false);
		
		p = new JPanel();
		bt_exit.setIcon(null);
		sp_ta.setBounds(12, 6, 183, 390);
		sendTF.setBounds(0, 420, 203, 41);

		sp_list.setBounds(207, 3, 74, 183);
		bt_exit.setBounds(207, 423, 74, 30);
		p.setLayout(null);
		p.setBackground(new Color(64, 128, 128));
		p.add(sp_ta);
		ta = new JTextArea();
		ta.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 12));
		sp_ta.setViewportView(ta);
		ta.setLineWrap(true);
		p.add(sendTF);
		p.add(sp_list);

		JLabel lblNewLabel = new JLabel("대화인원");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("나눔손글씨 펜", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		sp_list.setColumnHeaderView(lblNewLabel);
		
				li_inwon = new JList<String>();
				sp_list.setViewportView(li_inwon);
		p.add(bt_exit);

		getContentPane().add(p);
		
		bt_perChat = new JButton("귓속말");
		bt_perChat.setFont(new Font("777별나라달님", Font.PLAIN, 17));
		bt_perChat.setBorderPainted(false);
		bt_perChat.setBackground(Color.WHITE);
		bt_perChat.setBounds(207, 391, 74, 30);
		p.add(bt_perChat);
		
		lblNewLabel_1 = new JLabel("Message");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Gadugi", Font.ITALIC, 13));
		lblNewLabel_1.setBounds(12, 397, 74, 21);
		p.add(lblNewLabel_1);
		setBounds(300, 200, 302, 500);
		sendTF.requestFocus();

	}
}

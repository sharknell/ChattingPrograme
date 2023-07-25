package jframe.main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
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
import javax.swing.ListModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.SwingConstants;

public class ChatClient extends JFrame {
	DefaultListModel<String> defaultListModel = new DefaultListModel<>();
	// 채팅방
	ImageIcon img = new ImageIcon("./bt_img/cloud.png");
	JTextField sendTF;
	JButton bt_kick;
	JTextArea ta;
	JScrollPane sp_ta, sp_list;

	JList<String> li_inwon;
	JButton bt_exit; 
	JButton bt_perChat; 
	JButton bt_sendFile;
	JButton bt_vanWord;
	JPanel p;

	OutputStream out;
	Socket s = new Socket();
	private JLabel lblNewLabel_1;
	

	public ChatClient() throws IOException {
		getContentPane().setBackground(new Color(245, 245, 245));
		setBackground(new Color(245, 245, 245));
		p = new JPanel();
		ta = new JTextArea();
		li_inwon = new JList<>(defaultListModel);
		
				
		setForeground(new Color(64, 128, 128));
		setTitle("채팅방");

		sendTF = new JTextField(15);

		sp_ta = new JScrollPane();
		sp_list = new JScrollPane();
		
		bt_exit = new JButton("");
		bt_exit.setFont(new Font("777별나라달님", Font.PLAIN, 17));
		bt_exit.setBackground(new Color(245, 245, 245));
		bt_exit.setLayout(null);
		bt_exit.setBorderPainted(false);
		bt_exit.setIcon(new ImageIcon("image/door.png"));
		
		
		sp_ta.setBounds(12, 6, 220, 380);
		sendTF.setBounds(0, 425, 234, 30);

		sp_list.setBounds(262, 9, 70, 200);
		bt_exit.setBounds(299, 420, 45, 41);
		p.setLayout(null);
		p.setBackground(new Color(245, 245, 245));
		p.add(sp_ta);
		
		ta.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 12));
		sp_ta.setViewportView(ta);
		ta.setLineWrap(true);
		p.add(sendTF);
		p.add(sp_list);

		JLabel lblNewLabel = new JLabel("대화인원");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("HY엽서M", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		sp_list.setColumnHeaderView(lblNewLabel);
		
		
		sp_list.setViewportView(li_inwon);
		p.add(bt_exit);

		getContentPane().add(p);
		
		bt_perChat = new JButton("");
		bt_perChat.setOpaque(false);
		bt_perChat.setIcon(new ImageIcon("image/wishper.png"));
		bt_perChat.setPreferredSize(new Dimension(30, 30));
		bt_perChat.setFont(new Font("777별나라달님", Font.PLAIN, 17));
		bt_perChat.setBorderPainted(false);
		bt_perChat.setBackground(Color.WHITE);
		bt_perChat.setBounds(185, 400, 20, 20);
		p.add(bt_perChat);
		
		lblNewLabel_1 = new JLabel("Message");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Gadugi", Font.ITALIC, 13));
		lblNewLabel_1.setBounds(12, 400, 74, 21);
		p.add(lblNewLabel_1);
		
		bt_kick = new JButton();
		bt_kick.setIcon(new ImageIcon("image/강퇴.png"));
		bt_kick.setBackground(new Color(245, 245, 245));
		bt_kick.setBounds(272, 219, 60, 50);
		bt_kick.setBorderPainted(false);
		p.add(bt_kick);
		
		bt_sendFile = new JButton();
		bt_sendFile.setIcon(new ImageIcon("image/파일전송.png"));
		bt_sendFile.setBackground(new Color(245, 245, 245));
		bt_sendFile.setBounds(210, 400, 20, 20);
		bt_sendFile.setBorderPainted(false);
		p.add(bt_sendFile);
		
		setBounds(300, 200, 360, 500);
		sendTF.requestFocus();

	}
}

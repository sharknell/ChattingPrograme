package jframe.main;

import java.awt.BorderLayout;
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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	JButton bt_changeFont;

	JButton emoticon1;
   	JButton emoticon3; 
   	JButton emoticon2;
   	JButton emoticon4;
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

		getContentPane().add(p, BorderLayout.CENTER);
		
		addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                int panelWidth = p.getWidth();
                int panelHeight = p.getHeight();
                int frameWidth = getWidth();
                int frameHeight = getHeight();

                //각 컴포넌트들의 위치 지정 및 프레임 크기에 맞춰 조절하기
                sp_ta.setBounds(12, 6, (int)(frameWidth * 0.6), (int)(frameHeight * 0.76));
                sp_list.setBounds((int)(frameWidth * 0.73), 9, (int)(frameWidth * 0.2), (int)(frameHeight * 0.4));
                sendTF.setBounds(8, (int)(frameHeight * 0.85), (int)(frameWidth * 0.66), 30);
                lblNewLabel_1.setBounds(8, (int)(frameHeight * 0.8), 74, 21);
                bt_perChat.setBounds((int)(frameWidth * 0.52), (int)(frameHeight * 0.8), 20, 20);
                bt_sendFile.setBounds((int)(frameWidth * 0.6), (int)(frameHeight * 0.8), 20, 20);
                bt_changeFont.setBounds((int)(frameWidth * 0.44), (int)(frameHeight * 0.8), 20, 20);
                bt_kick.setBounds((int)(frameWidth * 0.78), (int)(frameHeight * 0.45), 60, 50);
                bt_exit.setBounds((int)(frameWidth * 0.83), (int)(frameHeight * 0.83), 45, 41);
            
            }
        });
		
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
		
		bt_changeFont = new JButton("");
        bt_changeFont.setBounds(160, 400, 20, 20);
        bt_changeFont.setBackground(new Color(245, 245, 245));
        bt_changeFont.setIcon(new ImageIcon("image/fontSize.png"));
        bt_changeFont.setBorderPainted(false);
        bt_changeFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FontSizeChanger.changeFontSize(ChatClient.this);
            }
        });
        p.add(bt_changeFont);
		
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


		emoticon1.setBounds(232, 279, 57, 30);
     		p.add(emoticon1);
      
      		emoticon2 = new JButton("울음");
     	 	emoticon2.setBounds(232, 357, 60, 23);
     		 p.add(emoticon2);
      
      		emoticon3 = new JButton("하트");
     		 emoticon3.setBounds(232, 319, 60, 23);
     		 p.add(emoticon3);
      
      		emoticon4 = new JButton("야유");
     		 emoticon4.setBounds(235, 392, 60, 23);
      		p.add(emoticon4);
		
		setBounds(300, 200, 360, 500);
		sendTF.requestFocus();

	}
}

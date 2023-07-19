package jframe.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import jframe.menu.EngVerFirstSwing;

public class GaebalTalk extends JFrame implements ActionListener, Runnable {
	JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel contentPane;
	private boolean panelsVisible = false; // 패널들의 가시성 상태를 저장하는 변수
	ChatClient client;
	JPanel panel_chat;
	String nickName="";
	// 소켓 입출력객체
	BufferedReader in;
	OutputStream out;
	String selectedRoom;

	JList<String> roomInfo, roommUser, waitInfo;

	// 로그 파일 경로
	private static final String LOG_DIRECTORY = "D:/chat_log";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GaebalTalk frame = new GaebalTalk();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public GaebalTalk() throws IOException {

		client = new ChatClient();
		roomInfo = new JList<String>();
		roomInfo.setBackground(new Color(187, 207, 210));
		roomInfo.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
		roomInfo.setBorder(new TitledBorder("방정보"));

		roomInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = roomInfo.getSelectedValue(); // "자바방--1"
				if (str == null)
					return;
				System.out.println("STR=" + str);
				selectedRoom = str.substring(0, str.indexOf("-")); // "자바방" <---- substring(0,3)
				// 대화방 내의 인원정보
				sendMsg("130|" + selectedRoom);
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(185, 207, 210));
		contentPane.setForeground(new Color(185, 207, 210));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(60, 0, 284, 60);
		contentPane.add(panel_4);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(27, 35, 42));
		panel.setBounds(0, 0, 60, 561);
		contentPane.add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(10, 20, 40, 40);
		panel_1.setBackground(new Color(27, 35, 42));
		panel_1.setBorder(null);
		panel.add(panel_1);

		ImageIcon imageIcon = new ImageIcon("image/human.png");
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblImage = new JLabel(imageIcon);
		panel_1.add(lblImage);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(27, 35, 42));
		panel_2.setBounds(10, 90, 40, 40);
		panel.add(panel_2);

		ImageIcon imageIcon_1 = new ImageIcon("image/speech.png");
		JLabel lblImage_1 = new JLabel(imageIcon_1);
		lblImage_1.setBounds(106, 270, 38, 20);
		panel_2.add(lblImage_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(27, 35, 42));
		panel_3.setBounds(10, 500, 40, 42);
		panel.add(panel_3);

		ImageIcon imageIcon_2 = new ImageIcon("image/setting.png");
		JLabel lblImage_2 = new JLabel(imageIcon_2);
		lblImage_2.setBounds(106, 270, 38, 20);
		panel_3.add(lblImage_2);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(187, 207, 210));
		panel_5.setBounds(60, 60, 284, 220);
		contentPane.add(panel_5);

		ImageIcon panel_5Icon = new ImageIcon("image/dePro.png");
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel panel_5Image = new JLabel(panel_5Icon);
		panel_5.add(panel_5Image);

		JPanel myProfile = new JPanel();
		myProfile.setBackground(new Color(187, 207, 210));
		myProfile.setBounds(100, 300, 40, 45);
		contentPane.add(myProfile);

		ImageIcon profile = new ImageIcon("image/myProfile.png");
		myProfile.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel myPro = new JLabel(profile);
		myProfile.add(myPro);

		JPanel display_Icon = new JPanel();
		display_Icon.setBackground(new Color(187, 207, 210));
		display_Icon.setBounds(182, 300, 40, 45);
		contentPane.add(display_Icon);

		ImageIcon color = new ImageIcon("image/dark_or_light.png");
		display_Icon.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel displayColor = new JLabel(color);
		display_Icon.add(displayColor);

		JPanel service = new JPanel();
		service.setBackground(new Color(187, 207, 210));
		service.setBounds(264, 310, 40, 45);
		contentPane.add(service);

		ImageIcon serviceCenter = new ImageIcon("image/callCenter.png");
		service.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel callCenter = new JLabel(serviceCenter);
		service.add(callCenter);

		JPanel font = new JPanel();
		font.setBackground(new Color(187, 207, 210));
		font.setBounds(100, 380, 40, 45);
		contentPane.add(font);

		ImageIcon kor_font = new ImageIcon("image/fontSize.png");
		font.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel korFont = new JLabel(kor_font);
		font.add(korFont);

		JPanel language = new JPanel();
		language.setBackground(new Color(187, 207, 210));
		language.setBounds(182, 380, 40, 45);
		contentPane.add(language);

		ImageIcon kor_lang = new ImageIcon("image/korVer.png");
		language.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel korVer = new JLabel(kor_lang);
		language.add(korVer);

		language.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EngVerFirstSwing engVer = new EngVerFirstSwing();
				engVer.setVisible(true);
				GaebalTalk.this.dispose();
			}
		});

		JPanel game = new JPanel();
		game.setBackground(new Color(187, 207, 210));
		game.setBounds(264, 380, 40, 45);
		contentPane.add(game);

		ImageIcon gaeGame = new ImageIcon("image/game.png");
		game.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel gaeGameIcon = new JLabel(gaeGame);
		game.add(gaeGameIcon);

		JPanel notification = new JPanel();
		notification.setBackground(new Color(187, 207, 210));
		notification.setBounds(100, 460, 40, 45);
		contentPane.add(notification);

		ImageIcon siren = new ImageIcon("image/siren.png");
		notification.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel notificationIcon = new JLabel(siren);
		notification.add(notificationIcon);

		JPanel gaebalVer = new JPanel();
		gaebalVer.setBackground(new Color(187, 207, 210));
		gaebalVer.setBounds(182, 460, 40, 45);
		contentPane.add(gaebalVer);

		ImageIcon ver = new ImageIcon("image/gaebalVer.png");
		gaebalVer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel gaever = new JLabel(ver);
		gaebalVer.add(gaever);

		JPanel panel_chat = new JPanel();
		panel_chat.setLayout(new BorderLayout());
		panel_chat.setBackground(new Color(187, 207, 210));
		panel_chat.setBounds(60, 60, 284, 501);
		contentPane.add(panel_chat);

		JScrollPane scrollPane = new JScrollPane(roomInfo);
		scrollPane.setBounds(10, 10, 266, 478);
		panel_chat.setLayout(null);
		panel_chat.add(scrollPane);

		// 초기에 보이지 않도록 설정
		panel_5.setVisible(false);
		myProfile.setVisible(false);
		display_Icon.setVisible(false);
		service.setVisible(false);
		font.setVisible(false);
		language.setVisible(false);
		game.setVisible(false);
		notification.setVisible(false);
		gaebalVer.setVisible(false);

		panel_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panelsVisible = !panelsVisible; // 패널들의 가시성 상태를 토글

				if (panelsVisible) {
					// 패널들을 보여줍니다.
					panel_chat.setVisible(false);
					panel_5.setVisible(true);
					myProfile.setVisible(true);
					display_Icon.setVisible(true);
					service.setVisible(true);
					font.setVisible(true);
					language.setVisible(true);
					game.setVisible(true);
					notification.setVisible(true);
					gaebalVer.setVisible(true);
				} else {
					// 패널들을 가립니다.
					panel_chat.setVisible(true);
					panel_5.setVisible(false);
					myProfile.setVisible(false);
					display_Icon.setVisible(false);
					service.setVisible(false);
					font.setVisible(false);
					language.setVisible(false);
					game.setVisible(false);
					notification.setVisible(false);
					gaebalVer.setVisible(false);
				}
			}
		});

		// this
		
		
		// 패널 크기를 JFrame 크기에 맞게 설정
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				int panelWidth = panel.getWidth();
				int panelHeight = panel.getHeight();
				int frameWidth = getWidth();
				int frameHeight = getHeight();

				panel.setBounds(0, 0, 60, getHeight());
				panel_4.setBounds(60, 0, getWidth(), 60);
				panel_chat.setBounds(60, 60, getWidth(), getHeight());
				scrollPane.setBounds(0, 0, panel_chat.getWidth(), panel_chat.getHeight());
				scrollPane.setBounds(0, 0, panel_chat.getWidth(), panel_chat.getHeight());

				int x = 10; // x 좌표
				int y = (int) (panelHeight * 0.8); // y 좌표 (panel 높이의 90% 위치에 설정)

				panel_3.setBounds(x, y, 40, 42);

				int panel_5X = (int) (frameWidth * 0.20); // panel_5 x 좌표
				int panel_5Y = (int) (frameHeight * 0.10); // panel_5 y 좌표
				panel_5.setBounds(panel_5X, panel_5Y, 284, 220);

				// myProfile 위치 조정
				int myProfileX = (int) (frameWidth * 0.25); // myProfile x 좌표
				int myProfileY = (int) (frameHeight * 0.5); // myProfile y 좌표
				myProfile.setBounds(myProfileX, myProfileY, 40, 45);

				// display_Icon 위치 조정
				int displayIconX = (int) (frameWidth * 0.52); // display_Icon x 좌표
				int displayIconY = (int) (frameHeight * 0.5); // display_Icon y 좌표
				display_Icon.setBounds(displayIconX, displayIconY, 40, 45);

				// service 위치 조정
				int serviceX = (int) (frameWidth * 0.80); // service x 좌표
				int serviceY = (int) (frameHeight * 0.50); // service y 좌표
				service.setBounds(serviceX, serviceY, 40, 45);

				// font 위치 조정
				int fontX = (int) (frameWidth * 0.25); // font x 좌표
				int fontY = (int) (frameHeight * 0.65); // font y 좌표
				font.setBounds(fontX, fontY, 40, 45);

				// language 위치 조정
				int languageX = (int) (frameWidth * 0.52); // language x 좌표
				int languageY = (int) (frameHeight * 0.65); // language y 좌표
				language.setBounds(languageX, languageY, 40, 45);

				// game 위치 조정
				int gameX = (int) (frameWidth * 0.80); // game x 좌표
				int gameY = (int) (frameHeight * 0.65); // game y 좌표
				game.setBounds(gameX, gameY, 40, 45);

				// notification 위치 조정
				int notificationX = (int) (frameWidth * 0.25); // notification x 좌표
				int notificationY = (int) (frameHeight * 0.80); // notification y 좌표
				notification.setBounds(notificationX, notificationY, 40, 45);

				// gaebalVer 위치 조정
				int gaebalVerX = (int) (frameWidth * 0.52); // gaebalVer x 좌표
				int gaebalVerY = (int) (frameHeight * 0.80); // gaebalVer y 좌표
				gaebalVer.setBounds(gaebalVerX, gaebalVerY, 40, 45);
			}
		});
		
		connect(); // 서버연결시도 (in,out객체생성)
		new Thread(this).start(); // 서버메시지 대기
		
		sendMsg("100|"); // (대기실)접속 알림
		 nickName = JOptionPane.showInputDialog(this, "대화명:");
		sendMsg("150|" + nickName); // 대화명 전달

		eventUp();

	}// 생성자 끝

	private void eventUp() { // 이벤트소스-이벤트처리부 연결
        // 대기실(MainChat)
       
        // 대화방(ChatClient)
        client.sendTF.addActionListener(this);
        client.bt_perChat.addActionListener(this);
        client.bt_exit.addActionListener(this);
        
        panel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String title = JOptionPane.showInputDialog(GaebalTalk.this, "방제목:");

                // 방제목을 서버에게 전달
                sendMsg("160|" + title);
                client.setTitle("채팅방-[" + title + "]");
                sendMsg("175|"); // 대화방내 인원정보 요청
                setVisible(false);
                client.setVisible(true); // 대화방 이동
            }
        });
        
        // "방들어가기" 이미지 클릭 이벤트
        panel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectedRoom == null) {
                    JOptionPane.showMessageDialog(GaebalTalk.this, "방을 선택하세요!");
                    return;
                }
                sendMsg("200|" + selectedRoom);
                sendMsg("175|"); // 대화방내 인원정보 요청
                setVisible(false);
                client.setVisible(true);
            }
        });
       
        
	 client.bt_perChat.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	// 선택된 항목 가져오기
	        String[] userName = client.li_inwon.getSelectedValuesList().toArray(new String[0]);
	        if (userName.length == 0) {
	        	JOptionPane.showMessageDialog(GaebalTalk.this, "1:1 대화할 유저를 선택하세요.");
	        	return;
	        }
	        
	       /* 이거 안됨
	         if (userName.equals(nickName)) {
				JOptionPane.showConfirmDialog(GaebalTalk.this, "자신을 선택 할 수 없습니다.");
			}*/
	        
	        String message = JOptionPane.showInputDialog(GaebalTalk.this, "귓속말 메시지");
	        System.out.println("선택된 항목 : " + userName );
	        if (message != null && !message.isEmpty()) {
	            for (String user : userName) {
	            	System.out.println("310|" + user + "|" + message);
	                sendMsg("310|" + user + "|" + message);
	                writeChatLog(client.getTitle(),"[귓속말]" + "[" + user + "]  "  + message);
	                break;
	            }
	        }else {
				JOptionPane.showMessageDialog(GaebalTalk.this, "메시지를 입력해주세요.");
			}
	    }
	       
	    
	 });

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == client.bt_exit) { // 대화방 나가기 요청
			sendMsg("400|");
			client.setVisible(false);
			setVisible(true);
		} else if (ob == client.sendTF) { // (TextField입력)메시지 보내기 요청
			String msg = client.sendTF.getText();
			if (msg.length() > 0) {
				sendMsg("300|" + msg);
				client.sendTF.setText("");
				// writeChatLog(client.getTitle(), msg); // 채팅 내용 로그에 기록

			}
		}
	} // actionPerformed

	public void connect() { // (소켓)서버연결 요청
		try {
			// Socket s = new Socket(String host<서버ip>, int port<서비스번호>);
			Socket s = new Socket("14.42.124.93", 6476); // 연결시도
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			// in: 서버메시지 읽기객체 서버-----msg------>클라이언트
			out = s.getOutputStream();
			// out: 메시지 보내기, 쓰기객체 클라이언트-----msg----->서버
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // End of connect

	public void sendMsg(String msg) { // 서버에게 메시지 보내기
		try {
			out.write((msg + "\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // End of sendMsg

	public void run() { // 서버가 보낸 메시지 읽기
		try {
			while (true) {
				String msg = in.readLine(); // msg: 서버가 보낸 메시지
				// msg==> "300|안녕하세요" "120|자바방--1,오라클방--1,JDBC방--1"
				String msgs[] = msg.split("\\|");
				String protocol = msgs[0];
				switch (protocol) {
				case "300":
					client.ta.append(msgs[1] + "\n");
					client.ta.setCaretPosition(client.ta.getText().length());
					writeChatLog(client.getTitle(), msgs[1]);
					break;
				case "160": // 방만들기
					// 방정보를 List에 뿌리기
					if (msgs.length > 1) {
						// 개설된 방이 한개 이상이었을때 실행
						// 개설된 방없음 ----> msg="120|" 였을때 에러
						String roomNames[] = msgs[1].split(",");
						// "자바방--1,오라클방--1,JDBC방--1"
						roomInfo.setListData(roomNames);
					}
					break;
				/*
				 * case "170": // (대기실에서) 대화방 인원정보 String roommUsers[] = msgs[1].split(",");
				 * roommUser.setListData(roommUsers); break;
				 */
				case "175": // (대화방에서) 대화방 인원정보
					String myroommUsers[] = msgs[1].split(",");
					client.li_inwon.setListData(myroommUsers);
					break;
				/*
				 * case "180": // 대기실 인원정보 String waitNames[] = msgs[1].split(",");
				 * waitInfo.setListData(waitNames); break;
				 */
				case "200": // 대화방 입장
					client.ta.append("[" + msgs[1] + "]님이 입장하셨습니다.\n");
					client.ta.setCaretPosition(client.ta.getText().length());
					break;
				case "400": // 대화방 퇴장
					client.ta.append("[" + msgs[1] + "]님이 퇴장하셨습니다.\n");
					client.ta.setCaretPosition(client.ta.getText().length());

					// 퇴장 시 방장이 퇴장 하면 그 다음 번 유저가 방장상속

					// 방 퇴장 시 남은 인원이 1명도 없다면 방 삭제
					break;

				case "320": // 귓속말 대화
					String a = msgs[1];
					// highlightText(a, Color.GREEN);
					client.ta.append(a + "\n");
					// client.ta.setForeground(Color.green);
					client.ta.setCaretPosition(client.ta.getText().length());

					break;

				case "202": // 개설된 방의 타이틀 제목 얻기
					client.setTitle("채팅방-[" + msgs[1] + "]");
					break;
				} // End of switch-case
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// End of run

	/*
	 * 여기 임시 private JTextPane textPane;
	 * 
	 * private void highlightText(String searchText, Color color) { textPane = new
	 * JTextPane(); StyledDocument doc = textPane.getStyledDocument(); Style style =
	 * textPane.addStyle("Highlight", null); StyleConstants.setForeground(style,
	 * color);
	 * 
	 * String text = textPane.getText(); int pos = 0;
	 * 
	 * while ((pos = text.indexOf(searchText, pos)) >= 0) { try {
	 * doc.setCharacterAttributes(pos, searchText.length(), style, true); pos +=
	 * searchText.length(); } catch (Exception e) { e.printStackTrace(); } } }// 임시
	 * 끝
	 */

	private void createChatLog(String roomTitle) {
		try {
			// 날짜와 시간을 포맷에 맞게 생성
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String fileName = roomTitle + "_" + dateFormat.format(new Date()) + ".txt";

			// 로그 디렉토리 생성 (존재하지 않을 경우)
			File directory = new File(LOG_DIRECTORY);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			// 로그 파일 생성 (존재하지 않을 경우)
			File file = new File(directory, fileName);
			if (!file.exists()) {
				if (file.createNewFile()) {
					System.out.println("파일 생성: " + file.getAbsolutePath());
				} else {
					System.out.println("파일 생성 실패");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeChatLog(String roomTitle, String message) {
		try {
			// 로그 파일 경로
			String logPath = LOG_DIRECTORY + "/" + roomTitle + ".txt";

			// 로그 파일 생성 (존재하지 않을 경우)
			File file = new File(logPath);
			if (!file.exists()) {
				if (file.createNewFile()) {
					System.out.println("파일 생성: " + file.getAbsolutePath());
				} else {
					System.out.println("파일 생성 실패");
				}
			}

			// 로그 파일에 내용 작성
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String log = "[" + dateFormat.format(new Date()) + "] " + message;
			writer.write(log);
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

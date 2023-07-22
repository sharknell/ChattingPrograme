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
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.Cleaner.Cleanable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import chatdb.MemberDTO;
import jframe.menu.EngVerFirstSwing;
import jframe.menu.ProfileWithDraw;
import jframe.menu.ProfliePasswordChangeScreen;
import javax.swing.border.LineBorder;

public class GaebalTalk extends JFrame implements ActionListener, Runnable {
	List<String> vanWord = new ArrayList<>();
	Room room;
	String title;
	JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JPanel panel_4;
	private JPanel contentPane;
	private boolean panelsVisible = false; // 패널들의 가시성 상태를 저장하는 변수
	ChatClient client;
	JPanel panel_chat;
	String nickName = "";
	// 소켓 입출력객체
	BufferedReader in;
	OutputStream out;
	String selectedRoom;
	DefaultListModel<String> listModel;
	DefaultListModel<String> listModel2;
	DefaultListModel<String> roominfoDefault = new DefaultListModel<>();
	JList<String> roomInfo, roommUser, waitInfo;

	MemberDTO member = new MemberDTO();

	public static JLabel dbName = new JLabel("");
	public static JLabel dbId = new JLabel("");
	public static JLabel dbPhonenumber = new JLabel("");

	public static JLabel weatherIcon = new JLabel("");
	public static JLabel temperatureLabel = new JLabel("");

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
		setBackground(new Color(245, 245, 245));
		room = new Room();
		client = new ChatClient();
		roomInfo = new JList<String>(roominfoDefault);
		roomInfo.setBackground(new Color(245, 245, 245));
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
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setForeground(new Color(185, 207, 210));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_4 = new JPanel();
		panel_4.setBounds(60, 0, 284, 60);
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(panel_4, BorderLayout.NORTH);

		// Weather c = new Weather();
		// c.weatherAPI();

		// 온도

		// temperatureLabel.setText(c.temperature + "C");
		// weatherIcon.setBounds(25, 0, 50, 50);
		// temperatureLabel.setBounds(75, 0, 50, 50);

		// panel_4.add(weatherIcon);
		// panel_4.add(temperatureLabel);

		// temperatureLabel.setHorizontalAlignment(JLabel.CENTER);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(new Color(27, 35, 42));
		panel.setBounds(0, 0, 60, 561);
		contentPane.add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(10, 20, 40, 45);
		panel_1.setBackground(new Color(27, 35, 42));
		panel_1.setBorder(null);
		panel.add(panel_1);

		ImageIcon imageIcon = new ImageIcon("image/human.png");
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblImage = new JLabel(imageIcon);
		panel_1.add(lblImage);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(27, 35, 42));
		panel_2.setBounds(10, 90, 40, 45);
		panel.add(panel_2);

		ImageIcon imageIcon_1 = new ImageIcon("image/speech.png");
		JLabel lblImage_1 = new JLabel(imageIcon_1);
		lblImage_1.setBounds(106, 270, 38, 20);
		panel_2.add(lblImage_1);

		panel_3 = new JPanel();
		panel_3.setBackground(new Color(27, 35, 42));
		panel_3.setBounds(10, 500, 40, 50);
		panel.add(panel_3);

		ImageIcon imageIcon_2 = new ImageIcon("image/setting.png");
		JLabel lblImage_2 = new JLabel(imageIcon_2);
		lblImage_2.setBounds(106, 270, 38, 45);
		panel_3.add(lblImage_2);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(245, 245, 245));
		panel_5.setBounds(60, 60, 284, 220);
		contentPane.add(panel_5);

		ImageIcon panel_5Icon = new ImageIcon("image/dePro.png");
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel panel_5Image = new JLabel(panel_5Icon);
		panel_5Image.setBackground(new Color(245, 245, 245));
		panel_5.add(panel_5Image);

		JPanel myProfile = new JPanel();
		myProfile.setBackground(new Color(245, 245, 245));
		myProfile.setBounds(100, 300, 40, 45);
		contentPane.add(myProfile);

		ImageIcon profile = new ImageIcon("image/myProfile.png");
		myProfile.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel myPro = new JLabel(profile);
		myPro.setBackground(new Color(245, 245, 245));
		myProfile.add(myPro);

		JPanel display_Icon = new JPanel();
		display_Icon.setBackground(new Color(245, 245, 245));
		display_Icon.setBounds(182, 300, 40, 45);
		contentPane.add(display_Icon);

		ImageIcon color = new ImageIcon("image/dark_or_light.png");
		display_Icon.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel displayColor = new JLabel(color);
		displayColor.setBackground(new Color(245, 245, 245));
		display_Icon.add(displayColor);

		display_Icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				DarkModeChanger.toggleDarkMode(GaebalTalk.this); // 다크 모드와 라이트 모드를 번갈아가며 적용
			}
		});

		JPanel service = new JPanel();
		service.setBackground(new Color(245, 245, 245));
		service.setBounds(264, 310, 40, 45);
		contentPane.add(service);

		ImageIcon serviceCenter = new ImageIcon("image/callCenter.png");
		service.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel callCenter = new JLabel(serviceCenter);
		callCenter.setBackground(new Color(245, 245, 245));
		service.add(callCenter);

		JPanel font = new JPanel();
		font.setBackground(new Color(245, 245, 245));
		font.setBounds(100, 380, 40, 45);
		contentPane.add(font);

		ImageIcon kor_font = new ImageIcon("image/fontSize.png");
		font.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel korFont = new JLabel(kor_font);
		korFont.setBackground(new Color(245, 245, 245));
		font.add(korFont);

		JPanel language = new JPanel();
		language.setBackground(new Color(245, 245, 245));
		language.setBounds(182, 380, 40, 45);
		contentPane.add(language);

		ImageIcon kor_lang = new ImageIcon("image/korVer.png");
		language.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel korVer = new JLabel(kor_lang);
		korVer.setBackground(new Color(245, 245, 245));
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
		game.setBackground(new Color(245, 245, 245));
		game.setBounds(264, 380, 40, 45);
		contentPane.add(game);

		ImageIcon gaeGame = new ImageIcon("image/game.png");
		game.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel gaeGameIcon = new JLabel(gaeGame);
		gaeGameIcon.setBackground(new Color(245, 245, 245));
		game.add(gaeGameIcon);

		JPanel notification = new JPanel();
		notification.setBackground(new Color(245, 245, 245));
		notification.setBounds(100, 460, 40, 45);
		contentPane.add(notification);

		ImageIcon siren = new ImageIcon("image/siren.png");
		notification.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel notificationIcon = new JLabel(siren);
		notificationIcon.setBackground(new Color(245, 245, 245));
		notification.add(notificationIcon);

		JPanel gaebalVer = new JPanel();
		gaebalVer.setBackground(new Color(245, 245, 245));
		gaebalVer.setBounds(182, 460, 40, 45);
		contentPane.add(gaebalVer);

		ImageIcon ver = new ImageIcon("image/gaebalVer.png");
		gaebalVer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel gaever = new JLabel(ver);
		gaever.setBackground(new Color(245, 245, 245));
		gaebalVer.add(gaever);

		panel_chat = new JPanel();
		panel_chat.setLayout(new BorderLayout());
		panel_chat.setBackground(new Color(245, 245, 245));
		panel_chat.setBounds(60, 60, 284, 381);
		contentPane.add(panel_chat);

		JScrollPane scrollPane = new JScrollPane(roomInfo);
		scrollPane.setBounds(10, 10, 266, 478);
		panel_chat.setLayout(null);
		panel_chat.add(scrollPane);

		JPanel profileSet = new JPanel();
		profileSet.setBackground(new Color(245, 245, 245));
		profileSet.setBounds(60, 280, 284, 281);
		contentPane.add(profileSet);
		profileSet.setLayout(null);

		JLabel myName = new JLabel("이름 : ");
		myName.setBackground(new Color(245, 245, 245));
		myName.setHorizontalAlignment(SwingConstants.RIGHT);
		myName.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		myName.setBounds(50, 25, 80, 30);
		profileSet.add(myName);

		JLabel myPhone = new JLabel("전화번호 : ");
		myPhone.setBackground(new Color(245, 245, 245));
		myPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		myPhone.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		myPhone.setBounds(50, 55, 80, 30);
		profileSet.add(myPhone);

		JLabel myID = new JLabel("아이디 : ");
		myID.setBackground(new Color(245, 245, 245));
		myID.setHorizontalAlignment(SwingConstants.RIGHT);
		myID.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		myID.setBounds(50, 85, 80, 30);
		profileSet.add(myID);

		String id = member.getId();
		String name = member.getName();
		String phonenumber = member.getPhonenumber();
		dbId.setBackground(new Color(245, 245, 245));

		dbId.setText(id);
		dbId.setBounds(140, 25, 80, 30);
		dbName.setBackground(new Color(245, 245, 245));
		dbName.setText(name);
		dbName.setBounds(140, 55, 80, 30);
		dbPhonenumber.setBackground(new Color(245, 245, 245));
		dbPhonenumber.setText(phonenumber);
		dbPhonenumber.setBounds(140, 85, 80, 30);

		profileSet.add(dbId);
		profileSet.add(dbName);
		profileSet.add(dbPhonenumber);

		JPanel changePass = new JPanel();
		changePass.setBackground(new Color(245, 245, 245));
		changePass.setBounds(30, 125, 100, 30);
		profileSet.add(changePass);

		ImageIcon changePW = new ImageIcon("image/비밀번호변경.png");
		changePass.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel changePs = new JLabel(changePW);
		changePs.setBackground(new Color(245, 245, 245));
		changePass.add(changePs);

		changePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProfliePasswordChangeScreen pwChan = new ProfliePasswordChangeScreen();
				pwChan.setVisible(true);
			}
		});

		JPanel withdrawal = new JPanel();
		withdrawal.setBackground(new Color(245, 245, 245));
		withdrawal.setBounds(145, 125, 100, 30);
		profileSet.add(withdrawal);

		ImageIcon withdraw = new ImageIcon("image/회원탈퇴.png");
		withdrawal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel draw = new JLabel(withdraw);
		draw.setBackground(new Color(245, 245, 245));
		withdrawal.add(draw);

		withdrawal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProfileWithDraw draw = new ProfileWithDraw();
				draw.setVisible(true);
			}
		});

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
		profileSet.setVisible(false);

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
					profileSet.setVisible(false);
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
					profileSet.setVisible(false);
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

				int x = 10; // x 좌표
				int y = (int) (panelHeight * 0.8); // y 좌표 (panel 높이의 90% 위치에 설정)

				int profileSetX = (int) (frameWidth * 0.20); // profileSet x 좌표
				int profileSetY = (int) (frameHeight * 0.5); // profileSet y 좌표
				profileSet.setBounds(profileSetX, profileSetY, 284, 281);

				panel_3.setBounds(x, y, 40, 42);

				int panel_5X = (int) (frameWidth * 0.20); // panel_5 x 좌표
				int panel_5Y = (int) (frameHeight * 0.10); // panel_5 y 좌표
				panel_5.setBounds(panel_5X, panel_5Y, 284, 220);

				// myProfile 위치 조정
				int myProfileX = (int) (frameWidth * 0.25); // myProfile x 좌표
				int myProfileY = (int) (frameHeight * 0.5); // myProfile y 좌표
				myProfile.setBounds(myProfileX, myProfileY, 40, 45);

				myProfile.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// myProfile 패널을 클릭했을 때 다른 패널들을 가립니다.
						panel_5.setVisible(true);
						panel_chat.setVisible(false);
						display_Icon.setVisible(false);
						service.setVisible(false);
						font.setVisible(false);
						language.setVisible(false);
						game.setVisible(false);
						notification.setVisible(false);
						gaebalVer.setVisible(false);
						myProfile.setVisible(false);
						// profile 패널만 보이도록 설정
						profileSet.setVisible(true);
					}
				});

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

		while (true) {
			nickName = JOptionPane.showInputDialog(this, "닉네임");
			if (nickName != null && !nickName.isEmpty()) {
				sendMsg("150|" + nickName); // 대화명 전달
				eventUp();
				break;
			} else {
				int option = JOptionPane.showConfirmDialog(this, "닉네임을 입력해주세요.", "닉네임", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					continue;
				} else if (option == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			}

		}
	}// 생성자 끝

	private void eventUp() { // 이벤트소스-이벤트처리부 연결
		// 대기실(MainChat)

		// 대화방(ChatClient)
		client.sendTF.addActionListener(this);
		client.bt_perChat.addActionListener(this);
		client.bt_exit.addActionListener(this);
		client.bt_kick.addActionListener(this);
		client.bt_vanWord.addActionListener(this);
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				title = JOptionPane.showInputDialog(GaebalTalk.this, "방제목:");
				if (title != null && !title.isEmpty()) {
					// 방제목을 서버에게 전달
					sendMsg("160|" + title);
					client.setTitle("채팅방-[" + title + "]");
					sendMsg("175|"); // 대화방내 인원정보 요청
					setVisible(false);
					client.setVisible(true); // 대화방 이동
				} else {
					JOptionPane.showMessageDialog(GaebalTalk.this, "방제목을 입력해주세요.");
				}
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

		client.bt_vanWord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (nickName.equals(client.defaultListModel.get(0))) {
					String vanWordSet = JOptionPane.showInputDialog(GaebalTalk.this, "금지어를 입력해주세요.");
					boolean alreadyExists = false;
		            for (String word : vanWord) {
		                if (word.equals(vanWordSet)) {
		                    alreadyExists = true;
		                    break;
		                }
		            }

		            if (!alreadyExists) {
		                vanWord.add(vanWordSet);
		                System.out.println(vanWordSet);
		                sendMsg("162|" + vanWordSet);
		            } else {
		                JOptionPane.showMessageDialog(GaebalTalk.this, "이미 등록된 금지어입니다.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(GaebalTalk.this, "해당 기능을 이용할 권한이 없습니다.");
		        }
		    }
		});
		client.bt_kick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] userName = client.li_inwon.getSelectedValuesList().toArray(new String[0]);
				if (!nickName.equals(client.defaultListModel.get(0))) {
					JOptionPane.showMessageDialog(GaebalTalk.this, "해당 기능을 이용할 권한이 없습니다.");
					return;
				}

				if (userName.length != 0) {
					System.out.println("선택된 항목 : " + userName);
					for (String user : userName) {
						System.out.println("600|" + user + "|" + nickName);
						sendMsg("600|" + user + "|" + nickName);

					}
				}
			}
		});
		client.bt_sendFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] selectedUsers = client.li_inwon.getSelectedValuesList().toArray(new String[0]);

				if (selectedUsers.length != 0) {
					JFileChooser fileChooser = new JFileChooser();
					int option = fileChooser.showOpenDialog(GaebalTalk.this);
					if (option == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						String fileName = selectedFile.getName();

						try {
							FileInputStream fileInputStream = new FileInputStream(selectedFile);
							byte[] fileContentBytes = fileInputStream.readAllBytes();
							String fileContent = new String(fileContentBytes);

							for (String user : selectedUsers) {
								String message = "500|" + user + "|" + nickName + "|" + fileName + "|" + fileContent;
								System.out.println("이벤트" + message);
								writeChatLog(client.getTitle(),
										"[파일전송]" + "<파일이름 : " + fileName + ">" + nickName + " --> " + user);
								sendMsg(message);
							}

							fileInputStream.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
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

				if (nickName.equals(userName[0])) {
					JOptionPane.showMessageDialog(GaebalTalk.this, "자기 자신을 선택할 수 없습니다.");
					return;
				} else {
					String message = JOptionPane.showInputDialog(GaebalTalk.this, "귓속말 메시지");
					if (message != null && !message.isEmpty()) {
						for (String word : vanWord) {
				            if (message.contains(word)) {
				                JOptionPane.showMessageDialog(GaebalTalk.this, word + "은(는) 금지어입니다 [" + word + "]");
				              
				                return; // 금지어가 있으면 바로 리턴하여 메시지를 서버로 보내지 않도록 합니다.
				            }
				        }

						for (String user : userName) {
							System.out.println("310|" + user + "|" + message);
							sendMsg("310|" + user + "|" + message);
							createChatLog(client.getTitle());
							writeChatLog(client.getTitle(),
									"[귓속말]" + "[" + nickName + " --> " + user + "]  " + message);
							break;
						}
					} else {
						JOptionPane.showMessageDialog(GaebalTalk.this, "메시지를 입력해주세요.");
					}

				}
			}

		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == client.bt_exit) { // 대화방 나가기 요청
			sendMsg("400|" + title + "|" + nickName);
			client.setVisible(false);
			setVisible(true);
		} else if (ob == client.sendTF) { // (TextField입력)메시지 보내기 요청
			String msg = client.sendTF.getText();
			if (msg.length() > 0) {
				for (String word : vanWord) {
		            if (msg.contains(word)) {
		                JOptionPane.showMessageDialog(GaebalTalk.this, word + "은(는) 금지어입니다 [" + word + "]");
		                client.sendTF.setText("");
		                return; // 금지어가 있으면 바로 리턴하여 메시지를 서버로 보내지 않도록 합니다.
		            }
		        }
		        sendMsg("300|" + nickName + "|" + msg);
		        client.sendTF.setText("");
		        createChatLog(client.getTitle());
		        writeChatLog(client.getTitle(), "[" + nickName + "]  " + msg);
			}
		}
	} // actionPerformed

	public void connect() { // (소켓)서버연결 요청
		try {
			// Socket s = new Socket(String host<서버ip>, int port<서비스번호>);
			Socket s = new Socket("localHost", 5509); // 연결시도
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
					
						if (nickName.equals(msgs[1])) {
							client.ta.append("[ME]" + msgs[2] + "\n");
							client.ta.setCaretPosition(client.ta.getText().length());
							
						} else {
							client.ta.append("[" + msgs[1] + "]" + msgs[2] + "\n");
							client.ta.setCaretPosition(client.ta.getText().length());
							
						
						}
					
					

					break;

				case "160": // 방만들기
					// 방정보를 List에 뿌리기
					if (msgs.length > 1) {
						// 개설된 방이 한개 이상이었을때 실행
						// 개설된 방없음 ----> msg="120|" 였을때 에러
						// String roomNames[] = msgs[1].split(",");
						// "자바방--1,오라클방--1,JDBC방--1"
						// roomInfo.setListData(roomNames);

						String roomNames[] = msgs[1].split(",");
						roominfoDefault.clear(); // 기존 요소 제거
						for (String room : roomNames) {
							roominfoDefault.addElement(room);
						}
						break;
					}
					break;
				/*
				 * case "170": // (대기실에서) 대화방 인원정보 String roommUsers[] = msgs[1].split(",");
				 * roommUser.setListData(roommUsers); break;
				 */

				case "165": // 방삭제
					String deleteRoom = msgs[1];
					System.out.println(msgs[1]);
					for (int i = 0; i < roominfoDefault.size(); i++) {
						String room = roominfoDefault.get(i);
						String[] splitName = room.split("-");
						for (int j = 0; j < splitName.length; j++) {
							String ch = splitName[j];
							System.out.println(ch);
							if (deleteRoom.equals(ch)) {

								System.out.println(roominfoDefault.get(i));
								System.out.println(deleteRoom);

								roominfoDefault.removeElementAt(i);

								i--; // 삭제 후 인덱스 조정
								break;
							}
						}
					}
					break;
				case "177":
					client.ta.append("[금지어 공지] " + msgs[1]+"\n");
					client.ta.setCaretPosition(client.ta.getText().length());
					break;

				case "175": // (대화방에서) 대화방 인원정보

					String myroomUsers[] = msgs[1].split(",");
					client.defaultListModel.clear(); // 기존 요소 제거
					for (String user : myroomUsers) {
						client.defaultListModel.addElement(user);
					}
					break;

				case "200": // 대화 입장
					client.ta.append("[" + msgs[1] + "]님이 입장하셨습니다.\n");
					client.ta.setCaretPosition(client.ta.getText().length());
					break;
				case "400": // 대화방 퇴장
					for (int i = 0; i < client.defaultListModel.getSize(); i++) {
						String exitUser = client.defaultListModel.getElementAt(i);

						if (exitUser.equals(msgs[1])) {
							client.defaultListModel.remove(i);

						}
					}
					if (nickName.equals(msgs[1])) {
						client.ta.setText("");
					} else {
						client.ta.append("[" + msgs[1] + "]님이 퇴장하셨습니다.\n");
						client.ta.setCaretPosition(client.ta.getText().length());
					}
					break;

				case "320": // 귓속말 대화
					
					
					
					
					if (nickName.equals(msgs[1])) {
						client.ta.append("<귓속말> To " + "[" + msgs[3] + "]\n" + msgs[2] + "\n");
						client.ta.setCaretPosition(client.ta.getText().length());
					} else {
						client.ta.append("<귓속말> [" + msgs[1] + "] --> " + msgs[2] + "\n");
					}

					// client.ta.setForeground(Color.green);
					client.ta.setCaretPosition(client.ta.getText().length());

					break;

				case "202": // 개설된 방의 타이틀 제목 얻기
					client.setTitle("채팅방-[" + msgs[1] + "]");
					break;
				case "502":
					JOptionPane.showMessageDialog(GaebalTalk.this,
							msgs[1] + "님이 " + msgs[2] + "파일을 보냈습니다.\nD:개발톡에서 보낸 파일 폴더를 확인해주세요!!\n");
					break;
				case "610": // 강퇴당한 서버
					client.ta.setText("");
					client.setVisible(false);
					setVisible(true);

					// stopClient();
					break;

				case "701": // 강퇴 알림

					client.ta.append("[관리자] " + msgs[1] + "님이 강퇴되었습니다.\n");
					client.ta.setCaretPosition(client.ta.getText().length());

				} // End of switch-case
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// End of run

	private void createChatLog(String roomTitle) {
		// 날짜와 시간을 포맷에 맞게 생성
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String fileName = roomTitle + "_" + dateFormat.format(new Date()) + ".txt";

		// 로그 디렉토리 생성 (존재하지 않을 경우)
		File directory = new File(LOG_DIRECTORY);
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}

	protected void writeChatLog(String roomTitle, String message) {
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

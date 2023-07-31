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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import chatdb.MemberDTO;
import jframe.menu.EngVerFirstSwing;
import jframe.menu.EngVerProfileWithDraw;
import jframe.menu.EngVerProfliePasswordChangeScreen;
import jframe.menu.EngVerSettingCustomerServiceGUI;
import jframe.menu.FindIdScreen;
import jframe.menu.FirstSwing;
import jframe.menu.JoinScreen;
import jframe.menu.ProfileWithDraw;
import jframe.menu.ProfliePasswordChangeScreen;
import jframe.menu.SettingCustomerServiceGUI;

public class EngGaebalTalk extends JFrame implements ActionListener, Runnable {
   private final String FILE_SAVE_PATH = "E:/개발톡에서 받은 파일/";
   List<String> vanWord = new ArrayList<>();
   Room room;
   String title;
   JPanel menuBar;
   public JPanel createRoom;
   private JLabel createRoomLb;
   public JPanel inRoom;
   private JLabel inRoomLb;
   JPanel setting;
   String fileContent = "";
   public NotificationScreen notificationScreen;
   private JLabel settingLb;
   public JPanel weather;
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

   static MemberDTO member = new MemberDTO();
   public JLabel korFont;
   public JLabel callCenter;
   private MemberDTO userDTO; //사용자 정보를 저장할 필드
   public JLabel myName;
   public JLabel myPhone;
   public JLabel myID;
   private JPanel logOut;
   private JLabel logOutLb;
   public JLabel changePs;
   public JLabel draw;
   public JLabel cancelLb;

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
               EngGaebalTalk frame = new EngGaebalTalk(member);
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    *  @param userDTO 
    * 
    * @throws IOException
    */
   public EngGaebalTalk(MemberDTO userDTO) throws IOException {
	   
	   this.userDTO = userDTO; //사용자 정보를 저장
		nickName = userDTO.getName();
		
		System.out.println(userDTO.getId());
	    System.out.println(userDTO.getName());
	    System.out.println(userDTO.getPhonenumber());
	    
      setBackground(new Color(245, 245, 245));
      room = new Room();
      client = new ChatClient();
      roomInfo = new JList<String>(roominfoDefault);
      roomInfo.setBackground(new Color(245, 245, 245));
      roomInfo.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
      roomInfo.setBorder(new TitledBorder("Room Info"));

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
      contentPane.setForeground(new Color(245, 245, 245));
      contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

      setContentPane(contentPane);
      contentPane.setLayout(null);

      weather = new JPanel();
      weather.setBounds(60, 0, 284, 60);
      weather.setBackground(new Color(255, 255, 255));
      weather.setLayout(new FlowLayout(FlowLayout.CENTER));
      contentPane.add(weather, BorderLayout.NORTH);

      // Weather c = new Weather();
      // c.weatherAPI();

      // 온도

      // temperatureLabel.setText(c.temperature + "C");
      // weatherIcon.setBounds(25, 0, 50, 50);
      // temperatureLabel.setBounds(75, 0, 50, 50);

      // weather.add(weatherIcon);
      // weather.add(temperatureLabel);

      // temperatureLabel.setHorizontalAlignment(JLabel.CENTER);

      menuBar = new JPanel();
      menuBar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
      menuBar.setBackground(new Color(245, 245, 245));
      menuBar.setBounds(0, 0, 60, 561);
      contentPane.add(menuBar);
      menuBar.setLayout(null);

      createRoom = new JPanel();
      createRoom.setBounds(10, 20, 40, 45);
      createRoom.setBackground(new Color(245, 245, 245));
      createRoom.setBorder(null);
      menuBar.add(createRoom);

      ImageIcon createRoomImage = new ImageIcon("image/human.png");
      createRoom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      createRoomLb = new JLabel(createRoomImage);
      createRoom.add(createRoomLb);

      inRoom = new JPanel();
      inRoom.setBackground(new Color(245, 245, 245));
      inRoom.setBounds(10, 90, 40, 45);
      menuBar.add(inRoom);

      ImageIcon inRoomImage = new ImageIcon("image/speech.png");
      inRoomLb = new JLabel(inRoomImage);
      inRoomLb.setBounds(106, 270, 38, 20);
      inRoom.add(inRoomLb);
      
      setting = new JPanel();
      setting.setBackground(new Color(245, 245, 245));
      setting.setBounds(10, 500, 40, 50);
      menuBar.add(setting);

      ImageIcon settingImage = new ImageIcon("image/setting.png");
      settingLb = new JLabel(settingImage);
      settingLb.setBounds(106, 270, 38, 45);
      setting.add(settingLb);

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
            EngDarkModeChanger.toggleDarkMode(EngGaebalTalk.this); // 다크 모드와 라이트 모드를 번갈아가며 적용
         }
      });

      JPanel service = new JPanel();
      service.setBackground(new Color(245, 245, 245));
      service.setBounds(264, 310, 40, 45);
      contentPane.add(service);

      ImageIcon serviceCenter = new ImageIcon("image/callCenter.png");
      service.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      callCenter = new JLabel(serviceCenter);
      callCenter.setBackground(new Color(245, 245, 245));
      service.add(callCenter);
      
      service.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
             EngVerSettingCustomerServiceGUI call = new EngVerSettingCustomerServiceGUI();
             call.setVisible(true);
          }
       });

      logOut = new JPanel();
      logOut.setBackground(new Color(245, 245, 245));
      logOut.setBounds(264, 380, 40, 45);
      contentPane.add(logOut);
      
      ImageIcon engLogOutIcon = new ImageIcon("image/logOut.png");
      logOutLb = new JLabel(engLogOutIcon);
      logOutLb.setBounds(106, 270, 38, 45);
      logOut.add(logOutLb);
      
      logOut.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseClicked(MouseEvent e) {
    		  EngVerFirstSwing main = new EngVerFirstSwing();
    		  main.setVisible(true);
    		  EngGaebalTalk.this.dispose();
    	  }
      });
      
      JPanel language = new JPanel();
      language.setBackground(new Color(245, 245, 245));
      language.setBounds(182, 380, 40, 45);
      contentPane.add(language);

      ImageIcon kor_lang = new ImageIcon("image/EngVer.png");
      language.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      JLabel korVer = new JLabel(kor_lang);
      korVer.setBackground(new Color(245, 245, 245));
      language.add(korVer);

      language.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            FirstSwing engVer = new FirstSwing();
            engVer.setVisible(true);
            EngGaebalTalk.this.dispose();
         }
      });

      JPanel game = new JPanel();
      game.setBackground(new Color(245, 245, 245));
      game.setBounds(264, 380, 40, 45);
      contentPane.add(game);

      ImageIcon gaeGame = new ImageIcon("image/engGame.png");
      game.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      JLabel gaeGameIcon = new JLabel(gaeGame);
      gaeGameIcon.setBackground(new Color(245, 245, 245));
      game.add(gaeGameIcon);

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

       	myName = new JLabel("Name : ");
		myName.setBackground(new Color(245, 245, 245));
		myName.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		myName.setBounds(30, 25, 80, 30);
		profileSet.add(myName);

		myPhone = new JLabel("Phone : ");
		myPhone.setBackground(new Color(245, 245, 245));
		myPhone.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		myPhone.setBounds(30, 55, 80, 30);
		profileSet.add(myPhone);

		myID = new JLabel("I D : ");
		myID.setBackground(new Color(245, 245, 245));
		myID.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		myID.setBounds(30, 85, 80, 30);
		profileSet.add(myID);

		dbId.setText(userDTO.getId());
		dbId.setBounds(110, 85, 150, 30);
		dbId.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		dbName.setBackground(new Color(245, 245, 245));
		dbName.setText(userDTO.getName());
		dbName.setBounds(110, 25, 150, 30);
		dbName.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		dbPhonenumber.setBackground(new Color(245, 245, 245));
		dbPhonenumber.setText(userDTO.getPhonenumber());
		dbPhonenumber.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		dbPhonenumber.setBounds(110, 55, 150, 30);

		profileSet.add(dbId);
		profileSet.add(dbName);
		profileSet.add(dbPhonenumber);

      JPanel changePass = new JPanel();
      changePass.setBackground(new Color(245, 245, 245));
      changePass.setBounds(30, 125, 100, 30);
      profileSet.add(changePass);

      ImageIcon changePW = new ImageIcon("image/eng비밀번호변경.png");
      changePass.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      changePs = new JLabel(changePW);
      changePs.setBackground(new Color(245, 245, 245));
      changePass.add(changePs);

      changePass.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
        	EngVerProfliePasswordChangeScreen pwChan = new EngVerProfliePasswordChangeScreen();
            pwChan.setVisible(true);
         }
      });

      JPanel withdrawal = new JPanel();
      withdrawal.setBackground(new Color(245, 245, 245));
      withdrawal.setBounds(145, 125, 100, 30);
      profileSet.add(withdrawal);

      ImageIcon withdraw = new ImageIcon("image/eng회원탈퇴.png");
      withdrawal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      draw = new JLabel(withdraw);
      draw.setBackground(new Color(245, 245, 245));
      withdrawal.add(draw);

      withdrawal.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
        	 EngVerProfileWithDraw draw = new EngVerProfileWithDraw();
            draw.setVisible(true);
         }
      });
      
      JPanel cancel = new JPanel();
      cancel.setForeground(new Color(245, 245, 245));
      cancel.setBackground(new Color(245, 245, 245));
      cancel.setBounds(220, 200, 30, 35);
      profileSet.add(cancel);
      
      ImageIcon cancelIcon = new ImageIcon("image/cancel.png");
      cancelLb = new JLabel(cancelIcon);
      cancelLb.setBounds(106, 270, 38, 20);
      cancel.add(cancelLb);

      cancel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
        	  panel_chat.setVisible(false);
              display_Icon.setVisible(true);
              service.setVisible(true);
              logOut.setVisible(true);
              language.setVisible(true);
              game.setVisible(true);
              myProfile.setVisible(true);
              // profile 패널만 보이도록 설정
              profileSet.setVisible(false);
          }
      });
  

      // SettingScreen 클래스의 인스턴스 생성
      notificationScreen = new NotificationScreen();
      // JScrollPane로 감싸서 notification에 추가
      JScrollPane nofitication = new JScrollPane(notificationScreen);
      nofitication.setBackground(new Color(0, 0, 0));
      nofitication.setBounds(60, 60, 284, 220);
      contentPane.add(nofitication);

      // 초기에 보이지 않도록 설정
      myProfile.setVisible(false);
      display_Icon.setVisible(false);
      service.setVisible(false);
      logOut.setVisible(false);
      language.setVisible(false);
      game.setVisible(false);
      profileSet.setVisible(false);

      setting.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            panelsVisible = !panelsVisible; // 패널들의 가시성 상태를 토글

            if (panelsVisible) {
               // 패널들을 보여줍니다.
               panel_chat.setVisible(false);
               myProfile.setVisible(true);
               display_Icon.setVisible(true);
               service.setVisible(true);
               logOut.setVisible(true);
               language.setVisible(true);
               game.setVisible(true);
               profileSet.setVisible(false);
            } else {
               // 패널들을 가립니다.
               panel_chat.setVisible(true);
               myProfile.setVisible(false);
               display_Icon.setVisible(false);
               service.setVisible(false);
               logOut.setVisible(false);
               language.setVisible(false);
               game.setVisible(false);
               profileSet.setVisible(false);
            }
         }
      });

      // 패널 크기를 JFrame 크기에 맞게 설정
      addComponentListener(new java.awt.event.ComponentAdapter() {
         public void componentResized(java.awt.event.ComponentEvent evt) {
            int panelWidth = menuBar.getWidth();
            int panelHeight = menuBar.getHeight();
            int frameWidth = getWidth();
            int frameHeight = getHeight();

            menuBar.setBounds(0, 0, 60, getHeight());
            weather.setBounds(60, 0, getWidth(), 60);
            panel_chat.setBounds(60, 60, getWidth(), getHeight());
            scrollPane.setBounds(0, 0, panel_chat.getWidth(), panel_chat.getHeight());
            nofitication.setBounds(60, 60, frameWidth, (int) (frameHeight * 0.4));

            int x = 10; // x 좌표
            int y = (int) (panelHeight * 0.8); // y 좌표 (panel 높이의 80% 위치에 설정)

            profileSet.setBounds((int) (frameWidth * 0.20), (int) (frameHeight * 0.5), 284, 281);

            logOut.setBounds(x, y, 40, 50);
            setting.setBounds(x, y + 60, 40, 50);

            // myProfile 위치 조정
            myProfile.setBounds((int) (frameWidth * 0.25), (int) (frameHeight * 0.6), 40, 45);

            myProfile.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                  // myProfile 패널을 클릭했을 때 다른 패널들을 가립니다.
                  panel_chat.setVisible(false);
                  display_Icon.setVisible(false);
                  service.setVisible(false);
                  logOut.setVisible(false);
                  language.setVisible(false);
                  game.setVisible(false);
                  myProfile.setVisible(false);
                  // profile 패널만 보이도록 설정
                  profileSet.setVisible(true);
               }
            });

            // display_Icon 위치 조정
            display_Icon.setBounds((int) (frameWidth * 0.52), (int) (frameHeight * 0.6), 40, 45);

            // service 위치 조정
            service.setBounds((int) (frameWidth * 0.80), (int) (frameHeight * 0.6), 40, 45);

            // game 위치 조정
            game.setBounds((int) (frameWidth * 0.25), (int) (frameHeight * 0.75), 40, 45);

            // language 위치 조정
            language.setBounds((int) (frameWidth * 0.52), (int) (frameHeight * 0.75), 40, 45);

            // logOut 위치 조정
            logOut.setBounds((int) (frameWidth * 0.80), (int) (frameHeight * 0.75), 40, 45);
         }
      });

      connect(); // 서버연결시도 (in,out객체생성)
      new Thread(this).start(); // 서버메시지 대기

      sendMsg("100|"); // (대기실)접속 알림
		sendMsg("150|" + nickName); // 대화명 전달
		eventUp();
		
   }// 생성자 끝

   private void eventUp() { // 이벤트소스-이벤트처리부 연결
      // 대기실(MainChat)

      // 대화방(ChatClient)
      client.sendTF.addActionListener(this);
      client.bt_perChat.addActionListener(this);
      client.bt_exit.addActionListener(this);
      client.bt_kick.addActionListener(this);

      createRoom.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            title = JOptionPane.showInputDialog(EngGaebalTalk.this, "Room name:");
            if (title != null && !title.isEmpty()) {
               // 방제목을 서버에게 전달
               sendMsg("160|" + title);
               client.setTitle("Chating Room-[" + title + "]");
               sendMsg("175|"); // 대화방내 인원정보 요청
               setVisible(false);
               client.setVisible(true); // 대화방 이동
            } else {
               JOptionPane.showMessageDialog(EngGaebalTalk.this, "Please enter the title of the room.");
            }
         }
      });

      // "방들어가기" 이미지 클릭 이벤트
      inRoom.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            if (selectedRoom == null) {
               JOptionPane.showMessageDialog(EngGaebalTalk.this, "Choose your room!");
               return;
            }
            sendMsg("200|" + selectedRoom);
            sendMsg("175|"); // 대화방내 인원정보 요청
            setVisible(false);
            client.setVisible(true);
         }
      });

      client.bt_kick.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String[] userName = client.li_inwon.getSelectedValuesList().toArray(new String[0]);
            if (!nickName.equals(client.defaultListModel.get(0))) {
               JOptionPane.showMessageDialog(EngGaebalTalk.this, "You do not have permission to use that function.");
               return;
            }

            if (userName.length != 0) {
               System.out.println("Selected: " + userName);
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
            if (nickName.equals(selectedUsers[0])) {
               JOptionPane.showMessageDialog(EngGaebalTalk.this, "You can't choose yourself.");
               return;
            }
            if (selectedUsers.length != 0) {
               JFileChooser fileChooser = new JFileChooser();
               int option = fileChooser.showOpenDialog(EngGaebalTalk.this);
               if (option == JFileChooser.APPROVE_OPTION) {
                  File selectedFile = fileChooser.getSelectedFile();
                  String fileName = selectedFile.getName();
                  String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

                  if (!fileExtension.equalsIgnoreCase("txt")) {
                     JOptionPane.showMessageDialog(EngGaebalTalk.this, "Only txt format files can be sent");
                     return;
                  }
                  try (FileInputStream fileInputStream = new FileInputStream(selectedFile);
                          ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                         byte[] buffer = new byte[1000000];
                         int bytesRead;
                         while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                             byteArrayOutputStream.write(buffer, 0, bytesRead);
                         }

                         byte[] fileContentBytes = byteArrayOutputStream.toByteArray();
                         fileContent = new String(fileContentBytes, StandardCharsets.UTF_8);

              //try (FileInputStream fileInputStream = new FileInputStream(selectedFile)) {
                 //byte[] fileContentBytes = fileInputStream.readAllBytes();

                 //fileContent = new String(fileContentBytes, StandardCharsets.UTF_8);
              } catch (IOException ex) {
                 ex.printStackTrace();
              }
                  // SharedData 클래스의 인스턴스를 얻고 파일 내용을 저장
                  SharedData sharedData = SharedData.getInstance();
                  sharedData.setFileContent(fileContent);
                  System.out.println(fileContent);

                  for (String user : selectedUsers) {
                     String message = "500|" + user + "|" + nickName + "|" + fileName + "|" + fileContent;
                     System.out.println("Event" + message);
                     writeChatLog(client.getTitle(),
                           "[File transfer]" + "<filename:" + fileName + ">" + nickName + " --> " + user);
                     sendMsg(message);
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
               JOptionPane.showMessageDialog(EngGaebalTalk.this, "Select a user to talk 1:1 with.");
               return;
            }

            if (nickName.equals(userName[0])) {
               JOptionPane.showMessageDialog(EngGaebalTalk.this, "You cannot choose yourself.");
               return;
            } else {
               String message = JOptionPane.showInputDialog(EngGaebalTalk.this, "whisper message");
               if (message != null && !message.isEmpty()) {

                  for (String user : userName) {
                     System.out.println("310|" + user + "|" + message);
                     sendMsg("310|" + user + "|" + message);
                     createChatLog(client.getTitle());
                     writeChatLog(client.getTitle(),
                           "[whisper]" + "[" + nickName + " --> " + user + "]  " + message);
                     break;
                  }
               } else {
                  JOptionPane.showMessageDialog(EngGaebalTalk.this, "Please enter your message.");
               }

            }
         }

      });
      client.emoticon1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {								
				try {
					Document doc = client.ta.getDocument();					
					
					Style styleMsg= client.ta.addStyle("emojiStyle", null);
					 String emojiImagePath = "image/emoji/따봉.png";
					sendMsg("320|" + nickName + "|" + emojiImagePath);
					client.emoticon.setVisible(true);
					client.bt_perChat.setVisible(true);
					client.bt_sendFile.setVisible(true);
					client.bt_changeFont.setVisible(true); 
					client.emoticon1.setVisible(false);
					client.emoticon2.setVisible(false);
					client.emoticon3.setVisible(false);
					client.emoticon4.setVisible(false);							
					} catch (Exception ex) {
						ex.printStackTrace();
					
				}
			}
		});
  client.emoticon2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {						
				try {
					Document doc = client.ta.getDocument();				
					Style styleMsg= client.ta.addStyle("emojiStyle", null);
					 String emojiImagePath = "image/emoji/오열.png";					
					sendMsg("320|" + nickName + "|" + emojiImagePath);			
					client.emoticon.setVisible(true);
					client.bt_perChat.setVisible(true);
					client.bt_sendFile.setVisible(true);
					client.bt_changeFont.setVisible(true); 
					client.emoticon1.setVisible(false);
					client.emoticon2.setVisible(false);
					client.emoticon3.setVisible(false);
					client.emoticon4.setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
			}
		}
	});
  client.emoticon3.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		try {
			Document doc = client.ta.getDocument();					
				Style styleMsg= client.ta.addStyle("emojiStyle", null);
				 String emojiImagePath = "image/emoji/한눈에반함.png";
				System.out.println("emojiButton if 문 : " +styleMsg );
				sendMsg("320|" + nickName + "|" + emojiImagePath);
				client.emoticon.setVisible(true);
				client.bt_perChat.setVisible(true);
				client.bt_sendFile.setVisible(true);
				client.bt_changeFont.setVisible(true); 
				client.emoticon1.setVisible(false);
				client.emoticon2.setVisible(false);
				client.emoticon3.setVisible(false);
				client.emoticon4.setVisible(false);							
				} catch (Exception ex) {
					ex.printStackTrace();
			}
		}
	});
  
  client.emoticon4.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Document doc = client.ta.getDocument();
				Style styleMsg= client.ta.addStyle("emojiStyle", null);
				 String emojiImagePath = "image/emoji/야유.png";
				sendMsg("320|" + nickName + "|" + emojiImagePath);
				client.emoticon.setVisible(true);
				client.bt_perChat.setVisible(true);
				client.bt_sendFile.setVisible(true);
				client.bt_changeFont.setVisible(true); 
				client.emoticon1.setVisible(false);
				client.emoticon2.setVisible(false);
				client.emoticon3.setVisible(false);
				client.emoticon4.setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
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
            case "300": //대화
                  try {
                     Document doc = client.ta.getDocument();
                     if (nickName != null && nickName.equals(msgs[1])) {
                        doc.insertString(doc.getLength(), "[ME]  " + msgs[2] + "\n", null);
                     } else {
                        doc.insertString(doc.getLength(), "[" + msgs[1] + "]" + msgs[2] + "\n", null);
                     }

                     client.ta.setCaretPosition(client.ta.getDocument().getLength());
                  }catch (BadLocationException e){
                     e.printStackTrace();
                  }
                  break;

            case "160": // 방만들기
               // 방정보를 List에 뿌리기
               if (msgs.length > 1) {
                  // 개설된 방이 한개 이상이었을때 실행
                  // 개설된 방없음 ----> msg="120|" 였을때 에러
                  // "자바방--1,오라클방--1,JDBC방--1"

                  String roomNames[] = msgs[1].split(",");
                  roominfoDefault.clear(); // 기존 요소 제거
                  for (String room : roomNames) {
                     roominfoDefault.addElement(room);
                  }
                  break;
               }
               break;

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

            case "175": // (대화방에서) 대화방 인원정보

               String myroomUsers[] = msgs[1].split(",");
               client.defaultListModel.clear(); // 기존 요소 제거
               for (String user : myroomUsers) {
                  client.defaultListModel.addElement(user);
               }
               break;

             case "200": // 대화 입장
               try {
                  Document doc = client.ta.getDocument();

                  doc.insertString(doc.getLength(), "[" + msgs[1] + "]Join the chatroom. \n" ,null);
                  client.ta.setCaretPosition(client.ta.getDocument().getLength());
               }catch (BadLocationException e){
                  e.printStackTrace();
               }
               break;
               case "400": // 대화방 퇴장
                  for (int i = 0; i < client.defaultListModel.getSize(); i++) {
                     String exitUser = client.defaultListModel.getElementAt(i);

                     if (exitUser.equals(msgs[1])) {
                        client.defaultListModel.remove(i);
                     }
                  }
                  try {
                     Document doc = client.ta.getDocument();
                     if (nickName.equals(msgs[1])) {
                        doc.remove(0, doc.getLength());
                     } else {
                        doc.insertString(doc.getLength(), "[" + msgs[1] + "]Out the chatroom.\n", null);
                        client.ta.setCaretPosition(client.ta.getDocument().getLength());
                     }
                  } catch (BadLocationException e) {
                     e.printStackTrace();
                  }
                  break;

            case "320": // 귓속말 대화

               try {
                  Document doc = client.ta.getDocument();
                  if (nickName.equals(msgs[1])){
                     doc.insertString(doc.getLength(),"<" + msgs[3] + "whisper>" + msgs[2] + "\n" ,null);
                  }else {
                     doc.insertString(doc.getLength(),"<whisper> [" + msgs[1] + "]" + msgs[2] + "\n" ,null);
                  }
                  client.ta.setCaretPosition(client.ta.getDocument().getLength());
               }catch (BadLocationException e){
                  e.printStackTrace();
               }
               break;
             case "302":
                try {
                    Document doc = client.ta.getDocument();
                    String nickNameReceived = msgs[1];
                    String emojiImagePath = msgs[2];

                   

                    // Add the nickname and emotion before the emoji
                    if (nickName != null && nickName.equals(nickNameReceived)) {
                        doc.insertString(doc.getLength(), "[ME]", null);
                    } else {
                        doc.insertString(doc.getLength(), "[" + nickNameReceived + "]", null);
                    }
                   

                    
                    Style style = client.ta.addStyle("emojiStyle", null);
                    StyleConstants.setIcon(style, new ImageIcon(emojiImagePath));
                    doc.insertString(doc.getLength(), "invisible text", style);

                    doc.insertString(doc.getLength(), "\n", null);
                    client.ta.setCaretPosition(client.ta.getDocument().getLength());
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
                break;

			    
            case "202": // 개설된 방의 타이틀 제목 얻기
               client.setTitle("Chating Room-[" + msgs[1] + "]");
               break;
            case "502":

               System.out.println("502:" +msgs[3] + " End");
               handleFileTransfer(msgs[1], msgs[2],msgs[3]);

               JOptionPane.showMessageDialog(EngGaebalTalk.this,
                     msgs[1] + "is " + msgs[2] + "The file has been sent.\\nD:Please check the file folder sent from DevTalk!!\n");
               break;
            case "610": // 강퇴당한 서버
               client.ta.setText("");
               client.setVisible(false);
               setVisible(true);

               // stopClient();
               break;

            case "701": // 강퇴 알림
               try {
                  Document doc =  client.ta.getDocument();
                  doc.insertString(doc.getLength(), "[boss]" + msgs[1] + " kick out. \n" ,null);
                  client.ta.setCaretPosition(client.ta.getDocument().getLength());
               }catch (BadLocationException e){
                  e.printStackTrace();
               }
               break;
         }
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
               System.out.println("Create file: " + file.getAbsolutePath());
            } else {
               System.out.println("File Creation Failed");
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

   private void handleFileTransfer(String sender, String fileName, String fileContent) throws IOException {
      System.out.println("Enter file method");

      int option = JOptionPane.showConfirmDialog(null, sender + "Would you like to receive file transfers from ?");
      if (option == JOptionPane.YES_OPTION) {
         File receiverFolder = new File(FILE_SAVE_PATH);

         if (!receiverFolder.exists()) {
            receiverFolder.mkdir();
            System.out.println("File creation complete!");
         }

         // 수신자의 폴더에 동일한 파일 생성하기
         try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_SAVE_PATH + fileName))) {
            writer.write(fileContent);
         } catch (IOException e) {
            e.printStackTrace();
         }
         // 전송 성공을 알림

      } else {
         return;
      }

   }
}

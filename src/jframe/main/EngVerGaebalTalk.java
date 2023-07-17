package jframe.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jframe.menu.FirstSwing;

public class EngVerGaebalTalk extends JFrame {

	private JPanel contentPane;
	private boolean panelsVisible = false; // 패널들의 가시성 상태를 저장하는 변수

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EngVerGaebalTalk frame = new EngVerGaebalTalk();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EngVerGaebalTalk() {

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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 20, 40, 40);
		panel_1.setBackground(new Color(27, 35, 42));
		panel_1.setBorder(null);
		panel.add(panel_1);
		
        ImageIcon imageIcon = new ImageIcon("image/human.png");
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel lblImage = new JLabel(imageIcon);
        panel_1.add(lblImage);
        
        JPanel panel_2 = new JPanel();
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
        
        ImageIcon imageIcon_2= new ImageIcon("image/setting.png");
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
        
        ImageIcon eng_font = new ImageIcon("image/engFontSize.png");
        font.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel korFont = new JLabel(eng_font);
        font.add(korFont);
        
        JPanel language = new JPanel();
        language.setBackground(new Color(187, 207, 210));
        language.setBounds(182, 380, 40, 45);
        contentPane.add(language);
        
        ImageIcon eng_lang = new ImageIcon("image/engVer.png");
        language.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel engVer = new JLabel(eng_lang);
        language.add(engVer);
        
        language.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FirstSwing korVer = new FirstSwing();
                korVer.setVisible(true);
                EngVerGaebalTalk.this.dispose();
            }
        });
        
        JPanel game = new JPanel();
        game.setBackground(new Color(187, 207, 210));
        game.setBounds(264, 380, 40, 45);
        contentPane.add(game);
        
        ImageIcon gaeGame = new ImageIcon("image/engGame.png");
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
                panel_5.setVisible(panelsVisible);
                myProfile.setVisible(panelsVisible);
                display_Icon.setVisible(panelsVisible);
                service.setVisible(panelsVisible);
                font.setVisible(panelsVisible);
                language.setVisible(panelsVisible);
                game.setVisible(panelsVisible);
                notification.setVisible(panelsVisible);
                gaebalVer.setVisible(panelsVisible);
            }
        });
        
     // 패널 크기를 JFrame 크기에 맞게 설정
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
            	int panelWidth = panel.getWidth();
                int panelHeight = panel.getHeight();
                int frameWidth = getWidth();
                int frameHeight = getHeight();
                
            	panel.setBounds(0, 0, 60, getHeight());
                panel_4.setBounds(60, 0, getWidth(), 60);
                
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
	}
}

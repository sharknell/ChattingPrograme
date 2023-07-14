package jframe.menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import chatdb.Login;
import jframe.main.GaebalTalk;

public class FirstSwing extends JFrame {

    private JPanel contentPane;
    private JTextField textField;    private JTextField textField_1;
    private JPanel panel_2;
    private GaebalTalk gaebalTalk;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FirstSwing frame = new FirstSwing();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FirstSwing() {
    	setResizable(false);

		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(185, 207, 210));
		contentPane.setForeground(new Color(185, 207, 210));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setBackground(new Color(155, 174, 176));
		textField_1.setToolTipText("");
		textField_1.setBounds(129, 180, 130, 20);
		textField_1.setBorder(BorderFactory.createEmptyBorder()); // 테두리 제거
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JPasswordField passwordField = new JPasswordField();
        passwordField.setForeground(new Color(255, 255, 255));
        passwordField.setBackground(new Color(155, 174, 176));
        passwordField.setBounds(129, 221, 130, 21);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(passwordField);
        
        JPanel passwordPanel = new JPanel();
        passwordPanel.setBackground(new Color(185, 207, 210));
        passwordPanel.setBounds(259, 221, 20, 20);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(passwordPanel);
        
        ImageIcon lockIcon = new ImageIcon("image/lock.png");
        ImageIcon unlockIcon = new ImageIcon("image/unlock.png");
        passwordPanel.setLayout(null);
        JLabel passwordLabel = new JLabel();
        passwordLabel.setBackground(new Color(185, 207, 210));
        passwordLabel.setIcon(lockIcon);
        passwordLabel.setBounds(0, 0, 20, 20);
        passwordPanel.add(passwordLabel);

        passwordPanel.addMouseListener(new MouseAdapter() {
            boolean passwordVisible = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                passwordVisible = !passwordVisible;
                if (passwordVisible) {
                    passwordLabel.setIcon(unlockIcon);
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordLabel.setIcon(lockIcon);
                    passwordField.setEchoChar('*');
                }
            }
        });
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblNewLabel.setBounds(59, 179, 56, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(59, 219, 56, 21);
		contentPane.add(lblNewLabel_1);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(185, 207, 210));
        panel_2.setBounds(32, 10, 280, 160);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("image/secondLogo.png");
        JLabel lblImage = new JLabel(imageIcon);
        lblImage.setBounds(0, 0, 280, 160);
        panel_2.add(lblImage);
        
        JPanel panel = new JPanel();
        panel.setForeground(new Color(185, 207, 210));
        panel.setBorder(null);
        panel.setBackground(new Color(185, 207, 210));
        panel.setBounds(127, 195, 38, 20);
        contentPane.add(panel);
        
        ImageIcon imageIcon_1 = new ImageIcon("image/ID찾기.png");
        JLabel lblImage_1 = new JLabel(imageIcon_1);
        lblImage_1.setBounds(106, 270, 38, 20);
        panel.add(lblImage_1);
        
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FindIdScreen findId = new FindIdScreen();
                findId.setVisible(true);
                FirstSwing.this.dispose();
            }
        });

        JPanel panel_1 = new JPanel();
        panel_1.setForeground(new Color(185, 207, 210));
        panel_1.setBorder(null);
        panel_1.setBackground(new Color(185, 207, 210));
        panel_1.setBounds(130, 236, 38, 20);
        contentPane.add(panel_1);
        
        ImageIcon imageIcon_2 = new ImageIcon("image/PW찾기.png");
        JLabel lblImage_2 = new JLabel(imageIcon_2);
        lblImage_2.setBounds(106, 270, 38, 20);
        panel_1.add(lblImage_2);

        panel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FindPasswordScreen findPw = new FindPasswordScreen();
                findPw.setVisible(true);
                FirstSwing.this.dispose();
            }
        });

        final JPanel panel_3 = new JPanel();
        panel_3.setForeground(new Color(185, 207, 210));
        panel_3.setBackground(new Color(185, 207, 210));
        panel_3.setBounds(157, 430, 30, 30);
        contentPane.add(panel_3);

        ImageIcon imageIcon_3 = new ImageIcon("image/callCenter.png");
        JLabel lblImage_3 = new JLabel(imageIcon_3);
        lblImage_3.setBounds(0, 0, 30, 30);
        panel_3.add(lblImage_3);

        panel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CustomerServiceGUI service = new CustomerServiceGUI();
                service.setVisible(true);
                FirstSwing.this.dispose();
            }
        });

        final JPanel panel_4 = new JPanel();
        panel_4.setForeground(new Color(185, 207, 210));
        panel_4.setBackground(new Color(185, 207, 210));
        panel_4.setBounds(110, 266, 70, 25);
        contentPane.add(panel_4);
        
        ImageIcon imageIcon_4 = new ImageIcon("image/Login.png");
        JLabel lblImage_4 = new JLabel(imageIcon_4);
        lblImage_4.setBounds(106, 270, 38, 20);
        panel_4.add(lblImage_4);

        panel_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final String id = textField_1.getText();
                final String password = passwordField.getText();

                Login login = new Login();
                boolean loginSuccessful = login.login(id, password);

                if (loginSuccessful) {
                    gaebalTalk = new GaebalTalk();
                    gaebalTalk.setVisible(true);
                    FirstSwing.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(FirstSwing.this, "로그인 실패! 아이디 또는 비밀번호를 확인해주세요.", "로그인 오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        final JPanel panel_5 = new JPanel();
        panel_5.setForeground(new Color(185, 207, 210));
        panel_5.setBackground(new Color(185, 207, 210));
        panel_5.setBounds(200, 266, 70, 25);
        contentPane.add(panel_5);
        
        ImageIcon imageIcon_5 = new ImageIcon("image/Join.png");
        JLabel lblImage_5 = new JLabel(imageIcon_5);
        lblImage_5.setBounds(106, 270, 38, 20);
        panel_5.add(lblImage_5);

        panel_5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JoinScreen jo = new JoinScreen();
                jo.setVisible(true);
                FirstSwing.this.dispose();
            }
        });
    }
}

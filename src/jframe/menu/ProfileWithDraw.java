package jframe.menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import chatdb.WithDraw;

public class ProfileWithDraw extends JFrame {

    private JPanel contentPane;
    private JPanel panel;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    private static final String DB_URL = "jdbc:mariadb://14.42.124.97:3306/chatdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gyuho9480!";
    private JTextField textField_3;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProfileWithDraw frame = new ProfileWithDraw();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ProfileWithDraw() {
    	
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
		
		panel = new JPanel();
		panel.setBackground(new Color(185, 207, 210));
        panel.setBounds(32, 10, 280, 160);
        contentPane.add(panel);
        panel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("image/secondLogo.png");
        JLabel lblImage = new JLabel(imageIcon);
        lblImage.setBounds(0, 0, 280, 160);
        panel.add(lblImage);
        
        JLabel lblNewLabel_1 = new JLabel("아이디");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(32, 191, 100, 20);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("주민등록번호");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(32, 251, 100, 20);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("비밀번호");
        lblNewLabel_3.setForeground(Color.BLACK);
        lblNewLabel_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(32, 221, 100, 20);
        contentPane.add(lblNewLabel_3);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setForeground(new Color(255, 255, 255));
        passwordField.setBackground(new Color(155, 174, 176));
        passwordField.setBounds(129, 221, 130, 20);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(passwordField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setBackground(new Color(185, 207, 210));
        passwordPanel.setBounds(257, 221, 20, 20);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(passwordPanel);

        ImageIcon showIcon = new ImageIcon("image/unlock.png");
        ImageIcon hideIcon = new ImageIcon("image/lock.png");
        passwordPanel.setLayout(null);
        JLabel passwordLabel = new JLabel(hideIcon);
        passwordLabel.setBounds(0, 0, 20, 20);
        passwordPanel.add(passwordLabel);

        passwordPanel.addMouseListener(new MouseAdapter() {
            boolean passwordVisible = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                passwordVisible = !passwordVisible;
                if (passwordVisible) {
                    passwordLabel.setIcon(showIcon);
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordLabel.setIcon(hideIcon);
                    passwordField.setEchoChar('*');
                }
            }
        });
        
        textField_2 = new JTextField();
        textField_2.setForeground(Color.WHITE);
        textField_2.setColumns(10);
        textField_2.setBorder(BorderFactory.createEmptyBorder());
        textField_2.setBackground(new Color(155, 174, 176));
        textField_2.setBounds(129, 191, 130, 20);
        contentPane.add(textField_2);

        ImageIcon imageIcon_1 = new ImageIcon("image/success.png");
        ImageIcon imageIcon_2 = new ImageIcon("image/cancel.png");
        
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(185, 207, 210));
        panel_1.setBounds(200, 430, 30, 35);
        contentPane.add(panel_1);
        JLabel lblImage_2 = new JLabel(imageIcon_2);
        lblImage_2.setBounds(106, 270, 38, 20);
        panel_1.add(lblImage_2);

        panel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ProfileWithDraw.this.dispose();
            }
        });

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(185, 207, 210));
        panel_2.setBounds(110, 430, 30, 35);
        contentPane.add(panel_2);
        JLabel lblImage_1 = new JLabel(imageIcon_1);
        lblImage_1.setBounds(106, 270, 38, 20);
        panel_2.add(lblImage_1);
        
        panel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = textField_2.getText(); // 입력된 사용자 아이디 가져오기
                String password = new String(passwordField.getPassword()); // 입력된 비밀번호 가져오기
                String RRN = textField_3.getText(); // 입력된 주민등록번호 가져오기

                boolean result = WithDraw.deleteMember(id, password, RRN);
                if (result) {
                    // 탈퇴 성공
                    JOptionPane.showMessageDialog(ProfileWithDraw.this, "회원 탈퇴가 완료되었습니다.");
                    dispose(); // ProfileWithDraw(JFrame) 닫기

                    // 현재 실행 중인 프로그램에서 모든 JFrame을 종료합니다.
                    for (Window window : Window.getWindows()) {
                        window.dispose();
                    }

                    // FirstSwing(JFrame) 열기
                    FirstSwing frame = new FirstSwing();
                    frame.setVisible(true);
                } else {
                    // 탈퇴 실패
                    JOptionPane.showMessageDialog(ProfileWithDraw.this, "회원 탈퇴에 실패하였습니다. 입력한 정보를 확인해주세요.");
                }
            }
        });
        
        textField_3 = new JTextField();
        textField_3.setForeground(Color.WHITE);
        textField_3.setColumns(10);
        textField_3.setBorder(BorderFactory.createEmptyBorder());
        textField_3.setColumns(14); // 14자리(6자리 + "-" + 7자리)로 설정
        textField_3.setBackground(new Color(155, 174, 176));
        textField_3.setBounds(129, 252, 130, 20);
        contentPane.add(textField_3);
        
        ((AbstractDocument) textField_3.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                Document doc = fb.getDocument();
                StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));
                sb.replace(offset, offset + length, text);

                // "-"를 삽입할 위치 계산
                int dashes = 0;
                for (int i = 0; i < sb.length(); i++) {
                    if (sb.charAt(i) == '-')
                        dashes++;
                }
                if (offset <= 5 && dashes > 0)
                    return; // 6자리 이상 입력 시 "-" 입력 방지
                if (offset == 6 && dashes == 0)
                    sb.insert(offset, "-"); // 6자리 입력 시 "-" 삽입
                if (sb.length() > 14)
                    return; // 14자리 이상 입력 방지

                super.replace(fb, 0, doc.getLength(), sb.toString(), attrs);
            }
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                replace(fb, offset, 0, string, attr);
            }
        });
    }
}

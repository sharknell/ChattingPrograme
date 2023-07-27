package jframe.menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
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

import chatdb.MemberDTO;
import chatdb.SignUp;
import chatdb.email.EmailSender;

public class EngVerJoinScreen extends JFrame {

    private JPanel contentPane;
    private JPanel panel;
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JPasswordField passwordField;
    private static final String DB_URL = "jdbc:mariadb://14.42.124.13:3306/chatdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gyuho9480!";

    private boolean isAuthenticationCompleted = false;

    // 인증이 완료되었는지 여부를 반환하는 메서드
    public boolean isAuthenticationCompleted() {
        return isAuthenticationCompleted;
    }

    // 인증이 완료되었다고 설정하는 메서드
    public void setAuthenticationCompleted(boolean completed) {
        isAuthenticationCompleted = completed;
    }

    private boolean isEmailSent = false; // 이메일 발송 여부
    private String verificationCode; // 랜덤 인증 코드

    // 회원 아이디를 저장할 ArrayList
    private List<String> registeredIds = new ArrayList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EngVerJoinScreen frame = new EngVerJoinScreen();
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
    public EngVerJoinScreen() {
        setResizable(false);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 360, 540);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setForeground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setBounds(32, 10, 280, 160);
        contentPane.add(panel);
        panel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("image/secondLogo.png");
        JLabel lblImage = new JLabel(imageIcon);
        lblImage.setBounds(0, 0, 280, 160);
        panel.add(lblImage);

        JLabel lblNewLabel = new JLabel("I D");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel.setBounds(49, 190, 68, 20);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("PW");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(49, 220, 68, 20);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Name");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(49, 250, 68, 20);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Phone");
        lblNewLabel_3.setForeground(new Color(0, 0, 0));
        lblNewLabel_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(49, 280, 68, 20);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("IDNum");
        lblNewLabel_4.setForeground(new Color(0, 0, 0));
        lblNewLabel_4.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_4.setBounds(49, 310, 68, 20);
        contentPane.add(lblNewLabel_4);

        textField = new JTextField();
        textField.setForeground(new Color(36, 36, 36));
        textField.setBackground(new Color(255, 255, 255));
        textField.setColumns(10);
        textField.setBounds(129, 191, 130, 20);
        textField.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField);

        passwordField = new JPasswordField();
        passwordField.setForeground(new Color(36, 36, 36));
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setBounds(129, 221, 130, 21);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(passwordField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setBackground(new Color(245, 245, 245));
        passwordPanel.setBounds(257, 221, 20, 20);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(passwordPanel);

        final ImageIcon lockIcon = new ImageIcon("image/lock.png");
        final ImageIcon unlockIcon = new ImageIcon("image/unlock.png");
        passwordPanel.setLayout(null);
        final JLabel passwordLabel = new JLabel();
        passwordLabel.setBackground(new Color(245, 245, 245));
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

        textField_2 = new JTextField();
        textField_2.setForeground(new Color(36, 36, 36));
        textField_2.setBackground(new Color(255, 255, 255));
        textField_2.setColumns(10);
        textField_2.setBounds(129, 251, 130, 20);
        textField_2.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setForeground(new Color(36, 36, 36));
        textField_3.setBackground(new Color(255, 255, 255));
        textField_3.setColumns(10);
        textField_3.setBounds(129, 281, 130, 20);
        textField_3.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setForeground(new Color(36, 36, 36));
        textField_4.setBackground(new Color(255, 255, 255));
        textField_4.setColumns(14); // 14자리(6자리 + "-" + 7자리)로 설정
        textField_4.setBounds(129, 311, 130, 20);
        textField_4.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_4);

        // DocumentFilter를 생성하여 textField_4에 적용
        ((AbstractDocument) textField_4.getDocument()).setDocumentFilter(new DocumentFilter() {
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

        JPanel panel_1 = new JPanel();
        panel_1.setForeground(new Color(245, 245, 245));
        panel_1.setBackground(new Color(245, 245, 245));
        panel_1.setBounds(110, 430, 30, 35);
        contentPane.add(panel_1);

        ImageIcon imageIcon_1 = new ImageIcon("image/success.png");
        JLabel lblImage_1 = new JLabel(imageIcon_1);
        lblImage_1.setBounds(106, 270, 38, 20);
        panel_1.add(lblImage_1);

        // JoinScreen 클래스의 마우스 클릭 이벤트 업데이트
        panel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EngVerJoinScreen joinScreen = (EngVerJoinScreen) EngVerJoinScreen.this;
                if (joinScreen.isAuthenticationCompleted()) {
                    String id = textField.getText();
                    String password = new String(passwordField.getPassword());
                    String name = textField_2.getText();
                    String phoneNumber = textField_3.getText();
                    String RRN = textField_4.getText();

                    List<String> errorMessages = new ArrayList<>();

                    // 아이디 중복 여부 확인
                    if (id.isEmpty()) {
                        errorMessages.add("I D");
                    } else if (isIdDuplicated(id)) {
                        errorMessages.add("Duplicate ID.");
                    }

                    if (password.isEmpty()) {
                        errorMessages.add("PW");
                    }
                    if (name.isEmpty()) {
                        errorMessages.add("Name");
                    }
                    if (phoneNumber.isEmpty()) {
                        errorMessages.add("Phone");
                    }
                    if (RRN.isEmpty()) {
                        errorMessages.add("IDNum");
                    }
                    if (!errorMessages.isEmpty()) {
                        StringBuilder errorMessage = new StringBuilder("The following items are missing or incorrect:\n");
                        for (String error : errorMessages) {
                            errorMessage.append("- ").append(error).append("\n");
                        }
                        JOptionPane.showMessageDialog(EngVerJoinScreen.this, errorMessage.toString());

                        textField.setText("");
                        passwordField.setText("");
                        textField_2.setText("");
                        textField_3.setText("");
                        textField_4.setText("");
                        textField.requestFocusInWindow();
                        return;
                    }

                    MemberDTO memberDTO = new MemberDTO();
                    memberDTO.setId(id);
                    memberDTO.setName(name);
                    memberDTO.setPassword(new String(passwordField.getPassword()));
                    memberDTO.setPhonenumber(phoneNumber);
                    memberDTO.setRRN(RRN);

                    if (SignUp.registerMember(memberDTO)) {
                        JOptionPane.showMessageDialog(null, "Sign up is complete!");
                        EngVerFirstSwing main = new EngVerFirstSwing();
                        main.setVisible(true);
                        EngVerJoinScreen.this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(EngVerJoinScreen.this, "Authentication is not complete.");
                }
            }
        });

        JPanel panel_2 = new JPanel();
        panel_2.setForeground(new Color(245, 245, 245));
        panel_2.setBackground(new Color(245, 245, 245));
        panel_2.setBounds(200, 430, 30, 35);
        contentPane.add(panel_2);

        ImageIcon imageIcon_2 = new ImageIcon("image/cancel.png");
        JLabel lblImage_2 = new JLabel(imageIcon_2);
        lblImage_2.setBounds(106, 270, 38, 20);
        panel_2.add(lblImage_2);

        JPanel panel_3 = new JPanel();
        panel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EngVerAuthenticate atc = new EngVerAuthenticate();
                atc.setVisible(true);
            }
        });
        panel_3.setBackground(new Color(245, 245, 245));
        panel_3.setBounds(189, 330, 70, 25);
        contentPane.add(panel_3);

        ImageIcon imageIcon_3 = new ImageIcon("image/email_Certified.png");
        JLabel lblImage_3 = new JLabel(imageIcon_3);
        lblImage_3.setBounds(106, 270, 38, 20);
        panel_3.add(lblImage_3);

        panel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EngVerFirstSwing main = new EngVerFirstSwing();
                main.setVisible(true);
                EngVerJoinScreen.this.dispose();
            }
        });
    }

    // 아이디 중복 여부 확인 메서드
    private boolean isIdDuplicated(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isDuplicated = false;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "SELECT COUNT(*) FROM members WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                isDuplicated = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isDuplicated;
    }

    

}

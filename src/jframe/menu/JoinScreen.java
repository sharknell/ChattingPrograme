package jframe.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class JoinScreen extends JFrame {

    private JPanel contentPane;
    private JPanel panel;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JoinScreen frame = new JoinScreen();
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
    public JoinScreen() {
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
        
        JLabel lblNewLabel = new JLabel("아이디");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel.setBounds(49, 190, 68, 20);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("비밀번호");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(49, 220, 68, 20);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("이름");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(49, 250, 68, 20);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("휴대폰");
        lblNewLabel_3.setForeground(new Color(0, 0, 0));
        lblNewLabel_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(49, 280, 68, 20);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("주민번호");
        lblNewLabel_4.setForeground(new Color(0, 0, 0));
        lblNewLabel_4.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_4.setBounds(49, 310, 68, 20);
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("이메일인증");
        lblNewLabel_5.setForeground(new Color(0, 0, 0));
        lblNewLabel_5.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_5.setBounds(48, 340, 80, 20);
        contentPane.add(lblNewLabel_5);
        
        textField = new JTextField();
        textField.setForeground(new Color(255, 255, 255));
        textField.setBackground(new Color(155, 174, 176));
        textField.setColumns(10);
        textField.setBounds(129, 191, 130, 20);
        textField.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField);
        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setForeground(new Color(255, 255, 255));
        passwordField.setBackground(new Color(155, 174, 176));
        passwordField.setBounds(129, 221, 130, 21);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(passwordField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setBackground(new Color(185, 207, 210));
        passwordPanel.setBounds(257, 221, 20, 20);
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
        
        textField_2 = new JTextField();
        textField_2.setForeground(new Color(255, 255, 255));
        textField_2.setBackground(new Color(155, 174, 176));
        textField_2.setColumns(10);
        textField_2.setBounds(129, 251, 130, 20);
        textField_2.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setForeground(new Color(255, 255, 255));
        textField_3.setBackground(new Color(155, 174, 176));
        textField_3.setColumns(10);
        textField_3.setBounds(129, 281, 130, 20);
        textField_3.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setForeground(new Color(255, 255, 255));
        textField_4.setBackground(new Color(155, 174, 176));
        textField_4.setColumns(14); // 14자리(6자리 + "-" + 7자리)로 설정
        textField_4.setBounds(129, 311, 130, 20);
        textField_4.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_4);

        textField_5 = new JTextField();
        textField_5.setForeground(new Color(255, 255, 255));
        textField_5.setBackground(new Color(155, 174, 176));
        textField_5.setBounds(129, 341, 130, 20);
        textField_5.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_5);

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
        panel_1.setForeground(new Color(185, 207, 210));
        panel_1.setBackground(new Color(185, 207, 210));
        panel_1.setBounds(110, 430, 30, 35);
        contentPane.add(panel_1);

        ImageIcon imageIcon_1 = new ImageIcon("image/success.png");
        JLabel lblImage_1 = new JLabel(imageIcon_1);
        lblImage_1.setBounds(106, 270, 38, 20);
        panel_1.add(lblImage_1);


        panel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = textField.getText();
                String password = textField_1.getText();
                String name = textField_2.getText();
                String phoneNumber = textField_3.getText();
                String RRN = textField_4.getText();

                List<String> errorMessages = new ArrayList<>();

                if (id.isEmpty()) {
                    errorMessages.add("I D");
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
                    StringBuilder errorMessage = new StringBuilder("다음 항목이 입력되지 않았습니다:\n");
                    for (String error : errorMessages) {
                        errorMessage.append("- ").append(error).append("\n");
                    }
                    JOptionPane.showMessageDialog(JoinScreen.this, errorMessage.toString());
                    
                    // 필드 내용 초기화
                    textField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");
                    textField_3.setText("");
                    textField_4.setText("");

                    // 선택된 필드로 포커스 이동
                    textField.requestFocusInWindow();
                    return;
                }

                if (!SignUp.isValidEmail(id)) {
                    errorMessages.add("유효한 이메일 형식이 아닙니다.");
                }
                if (!SignUp.isValidPassword(password)) {
                    errorMessages.add("비밀번호는 영문자, 숫자와 특수문자 (!, @)를 최소 1개 이상 포함해야하고 8글자 이상이어야 합니다.");
                }
                if (SignUp.isDuplicateId(id)) {
                    errorMessages.add("중복된 아이디입니다.");
                }
                if (SignUp.isDuplicatePhoneNumber(phoneNumber)) {
                    errorMessages.add("중복된 전화번호입니다.");
                }
                if (!SignUp.isValidRRN(RRN)) {
                    errorMessages.add("유효한 주민등록번호 형식이 아닙니다.");
                }

                if (!errorMessages.isEmpty()) {
                    StringBuilder errorMessage = new StringBuilder("다음 항목이 올바르지 않습니다:\n");
                    for (String error : errorMessages) {
                        errorMessage.append("- ").append(error).append("\n");
                    }
                    JOptionPane.showMessageDialog(JoinScreen.this, errorMessage.toString());
                    
                    // 필드 내용 초기화
                    textField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");
                    textField_3.setText("");
                    textField_4.setText("");

                    // 선택된 필드로 포커스 이동
                    textField.requestFocusInWindow();
                    return;
                }


                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setId(id);
                memberDTO.setName(name);
                memberDTO.setPassword(password);
                memberDTO.setPhonenumber(phoneNumber);
                memberDTO.setRRN(RRN);

                if (SignUp.registerMember(memberDTO)) {
                    JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!");
                    FirstSwing main = new FirstSwing();
                    main.setVisible(true);
                    JoinScreen.this.dispose();
                }
            }
        });



        JPanel panel_2 = new JPanel();
        panel_2.setForeground(new Color(185, 207, 210));
        panel_2.setBackground(new Color(185, 207, 210));
        panel_2.setBounds(200, 430, 30, 35);
        contentPane.add(panel_2);

        ImageIcon imageIcon_2 = new ImageIcon("image/cancel.png");
        JLabel lblImage_2 = new JLabel(imageIcon_2);
        lblImage_2.setBounds(106, 270, 38, 20);
        panel_2.add(lblImage_2);
        
        panel_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		FirstSwing main = new FirstSwing();
        		main.setVisible(true);
        		JoinScreen.this.dispose();
        	}
        });
  
    
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(185, 207, 210));
        panel_3.setBounds(189, 361, 70, 25);
        contentPane.add(panel_3);
        
        ImageIcon imageIcon_3 = new ImageIcon("image/email.png");
        JLabel lblImage_3 = new JLabel(imageIcon_3);
        lblImage_3.setBounds(106, 270, 38, 20);
        panel_3.add(lblImage_3);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(new Color(185, 207, 210));
        panel_4.setBounds(189, 361, 70, 25);
        contentPane.add(panel_4);
        panel_4.setLayout(null);
        panel_4.setVisible(false);
        
        panel_4.setLayout(new BorderLayout());
        ImageIcon imageIcon_4 = new ImageIcon("image/email_success.png");
        JLabel lblImage_4 = new JLabel(imageIcon_4);
        lblImage_4.setBounds(106, 270, 38, 20);
        panel_4.add(lblImage_4, BorderLayout.CENTER);
        
        panel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // panel_3 클릭 시 panel_4의 가시성을 변경합니다.
            	panel_3.setVisible(false);
                panel_4.setVisible(true);
            }
        });
        

  }
    private boolean registerMember() {
        String id = textField.getText();
        String password = textField_1.getText();
        String name = textField_2.getText();
        String phoneNumber = textField_3.getText();
        String RRN = textField_4.getText();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        memberDTO.setName(name);
        memberDTO.setPassword(password);
        memberDTO.setPhonenumber(phoneNumber);
        memberDTO.setRRN(RRN);

        return SignUp.registerMember(memberDTO);
    }
}
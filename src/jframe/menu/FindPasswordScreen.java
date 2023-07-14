package jframe.menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import chatdb.FindPassword;

public class FindPasswordScreen extends JFrame {

    private JPanel contentPane;
    private JPanel panel;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FindPasswordScreen frame = new FindPasswordScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FindPasswordScreen() {
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
        
        JLabel lblNewLabel = new JLabel("I D");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel.setBounds(60, 190, 57, 20);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("PW");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(60, 220, 57, 20);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Phone");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(60, 250, 60, 20);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("IDNum");
        lblNewLabel_3.setForeground(new Color(0, 0, 0));
        lblNewLabel_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(60, 280, 68, 20);
        contentPane.add(lblNewLabel_3);
        
        textField = new JTextField();
        textField.setForeground(new Color(255, 255, 255));
        textField.setBackground(new Color(155, 174, 176));
        textField.setColumns(10);
        textField.setBounds(129, 191, 150, 20);
        textField.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField);
        
        textField_1 = new JTextField();
        textField_1.setForeground(new Color(255, 255, 255));
        textField_1.setBackground(new Color(155, 174, 176));
        textField_1.setColumns(10);
        textField_1.setBounds(129, 221, 150, 20);
        textField_1.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setForeground(new Color(255, 255, 255));
        textField_2.setBackground(new Color(155, 174, 176));
        textField_2.setColumns(10);
        textField_2.setBounds(129, 251, 150, 20);
        textField_2.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setForeground(new Color(255, 255, 255));
        textField_3.setBackground(new Color(155, 174, 176));
        textField_3.setColumns(14); // 14자리(6자리 + "-" + 7자리)로 설정
        textField_3.setBounds(129, 281, 150, 20);
        textField_3.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_3);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(185, 207, 210));
        panel.setBounds(110, 430, 30, 35);
        contentPane.add(panel);
        
        ImageIcon imageIcon_1 = new ImageIcon("image/success.png");
        JLabel lblImage_1 = new JLabel(imageIcon_1);
        lblImage_1.setBounds(106, 270, 38, 20);
        panel.add(lblImage_1);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = textField.getText();
                String phoneNumber = textField_2.getText();
                String name = textField_1.getText();
                String RRN = textField_3.getText();

                // 주민등록번호 입력 시 앞자리와 뒷자리 사이에 "-" 삽입
                

                String password = FindPassword.findPassword(id, phoneNumber, name, RRN);
                if (password != null) {
                	PasswordChangeScreen main = new PasswordChangeScreen();
            		main.setVisible(true);
            		FindPasswordScreen.this.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "일치하는 회원 정보를 찾을 수 없습니다.");
                 // 필드 내용 초기화
                    textField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");
                    textField_3.setText("");
                

                    // 선택된 필드로 포커스 이동
                    textField.requestFocusInWindow();
                }
            }
        });

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(185, 207, 210));
        panel_1.setBounds(200, 430, 30, 35);
        contentPane.add(panel_1);

        ImageIcon imageIcon_2 = new ImageIcon("image/cancel.png");
        JLabel lblImage_2 = new JLabel(imageIcon_2);
        lblImage_2.setBounds(106, 270, 38, 20);
        panel_1.add(lblImage_2);

        panel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FirstSwing main = new FirstSwing();
                main.setVisible(true);
                FindPasswordScreen.this.dispose();
            }
        });

        // 주민등록번호 입력 시 앞자리와 뒷자리 사이에 "-" 삽입하는 로직
        ((AbstractDocument) textField_3.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                Document doc = fb.getDocument();
                StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));
                sb.replace(offset, offset + length, text);

                int dashes = 0;
                for (int i = 0; i < sb.length(); i++) {
                    if (sb.charAt(i) == '-')
                        dashes++;
                }
                if (offset <= 5 && dashes > 0)
                    return;
                if (offset == 6 && dashes == 0)
                    sb.insert(offset, "-");
                if (sb.length() > 14)
                    return;

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

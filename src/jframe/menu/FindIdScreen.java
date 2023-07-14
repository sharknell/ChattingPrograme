package jframe.menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import chatdb.FindId;

public class FindIdScreen extends JFrame {

    private JPanel contentPane;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FindIdScreen frame = new FindIdScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FindIdScreen() {
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
        
        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel.setBounds(60, 190, 57, 20);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Phone");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(60, 220, 60, 20);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("IDNum");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(60, 250, 68, 20);
        contentPane.add(lblNewLabel_2);

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
        textField_2.setColumns(14); // 14자리(6자리 + "-" + 7자리)로 설정
        textField_2.setBounds(129, 251, 150, 20);
        textField_2.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_2);

        ((AbstractDocument) textField_2.getDocument()).setDocumentFilter(new DocumentFilter() {
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
                String name = textField.getText();
                String phoneNumber = textField_1.getText();
                String idNum = textField_2.getText();

                String foundId = FindId.findId(name, phoneNumber, idNum);
                if (foundId != null) {
                    JOptionPane.showMessageDialog(null, "회원님의 ID는 " + foundId + "입니다.");
                    FindIdScreen.this.dispose();
                    FirstSwing main = new FirstSwing();
                    main.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "일치하는 회원 정보를 찾을 수 없습니다.");
                 // 필드 내용 초기화
                    textField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");
                 

                    // 선택된 필드로 포커스 이동
                    textField.requestFocusInWindow();
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
                FindIdScreen.this.dispose();
            }
        });
    }
}

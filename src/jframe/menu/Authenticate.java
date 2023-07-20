package jframe.menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Icon;

public class Authenticate extends JFrame {

	private JPanel contentPane;
	private boolean panelsVisible = true;
	private JTextField nameField;
	private JTextField emailField;
	private JTextField authenticateField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authenticate frame = new Authenticate();
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
	public Authenticate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(185, 207, 210));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		int mainFrameX = Authenticate.this.getX();
		int mainFrameY = Authenticate.this.getY();
		int mainFrameWidth = Authenticate.this.getWidth();
		
		int x = mainFrameX + mainFrameWidth;
		int y = mainFrameY;
		setLocation(x, y);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel email = new JPanel();
		email.setBackground(new Color(185, 207, 210));
		email.setBounds(50, 110, 100, 30);
		contentPane.add(email);
	
		
		ImageIcon emailA = new ImageIcon("image/이메일인증.png");
        JLabel emailLb = new JLabel(emailA);
        emailLb.setBounds(50, 110, 100, 25);
        email.add(emailLb);
        
		JPanel phone = new JPanel();
		phone.setBackground(new Color(185, 207, 210));
		phone.setBounds(234, 110, 100, 30);
		contentPane.add(phone);
		
		ImageIcon phoneA = new ImageIcon("image/휴대폰인증.png");
        JLabel phoneLb = new JLabel(phoneA);
        phoneLb.setBounds(234, 110, 100, 25);
        phone.add(phoneLb);
        
		/*
		 * JPanel emailAuthenticate = new JPanel(); emailAuthenticate.setBounds(0, 0,
		 * 384, 261); contentPane.add(emailAuthenticate);
		 * emailAuthenticate.setLayout(null);
		 * 
		 * nameField = new JTextField(); nameField.setBounds(160, 45, 150, 25);
		 * nameField.setBorder(BorderFactory.createEmptyBorder()); // 테두리 제거
		 * emailAuthenticate.add(nameField); nameField.setColumns(10);
		 * 
		 * emailField = new JTextField(); emailField.setBounds(160, 85, 150, 25);
		 * emailField.setBorder(BorderFactory.createEmptyBorder()); // 테두리 제거
		 * emailAuthenticate.add(emailField); emailField.setColumns(10);
		 * 
		 * JLabel lblNewLabel = new JLabel("이름");
		 * lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		 * lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		 * lblNewLabel.setBounds(90, 45, 60, 20); emailAuthenticate.add(lblNewLabel);
		 * 
		 * JLabel lblNewLabel_1 = new JLabel("이메일");
		 * lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		 * lblNewLabel_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		 * lblNewLabel_1.setBounds(90, 85, 60, 25);
		 * emailAuthenticate.add(lblNewLabel_1);
		 * 
		 * JLabel lblNewLabel_1_1 = new JLabel("인증코드");
		 * lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		 * lblNewLabel_1_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		 * lblNewLabel_1_1.setBounds(90, 125, 60, 25);
		 * emailAuthenticate.add(lblNewLabel_1_1);
		 * 
		 * authenticateField = new JTextField(); authenticateField.setColumns(10);
		 * authenticateField.setBounds(160, 125, 150, 25);
		 * authenticateField.setBorder(BorderFactory.createEmptyBorder()); // 테두리 제거
		 * emailAuthenticate.add(authenticateField);
		 * 
		 * JPanel panel = new JPanel(); panel.setBounds(0, 0, 384, 261);
		 * emailAuthenticate.add(panel);
		 * 
		 * JPanel complete_1 = new JPanel(); complete_1.setForeground(new Color(185,
		 * 207, 210)); complete_1.setBackground(new Color(185, 207, 210));
		 * complete_1.setBounds(112, 200, 30, 35); emailAuthenticate.add(complete_1);
		 * 
		 * ImageIcon completeI_2 = new ImageIcon("image/success.png"); JLabel
		 * completeLb_2 = new JLabel(completeI_2);
		 * completeLb_2.setHorizontalAlignment(SwingConstants.CENTER); // 이미지를 가운데 정렬
		 * completeLb_2.setVerticalAlignment(SwingConstants.CENTER); // 이미지를 가운데 정렬
		 * complete_1.add(completeLb_2);
		 * 
		 * JPanel cancel_1 = new JPanel(); cancel_1.setForeground(new Color(185, 207,
		 * 210)); cancel_1.setBackground(new Color(185, 207, 210));
		 * cancel_1.setBounds(242, 200, 30, 35); emailAuthenticate.add(cancel_1);
		 * 
		 * ImageIcon cancelI_2 = new ImageIcon("image/cancel.png"); JLabel cancelLb_2 =
		 * new JLabel(cancelI_2);
		 * cancelLb_2.setHorizontalAlignment(SwingConstants.CENTER); // 이미지를 가운데 정렬
		 * cancelLb_2.setVerticalAlignment(SwingConstants.CENTER); // 이미지를 가운데 정렬
		 * cancel_1.add(cancelLb_2);
		 * 
		 * email.setVisible(true); phone.setVisible(true);
		 * emailAuthenticate.setVisible(false);
		 * 
		 * email.addMouseListener(new MouseAdapter() { public void
		 * mouseClicked(MouseEvent e) { panelsVisible = !panelsVisible;
		 * 
		 * if(panelsVisible) { emailAuthenticate.setVisible(true);
		 * complete_1.setVisible(true); cancel_1.setVisible(true);
		 * email.setVisible(false); phone.setVisible(false); } } });
		 */
        
        JPanel complete = new JPanel();
        complete.setForeground(new Color(185, 207, 210));
        complete.setBackground(new Color(185, 207, 210));
        complete.setBounds(112, 200, 30, 35);
        contentPane.add(complete);

        ImageIcon completeI = new ImageIcon("image/success.png");
        JLabel completeLb = new JLabel(completeI);
        completeLb.setBounds(106, 270, 38, 20);
        complete.add(completeLb);
        
        JPanel cancel = new JPanel();
        cancel.setForeground(new Color(185, 207, 210));
        cancel.setBackground(new Color(185, 207, 210));
        cancel.setBounds(242, 200, 30, 35);
        contentPane.add(cancel);

        ImageIcon cancelI = new ImageIcon("image/cancel.png");
        JLabel cancelLb = new JLabel(cancelI);
        cancelLb.setBounds(106, 270, 38, 20);
        cancel.add(cancelLb);
        
        //SENSMessage.SENSMessage()
	}
}

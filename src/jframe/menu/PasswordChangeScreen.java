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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PasswordChangeScreen extends JFrame {

    private JPanel contentPane;
    private JPanel panel;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/chatdb";
    private static final String DB_USER = "gyuho";
    private static final String DB_PASSWORD = "gyuho9480!";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PasswordChangeScreen frame = new PasswordChangeScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PasswordChangeScreen() {
    	
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
        
        JLabel lblNewLabel_1 = new JLabel("I D");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(32, 190, 80, 20);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Confirm");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(32, 250, 80, 20);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("NEW PW");
        lblNewLabel_3.setForeground(Color.BLACK);
        lblNewLabel_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(32, 220, 80, 20);
        contentPane.add(lblNewLabel_3);

        textField = new JTextField();
        textField.setForeground(new Color(255, 255, 255));
        textField.setBackground(new Color(155, 174, 176));
        textField.setColumns(10);
        textField.setBounds(129, 251, 150, 20);
        textField.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField);
        
        textField_1 = new JTextField();
        textField_1.setForeground(new Color(255, 255, 255));
        textField_1.setBackground(new Color(155, 174, 176));
        textField_1.setColumns(10);
        textField_1.setBounds(129, 221, 150, 20);
        textField_1.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(textField_1);
        
        ImageIcon imageIcon_1 = new ImageIcon("image/success.png");
        ImageIcon imageIcon_2 = new ImageIcon("image/cancel.png");
        
        textField_2 = new JTextField();
        textField_2.setForeground(Color.WHITE);
        textField_2.setColumns(10);
        textField_2.setBorder(BorderFactory.createEmptyBorder());
        textField_2.setBackground(new Color(155, 174, 176));
        textField_2.setBounds(129, 191, 150, 20);
        contentPane.add(textField_2);
        
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
                PasswordChangeScreen.this.dispose();
                FirstSwing main = new FirstSwing();
                main.setVisible(true);
                PasswordChangeScreen.this.dispose();
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
                String id = textField_2.getText();
                String newPassword = textField.getText();

                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    String query = "SELECT * FROM members WHERE id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, id);

                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        String updateQuery = "UPDATE members SET password = ? WHERE id = ?";
                        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                        updateStatement.setString(1, newPassword);
                        updateStatement.setString(2, id);
                        updateStatement.executeUpdate();

                        JOptionPane.showMessageDialog(null, "비밀번호 변경이 완료되었습니다.");
                        FirstSwing main = new FirstSwing();
                        main.setVisible(true);
                        PasswordChangeScreen.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "아이디가 일치하지 않습니다.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class EngVerPasswordChangeScreen extends JFrame {

    private JPanel contentPane;
    private JPanel panel;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    private static final String DB_URL = "jdbc:mariadb://14.42.124.97:3306/chatdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gyuho9480!";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EngVerPasswordChangeScreen frame = new EngVerPasswordChangeScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EngVerPasswordChangeScreen() {
    	
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
        lblNewLabel_1.setBounds(62, 191, 100, 20);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Confirm");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(62, 251, 100, 20);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("PW");
        lblNewLabel_3.setForeground(Color.BLACK);
        lblNewLabel_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(62, 221, 100, 20);
        contentPane.add(lblNewLabel_3);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setForeground(new Color(255, 255, 255));
        passwordField.setBackground(new Color(155, 174, 176));
        passwordField.setBounds(129, 221, 130, 20);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(passwordField);

        JPasswordField passwordConfirmField = new JPasswordField();
        passwordConfirmField.setForeground(new Color(255, 255, 255));
        passwordConfirmField.setBackground(new Color(155, 174, 176));
        passwordConfirmField.setBounds(129, 251, 130, 20);
        passwordConfirmField.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(passwordConfirmField);

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
                    passwordConfirmField.setEchoChar((char) 0);
                } else {
                    passwordLabel.setIcon(hideIcon);
                    passwordField.setEchoChar('*');
                    passwordConfirmField.setEchoChar('*');
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
                EngVerPasswordChangeScreen.this.dispose();
                EngVerFirstSwing main = new EngVerFirstSwing();
                main.setVisible(true);
                EngVerPasswordChangeScreen.this.dispose();
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
                String newPassword = passwordField.getText();

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

                        JOptionPane.showMessageDialog(null, "Password change is complete.");
                        EngVerFirstSwing main = new EngVerFirstSwing();
                        main.setVisible(true);
                        EngVerPasswordChangeScreen.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "ID does not match.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

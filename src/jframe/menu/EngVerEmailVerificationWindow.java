package jframe.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import chatdb.email.EmailSender;

public class EngVerEmailVerificationWindow extends JFrame {
    private JLabel emailLabel;
    private JTextField emailField;
    private JButton sendButton;
    private JLabel verificationCodeLabel;
    private JTextField verificationCodeField;
    private JButton verifyButton;

    private String sentVerificationCode;
    private static boolean isEmailVerified = false;

    public EngVerEmailVerificationWindow() {
        setTitle("Email Authentication");
        setSize(400, 250);

        emailLabel = new JLabel("Email Address:");
        emailField = new JTextField(20);
        sendButton = new JButton("Send authentication number");
        verificationCodeLabel = new JLabel("Certification Number:");
        verificationCodeField = new JTextField(10);
        verifyButton = new JButton("authentication check");
        verificationCodeLabel.setVisible(false);
        verificationCodeField.setVisible(false);
        verifyButton.setVisible(false);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailAddress = emailField.getText();
                if (!emailAddress.isEmpty()) {
                    sentVerificationCode = sendVerificationCode(emailAddress);
                    verificationCodeLabel.setVisible(true);
                    verificationCodeField.setVisible(true);
                    verifyButton.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(EngVerEmailVerificationWindow.this, "Please enter your email address!");
                }
            }
        });

        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredCode = verificationCodeField.getText();
                if (!enteredCode.isEmpty()) {
                    verifyCode(enteredCode);
                } else {
                    JOptionPane.showMessageDialog(EngVerEmailVerificationWindow.this, "Enter your verification code!");
                }
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(emailLabel, constraints);

        constraints.gridy = 1;
        panel.add(emailField, constraints);

        constraints.gridy = 2;
        panel.add(sendButton, constraints);

        constraints.gridy = 3;
        panel.add(verificationCodeLabel, constraints);

        constraints.gridy = 4;
        panel.add(verificationCodeField, constraints);

        constraints.gridy = 5;
        panel.add(verifyButton, constraints);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String sendVerificationCode(String emailAddress) {
        // 인증 코드 생성 (여기서는 간단히 랜덤 숫자를 사용)
        String verificationCode = generateVerificationCode();

        // 이메일 전송
        EmailSender.sendVerificationEmail(emailAddress, verificationCode);

        return verificationCode;
    }

    private void verifyCode(String enteredCode) {
        if (enteredCode.trim().equals(sentVerificationCode)) {
            JOptionPane.showMessageDialog(EngVerEmailVerificationWindow.this, "Certified!");
            isEmailVerified = true; // 인증이 성공한 경우 isEmailVerified를 true로 설정
            dispose();
        } else {
            JOptionPane.showMessageDialog(EngVerEmailVerificationWindow.this, "Not verified!");
            isEmailVerified = false; // 인증이 실패한 경우 isEmailVerified를 false로 설정
        }
    }


    private String generateVerificationCode() {
        // 인증 코드 생성 로직 (예시로 랜덤 숫자를 생성하여 사용)
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }
    
    public boolean isVerified() {
        return isEmailVerified;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EngVerEmailVerificationWindow();
            }
        });
    }
}

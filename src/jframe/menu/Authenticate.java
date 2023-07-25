package jframe.menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;
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

import chatdb.phone.SENSMessage;

public class Authenticate extends JFrame {

    private JPanel contentPane;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField authenticateField;

    private EmailVerificationWindow emailVerificationWindow;
    private SENSMessage sensMessage;

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

    public Authenticate() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
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
        email.setBackground(new Color(245, 245, 245));
        email.setBounds(50, 110, 100, 30);
        contentPane.add(email);

        ImageIcon emailA = new ImageIcon("image/이메일인증.png");
        JLabel emailLb = new JLabel(emailA);
        emailLb.setBounds(50, 110, 100, 25);
        email.add(emailLb);

        JPanel phone = new JPanel();
        phone.setBackground(new Color(245, 245, 245));
        phone.setBounds(234, 110, 100, 30);
        contentPane.add(phone);

        ImageIcon phoneA = new ImageIcon("image/휴대폰인증.png");
        JLabel phoneLb = new JLabel(phoneA);
        phoneLb.setBounds(234, 110, 100, 25);
        phone.add(phoneLb);

        JPanel complete = new JPanel();
        complete.setForeground(new Color(245, 245, 245));
        complete.setBackground(new Color(245, 245, 245));
        complete.setBounds(112, 200, 30, 35);
        contentPane.add(complete);

        ImageIcon completeI = new ImageIcon("image/success.png");
        JLabel completeLb = new JLabel(completeI);
        completeLb.setBounds(106, 270, 38, 20);
        complete.add(completeLb);

        JPanel cancel = new JPanel();
        cancel.setForeground(new Color(245, 245, 245));
        cancel.setBackground(new Color(245, 245, 245));
        cancel.setBounds(242, 200, 30, 35);
        contentPane.add(cancel);

        ImageIcon cancelI = new ImageIcon("image/cancel.png");
        JLabel cancelLb = new JLabel(cancelI);
        cancelLb.setBounds(106, 270, 38, 20);
        cancel.add(cancelLb);

        emailVerificationWindow = new EmailVerificationWindow();
        sensMessage = new SENSMessage();

        // "이메일 인증" 라벨 클릭 시 EmailVerificationWindow 실행
        email.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (!emailVerificationWindow.isVisible()) {
                    emailVerificationWindow.setVisible(true);
                }
            }
        });

        // "휴대폰 인증" 라벨 클릭 시 SENSMessage 실행
        phone.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (!sensMessage.isVisible()) {
                    sensMessage.setVisible(true);
                }
            }
        });

        complete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean isEmailVerified = emailVerificationWindow.isVerified();
                boolean isPhoneVerified = sensMessage.isVerified();

                if (isEmailVerified || isPhoneVerified) {
                    // 인증이 완료되었다고 플래그 설정
                    JoinScreen joinScreen = getJoinScreenReference();
                    if (joinScreen != null) {
                        joinScreen.setAuthenticationCompleted(true);
                    }
                    Authenticate.this.dispose();
                } else {
                    // 둘 다 인증되지 않은 경우
                    JOptionPane.showMessageDialog(Authenticate.this, "아직 인증되지 않았습니다.");
                }
            }
        });

 

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Authenticate.this.dispose();
            }
        });

        // 이메일 인증창과 휴대폰 인증창을 실행 시키지 않도록 초기에 숨깁니다.
        emailVerificationWindow.setVisible(false);
        sensMessage.setVisible(false);
    }
 // JoinScreen 클래스의 참조를 가져오는 메서드 추가
    private JoinScreen getJoinScreenReference() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JoinScreen) {
                return (JoinScreen) window;
            }
        }
        return null;
    }
}

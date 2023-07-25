package jframe.menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Profile extends JFrame {

    private JPanel contentPane;
    private static final String DB_URL = "jdbc:mariadb://14.42.124.13:3306/chatdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gyuho9480!";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Profile frame = new Profile();
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
    public Profile() {
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

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setBounds(67, 22, 210, 210);
        contentPane.add(panel);
        panel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("image/dePro.png");
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel lblImage = new JLabel(imageIcon);
        panel.add(lblImage);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(245, 245, 245));
        panel_1.setBounds(100, 430, 40, 40);
        contentPane.add(panel_1);

        ImageIcon imageIcon_1 = new ImageIcon("image/photo.png");
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel lblImage_1 = new JLabel(imageIcon_1);
        panel_1.add(lblImage_1);

        panel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(Profile.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String imagePath = selectedFile.getAbsolutePath();

                    // 이미지 파일을 바이트 배열로 읽어옴
                    File imageFile = new File(imagePath);
                    byte[] imageData = new byte[(int) imageFile.length()];
                    try (FileInputStream fis = new FileInputStream(imageFile)) {
                        fis.read(imageData);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    // 프로필 사진을 데이터베이스에 저장
                    int userId = getId(); // 회원가입된 아이디의 사용자 ID를 얻어와야 함
                    uploadProfilePicture(userId, imageData);
                }
            }
        });

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(245, 245, 245));
        panel_2.setBounds(200, 430, 40, 40);
        contentPane.add(panel_2);

        ImageIcon imageIcon_2 = new ImageIcon("image/success.png");
        panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel lblImage_2 = new JLabel(imageIcon_2);
        panel_2.add(lblImage_2);

        panel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FirstSwing main = new FirstSwing();
                main.setVisible(true);
                Profile.this.dispose();
            }
        });
    }

    private int getId() {
        // 회원가입된 아이디의 사용자 ID를 가져오는 로직을 구현해야 함
        // 예시로 1을 반환하도록 구현되어 있습니다.
        return 1;
    }

    private void uploadProfilePicture(int userId, byte[] imageData) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // 데이터베이스 연결
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 프로필 사진 업로드를 위한 SQL 문장 작성
            String sql = "UPDATE members SET profile_picture = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setBytes(1, imageData);
            statement.setInt(2, userId);

            // SQL 문장 실행

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "프로필 사진이 업로드되었습니다!");
            } else {
                JOptionPane.showMessageDialog(null, "프로필 사진 업로드에 실패했습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 연결과 문장을 닫아줍니다.
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

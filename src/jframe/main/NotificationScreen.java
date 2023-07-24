package jframe.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NotificationScreen extends JPanel {
    private static JPanel cardPanel;
    private CardLayout cardLayout;
    public JList<String> notificationList;
    public JLabel contentLabel;
    public Component titleLabel;
    public JPanel returnPanel;
    public JLabel lblImage;

    private String[] notificationTitles = {
            "7/30 공지사항 1",
            "공지사항 2",
            "공지사항 3"
            // 여기에 추가적인 공지사항 제목들을 넣을 수 있습니다.
    };

    private String[] notificationContents = {
            "공지사항 1의 내용입니다.",
            "공지사항 2의 내용입니다.",
            "공지사항 3의 내용입니다."
            // 여기에 추가적인 공지사항 내용들을 넣을 수 있습니다.
    };

    public NotificationScreen() {
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(new Color(245, 245, 245));

        // 공지사항 목록을 보여주는 패널
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBackground(new Color(245, 245, 245));
        notificationList = new JList<>(notificationTitles);
        notificationList.setBackground(new Color(245,245,245));
        notificationList.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        JScrollPane listScrollPane = new JScrollPane(notificationList);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        cardPanel.add(listPanel, "listPanel");

        // 공지사항 내용을 보여주는 패널
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(245, 245, 245));
        titleLabel = new JLabel();
        titleLabel.setBackground(new Color(245, 245, 245));
        titleLabel.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
        contentLabel = new JLabel();
        contentLabel.setBackground(new Color(245, 245, 245));
        contentLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(contentLabel, BorderLayout.CENTER);
        cardPanel.add(contentPanel, "contentPanel");

        // 처음에는 공지사항 목록을 보여줍니다.
        cardLayout.show(cardPanel, "listPanel");

        add(cardPanel, BorderLayout.CENTER);

        notificationList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = notificationList.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < notificationContents.length) {
                    // 선택된 공지사항의 제목과 내용을 표시합니다.
                    showNotificationContent(selectedIndex);
                }
            }
        });
        
     // 공지사항 내용을 보여주는 패널에 되돌리는 패널 추가
        returnPanel = new JPanel();
        returnPanel.setBackground(new Color(245, 245, 245));
        ImageIcon imageIcon = new ImageIcon("image/cancel.png");
        lblImage = new JLabel(imageIcon);
        lblImage.setBounds(0, 0, 38, 20);
        returnPanel.add(lblImage);
        returnPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 되돌리는 패널을 클릭하면 다시 공지사항 목록을 보여줍니다.
                cardLayout.show(cardPanel, "listPanel");
            }
        });
        contentPanel.add(returnPanel, BorderLayout.SOUTH); // 패널을 남쪽에 배치합니다.
        
    }

    private void showNotificationContent(int index) {
        String title = notificationTitles[index];
        String content = notificationContents[index];

        // 해당 공지사항의 제목과 내용을 JLabel로 표시합니다.
        JPanel contentPanel = (JPanel) cardPanel.getComponent(1); // 두 번째 패널이 내용을 보여주는 패널입니다.
        JLabel titleLabel = (JLabel) contentPanel.getComponent(0);
        JLabel contentLabel = (JLabel) contentPanel.getComponent(1);
        titleLabel.setText(title);
        contentLabel.setText(content);

        // 공지사항 목록을 가리고 공지사항 내용을 보여줍니다.
        cardLayout.show(cardPanel, "contentPanel");
    }
}
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
            "07.24 <업데이트 완료>",
            "07.24 <데이터 베이스 업데이트 완료>",
            "07.24 <데이터 베이스 수정 진행중>",
            "07.23 <채팅방 이미지 업데이트 2차>",
            "07.23 <폰트 사이즈 추가>",
            "07.23 <채팅방 이미지 업데이트>",
            "07.22 <채팅 로그 >",
            "07.21 <파일 전송 기능 에러>",
            "07.21 <다크,라이트 모드 업데이트>",
            "07.21 <채팅방 목록 수정>",
            "07.20 <이미지 파일 경로 수정 >",
            "07.19 <관리자 기능 추가>",
            "07.13 <DB HOST CONNECT>"
            // 여기에 추가적인 공지사항 제목들을 넣을 수 있습니다.
    };

    private String[] notificationContents = {
    		"김규호 및 이승민 : chatdb패키지 안에있는 모든 파일 규호 컴퓨터 바뀌면서 IP 14.42.124.97로 되어있던거 전부 14.42.124.13 으로 변경함",
    		"김규호 및 이승민 : DTO private로 되어있던거 이름, 전화번호, 아이디 등의 정보 빼오기 위해 public으로 수정",
    		"김규호 및 이승민 : FirstSwing에서 로그인 하면 로그인 한 사람의 DB정보를 받아오게 수정 함. 닉네임 입력 삭제하고 채팅방에서 로그인한 사람의 이름이 나오게 수정 함.",
    		"이승민 : ChatClient 클래스 버튼들 이미지로 변경",
    		"이승민 : 라인정리, 각 아이콘들의 위치조절코드 수정, 설정 탭에 프로필지우고 NotificationScreen 클래스 추가 fontSize 및 engFontSize 크기조절 darkEngFontSize 및 darkFontSize 추가",
    		"이승민 : 채팅방 각 버튼들 이미지 추가.",
    		"이교진 , 이도형 : 채팅로그 파일 중복생성 수정, 관리자 기능( 금지어 설정 )  기능 추가, 내가보낸 채팅, 귓속말 구분 추가",
    		"이교진 : 채팅방 파일전송 추가",
    		"이승민 : GaebalTalk내에 다크모드 라이트모드 아이콘 클릭 시 변환하는 클래스 추가.색상은 여러색상 고민중. firstSwing 로그인 후 닫는 방식도 변경.",
            "이교진 : 방 인원 수 0명일 경우 방 자동삭제.",
            "백승현 : Weather API , if 문 경로 (MAC OS) Windows 변경 필요",
            "이교진 : 관리자 기능 ( 강퇴 ) 추가 .",
            "팀 전체 : DB 호스트 연결 성공"
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
        titleLabel.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 16));
        contentLabel = new JLabel();
        contentLabel.setBackground(new Color(245, 245, 245));
        contentLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
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

        // HTML 태그를 사용하여 줄 바꿈을 적용합니다.
        titleLabel.setText("<html><div style='width: 220px;'>" + title + "</div></html>");
        contentLabel.setText("<html><div style='width: 220px;'>" + content + "</div></html>");
        
     // JLabel의 크기를 동적으로 텍스트에 맞게 조절합니다.
        titleLabel.setSize(titleLabel.getPreferredSize());
        contentLabel.setSize(contentLabel.getPreferredSize());

        // 공지사항 목록을 가리고 공지사항 내용을 보여줍니다.
        cardLayout.show(cardPanel, "contentPanel");
    }
}

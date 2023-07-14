package jframe.main;

import jframe.menu.FirstSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatroomListScreen extends JFrame {

    public ChatroomListScreen() {
        setTitle("채팅방 목록");

        // 1. 컴포넌트들을 만들어 보자.
        JLabel title = new JLabel("채팅방 목록", JLabel.CENTER);
        title.setForeground(new Color(5, 0, 153));
        title.setFont(new Font("휴먼편지체", Font.BOLD, 30));

        JButton logoutButton = new JButton("로그아웃");
        JButton userListButton = new JButton("접속 중인 사용자 목록");
        JButton settingsButton = new JButton("설정 창");

        // 채팅방 리스트를 가져오기 위한 로직 구현
        // 예: DB에서 사용자의 채팅방 목록을 조회

        // 가상의 채팅방 목록
        String[] chatroomList = {"채팅방 1", "채팅방 2", "채팅방 3"};

        JList<String> list = new JList<>(chatroomList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 레이아웃 설정
        setLayout(new BorderLayout());

        // 좌측 패널
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));

        // 좌측 상단 패널
        JPanel leftTopPanel = new JPanel();
        leftTopPanel.setLayout(new BoxLayout(leftTopPanel, BoxLayout.Y_AXIS));
        leftTopPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftTopPanel.add(userListButton);
        leftTopPanel.add(Box.createVerticalStrut(10));
        leftTopPanel.add(settingsButton);

        // 좌측 하단 패널
        JPanel leftBottomPanel = new JPanel();
        leftBottomPanel.setLayout(new BoxLayout(leftBottomPanel, BoxLayout.Y_AXIS));
        leftBottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftBottomPanel.add(logoutButton);

        // 좌측 패널에 상단과 하단 패널 추가
        leftPanel.add(leftTopPanel, BorderLayout.NORTH);
        leftPanel.add(leftBottomPanel, BorderLayout.SOUTH);

        // 중앙 패널
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JScrollPane(list), BorderLayout.CENTER);

        // 레이아웃 설정
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        // 하단 패널
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // 로그아웃 버튼을 클릭했을 때 이벤트 처리
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그아웃 처리 로직 작성
                // 예: 로그인 화면으로 돌아가는 등의 동작

                JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
                new FirstSwing();
                dispose();
            }
        });

        // 접속 중인 사용자 목록 버튼을 클릭했을 때 이벤트 처리
        userListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 접속 중인 사용자 목록 화면으로 이동하는 로직 작성
                JOptionPane.showMessageDialog(null, "접속 중인 사용자 목록 버튼이 클릭되었습니다.");
            }
        });

        // 설정 창 버튼을 클릭했을 때 이벤트 처리
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 설정 창 화면으로 이동하는 로직 작성
                JOptionPane.showMessageDialog(null, "설정 창 버튼이 클릭되었습니다.");
            }
        });
    }

    public static void main(String[] args) {
        new ChatroomListScreen();
    }
}

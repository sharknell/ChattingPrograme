package jframe.menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerServiceGUI extends JFrame {

    private JComboBox<String> inquiryTypeComboBox;
    private JTextArea inquiryTextArea;
    private JButton sendButton;

    public CustomerServiceGUI() {
        setTitle("고객센터");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 고객센터 패널
        JPanel customerServicePanel = new JPanel();
        customerServicePanel.setLayout(new BorderLayout());

        // 문의 유형 선택
        String[] inquiryTypes = {"아이디찾기 오류 문의", "비밀번호찾기 오류 문의", "기타 문의"};
        inquiryTypeComboBox = new JComboBox<>(inquiryTypes);
        customerServicePanel.add(inquiryTypeComboBox, BorderLayout.NORTH);

        // 문의 내용 입력
        inquiryTextArea = new JTextArea(10, 30);
        JScrollPane inquiryScrollPane = new JScrollPane(inquiryTextArea);
        customerServicePanel.add(inquiryScrollPane, BorderLayout.CENTER);

        // 전송 버튼
        sendButton = new JButton("전송");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inquiryType = (String) inquiryTypeComboBox.getSelectedItem();
                String inquiryContent = inquiryTextArea.getText();
                // 문의 내용을 처리하는 로직을 추가하세요.
                JOptionPane.showMessageDialog(null, "문의 유형: " + inquiryType + "\n문의 내용: " + inquiryContent);
                inquiryTextArea.setText("");
                CustomerServiceGUI.this.dispose(); 
            }
        });
        customerServicePanel.add(sendButton, BorderLayout.SOUTH);

        add(customerServicePanel);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomerServiceGUI();
            }
        });
    }
}

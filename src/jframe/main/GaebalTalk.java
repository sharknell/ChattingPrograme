package jframe.main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JList;

public class GaebalTalk extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GaebalTalk frame = new GaebalTalk();
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
	public GaebalTalk() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(185, 207, 210));
		contentPane.setForeground(new Color(185, 207, 210));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(27, 35, 42));
		panel.setBounds(0, 0, 60, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 20, 40, 40);
		panel_1.setBackground(new Color(27, 35, 42));
		panel_1.setBorder(null);
		panel.add(panel_1);
		
        ImageIcon imageIcon = new ImageIcon("image/human.png");
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel lblImage = new JLabel(imageIcon);
        panel_1.add(lblImage);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(27, 35, 42));
        panel_2.setBounds(10, 90, 40, 40);
        panel.add(panel_2);
		
        ImageIcon imageIcon_1 = new ImageIcon("image/speech.png");
        JLabel lblImage_1 = new JLabel(imageIcon_1);
        lblImage_1.setBounds(106, 270, 38, 20);
        panel_2.add(lblImage_1);
        
     // 패널 크기를 JFrame 크기에 맞게 설정
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panel.setBounds(0, 0, 60, getHeight());
            }
        });
	}
}

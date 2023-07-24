package jframe.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class DarkModeChanger {
    private static boolean isDarkMode = false;

    public static void toggleDarkMode(GaebalTalk frame) {
        if (isDarkMode) {
            setLightMode(frame);
            isDarkMode = false;
        } else {
            setDarkMode(frame);
            isDarkMode = true;
        }
    }

    public static void setDarkMode(GaebalTalk frame) {
        frame.getContentPane().setBackground(Color.BLACK);
        setPanelDarkMode(frame,  new Color(36,36,36), new Color(36, 36, 36)); // 패널들의 배경색과 전경색 변경
        frame.menuBar.setBackground(new Color(36, 36, 36));
        frame.createRoom.setBackground(new Color(36, 36, 36));
        frame.inRoom.setBackground(new Color(36, 36, 36));
        frame.setting.setBackground(new Color(36, 36, 36));
        frame.weather.setBackground(new Color(255, 255, 255));
        frame.panel_chat.setBackground(new Color(245, 245, 245));
        frame.roomInfo.setBackground(new Color(36, 36, 36));
        frame.notificationScreen.notificationList.setBackground(new Color(36,36,36));
        frame.notificationScreen.notificationList.setForeground(new Color(245, 245, 245));
        frame.roomInfo.setForeground(new Color(245,245,245));
        frame.notificationScreen.contentLabel.setForeground(new Color(245, 245, 245));
        frame.notificationScreen.titleLabel.setForeground(new Color(245, 245, 245));
        frame.notificationScreen.lblImage.setIcon(new ImageIcon("image/darkCancel.png"));
        frame.korFont.setIcon(new ImageIcon("image/darkFontSize.png"));
        frame.callCenter.setIcon(new ImageIcon("image/darkCallCenter.png"));
        
        // 기타 요소들의 스타일 및 색상 변경
        TitledBorder titledBorder = (TitledBorder) frame.roomInfo.getBorder();
        titledBorder.setTitleColor(new Color(245,245,245));
        frame.roomInfo.setBorder(titledBorder);
    }

    public static void setLightMode(GaebalTalk frame) {
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        setPanelDarkMode(frame, new Color(245, 245, 245), new Color(245,245,245)); // 패널들의 배경색과 전경색 변경
        frame.menuBar.setBackground(new Color(245, 245, 245));
        frame.createRoom.setBackground(new Color(245, 245, 245));
        frame.inRoom.setBackground(new Color(245, 245, 245));
        frame.setting.setBackground(new Color(245, 245, 245));
        frame.weather.setBackground(new Color(255, 255, 255));
        frame.panel_chat.setBackground(new Color(36, 36, 36));
        frame.roomInfo.setBackground(new Color(245, 245, 245));
        frame.notificationScreen.notificationList.setBackground(new Color(245,245,245));
        frame.notificationScreen.notificationList.setForeground(new Color(36, 36, 36));
        frame.roomInfo.setForeground(new Color(36,36,36));
        frame.korFont.setIcon(new ImageIcon("image/fontSize.png"));
        frame.callCenter.setIcon(new ImageIcon("image/callCenter.png"));
        
        // 기타 요소들의 스타일 및 색상 변경
        // TitledBorder의 폰트 색상 변경
        TitledBorder titledBorder = (TitledBorder) frame.roomInfo.getBorder();
        titledBorder.setTitleColor(new Color(36,36,36));
        frame.roomInfo.setBorder(titledBorder);
    }

    private static void setPanelDarkMode(Container container, Color backgroundColor, Color foregroundColor) {
        for (Component component : container.getComponents()) {
            if (component instanceof JPanel) {
                component.setBackground(backgroundColor);
                component.setForeground(foregroundColor);
            }
            if (component instanceof Container) {
                setPanelDarkMode((Container) component, backgroundColor, foregroundColor);
            }
        }
    }
}
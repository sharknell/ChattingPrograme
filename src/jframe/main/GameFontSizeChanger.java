package jframe.main;

import java.awt.Font;

public class GameFontSizeChanger {
    private static int fontSize = 12; // 기본 폰트 크기

    // 폰트 크기를 변경하는 메서드
    public static void GameChangeFontSize(GameChatClient chatClient) {
        // 현재 폰트 크기에 따라 다음 폰트 크기로 변경
        if (fontSize == 8) {
            fontSize = 10;
        } else if (fontSize == 10) {
            fontSize = 12;
        } else if (fontSize == 12) {
            fontSize = 14;
        } else if (fontSize == 14) {
        	fontSize = 16;
        } else if (fontSize == 16) {
        	fontSize = 8;
        }

        // JTextArea인 ta의 폰트 크기 변경
        Font currentFont = chatClient.ta.getFont();
        Font newFont = currentFont.deriveFont((float) fontSize);
        chatClient.ta.setFont(newFont);
    }
}
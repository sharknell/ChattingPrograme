package chatdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WithDraw {

    private static final String DB_URL = "jdbc:mariadb://14.42.124.97:3306/chatdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gyuho9480!";

    public static boolean deleteMember(String id, String password, String RRN) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 회원 정보 확인
            String query = "SELECT * FROM members WHERE id = ? AND password = ? AND RRN = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.setString(2, password);
                statement.setString(3, RRN);
                if (!statement.executeQuery().next()) {
                    System.out.println("일치하는 회원 정보를 찾을 수 없습니다.");
                    return false;
                }
            }

            // 회원 정보 삭제
            query = "DELETE FROM members WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                int rowsDeleted = statement.executeUpdate();
                return rowsDeleted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

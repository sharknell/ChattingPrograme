package chatdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditInformation {

    private static final String DB_URL = "jdbc:mariadb://14.42.124.13:3306/chatdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gyuho9480!";

    public static boolean updateMember(MemberDTO member, String currentPassword, String newPassword) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 회원의 현재 비밀번호 확인
            String query = "SELECT password FROM members WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, member.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String storedPassword = resultSet.getString("password");
                        if (!storedPassword.equals(currentPassword)) {
                            System.out.println("현재 비밀번호가 일치하지 않습니다.");
                            return false;
                        }
                    } else {
                        System.out.println("회원 정보를 찾을 수 없습니다.");
                        return false;
                    }
                }
            }

            // 회원 정보 업데이트
            query = "UPDATE members SET password = ?, phonenumber = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, newPassword);
                statement.setString(2, member.getPhonenumber());
                statement.setString(3, member.getId());
                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

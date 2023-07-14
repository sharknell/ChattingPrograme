package chatdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindPassword {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/chatdb";
    private static final String DB_USER = "gyuho";
    private static final String DB_PASSWORD = "gyuho9480!";

    public static String findPassword(String id, String phoneNumber, String name, String RRN) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String password = null;  // 비밀번호 변수

        try {
            // 데이터베이스 연결
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL 문 준비
            String sql = "SELECT password FROM members WHERE id = ? AND phonenumber = ? AND name = ? AND RRN = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, name);
            pstmt.setString(4, RRN);

            // 쿼리 실행
            rs = pstmt.executeQuery();

            if (rs.next()) {
                password = rs.getString("password");
            } // 일치하는 회원 정보가 없는 경우, password 변수는 그대로 null로 유지됨

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 예외 발생 시 데이터베이스 연결 해제
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return password; // 비밀번호 반환
    }
}

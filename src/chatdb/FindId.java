package chatdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindId {
    private static final String DB_URL = "jdbc:mariadb://14.42.124.13:3306/chatdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gyuho9480!";

    public static String findId(String name, String phoneNumber, String idNum) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String foundId = null;

        try {
            // 데이터베이스 연결
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL 문 준비
            String sql = "SELECT id FROM members WHERE phonenumber = ? AND name = ? AND RRN = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, name);
            pstmt.setString(3, idNum);

            // 쿼리 실행
            rs = pstmt.executeQuery();

            if (rs.next()) {
                foundId = rs.getString("id");
            }
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

        return foundId;
    }
}

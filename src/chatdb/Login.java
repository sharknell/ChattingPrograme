package chatdb;

import java.sql.*;
import java.util.Scanner;

public class Login {
    private static final String DB_URL = "jdbc:mariadb://14.42.124.97:3306/chatdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gyuho9480!";

    public boolean login(String id, String password) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean loginSuccessful = false;

        try {
            // 데이터베이스 연결
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL 문 준비
            String sql = "SELECT * FROM members WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);

            // 쿼리 실행
            rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                if (password.equals(storedPassword)) {
                    System.out.println("로그인 성공!");
                    loginSuccessful = true;
                    // 로그인 성공 후 추가 동작 수행
                    // ...
                } else {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                }
            } else {
                System.out.println("아이디가 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 예외 발생 시 데이터베이스 연결 해제
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return loginSuccessful;
    }
}

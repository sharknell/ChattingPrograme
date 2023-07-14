package chatdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class ChatDAO {
    private static final ChatDAO instance = new ChatDAO();

    private static final String DB_URL = "jdbc:mariadb://14.42.124.97:3306/chatdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gyuho9480!";

    private ChatDAO() {
    }

    public static ChatDAO getInstance() {
        return instance;
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public ArrayList<MemberDTO> getAllMember() {
        Connection conn = getConnection();
        String sql = "SELECT * FROM members";
        ResultSet rs = null;
        Statement stmt = null;
        MemberDTO dto = null;
        ArrayList<MemberDTO> members = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                dto = new MemberDTO();

                dto.setName(rs.getString("name"));
                dto.setId(rs.getString("id"));
                dto.setPassword(rs.getString("password"));
                dto.setReg_date(rs.getDate("reg_date"));
                dto.setPhonenumber(rs.getString("phonenumber"));
                dto.setCountrycode(rs.getInt("countrycode"));
                dto.setRRN(rs.getString("RRN"));

                members.add(dto);
            }
            rs.close();
            stmt.close();
            conn.close();

            return members;
        } catch (SQLException e) {
            e.printStackTrace();
            return (ArrayList)(Collections.emptyList());
        }
    }
    
    public int deleteMember(MemberDTO dto, String password) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM members WHERE id = ? AND password = ?";
        try {
            conn = getConnection();

            // 아이디와 비밀번호를 확인하는 쿼리 실행
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getId());
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 일치하는 아이디와 비밀번호가 존재하는 경우에만 삭제 수행
                sql = "DELETE FROM members WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, dto.getId());
                result = pstmt.executeUpdate();
            } else {
                System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return result;
    }




    public boolean registerMember(MemberDTO memberDTO) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO members (id, name, password, phonenumber) VALUES (?, ?, ?, ?)")) {

            pstmt.setString(1, memberDTO.getId());
            pstmt.setString(2, memberDTO.getName());
            pstmt.setString(3, memberDTO.getPassword());
            pstmt.setString(4, memberDTO.getPhonenumber());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error registering member: " + e.getMessage());
            return false;
        }
    }
}

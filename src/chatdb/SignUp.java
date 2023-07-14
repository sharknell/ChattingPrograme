package chatdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class SignUp {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@]).{8,}$";

    private static final Pattern EMAIL_REGEX = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern PASSWORD_REGEX = Pattern.compile(PASSWORD_PATTERN);

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/chatdb";
    private static final String DB_USER = "gyuho";
    private static final String DB_PASSWORD = "gyuho9480!";

    static Scanner scanner = new Scanner(System.in);

    public static boolean registerMember() {
        String id;
        boolean isValidId = false;
        do {
            System.out.print("아이디(이메일)를 입력하세요: ");
            id = scanner.nextLine();

            if (isValidEmail(id)) {
                isValidId = true;

                if (isDuplicateId(id)) {
                    JOptionPane.showMessageDialog(null, "중복된 아이디입니다. 다시 입력해주세요.");
                    isValidId = false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "유효한 이메일 형식이 아닙니다. 다시 입력해주세요.");
            }
        } while (!isValidId);

        String password;
        boolean isValidPassword = false;
        do {
            System.out.print("비밀번호를 입력하세요: ");
            password = scanner.nextLine();

            if (isValidPassword(password)) {
                isValidPassword = true;
            } else {
                JOptionPane.showMessageDialog(null, "비밀번호는 영문자, 숫자와 특수문자 (!, @)를 최소 1개 이상 포함해야하고 8글자 이상이어야 합니다. 다시 입력해주세요.");
            }
        } while (!isValidPassword);

        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();

        String phoneNumber;
        boolean isValidPhoneNumber = false;
        do {
            System.out.print("전화번호를 입력하세요: ");
            phoneNumber = scanner.nextLine();

            if (isDuplicatePhoneNumber(phoneNumber)) {
                JOptionPane.showMessageDialog(null, "중복된 전화번호입니다. 다시 입력해주세요.");
            } else {
                isValidPhoneNumber = true;
                JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
            }
        } while (!isValidPhoneNumber);

        String RRN;
        boolean isValidRRN = false;
        do {
            System.out.print("주민등록번호를 입력하세요 (앞자리-뒷자리): ");
            RRN = scanner.nextLine();

            if (isValidRRN(RRN)) {
                isValidRRN = true;
            } else {
                JOptionPane.showMessageDialog(null, "유효한 주민등록번호 형식이 아닙니다. 다시 입력해주세요.");
            }
        } while (!isValidRRN);

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        memberDTO.setName(name);
        memberDTO.setPassword(password);
        memberDTO.setPhonenumber(phoneNumber);
        memberDTO.setRRN(RRN);

        return registerMember(memberDTO);
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.matches();
    }

    public static boolean isDuplicateId(String id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM members WHERE id = ?")) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking duplicate id: " + e.getMessage());
            return true;
        }
    }

    public static boolean isValidPassword(String password) {
        Matcher matcher = PASSWORD_REGEX.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidRRN(String RRN) {
        // 정규식을 사용하여 주민등록번호 형식 검사
        Pattern pattern = Pattern.compile("^\\d{6}-\\d{7}$");
        Matcher matcher = pattern.matcher(RRN);
        return matcher.matches();
    }

    public static boolean registerMember(MemberDTO memberDTO) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement pstmt = conn
                        .prepareStatement("INSERT INTO members (id, name, password, phonenumber, RRN) VALUES (?, ?, ?, ?, ?)")) {

            pstmt.setString(1, memberDTO.getId());
            pstmt.setString(2, memberDTO.getName());
            pstmt.setString(3, memberDTO.getPassword());
            pstmt.setString(4, memberDTO.getPhonenumber());
            pstmt.setString(5, memberDTO.getRRN());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error registering member: " + e.getMessage());
            return false;
        }

    }

    public static boolean isDuplicatePhoneNumber(String phoneNumber) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement("SELECT phonenumber FROM members WHERE phonenumber = ?")) {
            pstmt.setString(1, phoneNumber);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking duplicate phone number: " + e.getMessage());
            return true;
        }
    }
}

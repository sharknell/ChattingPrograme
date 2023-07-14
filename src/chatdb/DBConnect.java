package chatdb;

import java.util.ArrayList;
import java.util.Scanner;

public class DBConnect {
    public static void main(String[] args) {
        ChatDAO dao = ChatDAO.getInstance();

        boolean isRunning = true;

        try (Scanner scanner = new Scanner(System.in)) {
            while (isRunning) {
                System.out.println("1. 회원 가입");
                System.out.println("2. 회원 전체 정보 보기");
                System.out.println("3. 로그인");
                System.out.println("4. 회원 탈퇴");
                System.out.println("5. 아이디 찾기");
                System.out.println("6. 비밀번호 찾기");
                System.out.println("0. 종료");
                System.out.print("원하는 작업의 번호를 입력하세요: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (choice == 1) {
                    // 회원 가입 수행
                    SignUp.registerMember();
                } else if (choice == 2) {
                    // 회원의 모든 정보 보기
                    ArrayList<MemberDTO> members = dao.getAllMember();

                    for (MemberDTO dto : members) {
                        System.out.println(dto.getName() + " " +
                                dto.getId() + " " +
                                dto.getPassword() + " " +
                                dto.getPhonenumber() + " " +
                                dto.getReg_date() + " " +
                                dto.getRRN() + " " +
                                dto.getCountrycode());
                    }
                } else if (choice == 3) {
                    Login login = new Login();
                    System.out.print("아이디를 입력하세요: ");
                    String id = scanner.nextLine();

                    System.out.print("비밀번호를 입력하세요: ");
                    String password = scanner.nextLine();

                    login.login(id, password);
                } else if (choice == 4) {
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("아이디를 입력하세요: ");
                    String deleteId = scanner.nextLine();

                    System.out.print("비밀번호를 입력하세요: ");
                    String password = scanner.nextLine();

                    MemberDTO delDto = new MemberDTO();
                    delDto.setId(deleteId); // 입력받은 아이디를 삭제할 아이디로 설정

                    int result = dao.deleteMember(delDto, password);
                    if (result > 0) {
                        System.out.println("회원 탈퇴가 완료되었습니다.");
                    } else {
                        System.out.println("아이디 또는 비밀번호가 일치하지 않아 회원 탈퇴를 실패하였습니다.");
                    }
                } else if (choice == 5) {
                    System.out.print("이름을 입력하세요: ");
                    String name = scanner.nextLine();

                    System.out.print("전화번호를 입력하세요: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("주민등록번호를 입력하세요: ");
                    String idNum = scanner.nextLine();

                    FindId.findId(name, phoneNumber, idNum);
                } else if (choice == 6) {
                    FindPassword.findPassword(null, null, null, null);
                } else if (choice == 0) {
                    isRunning = false;
                } else {
                    System.out.println("잘못된 선택입니다. 다시 입력하세요.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

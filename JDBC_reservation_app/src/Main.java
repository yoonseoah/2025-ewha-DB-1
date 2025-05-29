import dao.UserDAO;
import dto.User;
import util.DBUtil;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            UserDAO userDAO = new UserDAO(conn);

            boolean isRunning = true;
            boolean isLoggedIn = false;

            while (isRunning) {
                System.out.println("\n==== 사진관 예약 시스템 ====");
                System.out.println("1. 회원가입");
                System.out.println("2. 로그인");
                System.out.println("3. 로그아웃");
                System.out.println("4. 회원탈퇴");
                System.out.println("0. 종료");
                System.out.print("선택: ");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.print("이름: ");
                        String userName = scanner.nextLine();
                        System.out.print("전화번호: ");
                        String phone = scanner.nextLine();
                        System.out.print("이메일: ");
                        String email = scanner.nextLine();

                        User newUser = new User(userName, phone, email);
                        if (userDAO.register(newUser)) {
                            System.out.println("회원가입 성공!");
                        } else {
                            System.out.println("회원가입 실패.");
                        }
                        break;

                    case "2":
                        System.out.print("전화번호: ");
                        String loginPhone = scanner.nextLine();
                        System.out.print("이메일: ");
                        String loginEmail = scanner.nextLine();

                        if (userDAO.login(loginPhone, loginEmail)) {
                            isLoggedIn = true;
                            System.out.println("로그인 성공!");
                        } else {
                            System.out.println("로그인 실패.");
                        }
                        break;

                    case "3":
                        if (isLoggedIn) {
                            userDAO.logout();
                            isLoggedIn = false;
                            System.out.println("로그아웃 완료.");
                        } else {
                            System.out.println("로그인 상태가 아닙니다.");
                        }
                        break;

                    case "4":
                        System.out.print("탈퇴할 전화번호: ");
                        String deletePhone = scanner.nextLine();
                        if (userDAO.deleteUser(deletePhone)) {
                            System.out.println("회원탈퇴 성공.");
                        } else {
                            System.out.println("회원탈퇴 실패.");
                        }
                        break;

                    case "0":
                        isRunning = false;
                        System.out.println("프로그램을 종료합니다.");
                        break;

                    default:
                        System.out.println("잘못된 선택입니다.");
                        break;
                }
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

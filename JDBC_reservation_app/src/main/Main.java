package main;

import dao.*;
import dto.*;
import util.DBUtil;

import java.sql.Connection;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection(); Scanner scanner = new Scanner(System.in)) {
            UserDAO userDAO = new UserDAO(conn);
            ReservationDAO reservationDAO = new ReservationDAO(conn);
            StudioDAO studioDAO = new StudioDAO(conn);
            PhotographerDAO photographerDAO = new PhotographerDAO(conn);
            BackgroundDAO backgroundDAO = new BackgroundDAO(conn);
            RentalDAO rentalDAO = new RentalDAO(conn);
            ReservationRentalDAO rrDAO = new ReservationRentalDAO(conn);

            boolean isRunning = true;
            boolean isLoggedIn = false;
            int loggedInUserId = -1;

            while (isRunning) {
                if (!isLoggedIn) {
                    System.out.println("\n=== 사진관 예약 시스템 ===");
                    System.out.println("1. 회원가입");
                    System.out.println("2. 로그인");
                    System.out.println("0. 종료");
                    System.out.print("선택: ");

                    String input = scanner.nextLine();

                    if (input.equals("1")) {
                        System.out.print("이름: ");
                        String userName = scanner.nextLine();
                        System.out.print("전화번호: ");
                        String phone = scanner.nextLine();
                        System.out.print("이메일: ");
                        String email = scanner.nextLine();
                        User user = new User(userName, phone, email);
                        if (userDAO.register(user)) System.out.println("회원가입 성공!");
                        else System.out.println("회원가입 실패.");
                    } else if (input.equals("2")) {
                        System.out.print("전화번호: ");
                        String phone = scanner.nextLine();
                        System.out.print("이메일: ");
                        String email = scanner.nextLine();
                        if (userDAO.login(phone, email)) {
                            loggedInUserId = userDAO.getUserIdByPhone(phone);
                            isLoggedIn = true;
                            System.out.println("로그인 성공!");
                        } else {
                            System.out.println("로그인 실패.");
                        }
                    } else if (input.equals("0")) {
                        isRunning = false;
                    } else {
                        System.out.println("잘못된 입력입니다.");
                    }
                } else {
                    System.out.println("\n[사용자 메뉴]");
                    System.out.println("1. 전체 사진관 조회");
                    System.out.println("2. 사진관 작가 조회");
                    System.out.println("3. 배경 목록 조회");
                    System.out.println("4. 예약 생성");
                    System.out.println("5. 내 예약 조회");
                    System.out.println("6. 예약 수정");
                    System.out.println("7. 예약 취소");
                    System.out.println("8. 대여 아이템 조회 + 연결");
                    System.out.println("9. 즐겨찾기 작가 조회");
                    System.out.println("10. 인기 작가 순위");
                    System.out.println("11. 사진관 예약 통계");
                    System.out.println("12. 로그아웃");
                    System.out.println("13. 회원탈퇴");
                    System.out.println("0. 종료");
                    System.out.print("선택: ");

                    String choice = scanner.nextLine();

                    if (choice.equals("1")) {
                        List<Studio> studios = studioDAO.getAllStudios();
                        System.out.println("===== 전체 사진관 목록 =====");
                        for (Studio s : studios) {
                            System.out.println(s.getStudioId() + " | " + s.getStudioName() + " | " + s.getLocation());
                        }
                    } else if (choice.equals("2")) {
                        System.out.print("\n조회할 사진관 ID를 입력하세요: ");
                        int studioId = scanner.nextInt();
                        scanner.nextLine();
                        List<Photographer> photographers = photographerDAO.getPhotographersByStudio(studioId);

                        if (photographers.isEmpty()) {
                            System.out.println("해당 사진관에 소속된 작가가 없습니다.");
                        } else {
                            System.out.println("===== 작가 목록 =====");
                            for (Photographer p : photographers) {
                                System.out.println("이름: " + p.getName() + ", 연차: " + p.getYears() + ", 예약 수: " + p.getReservationCount());
                            }
                        }
                    } else if (choice.equals("3")) {
                        List<Background> backgrounds = backgroundDAO.getAllBackgrounds();
                        System.out.println("\n===== 예약 가능 배경 색상 목록 =====");
                        for (Background bg : backgrounds) {
                            System.out.println("ID: " + bg.getBackgroundId() + ", 색상: " + bg.getColorName());
                        }
                    } else if (choice.equals("4")) {
                        System.out.print("스튜디오 ID: ");
                        int studioId = Integer.parseInt(scanner.nextLine());
                        System.out.print("사진작가 ID: ");
                        int photographerId = Integer.parseInt(scanner.nextLine());
                        System.out.print("배경 ID: ");
                        int backgroundId = Integer.parseInt(scanner.nextLine());
                        System.out.print("시작 시간 (YYYY-MM-DD HH:MM:SS): ");
                        String start = scanner.nextLine();
                        System.out.print("종료 시간 (YYYY-MM-DD HH:MM:SS): ");
                        String end = scanner.nextLine();
                        Reservation res = new Reservation(0, loggedInUserId, studioId, photographerId, backgroundId, start, end);
                        if (reservationDAO.insertReservation(res))
                            System.out.println("예약 성공! ID: " + res.getReservationId());
                        else System.out.println("예약 실패");

                    } else if (choice.equals("5")) {
                        System.out.println("\n[나의 예약 조회]");
                        System.out.print("사용자 ID: ");
                        int userId = Integer.parseInt(scanner.nextLine());
                        List<Reservation> list = reservationDAO.getReservationsByUser(userId);  // 타입 맞게 받고

                        for (Reservation r : list) {
                            System.out.println("- 예약 ID: " + r.getReservationId());
                            System.out.println("  스튜디오 ID: " + r.getStudioId());
                            System.out.println("  사진작가 ID: " + r.getPhotographerId());
                            System.out.println("  배경 ID: " + r.getBackgroundId());
                            System.out.println("  시간: " + r.getStartTime() + " ~ " + r.getEndTime());
                            System.out.println();
                        }

                    } else if (choice.equals("6")) {
                        System.out.println("\n[예약 수정]");
                        System.out.print("수정할 예약 ID: ");
                        int reservationId = Integer.parseInt(scanner.nextLine());

                        Reservation origin = reservationDAO.findById(reservationId);
                        if (origin == null) {
                            System.out.println("해당 예약을 찾을 수 없습니다.");
                            break;
                        }

                        System.out.print("새 스튜디오 ID: ");
                        String studioIdInput = scanner.nextLine();
                        if (!studioIdInput.isBlank()) {
                            origin.setStudioId(Integer.parseInt(studioIdInput));
                        }
                        System.out.print("새 사진작가 ID: ");
                        String photographerIdInput = scanner.nextLine();
                        if (!photographerIdInput.isBlank()) {
                            origin.setPhotographerId(Integer.parseInt(photographerIdInput));
                        }
                        System.out.print("새 배경 ID: ");
                        String backgroundIdInput = scanner.nextLine();
                        if (!backgroundIdInput.isBlank()) {
                            origin.setBackgroundId(Integer.parseInt(backgroundIdInput));
                        }
                        System.out.print("새 시작 시간 (YYYY-MM-DD HH:MM): ");
                        String startTime = scanner.nextLine();
                        if (!startTime.isBlank()) {
                            origin.setStartTime(startTime);
                        }
                        System.out.print("새 종료 시간 (YYYY-MM-DD HH:MM): ");
                        String endTime = scanner.nextLine();
                        if (!endTime.isBlank()) {
                            origin.setEndTime(endTime);
                        }

                        boolean updated = reservationDAO.updateReservation(origin);
                        if (updated) {
                            System.out.println("예약이 수정되었습니다.");
                        } else {
                            System.out.println("예약 수정 실패");
                        }


                    } else if (choice.equals("7")) {
                        System.out.println("\n[예약 취소]");
                        System.out.print("취소할 예약 ID: ");
                        int rid = Integer.parseInt(scanner.nextLine());
                        System.out.println(reservationDAO.deleteReservation(rid) ? "취소 완료" : "취소 실패");

                    } else if (choice.equals("8")) {
                        rentalDAO.getAllRentals().forEach(System.out::println);
                        System.out.print("대여할 아이템 ID들 (쉼표 구분): ");
                        String[] ids = scanner.nextLine().split(",");
                        List<Integer> itemIds = new ArrayList<>();
                        for (String s : ids) itemIds.add(Integer.parseInt(s.trim()));
                        System.out.print("예약 ID 입력: ");
                        int rid = Integer.parseInt(scanner.nextLine());
                        itemIds.forEach(item -> rrDAO.addReservationRental(rid, item));
                        System.out.println("아이템 연결 완료");
                    } else if (choice.equals("9")) {
                        photographerDAO.getMyFavPhotographers(loggedInUserId).forEach(System.out::println);
                    } else if (choice.equals("10")) {
                        photographerDAO.getPhotographerRanks().forEach(System.out::println);
                    } else if (choice.equals("11")) {
                        studioDAO.getStudioSummary(conn).forEach(System.out::println);
                    } else if (choice.equals("12")) {
                        isLoggedIn = false;
                        loggedInUserId = -1;
                        System.out.println("로그아웃 완료");
                    } else if (choice.equals("13")) {
                        System.out.print("전화번호 입력: ");
                        String phone = scanner.nextLine();
                        if (userDAO.deleteUser(phone)) {
                            System.out.println("탈퇴 완료");
                            isLoggedIn = false;
                            loggedInUserId = -1;
                        } else System.out.println("탈퇴 실패");
                    } else if (choice.equals("0")) {
                        isRunning = false;
                    } else {
                        System.out.println("잘못된 선택입니다");
                    }
                }
            }

            System.out.println("시스템을 종료합니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
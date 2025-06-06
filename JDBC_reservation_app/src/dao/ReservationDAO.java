package dao;

import dto.Reservation;
import dto.ReservationInfo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    private final Connection conn;

    public ReservationDAO(Connection conn) {
        this.conn = conn;
    }

    // 예약 생성
    public boolean insertReservation(Reservation reservation) {
        String sql = """
                INSERT INTO Reservation 
                (user_id, studio_id, photographer_id, background_id, start_time, end_time)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, reservation.getUserId());
            pstmt.setInt(2, reservation.getStudioId());
            pstmt.setInt(3, reservation.getPhotographerId());
            pstmt.setInt(4, reservation.getBackgroundId());
            pstmt.setString(5, reservation.getStartTime());
            pstmt.setString(6, reservation.getEndTime());

            int affected = pstmt.executeUpdate();

            if (affected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    reservation.setReservationId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 나의 예약 조회 (사용자 ID로)
    public List<ReservationInfo> getReservationsByUser(int userId) {
        List<ReservationInfo> list = new ArrayList<>();
        String sql = "SELECT * FROM View_Reservation_Info WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReservationInfo info = new ReservationInfo(
                        rs.getInt("reservation_id"),
                        rs.getString("user_name"),
                        rs.getString("studio_name"),
                        rs.getString("photographer_name"),
                        rs.getString("color_name"),
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getTimestamp("end_time").toLocalDateTime()
                );
                list.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 예약 ID로 예약 조회 (수정 시 활용)
    public Reservation findById(int reservationId) {
        String sql = "SELECT * FROM Reservation WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getInt("user_id"),
                        rs.getInt("studio_id"),
                        rs.getInt("photographer_id"),
                        rs.getInt("background_id"),
                        rs.getString("start_time"),
                        rs.getString("end_time")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 예약 수정
    public boolean updateReservation(Reservation reservation) {
        String sql = """
                UPDATE Reservation SET 
                    user_id = ?, studio_id = ?, photographer_id = ?, background_id = ?, 
                    start_time = ?, end_time = ?
                WHERE reservation_id = ?
                """;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getUserId());
            pstmt.setInt(2, reservation.getStudioId());
            pstmt.setInt(3, reservation.getPhotographerId());
            pstmt.setInt(4, reservation.getBackgroundId());
            pstmt.setString(5, reservation.getStartTime());
            pstmt.setString(6, reservation.getEndTime());
            pstmt.setInt(7, reservation.getReservationId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 예약 취소
    public boolean deleteReservation(int reservationId) {
        String sql = "DELETE FROM Reservation WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
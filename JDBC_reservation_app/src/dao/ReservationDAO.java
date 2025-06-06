package dao;

import dto.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private Connection conn;

    public ReservationDAO(Connection conn) {
        this.conn = conn;
    }

    // 예약 생성
    public boolean insertReservation(Reservation res) {
        String sql = "INSERT INTO reservation (user_id, studio_id, photographer_id, background_id, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, res.getUserId());
            pstmt.setInt(2, res.getStudioId());
            pstmt.setInt(3, res.getPhotographerId());
            pstmt.setInt(4, res.getBackgroundId());
            pstmt.setString(5, res.getStartTime());
            pstmt.setString(6, res.getEndTime());

            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        res.setReservationId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 사용자 ID로 예약 전체 조회
    public List<Reservation> getReservationsByUser(int userId) {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM View_Reservation_Info WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Reservation res = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getInt("user_id"),
                            rs.getInt("studio_id"),
                            rs.getInt("photographer_id"),
                            rs.getInt("background_id"),
                            rs.getString("start_time"),
                            rs.getString("end_time")
                    );
                    list.add(res);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 예약 ID로 예약 상세 조회
    public Reservation findById(int id) {
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 예약 수정
    public boolean updateReservation(Reservation res) {
        String sql = "UPDATE reservation SET studio_id = ?, photographer_id = ?, background_id = ?, start_time = ?, end_time = ? WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, res.getStudioId());
            pstmt.setInt(2, res.getPhotographerId());
            pstmt.setInt(3, res.getBackgroundId());
            pstmt.setString(4, res.getStartTime());
            pstmt.setString(5, res.getEndTime());
            pstmt.setInt(6, res.getReservationId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 예약 삭제
    public boolean deleteReservation(int id) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

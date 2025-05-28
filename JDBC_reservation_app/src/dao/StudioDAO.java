package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.StudioStatistics;

public class StudioDAO {
    public List<StudioStatistics> getStudioSummary(Connection conn) throws SQLException {
        String sql = "SELECT studio_id, COUNT(*) AS total_reservations " +
                "FROM Reservation GROUP BY ROLLUP(studio_id)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            List<StudioStatistics> stats = new ArrayList<>();
            while (rs.next()) {
                StudioStatistics stat = new StudioStatistics();
                stat.setStudioId(rs.getInt("studio_id"));
                stat.setTotalReservations(rs.getInt("total_reservations"));
                stats.add(stat);
            }
            return stats;
        }
    }
}

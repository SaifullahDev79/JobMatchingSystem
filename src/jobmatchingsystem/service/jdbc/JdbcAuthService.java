package jobmatchingsystem.service.jdbc;

import jobmatchingsystem.model.User;
import jobmatchingsystem.persistence.Db;
import jobmatchingsystem.security.PasswordUtil;
import jobmatchingsystem.service.AuthService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcAuthService implements AuthService {

    @Override
    public Optional<User> login(String username, String rawPassword) {
        final String sql = "SELECT id, username, role FROM users WHERE username=? AND password_hash=?";
        final String u = username == null ? "" : username.trim();
        final String p = rawPassword == null ? "" : rawPassword.trim();
        final String hash = PasswordUtil.sha256(p);

        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u);
            ps.setString(2, hash);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("role")
                    ));
                }
                return Optional.empty();
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Login failed: " + ex.getMessage(), ex);
        }
    }

    @Override
    public boolean register(String username, String rawPassword, String role) {
        final String sql = "INSERT INTO users(username, password_hash, role) VALUES(?,?,?)";
        final String u = username == null ? "" : username.trim();
        final String p = rawPassword == null ? "" : rawPassword.trim();
        final String r = (role == null || role.isBlank()) ? "user" : role.trim().toLowerCase();
        final String hash = PasswordUtil.sha256(p);

        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u);
            ps.setString(2, hash);
            ps.setString(3, r);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            // Duplicate username (unique constraint) -> SQLState 23xxx
            if (e.getSQLState() != null && e.getSQLState().startsWith("23")) return false;
            throw new RuntimeException("Register failed: " + e.getMessage(), e);
        }
    }
}

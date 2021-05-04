package pl.coderslab.dao;

import pl.coderslab.model.About;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AboutDao {
    private static final String READ_ABOUT_QUERY = "SELECT * FROM about where id = ?;";

    public About read(Integer adminId) {
        About about = new About();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ABOUT_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    about.setTopic(resultSet.getString("topic"));
                    about.setDescription(resultSet.getString("description"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return about;
    }
}

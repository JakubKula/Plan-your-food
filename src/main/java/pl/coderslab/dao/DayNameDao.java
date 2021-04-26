package pl.coderslab.dao;

import pl.coderslab.model.Book;
import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {


    private static final String FIND_ALL_BY_DAY_NAMES = "SELECT * FROM day_name WHERE name = ?;";
    private static final String SHOW_ALL = "SELECT * FROM day_name;";


    public DayName findAll(String dayName) {
        DayName dayName1 = new DayName();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_DAY_NAMES)
        ) {
            statement.setString(1, dayName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    dayName1.setId(resultSet.getInt("id"));
                    dayName1.setName(resultSet.getString("name"));
                    dayName1.setDisplayOrder(resultSet.getString("display_order"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayName1;
    }

    public List<DayName> showAll() {

        List<DayName> list = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SHOW_ALL)
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DayName dayName1 = new DayName();
                    dayName1.setId(resultSet.getInt("id"));
                    dayName1.setName(resultSet.getString("name"));
                    dayName1.setDisplayOrder(resultSet.getString("display_order"));
                    list.add(dayName1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

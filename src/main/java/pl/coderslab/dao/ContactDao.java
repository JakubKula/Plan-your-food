package pl.coderslab.dao;

import pl.coderslab.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pl.coderslab.utils.DbUtil;

public class ContactDao {
    private static final String READ_CONTACT_QUERY = "SELECT * FROM contact where id = ?;";

    public Contact read(Integer adminId) {
        Contact contact = new Contact();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_CONTACT_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    contact.setTopic1(resultSet.getString("topic1"));
                    contact.setDescription1(resultSet.getString("description1"));
                    contact.setDescription2list(resultSet.getString("description2list"));
                    contact.setTopic2(resultSet.getString("topic2"));
                    contact.setTopic3(resultSet.getString("topic3"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contact;
    }
}

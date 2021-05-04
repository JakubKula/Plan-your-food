package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.*;
import pl.coderslab.utils.DbUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name,last_name,email,password) VALUES (?,?,?,?);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins WHERE id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ? , last_name = ?, email = ? WHERE id = ?;";
    private static final String READ_PASSWORD_QUERY = "SELECT password, enable FROM admins WHERE email = ?;";
    private static final String READ_ADMIN_EMAIL_QUERY = "SELECT * FROM admins WHERE email = ?;";
    private static final String UPDATE_ADMIN_PASSWORD_QUERY = "UPDATE admins SET password = ? WHERE id = ?;";
    private static final String CHECK_IF_SUPERADMIN = "SELECT superadmin FROM admins where email = ?;";
    private static final String BLOCK_USER = "UPDATE admins SET enable = 0 WHERE id = ?;";
    private static final String UNBLOCK_USER = "UPDATE admins SET enable = 1 WHERE id = ?;";

    public Admin read(Integer adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }



    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin adminToAdd = new Admin();
                adminToAdd.setId(resultSet.getInt("id"));
                adminToAdd.setFirstName(resultSet.getString("first_name"));
                adminToAdd.setLastName(resultSet.getString("last_name"));
                adminToAdd.setEmail(resultSet.getString("email"));
                adminToAdd.setPassword(resultSet.getString("password"));
                adminToAdd.setEnable(resultSet.getInt("enable"));
                adminList.add(adminToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }


    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_ADMIN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, admin.getFirstName());
            insertStm.setString(2, admin.getLastName());
            insertStm.setString(3, admin.getEmail());
            insertStm.setString(4, BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt(12)));
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admin.setId(generatedKeys.getInt(1));
                    return admin;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer adminID) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, adminID);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            statement.setInt(4, admin.getId());
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
//            statement.setString(4, BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt(12)));

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String verification (String email, String password){
        String verification = "bad";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PASSWORD_QUERY)
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String passwordHashed = resultSet.getString("password");
                    int enable = resultSet.getInt("enable");
                    if(enable == 1){
                        if (BCrypt.checkpw(password, passwordHashed)){
                            verification = "log";
                        }
                    }else{
                        verification = "block";
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verification;
    }

    public Admin readByEmail(String adminEmail) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_EMAIL_QUERY)
        ) {
            statement.setString(1, adminEmail);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public void updatePassword(int adminId, String newPassword) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_PASSWORD_QUERY)) {
            statement.setInt(2, adminId);
            statement.setString(1, BCrypt.hashpw(newPassword, BCrypt.gensalt(12)));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean superAdmin(String email){
        boolean superAdmin = false;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_IF_SUPERADMIN)
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int isSuper = resultSet.getInt("superadmin");
                    if(isSuper == 1){
                        superAdmin = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return superAdmin;
    }

    public void block(int adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(BLOCK_USER)) {
            statement.setInt(1, adminId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unblock(int adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UNBLOCK_USER)) {
            statement.setInt(1, adminId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipie;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecipieDao {



    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ? , ingredients = ?, description = ?, updated = ?, preparation_time = ?, preparation = ?  WHERE	id = ?;";

    private static final String SHOW_ALL = "SELECT * FROM recipe;";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String READ_RECIPE_QUERY_FOR_ADMIN = "SELECT * from recipe where admin_id = ?;";
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name,ingredients,description,created,updated,preparation_time,preparation,admin_id) VALUES (?,?,?,?,?,?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";

    private static final String USER_NUMBER_OF_RECIPE = "SELECT count(*) FROM recipe WHERE admin_id = ?";


    public List<Recipie> showAll() {

        List<Recipie> list = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SHOW_ALL)
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Recipie recipie = new Recipie();
                    recipie.setId(resultSet.getInt("id"));
                    recipie.setName(resultSet.getString("name"));
                    recipie.setIngredients(resultSet.getString("ingredients"));
                    recipie.setDescription(resultSet.getString("description"));
                    recipie.setCreated(resultSet.getString("created"));
                    recipie.setUpdated(resultSet.getString("updated"));
                    recipie.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipie.setPreparation(resultSet.getString("preparation"));
                    recipie.setAdminId(resultSet.getInt("admin_id"));
                    list.add(recipie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Recipie> showAllForAdmin(int AdminId) {

        List<Recipie> list = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY_FOR_ADMIN)
        ) {
            statement.setInt(1, AdminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Recipie recipie = new Recipie();
                    recipie.setId(resultSet.getInt("id"));
                    recipie.setName(resultSet.getString("name"));
                    recipie.setIngredients(resultSet.getString("ingredients"));
                    recipie.setDescription(resultSet.getString("description"));
                    recipie.setCreated(resultSet.getString("created"));
                    recipie.setUpdated(resultSet.getString("updated"));
                    recipie.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipie.setPreparation(resultSet.getString("preparation"));
                    recipie.setAdminId(resultSet.getInt("admin_id"));
                    list.add(recipie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Recipie read(int id) {
        Recipie recipie = new Recipie();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipie.setId(resultSet.getInt("id"));
                    recipie.setName(resultSet.getString("name"));
                    recipie.setIngredients(resultSet.getString("ingredients"));
                    recipie.setDescription(resultSet.getString("description"));
                    recipie.setCreated(resultSet.getString("created"));
                    recipie.setUpdated(resultSet.getString("updated"));
                    recipie.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipie.setPreparation(resultSet.getString("preparation"));
                    recipie.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipie;

    }

    public int numberOfRecipie(int userId) {

        int value = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(USER_NUMBER_OF_RECIPE)
        ) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                   value = resultSet.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public Recipie create(Recipie recipie) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipie.getName());
            insertStm.setString(2, recipie.getIngredients());
            insertStm.setString(3, recipie.getDescription());
            insertStm.setString(4, recipie.getCreated());
            insertStm.setString(5, recipie.getUpdated());
            insertStm.setInt(6, recipie.getPreparationTime());
            insertStm.setString(7, recipie.getPreparation());
            insertStm.setInt(8, recipie.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipie.setId(generatedKeys.getInt(1));
                    return recipie;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int Id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, Id);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void update(Recipie recipie, int recipieID) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {

            statement.setString(1, recipie.getName());
            statement.setString(2, recipie.getIngredients());
            statement.setString(3, recipie.getDescription());
            statement.setString(4, recipie.getUpdated());
            statement.setInt(5, recipie.getPreparationTime());
            statement.setString(6, recipie.getPreparation());
            statement.setInt(7, recipieID);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

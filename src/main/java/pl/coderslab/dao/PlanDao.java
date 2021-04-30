package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetails;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created,adminId) VALUES (?,?,?,?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan WHERE id = ?;";
    private static final String FIND_ALL_PLANS_BY_ADMIN_QUERY = "SELECT * FROM plan WHERE id = ?;";
    private static final String READ_PLAN_QUERY = "SELECT * from plan WHERE id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ?, adminId = ? WHERE	id = ?;";
    private static final String PLANS_NUMBER_QUERY = "SELECT COUNT(admin_id) AS plans FROM plan WHERE admin_id = ?;";
    private static final String RECENT_PLAN_QUERY = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description " +
            "FROM `recipe_plan` " +
            "JOIN day_name on day_name.id=day_name_id " +
            "JOIN recipe on recipe.id=recipe_id WHERE " +
            "recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?) " +
            "ORDER by day_name.display_order, recipe_plan.display_order;";

    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getDate("created"));
                    plan.setId(resultSet.getInt("adminId"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }

    public List<Plan> findAll(int adminId) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_BY_ADMIN_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Plan planToAdd = new Plan();
                    planToAdd.setId(resultSet.getInt("id"));
                    planToAdd.setName(resultSet.getString("name"));
                    planToAdd.setDescription(resultSet.getString("description"));
                    planToAdd.setCreated(resultSet.getDate("created"));
                    planList.add(planToAdd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }

    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setDate(3, (Date) plan.getCreated());
            insertStm.setInt(4, plan.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(5, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setDate(3, (Date) plan.getCreated());
            statement.setInt(4, plan.getAdminId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int plansNumber(int adminId) {
        int result = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(PLANS_NUMBER_QUERY)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result = resultSet.getInt("plans");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<PlanDetails> recentPlan(int adminId) {
        List<PlanDetails> result = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(RECENT_PLAN_QUERY)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PlanDetails planDetails = new PlanDetails();

                    planDetails.setDayName(resultSet.getString("day_name"));
                    planDetails.setDayName(resultSet.getString("meal_name"));
                    planDetails.setDayName(resultSet.getString("recipe_name"));
                    planDetails.setDayName(resultSet.getString("recipe_description"));

                    result.add(planDetails);
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

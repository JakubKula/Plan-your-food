package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.MealInPlan;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealInPlanDao {
    private static final String FIND_ALL_MEALS_IN_PLAN_QUERY = "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description, recipe.id as recipe_id, recipe_plan.id as id\n" +
            "FROM `recipe_plan`\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE plan_id = ?\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String DELETE_MEAL_FROM_PLAN_QUERY = "DELETE FROM recipe_plan WHERE id = ?;";

    public List<MealInPlan> findAll(int planId) {
        List<MealInPlan> mealInPlanList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_MEALS_IN_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    MealInPlan mealInPlanToAdd = new MealInPlan();
                    mealInPlanToAdd.setDayName(resultSet.getString("day_name"));
                    mealInPlanToAdd.setMealName(resultSet.getString("meal_name"));
                    mealInPlanToAdd.setRecipeName(resultSet.getString("recipe_name"));
                    mealInPlanToAdd.setRecipeDescription(resultSet.getString("recipe_description"));
                    mealInPlanToAdd.setRecipeId(resultSet.getInt("recipe_id"));
                    mealInPlanToAdd.setId(resultSet.getInt("id"));
                    mealInPlanList.add(mealInPlanToAdd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mealInPlanList;

    }

    public void delete(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MEAL_FROM_PLAN_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

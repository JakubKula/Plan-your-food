package pl.coderslab.model;

public class PlanDetails {
    private String dayName;
    private String mealName;
    private String recipeName;

    public PlanDetails() {
    }

    public PlanDetails(String dayName, String mealName, String recipeName) {
        this.dayName = dayName;
        this.mealName = mealName;
        this.recipeName = recipeName;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }



    @Override
    public String toString() {
        return "PlanDetails{" +
                "dayName='" + dayName + '\'' +
                ", mealName='" + mealName + '\'' +
                ", recipeName='" + recipeName + '\'' +
                '}';
    }
}

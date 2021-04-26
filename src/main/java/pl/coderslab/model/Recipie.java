package pl.coderslab.model;

public class Recipie {

    public int getId() {
        return id;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public String getPreparation() {
        return preparation;
    }

    private int id;
    private int adminId;
    private String name;
    private String ingredients;
    private String description;
    private String created;
    private String updated;
    private int preparationTime;
    private String preparation;

    public Recipie(String name, String ingredients, String description, String created, String updated, int preparationTime, String preparation, int adminId){

        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.preparationTime = preparationTime;
        this.preparation = preparation;
        this.adminId = adminId;
    }

    public  Recipie(){};

    public void setId(int id) {
        this.id = id;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    @Override
    public String toString() {
        return "Recipie:" +
                "id=" + id +
                "name='" + name + '\'' +
                "ingredients='" + ingredients + '\'' +
                "description='" + description + '\'' +
                "created='" + created + '\'' +
                "updated='" + updated + '\'' +
                "preparationTime=" + preparationTime +
                "preparation='" + preparation + '\'' +
                "adminId=" + adminId +"\n"
                ;
    }
}

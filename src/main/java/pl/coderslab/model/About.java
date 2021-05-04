package pl.coderslab.model;

public class About {
    private String topic;
    private String description;

    public About(String topic, String description) {
        this.topic = topic;
        this.description = description;
    }

    public About() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
package pl.coderslab.model;

public class Contact {
    private String topic1;
    private String description1;
    private String topic2;
    private String description2list;
    private String topic3;

    public String getTopic1() {
        return topic1;
    }

    public String getDescription1() {
        return description1;
    }

    public String getTopic2() {
        return topic2;
    }

    public String getDescription2list() {
        return description2list;
    }

    public String getTopic3() {
        return topic3;
    }

    public Contact(String topic1, String description1, String topic2, String description2list, String topic3) {
        this.topic1 = topic1;
        this.description1 = description1;
        this.topic2 = topic2;
        this.description2list = description2list;
        this.topic3 = topic3;
    }

    public void setTopic1(String topic1) {
        this.topic1 = topic1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public void setTopic2(String topic2) {
        this.topic2 = topic2;
    }

    public void setDescription2list(String description2list) {
        this.description2list = description2list;
    }

    public void setTopic3(String topic3) {
        this.topic3 = topic3;
    }

    public Contact(){

    }
}

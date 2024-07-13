package org.example.entity;

//@Table(name = "series_episodes")
public class Episode {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int season;
    private int order_number;
    private String title;

    public Episode(int id, int season, int order_number, String title) {
        this.id = id;
        this.season = season;
        this.order_number = order_number;
        this.title = title;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

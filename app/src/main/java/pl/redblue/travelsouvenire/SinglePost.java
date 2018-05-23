package pl.redblue.travelsouvenire;

public class SinglePost {
    private int id;
    private String place;
    private String description;
    private String photo;

    public SinglePost(String place, String description, String photo){
        this.place=place;
        this.description=description;
        this.photo=photo;
    }

    public int getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

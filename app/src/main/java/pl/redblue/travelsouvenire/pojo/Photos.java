package pl.redblue.travelsouvenire.pojo;

import com.google.gson.annotations.SerializedName;

public class Photos {
    @SerializedName("photoId")
    private Integer photoId;
    @SerializedName("photoName")
    private String photoName;

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}

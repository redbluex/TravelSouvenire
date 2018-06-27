package pl.redblue.travelsouvenire.pojo;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class SinglePlace {
    @SerializedName("idsp")
    private Integer idsp;
    @SerializedName("city")
    private String city;
    @SerializedName("country")
    private String country;
    @SerializedName("description")
    private String description;
    @SerializedName("linkPhoto")
    private String linkPhoto;

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String descriptionPlace) {
        this.description = descriptionPlace;
    }

    public Integer getIdsp() {
        return idsp;
    }
}

package pl.redblue.travelsouvenire.pojo;


import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class UserPersonal {

    @SerializedName("id")
    private Integer id;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("password")
    private String password;
    @SerializedName("description")
    private String description;

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

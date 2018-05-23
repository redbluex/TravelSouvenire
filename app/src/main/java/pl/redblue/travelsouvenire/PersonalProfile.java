package pl.redblue.travelsouvenire;

public class PersonalProfile {
    private int id;
    private String nickName;
    private String descriptionProfile;
    private String avatarPhotoPath;

    public int getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public String getDescriptionProfile() {
        return descriptionProfile;
    }

    public String getAvatarPhotoPath() {
        return avatarPhotoPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setDescriptionProfile(String descriptionProfile) {
        this.descriptionProfile = descriptionProfile;
    }

    public void setAvatarPhotoPath(String avatarPhotoPath) {
        this.avatarPhotoPath = avatarPhotoPath;
    }
}

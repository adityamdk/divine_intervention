package amogh.org.meettheteam.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Amogh on 1/29/2018.
 */

public class Person implements Parcelable {

    private String id;
    private String avatar;
    private String bio;
    private String firstName;
    private String lastName;
    private String title;

    public Person() {

    }

    private Person(Parcel in) {
        id = in.readString();
        avatar = in.readString();
        bio = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(avatar);
        dest.writeString(bio);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

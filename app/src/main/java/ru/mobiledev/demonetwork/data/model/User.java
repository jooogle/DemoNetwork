package ru.mobiledev.demonetwork.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
	private long id;
	private String name;
	private String url;
	private String email;
	private String login;
	private String location;
	@SerializedName("avatar_url")
	private String avatarUrl;

	public User() {

	}

	protected User(Parcel in) {
		id = in.readLong();
		name = in.readString();
		url = in.readString();
		email = in.readString();
		login = in.readString();
		location = in.readString();
		avatarUrl = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(name);
		dest.writeString(url);
		dest.writeString(email);
		dest.writeString(login);
		dest.writeString(location);
		dest.writeString(avatarUrl);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (name != null ? !name.equals(user.name) : user.name != null) return false;
		if (url != null ? !url.equals(user.url) : user.url != null) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		if (login != null ? !login.equals(user.login) : user.login != null) return false;
		if (location != null ? !location.equals(user.location) : user.location != null)
			return false;
		return !(avatarUrl != null ? !avatarUrl.equals(user.avatarUrl) : user.avatarUrl != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (url != null ? url.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (location != null ? location.hashCode() : 0);
		result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
		return result;
	}

	public boolean hasEmail() {
		return email != null && !email.isEmpty();
	}

	public boolean hasLocation() {
		return location != null && !location.isEmpty();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}

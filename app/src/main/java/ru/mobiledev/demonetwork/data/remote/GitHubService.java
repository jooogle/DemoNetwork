package ru.mobiledev.demonetwork.data.remote;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import ru.mobiledev.demonetwork.data.model.Repository;
import ru.mobiledev.demonetwork.data.model.User;
import rx.Observable;

public interface GitHubService {
	@GET("users/{username}/repos")
	Observable<List<Repository>> publicRepositories(@Path("username") String username);

	@GET
	Observable<User> userFromUrl(@Url String userUrl);
}

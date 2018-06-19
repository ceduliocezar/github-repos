package com.ceduliocezar.repos.model.datasource;

import com.ceduliocezar.repos.model.entities.GitHubRepo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GitHubAPI {

    private static final String BASE_URL = "https://api.github.com";
    private static final String REPOS = BASE_URL + "/users/%s/repos";

    private JsonReader jsonReader;

    public GitHubAPI(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }


    public List<GitHubRepo> repos(String user) throws Exception {
        String responseJson = jsonReader.read(new URL(String.format(REPOS, user)));
        JSONArray reposArray = new JSONArray(responseJson);
        List<GitHubRepo> repositories = new ArrayList<>();

        for (int i = 0; i < reposArray.length(); i++) {
            JSONObject jsonRepository = reposArray.getJSONObject(i);

            String id = String.valueOf(jsonRepository.getInt("id"));
            String fullName = jsonRepository.getString("full_name");
            repositories.add(new GitHubRepo(id, fullName));
        }

        return repositories;
    }

}

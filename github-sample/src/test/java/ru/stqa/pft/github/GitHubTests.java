package ru.stqa.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by luk on 2017-05-25.
 */
public class GitHubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub(".. your OAuth token ..");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("Lukasz-Jab", "java_pft")).commits();
        for(RepoCommit commit : commits.iterate(new ImmutableBiMap.Builder<String, String>().build())) {
           System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}

package github;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pojos.GithubUser;

public class GithubRepoTests {

  private final CloseableHttpClient httpClient = HttpClients.createDefault();

  @AfterTest
  private void close() throws IOException {
    httpClient.close();
  }

  @Test
  public void createRepository() throws IOException, AuthenticationException {
    HttpPost request = new HttpPost(Constants.BASE_URL + "/user/repos");
    UsernamePasswordCredentials creds
        = new UsernamePasswordCredentials(Constants.GITHUB_LOGIN, Constants.GITHUB_PWD);
    request.addHeader(new BasicScheme().authenticate(creds, request, null));

    String json = "{\n"
        + "\t\"name\": \"test\",\n"
        + "\t\"description\": \"repo\"\n"
        + "}";

    StringEntity entity = new StringEntity(json);
    request.setEntity(entity);
    request.setHeader("Accept", "application/json");
    request.setHeader("Content-type", "application/json");

    Assert.assertEquals(httpClient.execute(request).getStatusLine().getStatusCode(), 201);
  }

  @Test
  public void deleteRepository() throws IOException, AuthenticationException {
    HttpDelete request = new HttpDelete(Constants.BASE_URL + "/repos/msirghi/test");
    UsernamePasswordCredentials creds
        = new UsernamePasswordCredentials(Constants.GITHUB_LOGIN, Constants.GITHUB_PWD);
    request.addHeader(new BasicScheme().authenticate(creds, request, null));

    request.setHeader("Accept", "application/json");
    request.setHeader("Content-type", "application/json");

    Assert.assertEquals(httpClient.execute(request).getStatusLine().getStatusCode(), 204);
  }

  @Test
  public void getUserInfo() throws IOException, AuthenticationException {
    ObjectMapper objectMapper = new ObjectMapper();
    HttpGet request = new HttpGet(Constants.BASE_URL + "/users/msirghi");
    String responseBody = EntityUtils.toString(httpClient.execute(request).getEntity());

    request.setHeader("Accept", "application/json");
    request.setHeader("Content-type", "application/json");

    GithubUser user = objectMapper.readValue(responseBody, GithubUser.class);

    Assert.assertEquals(httpClient.execute(request).getStatusLine().getStatusCode(), 200);
    Assert.assertEquals(user.getId(), java.util.Optional.of(12345));
    Assert.assertEquals(user.getLogin(), "msirghi");
    Assert.assertEquals(user.getUrl(), "https://api.github.com/users/msirghi");
  }

}

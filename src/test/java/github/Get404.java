package github;

import java.io.IOException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Get404 {

  private final CloseableHttpClient httpClient = HttpClients.createDefault();

  @AfterTest
  private void close() throws IOException {
    httpClient.close();
  }

  @Test
  public void getNotFoundResponse() throws IOException {
    HttpGet request = new HttpGet(Constants.BASE_URL + "/nonExistingName");
    StatusLine sl = httpClient.execute(request).getStatusLine();
    Assert.assertEquals(sl.getStatusCode(), 404);
  }
}

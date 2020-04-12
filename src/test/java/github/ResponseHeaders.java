package github;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class ResponseHeaders {

  private final CloseableHttpClient httpClient = HttpClients.createDefault();

  @AfterTest
  private void close() throws IOException {
    httpClient.close();
  }

  @Test
  public void getResponseHeaderValue() throws IOException {
    HttpGet request = new HttpGet(Constants.BASE_URL + "/users/billgates");
    HttpResponse response = httpClient.execute(request);
    Assert.assertEquals(response.getFirstHeader("vary").getValue(),
        "Accept, Accept-Encoding, Accept, X-Requested-With");
  }

  @Test
  public void getResponseHeader() throws IOException {
    HttpGet request = new HttpGet(Constants.BASE_URL + "/users/billgates");
    HttpResponse response = httpClient.execute(request);
    Assert.assertEquals(response.getFirstHeader("vary").getName().contains("vary"), true);
  }
}

package test.rest.mvc.curl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestLogin {

	@Test
	public void test() {

		try {
			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/testC");

			String input = "";
			System.out.println(input);

			// -H "X-Username: admin" -H "X-Password: admin"
			webResource.setProperty("X-Username", "admin");
			webResource.setProperty("X-Password", "admin");
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			
			System.out.println(response.getCookies()+"\n");
			System.out.println(response.getStatus()+"\n");
			System.out.println(response.getHeaders()+"\n");
			
		} catch (Exception e) {
			System.out.println("test failed !");
			e.printStackTrace();
		}
	}

}

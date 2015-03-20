import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.format.annotation.DateTimeFormat;

import com.annotations.Phone;
import com.enumurations.Gender;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class FirstTest {

	public static void main(String[] args) {

			{

			System.out.println("\n 2- Post cretae person test");
			try {

				Client client = Client.create();

				WebResource webResource = client
						.resource("http://localhost:8080/ColisDrive/person/save");

				String input = "{"
						+ "\"name\":\"Metallica\","
						+ "\"firstname\":\"Fade To Black\","
						+ "\"adress\":\"testadress home hihi\","
						+ "\"email\":\"me@me.fr\","
						+ "\"birthday\":\"11-02-1990\","
						+ "\"gender\":1,"
						+ "\"country\":\"algeria\""
						+ "}";
				//	+ "\"phone\":\"0712345678\""

				System.out.println(input);
				ClientResponse response = webResource.type("application/json")
						.post(ClientResponse.class, input);

				if (response.getStatus() != 201) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());
				}

				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				System.out.println(output);

			} catch (Exception e) {
				System.out.println("test failed !");
				e.printStackTrace();
			}

		}
			{
				System.out.println("1- Get list persons test");
				try {

					Client client = Client.create();
					WebResource webResource = client
							.resource("http://localhost:8080/ColisDrive/person/listPersons");

					ClientResponse response = webResource.accept("application/json")
							.get(ClientResponse.class);

					if (response.getStatus() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
								+ response.getStatus());
					}

					String output = response.getEntity(String.class);

					System.out.println("Output from Server .... \n");
					System.out.println(output);
					System.out.println("test ok");

				} catch (Exception e) {
					System.out.println("test failed !");
					e.printStackTrace();
				}		
			}

		{
			System.out.println("\n 3- Get person with id test");
			try {

				Client client = Client.create();
				WebResource webResource = client
						.resource("http://localhost:8080/ColisDrive/person/get/1");

				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);

				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());
				}

				String output = response.getEntity(String.class);

				System.out.println("Output from Server .... \n");
				System.out.println(output);
				System.out.println("test ok");

			} catch (Exception e) {
				System.out.println("test failed !");
				e.printStackTrace();
			}		

		}

		{
			System.out.println("4- date time form");
			System.out.println("Date --> "+new Date());
		}
	}

}
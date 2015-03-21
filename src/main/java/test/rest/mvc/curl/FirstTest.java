package test.rest.mvc.curl;
import java.util.Date;
import java.util.Random;

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
import com.utils.GenerateString;

public class FirstTest {

	public static void main(String[] args) {

			{

			System.out.println("\n 2- Post cretae person test");
			try {

				String emailpre = GenerateString.generateRandomString(10, GenerateString.Mode.ALPHA); 
				String domain = GenerateString.generateRandomString(5, GenerateString.Mode.ALPHA);
				String emailpost = GenerateString.generateRandomString(2, GenerateString.Mode.ALPHA); 
				
				String pseudoNameFirstNmae = GenerateString.generateRandomString(10, GenerateString.Mode.ALPHA);
				String country = GenerateString.generateRandomString(5, GenerateString.Mode.ALPHA);
				
				Client client = Client.create();

				WebResource webResource = client
						.resource("http://localhost:8080/testC/person/save");

				String input = "{\"firstname\":\""+pseudoNameFirstNmae+"\",\"name\":\""+pseudoNameFirstNmae+"\",\"country\":\""+country+"\",\"adress\":\"miami 34090 sefaze\",\"email\":\""+emailpre+"@"+domain+"."+emailpost+"\",\"birthday\":\"2012-06-06\",\"gender\":0,\"pseudo\":\""+pseudoNameFirstNmae+"\",\"phone\":\"0123456789\"}";
				System.out.println(input);

				ClientResponse response = webResource.type("application/json")
						.post(ClientResponse.class, input);

				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());
				}

				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				System.out.println(output);
				System.out.println("\n test ok \n");

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
							.resource("http://localhost:8080/testC/person/listPersons");

					ClientResponse response = webResource.accept("application/json")
							.get(ClientResponse.class);

					if (response.getStatus() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
								+ response.getStatus());
					}

					String output = response.getEntity(String.class);

					System.out.println("Output from Server .... \n");
					System.out.println(output);
					System.out.println("\n test ok \n");

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
						.resource("http://localhost:8080/testC/person/get/1");

				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);

				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());
				}

				String output = response.getEntity(String.class);

				System.out.println("Output from Server .... \n");
				System.out.println(output);
				System.out.println("\n test ok \n");

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
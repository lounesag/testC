package test.rest.mvc.curl;
import java.util.Date;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.utils.GenerateString;

public class FirstTest {

	public static void main(String[] args) throws Exception {

		String emailpre =  GenerateString.generateRandomString(10, GenerateString.Mode.ALPHA);
		String domain = GenerateString.generateRandomString(5, GenerateString.Mode.ALPHA);
		String emailpost = GenerateString.generateRandomString(2, GenerateString.Mode.ALPHA); 

		String pseudoNameFirstNmae = GenerateString.generateRandomString(10, GenerateString.Mode.ALPHA);
		String country = GenerateString.generateRandomString(5, GenerateString.Mode.ALPHA);

		{

			System.out.println("\n 2- Post create person test");
			try {

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
			System.out.println("\n 4- Get person with name and first name test");
			try {

				Client client = Client.create();
				WebResource webResource = client
						.resource("http://localhost:8080/testC/person/get/"+pseudoNameFirstNmae+"/"+pseudoNameFirstNmae);

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
			System.out.println("5- date time form");
			System.out.println("Date --> "+new Date());
		}
		{

			System.out.println("\n 6- Put update person test");
			//TODO update test curl  !!
//			try {
//
//				Client client = Client.create();
//
//				WebResource webResource = client
//						.resource("http://localhost:8080/testC/person/save");
//
//				String input = "{\"firstname\":\""+pseudoNameFirstNmae+"\",\"name\":\""+pseudoNameFirstNmae+"\",\"country\":\""+country+"\",\"adress\":\"miami 34090 sefaze\",\"email\":\""+emailpre+"@"+domain+"."+emailpost+"\",\"birthday\":\"2012-06-06\",\"gender\":0,\"pseudo\":\""+pseudoNameFirstNmae+"\",\"phone\":\"0123456789\"}";
//				System.out.println(input);
//
//				ClientResponse response = webResource.type("application/json")
//						.post(ClientResponse.class, input);
//
//				if (response.getStatus() != 200) {
//					throw new RuntimeException("Failed : HTTP error code : "
//							+ response.getStatus());
//				}
//
//				System.out.println("Output from Server .... \n");
//				String output = response.getEntity(String.class);
//				System.out.println(output);
//				System.out.println("\n test ok \n");
//
//			} catch (Exception e) {
//				System.out.println("test failed !");
//				e.printStackTrace();
//			}
//
		}


	}

}
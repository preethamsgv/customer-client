package com.example.customer_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CustomerClientApplication {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(CustomerClientApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:8000/api/customers";
		ObjectMapper mapper = new ObjectMapper();

		// Get All Customers
		ResponseEntity<String> getAllResponse = restTemplate.getForEntity(baseUrl, String.class);
		System.out.println("Get All Customers Response: " + getAllResponse.getBody());

		// POST a new customer (Wasp Dolittle Antman)
		Map<String, String> requestWasp = new HashMap<>();
		requestWasp.put("firstName", "Wasp");
		requestWasp.put("middleName", "Dolittle");
		requestWasp.put("lastName", "Antman");
		requestWasp.put("emailAddress", "ant@example.com");
		requestWasp.put("phoneNumber", "321-406-7890");
		ResponseEntity<String> postResponseWasp = restTemplate.postForEntity(baseUrl, requestWasp, String.class);
		Customer cust = mapper.readValue(postResponseWasp.getBody(), Customer.class);
		System.out.println("Post Customer (Wasp) Response: " + postResponseWasp.getBody());

		// POST a new customer (Banner Hulk)
		Map<String, String> requestHulk = new HashMap<>();
		requestHulk.put("firstName", "Banner");
		requestHulk.put("middleName", "Earth");
		requestHulk.put("lastName", "Hulk");
		requestHulk.put("emailAddress", "hulk@example.com");
		requestHulk.put("phoneNumber", "321-456-7820");
		ResponseEntity<String> postResponseHulk = restTemplate.postForEntity(baseUrl, requestHulk, String.class);
		System.out.println("Post Customer (Hulk) Response: " + postResponseHulk.getBody());

		// Get a customer by ID
		String getByIdUrl = baseUrl + "/" + cust.getId().toString();
		ResponseEntity<String> getByIdResponse = restTemplate.getForEntity(getByIdUrl, String.class);
		System.out.println("Get Customer by ID Response: " + getByIdResponse.getBody());

		// PUT (update) a customer
		String updateUrl = baseUrl +  "/" + cust.getId().toString();
		Map<String, String> updateRequest = new HashMap<>();
		updateRequest.put("firstName", "Antman");
		updateRequest.put("middleName", "Research");
		updateRequest.put("lastName", "Wasp");
		updateRequest.put("emailAddress", "antwasp@example.com");
		updateRequest.put("phoneNumber", "123-456-7890");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(updateRequest, headers);
		ResponseEntity<String> putResponse = restTemplate.exchange(updateUrl, HttpMethod.PUT, entity, String.class);
		System.out.println("Put Customer Response: " + putResponse.getBody());

		// DELETE a customer
		String deleteUrl = baseUrl +  "/" + cust.getId().toString();
		restTemplate.delete(deleteUrl);
		System.out.println("Delete Customer Response: Customer deleted");
	}

}





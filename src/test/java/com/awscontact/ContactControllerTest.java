package com.awscontact;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.awscontact.model.Contact;
import com.awscontact.model.Status;

public class ContactControllerTest {

	static final String BASE_URL = "http://localhost:8090/contacts";
	RestTemplate restTemplate = new RestTemplate();

	
	/*@Test
	public void tesGetContacts(){

		Contact [] contacts = restTemplate.getForObject(BASE_URL, Contact[].class);
		List<Contact> contactList = new ArrayList<Contact>();
		for(Contact contatc: contacts) {
			contactList.add(contatc);
		}
		assertEquals(6, contactList.size());
		
	}*/
	
	@Test
	public void testCreateContact(){

		Contact contact = new Contact();
		contact.setEmail("this is john");
		ResponseEntity<Status> postForEntity = restTemplate.postForEntity("http://localhost:8090/contacts/create", contact, Status.class);
		
		Status status = postForEntity.getBody();
		
		assertEquals("Contact added Successfully !", status.getMessage());
		
	}

}


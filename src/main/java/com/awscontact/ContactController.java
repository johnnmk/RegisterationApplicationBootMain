package com.awscontact;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awscontact.model.Contact;
import com.awscontact.model.Status;
import com.awscontact.repository.ContactRepository;

/**
 * A class to test interactions with the PostgreSQL database using the ContactDao class.
 *
 * @author john
 */
@Controller
@RequestMapping("/contacts")
public class ContactController {

	@Inject
	ContactRepository contactRepository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Contact> readContacts() {

		List<Contact> contactList = new ArrayList<Contact>();
		Iterable<Contact> findAll = contactRepository.findAll();

		for (Contact contact : findAll) {
			contactList.add(contact);
		}
		return contactList;
	}

	/**
	 * /create --> Create a new contact and save it in the database.
	 * 
	 * @param email
	 *            contact's email
	 * @param name
	 *            contact's name
	 * @return A string describing if the user is successfully created or not.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody Status createContact(@RequestBody Contact contact) {

		try {

			Contact save = contactRepository.save(contact);

			System.out.println("new save " + save);
			return new Status(1, "Contact added Successfully !");

		} catch (Exception exception) {
			return new Status(0, exception.toString());
		}
	}

	/**
	 * /delete --> Delete the contact having the passed id.
	 * 
	 * @param id
	 *            The id of the user to delete
	 * @return A string describing if the user is successfully deleted or not.
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteContact(@PathVariable("id") long id) {

		try {
			return new Status(1, "Contact deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}

	 /**
	   * /update  --> Update the email and the name for the user in the database 
	   * having the passed id.
	   * 
	   * @param id The id for the contact to update.
	   * @return A string describing if the user is successfully updated or not.
	   */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status editContact(Contact contact, @PathVariable long id) {

		try {

			contact.setName(contact.getName());
			contact.setName(contact.getEmail());
			contact.setEmail(contact.getProfession());

			System.out.println("new contact " + contact.getEmail());
		} catch (Exception ex) {
			return new Status(2, "Error updating the user: " + ex.toString());
		}
		return new Status(3, "User succesfully updated!");

	}

}

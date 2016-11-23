package com.awscontact.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.awscontact.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact,Long>{
}

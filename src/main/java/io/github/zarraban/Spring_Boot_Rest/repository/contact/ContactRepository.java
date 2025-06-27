package io.github.zarraban.Spring_Boot_Rest.repository.contact;

import io.github.zarraban.Spring_Boot_Rest.entity.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> readById(Long id);
}

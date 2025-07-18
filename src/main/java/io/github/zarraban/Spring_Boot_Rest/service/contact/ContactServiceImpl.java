package io.github.zarraban.Spring_Boot_Rest.service.contact;

import io.github.zarraban.Spring_Boot_Rest.dto.ContactRequestDTO;
import io.github.zarraban.Spring_Boot_Rest.entity.contact.Contact;
import io.github.zarraban.Spring_Boot_Rest.entity.contact.ContactMapper;
import io.github.zarraban.Spring_Boot_Rest.repository.contact.ContactRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;



@Service("contactService")
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ContactMapper mapper;

    public ContactServiceImpl(
            @Qualifier("contactRepository") ContactRepository repository,
            @Qualifier("contactMapper") ContactMapper mapper
    ){
        this.repository=repository;
        this.mapper = mapper;
    }



    @Override
    @Transactional
    public Contact create(ContactRequestDTO request) {
        Objects.requireNonNull(request, "Param [request] must not be null");
        Contact contact = mapper.requestToContact(request);
        return repository.save(contact);
    }

    @Override
    @Transactional(readOnly = true)
    public Contact readById(Long id) {
        Objects.requireNonNull(id,
                "Param [id] must not be null");
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id,
                "Param [id] must not be null");

        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Contact updateById(Long id, ContactRequestDTO request) {
        Objects.requireNonNull(id,
                "Param [request] must not be null");
        Objects.requireNonNull(request,
                "Param [request] must not be null");

        Contact contactToUpdate = mapper.requestToContact(request);
        contactToUpdate.setId(id);

        if(repository.findById(id).isPresent()){
            repository.save(contactToUpdate);
            return contactToUpdate;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> readAll() {
        return repository.findAll();
    }
}

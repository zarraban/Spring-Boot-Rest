package io.github.zarraban.Spring_Boot_Rest.controller.contact;


import io.github.zarraban.Spring_Boot_Rest.dto.ContactRequestDTO;
import io.github.zarraban.Spring_Boot_Rest.dto.error.AppError;
import io.github.zarraban.Spring_Boot_Rest.entity.contact.Contact;
import io.github.zarraban.Spring_Boot_Rest.service.contact.ContactService;
import io.github.zarraban.Spring_Boot_Rest.utiils.MessageMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ContactController {

    private final ContactService service;
    private final MessageMapper messageMapper;

    public ContactController(
        @Qualifier("contactService") ContactService service,
        MessageMapper mapper
    ){
        this.service = service;
        this.messageMapper = mapper;
    }

    @GetMapping("/contacts")
    public ResponseEntity<?> fetchAllContacts() {
        try {
            List<Contact> list = service.readAll();
            if (list.isEmpty()) {
                return new ResponseEntity<>(messageMapper.messageToJson("List is empty by now!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new AppError("Couldn't get all users! Contact developer for information", HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/contacts")
    public ResponseEntity<?> createContact(@RequestBody ContactRequestDTO request){
            try {

                Contact createdContact = service.create(request);

                return new ResponseEntity<>(createdContact, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(
                        new AppError("Couldn't create new contact", HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        HttpStatus.INTERNAL_SERVER_ERROR
                        );
            }
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<?> getContactById(@PathVariable("id")Long id){
        try{
            Contact contactFound = service.readById(id);

            if(contactFound==null){
                return new ResponseEntity<>(new MessageMapper().messageToJson("No contact was found with id " +id), HttpStatus.OK);
            }
            return new ResponseEntity<>(contactFound, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(
                    new AppError("Couldn't get contact with id "+ id, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @PutMapping("/contacts/{id}")
    public ResponseEntity<?> updateContactById(@PathVariable("id") Long id,@RequestBody ContactRequestDTO request) {
        try {
            Contact contact = service.updateById(id, request);
            if(contact==null){
                return new ResponseEntity<>(new MessageMapper().messageToJson("No contact was found with id " +id), HttpStatus.OK);
            }
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new AppError("Couldn't update contact with id " + id, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<?> deleteContactById(@PathVariable("id") Long id){
        try {
            if(service.deleteById(id)){
                return new ResponseEntity<>(
                        messageMapper.messageToJson(String.format("Contact with id %d was deleted",id)),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(String.format("Contact with id %d was not deleted",id),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new AppError("Couldn't delete contact with id "+ id, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}


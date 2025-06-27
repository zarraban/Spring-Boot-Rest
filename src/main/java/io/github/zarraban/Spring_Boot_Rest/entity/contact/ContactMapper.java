package io.github.zarraban.Spring_Boot_Rest.entity.contact;


import io.github.zarraban.Spring_Boot_Rest.dto.ContactRequestDTO;
import org.springframework.stereotype.Component;

@Component("contactMapper")
public class ContactMapper {

    public Contact requestToContact(ContactRequestDTO requestDTO){
        Contact contact = new Contact();
        if(requestDTO.id() != null){
            contact.setId(requestDTO.id());
        }
        if(!requestDTO.first_name().isEmpty()){
            if(!requestDTO.first_name().isBlank()){
                contact.setFirstName(requestDTO.first_name());
            }
        }
        if(!requestDTO.last_name().isEmpty()){
            if(!requestDTO.last_name().isBlank()){
                contact.setLastName(requestDTO.last_name());
            }
        }


        return contact;

    }


}

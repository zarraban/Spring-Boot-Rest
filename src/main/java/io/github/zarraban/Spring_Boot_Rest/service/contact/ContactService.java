package io.github.zarraban.Spring_Boot_Rest.service.contact;

import io.github.zarraban.Spring_Boot_Rest.dto.ContactRequestDTO;
import io.github.zarraban.Spring_Boot_Rest.entity.contact.Contact;
import io.github.zarraban.Spring_Boot_Rest.service.BaseService;

public interface ContactService extends BaseService<Contact, ContactRequestDTO> {
}

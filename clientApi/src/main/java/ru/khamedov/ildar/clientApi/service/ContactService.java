package ru.khamedov.ildar.clientApi.service;

import org.springframework.stereotype.Service;
import ru.khamedov.ildar.clientApi.model.Contact;
import ru.khamedov.ildar.clientApi.model.ContactType;
import ru.khamedov.ildar.clientApi.model.EmailContact;
import ru.khamedov.ildar.clientApi.model.PhoneContact;

@Service
public class ContactService {

    public Contact createContact(ContactType contactType,String information){
        Contact contact=null;
        if(contactType==ContactType.PHONE){
            contact=new PhoneContact();
        }
        if(contactType==ContactType.EMAIL){
            contact=new EmailContact();
        }
        contact.setInformation(information);
        return contact;
    }
}

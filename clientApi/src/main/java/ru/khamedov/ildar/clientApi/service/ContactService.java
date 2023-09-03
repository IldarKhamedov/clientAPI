package ru.khamedov.ildar.clientApi.service;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.khamedov.ildar.clientApi.dto.ContactDTO;
import ru.khamedov.ildar.clientApi.model.Contact;
import ru.khamedov.ildar.clientApi.model.ContactType;
import ru.khamedov.ildar.clientApi.model.EmailContact;
import ru.khamedov.ildar.clientApi.model.PhoneContact;
import ru.khamedov.ildar.clientApi.repository.ContactRepository;
import ru.khamedov.ildar.clientApi.util.Constant;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Resource
    private ContactRepository contactRepository;

    @Resource
    private ModelMapperService modelMapperService;

    @Resource
    private AuthService authService;

    private static final String TYPE=null;

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

    public List<ContactDTO> getContactList(){
        return getContactDTOList(TYPE);
    }

    public List<ContactDTO> getContactList(String type){
        return getContactDTOList(type);
    }

    private List<ContactDTO> getContactDTOList(String type){
        List<Contact> contactList= contactRepository.findByName(authService.getUser().getName(),
                parseType(type));
        return contactList.stream().map(c->modelMapperService.convertToContactDTO(c)).collect(Collectors.toList());
    }

    private Class parseType(String type){
        if(Constant.PHONE_CONTACT.equals(type)){
            return PhoneContact.class;
        }
        if (Constant.EMAIL_CONTACT.equals(type)) {
           return EmailContact.class;
        }
        if(type != null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,type + "-" + Constant.CONTACT_ERROR);
        }
        return null;
    }
}

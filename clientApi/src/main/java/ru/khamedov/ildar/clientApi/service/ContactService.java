package ru.khamedov.ildar.clientApi.service;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.khamedov.ildar.clientApi.dto.ContactDTO;
import ru.khamedov.ildar.clientApi.model.Contact;
import ru.khamedov.ildar.clientApi.model.ContactType;
import ru.khamedov.ildar.clientApi.repository.ContactRepository;
import ru.khamedov.ildar.clientApi.util.Constant;

import java.util.Arrays;
import java.util.Collections;
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

    private static final String DEFAULT_TYPE = null;


    public Contact createContact(ContactType contactType, String information) {
        Contact contact = contactType.getContact();
        contact.setInformation(information);
        return contact;
    }

    public List<ContactDTO> getContactList() {
        return getContactDTOList(DEFAULT_TYPE);
    }

    public List<ContactDTO> getContactList(String type) {
        return getContactDTOList(type);
    }

    private List<ContactDTO> getContactDTOList(String type) {
        List<Contact> contactList = contactRepository.findByName(authService.getUser().getName(),
                parseType(type));
        if (contactList.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return contactList.stream().map(c -> modelMapperService.convertToContactDTO(c)).collect(Collectors.toList());
    }

    private Class parseType(String type) {
        if (type == null) {
            return null;
        }
        if (!Constant.CONTACT_MAP.containsKey(type)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, type + "-" + Constant.CONTACT_ERROR);
        }
        return Constant.CONTACT_MAP.get(type);
    }

}

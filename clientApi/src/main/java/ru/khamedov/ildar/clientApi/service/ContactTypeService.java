package ru.khamedov.ildar.clientApi.service;

import org.springframework.stereotype.Service;
import ru.khamedov.ildar.clientApi.model.Contact;
import ru.khamedov.ildar.clientApi.model.ContactType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactTypeService {

    private static final int INDEX_FILTERED_CONTACT_TYPE = 0;

    public ContactType getByContact(Contact contact) {
        List<ContactType> contactTypeList = Arrays.stream(ContactType.values()).filter(c -> c.getContact().getClass() == contact.getClass()).collect(Collectors.toList());
        if (contactTypeList.isEmpty()) {
            return ContactType.CONTACT;
        }
        return contactTypeList.get(INDEX_FILTERED_CONTACT_TYPE);
    }
}

package ru.khamedov.ildar.clientApi.service;

import jakarta.annotation.Resource;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.khamedov.ildar.clientApi.dto.ContactDTO;
import ru.khamedov.ildar.clientApi.model.Contact;
import ru.khamedov.ildar.clientApi.model.ContactType;
import ru.khamedov.ildar.clientApi.repository.ContactRepository;
import ru.khamedov.ildar.clientApi.util.Constant;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
/**
 * Сервис для работы с контактами.
 */
public class ContactService {

    @Resource
    private ContactRepository contactRepository;

    @Resource
    private ModelMapperService modelMapperService;

    @Resource
    private AuthService authService;

    /**
     * Если в запросе не передан тип контакта, то type==null,
     * и получем все контакты, независимо от типа.
     */
    private static final String DEFAULT_TYPE = null;


    /**
     * Создать новый контакт.
     * @param contactType
     * @param information
     */
    public Contact createContact(ContactType contactType, String information) {
        validateContact(contactType,information);
        Contact contact = contactType.getContact();
        contact.setInformation(information);
        return contact;
    }

    /**
     * Получение всех контактов пользователя.
     */
    public List<ContactDTO> getContactList() {
        return getContactDTOList(DEFAULT_TYPE);
    }

    /**
     * Получение контактов пользователя по типу.
     * @param type
     */
    public List<ContactDTO> getContactList(String type) {
        return getContactDTOList(type);
    }

    /**
     * Получение контактов из базы.
     * @param type
     */
    private List<ContactDTO> getContactDTOList(String type) {
        List<Contact> contactList = contactRepository.findByName(authService.getUser().getName(),
                parseType(type));
        if (contactList.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return contactList.stream().map(c -> modelMapperService.convertToContactDTO(c)).collect(Collectors.toList());
    }

    /**
     * Получаем тип контакта для запроса к базе данных.
     * @param type
     */
    private Class parseType(String type) {
        if (type == null) {
            return null;
        }
        if (!Constant.CONTACT_MAP.containsKey(type)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, type + "-" + Constant.CONTACT_ERROR);
        }
        return Constant.CONTACT_MAP.get(type);
    }

    /**
     * Проверяем контакт.
     * @param contactType
     * @param information
     */
    private void validateContact(ContactType contactType, String information) {
        boolean check=true;
        if (contactType == ContactType.EMAIL) {
            check= EmailValidator.getInstance().isValid(information);
        }
        if(contactType==ContactType.PHONE){
            String digits = information.replaceAll("[^0-9]", "");
            check= !digits.isEmpty();
        }
        if(!check){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,contactType+":"+information + "-" + Constant.CONTACT_INFORMATION_ERROR);
        }
    }

}

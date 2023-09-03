package ru.khamedov.ildar.clientApi.util;

import ru.khamedov.ildar.clientApi.model.*;

import java.util.*;

public class Constant {

    public static final String AUTHORITY_ROLE="ROLE_USER";

    public static final String CLIENT="client";

    public static final String USER_TYPE_ERROR="Данный тип пользователя недоступен";

    public static final String USER_NAME_ERROR="Данное имя уже занято";

    public static final String USER_ID_ERROR="Данный пользователь не существует";

    public static final String PHONE_CONTACT="phone";

    public static final String EMAIL_CONTACT="email";

    public static final String CONTACT_ERROR="Данный тип контакта недоступен";

    public static final String CONTACT_INFORMATION_ERROR="Неправильный формат контакта";

    private static Map<String,Class<? extends UserProfile>> userMap;
    private static Map<String,Class<? extends Contact>> contactMap;

    static {
        userMap=new HashMap<>();
        userMap.put(CLIENT,Client.class);

        contactMap=new HashMap<>();
        contactMap.put(PHONE_CONTACT, PhoneContact.class);
        contactMap.put(EMAIL_CONTACT, EmailContact.class);
    }

    public static final Map<String,Class<? extends UserProfile>> USER_MAP= Collections.unmodifiableMap(userMap);

    public static final Map<String,Class<? extends Contact>> CONTACT_MAP= Collections.unmodifiableMap(contactMap);



}

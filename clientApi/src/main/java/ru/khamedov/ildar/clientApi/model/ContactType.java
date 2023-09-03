package ru.khamedov.ildar.clientApi.model;

/**
 * Типы контактов:
 * - телефон
 * - email
 */
public enum ContactType {
    PHONE(new PhoneContact()),
    EMAIL(new EmailContact()),
    CONTACT(new Contact());

    private Contact contact;

    ContactType(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }


}

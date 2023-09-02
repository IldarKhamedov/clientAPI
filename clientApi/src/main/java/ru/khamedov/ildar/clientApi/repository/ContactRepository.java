package ru.khamedov.ildar.clientApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khamedov.ildar.clientApi.model.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}

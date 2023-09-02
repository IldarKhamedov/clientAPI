package ru.khamedov.ildar.clientApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khamedov.ildar.clientApi.model.EmailContact;

public interface EmailContactRepository extends JpaRepository<EmailContact,Long> {
}

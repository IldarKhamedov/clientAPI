package ru.khamedov.ildar.clientApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khamedov.ildar.clientApi.model.EmailContact;

@Repository
public interface EmailContactRepository extends JpaRepository<EmailContact,Long> {
}

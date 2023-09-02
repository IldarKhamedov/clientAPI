package ru.khamedov.ildar.clientApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khamedov.ildar.clientApi.model.PhoneContact;

public interface PhoneContactRepository extends JpaRepository<PhoneContact,Long> {
}

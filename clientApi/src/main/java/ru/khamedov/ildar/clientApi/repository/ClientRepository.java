package ru.khamedov.ildar.clientApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khamedov.ildar.clientApi.model.Client;

public interface ClientRepository extends JpaRepository<Client,String> {
}

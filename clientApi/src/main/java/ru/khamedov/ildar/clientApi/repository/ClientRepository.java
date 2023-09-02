package ru.khamedov.ildar.clientApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.khamedov.ildar.clientApi.model.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,String> {

    @Query("SELECT c FROM Client c WHERE c.blocked=FALSE")
    List<Client> findByNotBlocked();
}

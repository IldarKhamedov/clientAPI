package ru.khamedov.ildar.clientApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khamedov.ildar.clientApi.model.User;

public interface UserRepository extends JpaRepository<User,String> {
}

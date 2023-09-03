package ru.khamedov.ildar.clientApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.khamedov.ildar.clientApi.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {

    @Query("SELECT u FROM UserProfile u WHERE u.name=:name AND u.blocked=FALSE")
    UserProfile findByName(@Param("name")String name);

    @Query("SELECT COUNT(u)>0 FROM UserProfile u WHERE u.name=:name")
    boolean checkByName(@Param("name")String name);

}

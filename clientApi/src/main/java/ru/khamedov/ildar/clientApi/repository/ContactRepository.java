package ru.khamedov.ildar.clientApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.khamedov.ildar.clientApi.model.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    @Query("SELECT  c FROM UserProfile u " +
            " LEFT JOIN u.contactSet as c" +
            " WHERE u.name=:name " +
            " AND u.blocked=FALSE " +
            " AND c.active=TRUE " +
            " AND (:clazz IS NULL OR type(c)=:clazz) ")
    List<Contact> findByName(@Param("name")String name,@Param("clazz")Class clazz);
}

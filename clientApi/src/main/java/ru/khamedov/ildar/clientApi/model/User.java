package ru.khamedov.ildar.clientApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "UserProfile")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class User extends AbstractPersistable<Long> {

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String password;

    private boolean blocked;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "userprofile_id")
    private List<Contact> contactList=new ArrayList<>();
}

package ru.khamedov.ildar.clientApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;


import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class UserProfile extends AbstractPersistable<Long> {


    @Column(unique = true,nullable = false)
    private String name;

    @Basic(optional = false)
    private String password;

    private boolean blocked;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "userprofile_id")
    private Set<Contact> contactSet =new HashSet<>();
}

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
/**
 * Абстрактный класс пользователя для наследования и реализации конкретного типа.
 */
public abstract class UserProfile extends AbstractPersistable<Long> {


    @Column(unique = true,nullable = false)
    /**
     * Имя.
     */
    private String name;

    @Basic(optional = false)
    /**
     * Пароль.
     */
    private String password;

    /**
     * Заблокирован ли.
     */
    private boolean blocked;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "userprofile_id")
    /**
     * Список контактов.
     */
    private Set<Contact> contactSet =new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return name.equals(((UserProfile) o).name) && password.equals(((UserProfile) o).password);
    }

    @Override
    public int hashCode() {
        return name.hashCode()+password.hashCode();
    }
}

package ru.khamedov.ildar.clientApi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contact extends AbstractPersistable<Long> {

    @Basic(optional = false)
    private String information;

    private boolean active=true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return information.equals(contact.information);
    }

    @Override
    public int hashCode() {
        return information.hashCode();
    }
}

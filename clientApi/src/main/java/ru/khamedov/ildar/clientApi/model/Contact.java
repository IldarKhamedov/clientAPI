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
}

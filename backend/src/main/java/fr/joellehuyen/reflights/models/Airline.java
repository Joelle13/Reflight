package fr.joellehuyen.reflights.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="airline")
@Getter
@Setter
public class Airline {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;
    private String name;
    private String url;
}

package com.team3.techniko.model;

import javax.persistence.*;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "property_owners")
@Data
@NoArgsConstructor
public class PropertyOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "vat_number", unique = true, nullable = false)
    private String vatNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Property> properties;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PropertyRepair> repairs;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;
}

package com.team3.techniko.model;

import javax.persistence.*;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "year_of_construction")
    private int yearOfConstruction;

    @Column(name = "property_type", nullable = false)
    private String propertyType;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private PropertyOwner owner;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PropertyRepair> repairs;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;
}

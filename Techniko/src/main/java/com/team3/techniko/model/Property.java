package com.team3.techniko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import lombok.Data;
import java.util.Set;

@Entity
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    @Column(name = "address", nullable = false)
    private String address;

    private int yearOfConstruction;

    @Column(name = "property_type", nullable = false)
    private String propertyType;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private PropertyOwner owner;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PropertyRepair> repairs;

}

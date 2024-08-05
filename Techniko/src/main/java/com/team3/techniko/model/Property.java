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
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
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

    public Property(String address, String propertyType, PropertyOwner owner) {
        this.address = address;
        this.propertyType = propertyType;
        this.owner = owner;
    }
}

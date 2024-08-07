package com.team3.techniko.model;

import com.team3.techniko.model.enums.RepairType;
import com.team3.techniko.model.enums.Status;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PropertyRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repair_id")
    private Long repairId;

    @Column(nullable = false)
    private RepairType repairType;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Column(name = "date_submitted", nullable = false)
    private Date dateSubmitted;

    private String description;

    private Date proposedStartDate;

    private Date proposedEndDate;

    private Double proposedCost;

    private Boolean ownerAcceptance;

    @Column(nullable = false)
    private Status Status;

    private Date actualStartDate;

    private Date actualEndDate;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private PropertyOwner owner;

    public PropertyRepair(RepairType repairType, String shortDescription, Date dateSubmitted, Status Status, Property property, PropertyOwner owner) {
        this.repairType = repairType;
        this.shortDescription = shortDescription;
        this.dateSubmitted = dateSubmitted;
        this.Status = Status;
        this.property = property;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "repairId=" + repairId + ", repairType=" + repairType +", status="+Status+ ", shortDescription=" + shortDescription + ", dateSubmitted=" + dateSubmitted + ", description=" + description;
    }

}

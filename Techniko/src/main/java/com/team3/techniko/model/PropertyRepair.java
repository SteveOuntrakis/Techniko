package com.team3.techniko.model;

import com.team3.techniko.model.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "property_repairs")
@Data
@NoArgsConstructor
public class PropertyRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repair_id")
    private Long repairId;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private PropertyOwner owner;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(name = "repair_type", nullable = false)
    private String repairType;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Column(name = "date_submitted", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSubmitted;

    @Column(name = "description")
    private String description;

    @Column(name = "proposed_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proposedStartDate;

    @Column(name = "proposed_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proposedEndDate;

    @Column(name = "proposed_cost")
    private Double proposedCost;

    @Column(name = "owner_acceptance")
    private Boolean ownerAcceptance;

    @Enumerated(EnumType.STRING)
    @Column(name = "repair_status")
    private Status repairStatus;

    @Column(name = "actual_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualStartDate;

    @Column(name = "actual_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualEndDate;
}
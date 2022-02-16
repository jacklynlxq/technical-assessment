package com.example.demo.infrastructure.entity;

import com.example.demo.domain.entitiy.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Access")
public class AccessJpaEntity {

    @Id
    @GeneratedValue
    private long id;

    // many to one
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, referencedColumnName="id")
    private UserJpaEntity user;

    // many to one
    @ManyToOne
    @JoinColumn(name="feature_id", nullable=false, referencedColumnName="id")
    private FeatureJpaEntity feature;

    @Column(name="access")
    private boolean userFeatureAccess;
}

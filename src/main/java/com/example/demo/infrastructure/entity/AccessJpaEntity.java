package com.example.demo.infrastructure.entity;

import com.example.demo.domain.entitiy.Access;
import com.example.demo.domain.entitiy.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Access")
public class AccessJpaEntity implements Access {

    @Id
    @GeneratedValue
    private Long id;

    // many to one
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, referencedColumnName="id")
    private UserJpaEntity user;

    // many to one
    @ManyToOne
    @JoinColumn(name="feature_id", nullable=false, referencedColumnName="id")
    private FeatureJpaEntity feature;

    @Column(name="access")
    private Boolean userFeatureAccess;

}

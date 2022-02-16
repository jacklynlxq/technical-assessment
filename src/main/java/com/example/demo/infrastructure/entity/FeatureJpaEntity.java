package com.example.demo.infrastructure.entity;

import com.example.demo.domain.entitiy.Feature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="feature")
public class FeatureJpaEntity implements Feature {
    @Id
    @GeneratedValue
//    @Column(name="feature_id")
    private Long id;

    @Column(name="feature_name")
    private String featureName;

    @OneToMany(mappedBy="feature", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<AccessJpaEntity> accessList;

}

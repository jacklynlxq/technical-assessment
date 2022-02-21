package com.example.demo.infrastructure.entity;

import com.example.demo.domain.entitiy.Feature;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="feature")
public class FeatureJpaEntity  extends BaseEntity implements Feature {
    @Column(name="feature_name")
    private String featureName;

    @OneToMany(mappedBy="feature", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<AccessJpaEntity> accessList;

    public FeatureJpaEntity(String featureName){
        this.featureName = featureName;
    }
}

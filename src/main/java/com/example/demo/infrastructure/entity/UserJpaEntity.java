package com.example.demo.infrastructure.entity;

import com.example.demo.domain.entitiy.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
public class UserJpaEntity extends BaseEntity implements User {
    @Column(name="user_email")
    private String userEmail;

    @OneToMany(mappedBy="user", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<AccessJpaEntity> accessList;

    public UserJpaEntity(String userEmail){
        this.userEmail = userEmail;
    }
}

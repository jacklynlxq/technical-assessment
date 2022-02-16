package com.example.demo.infrastructure.entity;

import com.example.demo.domain.entitiy.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
public class UserJpaEntity implements User {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="user_email")
    private String userEmail;

    @OneToMany(mappedBy="user", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<AccessJpaEntity> accessList;
}

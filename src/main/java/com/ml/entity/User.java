package com.ml.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @Getter
    @Setter
    @NotNull
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @NotNull
    @Column(nullable = false)
    private String password;

    @Getter
    @Setter
    private String gender;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();
}

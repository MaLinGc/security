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
public class Resource implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(unique = true)
    private String name;

    @Getter
    @Setter
    @NotNull
    private String url;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    @OneToMany(mappedBy = "resource", fetch = FetchType.LAZY)
    private Set<RoleResource> roleResources = new HashSet<>();
}

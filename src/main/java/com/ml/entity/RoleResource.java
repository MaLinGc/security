package com.ml.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "role_resource")
@Entity
public class RoleResource implements Serializable {


    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "roleId",column = @Column(name = "role_id")),
            @AttributeOverride(name = "resourceId",column = @Column(name = "resource_id"))
    })
    @Getter
    @Setter
    private RoleResourceId id;

    @Getter
    @Setter

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    @Getter
    @Setter

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", insertable = false, updatable = false)
    private Resource resource;
}

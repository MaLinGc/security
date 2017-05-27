package com.ml.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RoleResourceId implements Serializable {

    @Getter
    @Setter

    @Column(name = "role_id")
    private Long roleId;

    @Getter
    @Setter

    @Column(name = "resource_id")
    private Long resourceId;
}

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
public class UserRoleId implements Serializable {

    @Getter
    @Setter

    @Column(name = "user_id")
    private Long userId;

    @Getter
    @Setter

    @Column(name = "role_id")
    private Long roleId;
}
package com.kyhns7.rbac.service.event;

import lombok.Getter;

@Getter
public class RoleEvent extends CrudEvent {
    public RoleEvent(Object source, Event event, Long roleId) {
        super(source, event);
        this.roleId = roleId;
    }

    /**
     * 角色id
     */
    private final Long roleId;

}

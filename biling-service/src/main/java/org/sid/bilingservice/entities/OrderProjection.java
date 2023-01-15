package org.sid.bilingservice.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "fullOrder", types = Bill.class)
public interface OrderProjection {
    Long getId();
    Date getCreatedAt();
    Long getCustomerId();

}

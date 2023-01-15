package org.sid.customerservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "CustomerDetails", types=Customer.class)
public interface CustomerProjection {

    public String getName();
    public String getEmail();
}

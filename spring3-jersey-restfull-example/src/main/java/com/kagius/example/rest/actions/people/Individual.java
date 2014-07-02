package com.kagius.example.rest.actions.people;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kagius.example.rest.entities.data.PersonDao;
import com.sun.jersey.spi.inject.Inject;

@Component
@Scope("request")
@Path("/people/{code}")
public class Individual {

    @Inject("people") private PersonDao dao;
    @GET
    @Produces({"application/json", "application/xml"})
    public Object find(@PathParam("code") String code) {

        return dao.find(code);
    }

    @DELETE
    public void remove(@PathParam("code") String code) {
        dao.delete(code);
    }
}

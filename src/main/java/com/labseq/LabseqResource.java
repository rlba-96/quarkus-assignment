package com.labseq;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/labseq")
@Produces(MediaType.APPLICATION_JSON)
public class LabseqResource {

    @Inject
    LabseqService service;

    @GET
    @Path("/{n}")
    public Response getLabseq(@PathParam("n") int n) {
        if (n < 0) {
            System.out.println("Input cannot be negative");
            return Response.status(Response.Status.BAD_REQUEST).entity("Input cannot be negative").build();
        }

        long result = service.getLabseqValue(n);
        LabseqResponse response = new LabseqResponse();
        response.setInput(n);
        response.setValue(result);

        return Response.status(Response.Status.OK).entity(response).build();
    }
}

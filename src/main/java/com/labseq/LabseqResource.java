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
            System.out.println("Error: input must be non negative");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error: input must be non-negative").build();
        }

        long result = service.getLabseqValue(n);
        return Response.ok(result).build();
    }
}

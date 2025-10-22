package com.labseq;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/labseq")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Labseq API", description = "Provides the LabSeq sequence values for a given index.")
public class LabseqResource {

    @Inject
    LabseqService service;

    @GET
    @Path("/{n}")
    @Operation(
            summary = "Get the LabSeq value for a given value",
            description = "Computes the LabSeq sequence value for a given non-negative integer n, using memoization for performance."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Successful computation of the LabSeq value",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LabseqResponse.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid input: n must be non-negative",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LabseqError.class))
            )
    })
    public Response getLabseq(
            @Parameter(
                    description = "Non-negative integer index for which to compute the LabSeq value",
                    required = true,
                    example = "10"
            )
            @PathParam("n") int n) {
        if (n < 0) {
            LabseqError labseqError = new LabseqError();
            labseqError.setMessage("Input cannot be negative");

            return Response.status(Response.Status.BAD_REQUEST).entity(labseqError).build();
        }

        long result = service.getLabseqValue(n);

        LabseqResponse response = new LabseqResponse();
        response.setInput(n);
        response.setValue(result);

        return Response.status(Response.Status.OK).entity(response).build();
    }
}

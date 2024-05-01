package org.tiere.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.tiere.dto.FileDetails;
import org.tiere.service.FileService;
import org.tiere.util.ContentType;

import java.io.IOException;

@Path("/files/{id}")
public class FileResource {

    private final FileService fileService;

    @Inject
    public FileResource(FileService fileService) {
        this.fileService = fileService;
    }

    @GET
    @Produces
    public Response get(@PathParam("id") int fileId) throws IOException {
        FileDetails fileDetails = fileService.findById(fileId);
        return Response.ok(fileDetails.file()).header("Content-Type", fileDetails.mimeType()).build();
    }

}

package org.tiere.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.tiere.dto.Account;
import org.tiere.dto.AccountUpdate;
import org.tiere.service.AccountService;

@Path("/accounts")
public class AccountResource {

    private final AccountService accountService;

    @Inject
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Account get() {
        return accountService.find();
    }

    @PUT
    @Path("/me/password")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces
    public void put(String password) {
        accountService.setPassword(password);
    }

    @PUT
    @Path("/me")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces
    public void putAccount(AccountUpdate account) {
        accountService.update(account);
    }

}

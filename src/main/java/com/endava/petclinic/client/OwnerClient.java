package com.endava.petclinic.client;

import com.endava.petclinic.model.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.endava.petclinic.util.EnvReader.getBasePath;

public class OwnerClient extends BaseClient{

    public Response createOwner(Owner owner) {
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(owner)
                .post("/api/owners");
    }

    public Response getOwnerById(Long ownerId) {
        return getBasicRestConfig()
                .pathParam("ownerId", ownerId)
                .get("/api/owners/{ownerId}");

    }

    public Response getOwnerList() {
        return getBasicRestConfig()
                .basePath(getBasePath())
                .get("/api/owners");

    }

    public Response deleteOwnerById(Long ownerId) {
        return getBasicRestConfig()
                .pathParam("ownerId", ownerId)
                .delete("/api/owners/{ownerId}");
    }

    public Response updateOwnerById(Long ownerId, Owner owner) {
        return getBasicRestConfig()
                .basePath(getBasePath())
                .contentType(ContentType.JSON)
                .body(owner)
                .pathParam("ownerId", ownerId)
                .put("/api/owners/{ownerId}");

    }

}

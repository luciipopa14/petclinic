package com.endava.petclinic.owner;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.client.OwnerClient;
import com.endava.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOwnerTest extends TestBaseClass {


    @Test
    public void shouldCreateOwner() {
        //given
        Owner owner = testDataProvider.getOwner();
        //when
        Response response = ownerClient.createOwner(owner);
        //then
        response.then().statusCode(HttpStatus.SC_CREATED)
                .body("id", is(notNullValue()));
        long id = response.body().jsonPath().getLong("id");

        Owner actualOwnerInDb = db.getOwnerById(id);
        assertThat(actualOwnerInDb, is(owner));
    }

    @Test
    public void shouldFailToCreateOwnerGivenEmptyFirstName() {
        //given
        Owner owner = testDataProvider.getOwner();
        owner.setFirstName("");
        //when
        Response response = ownerClient.createOwner(owner);
        //then
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldFailToCreateOwnerFewDigitsTelephone() {
        //given
        Owner owner = testDataProvider.getOwner();
        owner.setTelephone(testDataProvider.getNumberWithDigits(0, 0));
        //when
        Response response = ownerClient.createOwner(owner);
        //then
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldFailToCreateOwnerManyDigitsTelephone() {
        //given
        Owner owner = testDataProvider.getOwner();
        owner.setTelephone(testDataProvider.getNumberWithDigits(11, 100));
        //when
        Response response = ownerClient.createOwner(owner);
        //then
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}

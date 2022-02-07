package com.endava.petclinic;

import com.endava.petclinic.model.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FirstTest {

    @Test
    public void firstTest() {

        given().baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("petclinic")
                
        .when()
                .get("api/owners")
                
        .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createOwner() {
        //given
        Owner owner = new Owner("Gica", "Hagi", "constanta", "constanta", "983748248");
        System.out.println(owner.toString());
        //when
        Response response =  given().baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(owner)
                
        .when()
                .post("api/owners")
                ;
        //then
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .header("Location", notNullValue())
                .body("id", notNullValue())
                .body("firstName", is(owner.getFirstName()))
                .body("lastName", is(owner.getLastName()))
                .body("address", is(owner.getAddress()))
                .body("city", is(owner.getCity()))
                .body("telephone", is(owner.getTelephone()))
                .body("pets", empty());

        Owner actualOwner = response.as(Owner.class);
        assertThat(actualOwner, is(owner));
    }

    @Test
    public void getOwnerById(){

        given().baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("petclinic")
                .pathParam("ownerId", 76)
                
        .when()
                .get("api/owners/{ownerId}")
                
        .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void deleteOwnerById(){

        given().baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("petclinic")
                .pathParam("ownerId", 76)
                
        .when()
                .delete("api/owners/{ownerId}")
                
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

}

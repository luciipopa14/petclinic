package com.endava.petclinic;

import com.endava.petclinic.client.OwnerClient;
import com.endava.petclinic.client.PetClient;
import com.endava.petclinic.extension.TestReporterExtension;
import com.endava.petclinic.fixture.PetclinicFixture;
import com.endava.petclinic.services.DBService;
import com.endava.petclinic.testData.TestDataProvider;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestReporterExtension.class)
public class TestBaseClass {

    protected OwnerClient ownerClient = new OwnerClient();
    protected PetClient petClient = new PetClient();
    protected TestDataProvider testDataProvider = new TestDataProvider();

    protected PetclinicFixture fixture = new PetclinicFixture();

    protected DBService db = new DBService();
}

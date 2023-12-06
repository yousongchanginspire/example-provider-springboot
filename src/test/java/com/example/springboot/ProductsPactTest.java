package com.example.springboot;

import au.com.dius.pact.provider.junitsupport.*;
import au.com.dius.pact.provider.junitsupport.loader.*;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("pactflow-example-provider-springboot")
@PactBroker(scheme = "https", host = "yousongchang.pactflow.io",providerBranch = "master", enablePendingPacts = "true", authentication = @PactBrokerAuth(token = "c4T2hu2SKbCoQUiGhTiTWQ"))
class ProductsPactTest {

  @BeforeEach
  public void setupTestTarget(PactVerificationContext context) {
    context.setTarget(new HttpTestTarget("localhost", 8080));
  }

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  public void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }
  
  @State("a product with ID 10 exists")
  public void setupProductX010000021() throws IOException {
    System.out.println("a product with ID 10 exists");
  }

  @State("products exist")
  public void setupProducts() throws IOException {
    System.out.println("a product with ID 11 does not exist");
  }
}

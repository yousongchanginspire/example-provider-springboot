package com.example.springboot;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider(" pactflow-example-provider-springboot")
@PactBroker(scheme = "https", host = "${PACT_BROKER_BASE_URL}",
  authentication = @PactBrokerAuth(token = "${PACT_BROKER_TOKEN}"))
class ProductsPactTest {

  @Autowired
  ProductRepository repository;

  @BeforeEach
  public void setupTestTarget(PactVerificationContext context) {
    context.setTarget(new HttpTestTarget("localhost", 8080));

    System.setProperty("pact.provider.tag", "dev");
    System.setProperty("pact.provider.version", "0.0.0");
    System.setProperty("pact.verifier.publishResults", System.getenv("PACT_BROKER_PUBLISH_VERIFICATION_RESULTS") == null ? "false" : "true");
  }

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  public void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @State("a product with ID 10 exists")
  public void setupProductX010000021() throws IOException {
    repository.save(new Product(10L, "test", "product description"));
  }

  @State("a product with ID 11 does not exist")
  public void setupProductX010000022() throws IOException {
    repository.deleteAll();
  }

}
package com.apiautomation.commonstepdefinitons;

import static org.assertj.core.api.Assertions.assertThat;

import com.apiautomation.models.AbstractSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonSteps extends AbstractSteps {

  @Then("the response code is {string}")
  public void theResponseCodeIs(String responseCode) {
    assertThat(transaction.getResponseStatusCode()).isEqualTo(Integer.valueOf(responseCode));
  }

  @Then("the errorMessage has {string}")
  public void theErrorMessageHas(String expectedErrorMessage) {
    assertThat(transaction.getValueFromResponse("errorMessage")).contains(expectedErrorMessage);
  }

  @And("the response has a ID field")
  public void theResponseHasAIDField() {
    assertThat(transaction.getValueFromResponse("id")).isNotEmpty();
  }
}

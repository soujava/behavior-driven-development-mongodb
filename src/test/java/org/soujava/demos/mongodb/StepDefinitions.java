package org.soujava.demos.mongodb;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.soujava.demos.mongodb.document.RoomRepository;

@ApplicationScoped
public class StepDefinitions {

    @Inject
    private RoomRepository repository;

    @Given("an example scenario")
    public void anExampleScenario() {
        Assertions.assertThat(repository).isNotNull();
    }

    @When("all step definitions are implemented")
    public void allStepDefinitionsAreImplemented() {
    }

    @Then("the scenario passes")
    public void theScenarioPasses() {
    }

}

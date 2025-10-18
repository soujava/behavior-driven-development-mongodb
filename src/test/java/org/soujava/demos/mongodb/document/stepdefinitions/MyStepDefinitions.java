package org.soujava.demos.mongodb.document.stepdefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class MyStepDefinitions {

    @Given("a MongoDB database is available")
    public void mongoDBIsAvailable() {
        System.out.println("MongoDB is available");
    }

    @When("I insert a sample document")
    public void insertDocument() {
        System.out.println("Document inserted");
    }

    @Then("I can retrieve it successfully")
    public void retrieveDocument() {
        System.out.println("Document retrieved successfully");
    }
}
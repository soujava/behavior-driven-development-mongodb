package org.soujava.demos.mongodb.document;

import io.cucumber.junit.platform.engine.Cucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.soujava.demos.mongodb.stepdefinitions")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, summary")
public class RunCucumberTest {

}
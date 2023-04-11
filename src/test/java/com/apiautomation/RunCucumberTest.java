package com.apiautomation;

import static io.cucumber.core.options.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,html:target/reports/html/apitestautomation-report.html,json:target/reports/json/apitestautomation-report.json")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.apiautomation")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@smokeTest")
class RunCucumberTest {

}


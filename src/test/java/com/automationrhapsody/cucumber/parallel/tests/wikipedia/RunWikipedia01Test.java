package com.automationrhapsody.cucumber.parallel.tests.wikipedia;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = {"src/test/resources/com/automationrhapsody/cucumber/parallel/tests/wikipedia"},
    plugin = {
        "json:target/cucumber.json",
        //"html:target/cucumber/wikipedia.html",
        //"pretty"
    },
    tags = {"~@ignored"}
)
public class RunWikipedia01Test {
}
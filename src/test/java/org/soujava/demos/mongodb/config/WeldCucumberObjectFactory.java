package org.soujava.demos.mongodb.config;

import io.cucumber.core.backend.ObjectFactory;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class WeldCucumberObjectFactory implements ObjectFactory {

    private Weld weld;
    private WeldContainer container;

    @Override
    public void start() {
        weld = new Weld();
        container = weld.initialize();
    }

    @Override
    public void stop() {
        if (weld != null) {
            weld.shutdown();
        }
    }

    @Override
    public boolean addClass(Class<?> stepClass) {
        return true; // accept all step classes
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        return (T)  container.select(type).get();
    }
}
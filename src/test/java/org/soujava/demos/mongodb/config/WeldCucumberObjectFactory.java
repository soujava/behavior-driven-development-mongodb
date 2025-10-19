package org.soujava.demos.mongodb.config;

import io.cucumber.core.backend.ObjectFactory;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.HashMap;
import java.util.Map;

public class WeldCucumberObjectFactory implements ObjectFactory {

    private Weld weld;
    private WeldContainer container;
    private final Map<Class<?>, Object> instances = new HashMap<>();

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
        instances.clear();
    }

    @Override
    public boolean addClass(Class<?> stepClass) {
        return true; // accept all step classes
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        return (T) instances.computeIfAbsent(type, c -> container.select(type).get());
    }
}
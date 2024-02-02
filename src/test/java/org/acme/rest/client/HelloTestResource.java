package org.acme.rest.client;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class HelloTestResource implements QuarkusTestResourceLifecycleManager {

    private GenericContainer<?> helloContainer;

    @Override
    public Map<String, String> start() {
        System.out.println("TESTCONTAINERS_RYUK_DISABLED = " + System.getenv("TESTCONTAINERS_RYUK_DISABLED"));
        helloContainer = new GenericContainer<>("thomaspoignant/hello-world-rest-json")
                .withExposedPorts(8080)
                .waitingFor(Wait.forHttp("/"));

        helloContainer.start();

        final Map<String, String> props = new LinkedHashMap<>();
        props.put(
                "quarkus.rest-client.\"" + org.acme.rest.client.HelloService.class.getName() + "\".url",
                "http://" + helloContainer.getHost() + ":" + helloContainer.getMappedPort(8080) + "/");
        return props;
    }

    @Override
    public void stop() {
        try {
            if (helloContainer != null) {
                helloContainer.stop();
            }
        } catch (Exception e) {
            // ignored
        }
    }

}

package service;

import resources.TestResource;
import resources.server.ResourceServer;

public class ResourceService {

    private ResourceServer resourceServer;

    public ResourceService(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

    public void bindToResource(Object object) {

        try {
            TestResource tr = (TestResource) object;
            resourceServer.setTestResource(tr);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }

}

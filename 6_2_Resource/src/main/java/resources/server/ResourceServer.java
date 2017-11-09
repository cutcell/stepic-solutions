package resources.server;

import resources.TestResource;

public class ResourceServer {

    private TestResource testResource;

    public void setTestResource(TestResource testResource) {
        this.testResource = testResource;
    }

    public String getName() {
        return testResource.getName();
    }

    public int getAge() {
        return testResource.getAge();
    }

}

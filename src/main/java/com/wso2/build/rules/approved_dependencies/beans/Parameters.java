package com.wso2.build.rules.approved_dependencies.beans;

import java.io.Serializable;

/**
 * Created by uvindra on 2/12/14.
 */
public class Parameters implements Serializable {
    private String gregHome;
    private String gregModuleEndpoint;
    private String gregDependencyEndpoint;
    private String gregArtifactEndpoint;
    private String gregLifecycleEndpoint;
    private String gregUsername;
    private String gregPassword;
    private String trustStorePassword;

    public String getGregHome() {
        return gregHome;
    }

    public void setGregHome(String gregHome) {
        this.gregHome = gregHome;
    }

    public String getGregModuleEndpoint() {
        return gregModuleEndpoint;
    }

    public void setGregModuleEndpoint(String gregModuleEndpoint) {
        this.gregModuleEndpoint = gregModuleEndpoint;
    }

    public String getGregUsername() {
        return gregUsername;
    }

    public void setGregUsername(String gregUsername) {
        this.gregUsername = gregUsername;
    }

    public String getGregPassword() {
        return gregPassword;
    }

    public void setGregPassword(String gregPassword) {
        this.gregPassword = gregPassword;
    }

    public String getTrustStorePassword() {
        return trustStorePassword;
    }

    public void setTrustStorePassword(String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
    }

    public String getGregDependencyEndpoint() {
        return gregDependencyEndpoint;
    }

    public void setGregDependencyEndpoint(String gregDependencyEndpoint) {
        this.gregDependencyEndpoint = gregDependencyEndpoint;
    }

    public String getGregArtifactEndpoint() {
        return gregArtifactEndpoint;
    }

    public void setGregArtifactEndpoint(String gregArtifactEndpoint) {
        this.gregArtifactEndpoint = gregArtifactEndpoint;
    }

    public String getGregLifecycleEndpoint() {
        return gregLifecycleEndpoint;
    }

    public void setGregLifecycleEndpoint(String gregLifecycleEndpoint) {
        this.gregLifecycleEndpoint = gregLifecycleEndpoint;
    }
}

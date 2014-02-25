package com.wso2.build.rules.approved_dependencies;

import com.wso2.build.rules.approved_dependencies.beans.Artifact;
import com.wso2.build.rules.approved_dependencies.beans.Parameters;
import com.wso2.build.rules.approved_dependencies.registry.BuildDependencyClient;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Settings;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by uvindra on 2/24/14.
 */

/**
 * @goal enforce_approved_dependencies
 */
public class EnforceApprovedDependenciesMojo extends AbstractMojo {

    /**
     * Base directory of the project.
     *
     * @parameter default-value="${basedir}"
     * @required
     * @readonly
     */
    private File basedir;

    /**
     * The current Maven session.
     *
     * @parameter expression="${session}"
     * @required
     * @readonly
     */
    MavenSession mavenSession;

    /**
     * The settings.xml file in .m2.
     *
     * @parameter expression="${settings}"
     * @required
     */
    private Settings settings;

    private static final String approvedState = "Approved";

    @Override
    public void execute() throws MojoExecutionException {
        if (true != mavenSession.getExecutionRootDirectory().equalsIgnoreCase(basedir.toString())) {
            return;
        }

        BuildDependencyClient client = new BuildDependencyClient();

        Map<String, Profile> profileMap = settings.getProfilesAsMap();

        Profile profile = profileMap.get("rule");

        Properties properties = profile.getProperties();

        Parameters parameters = new Parameters();

        parameters.setGregHome(properties.getProperty("greg.home"));
        parameters.setGregModuleEndpoint(properties.getProperty("greg.module.endpoint"));
        parameters.setGregDependencyEndpoint(properties.getProperty("greg.dependency.endpoint"));
        parameters.setGregArtifactEndpoint(properties.getProperty("greg.artifact.endpoint"));
        parameters.setGregLifecycleEndpoint(properties.getProperty("greg.lifecycle.endpoint"));
        parameters.setGregUsername(properties.getProperty("greg.username"));
        parameters.setGregPassword(properties.getProperty("greg.password"));
        parameters.setTrustStorePassword(properties.getProperty("trust.store.password"));

        client.loadDependecies(parameters);

        System.out.println("No of Artifacts : " + client.getArtifactsSize());

        Iterator it = client.getArtifactIterator();

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();

            Artifact artifact = (Artifact) entry.getValue();

            if (true != approvedState.equalsIgnoreCase(artifact.getState())) {
                List<String> usedModules = client.getArtifactUsage((String) entry.getKey());

                for (String usedModule : usedModules) {
                    System.out.println("Module affected : " + usedModule);
                }

                throw new MojoExecutionException("Artifact : " + entry.getKey() + " is not approved");
            }
        }
    }
}

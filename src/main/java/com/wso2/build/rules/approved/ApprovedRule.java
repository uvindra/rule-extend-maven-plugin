package com.wso2.build.rules.approved;

import com.wso2.build.interfaces.rules.ProjectRule;
import com.wso2.build.beans.Parameters;
import com.wso2.build.registry.GRegDependencyClient;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.component.annotations.Component;

import java.util.Map;
import java.util.Properties;


/**
 * Created by uvindra on 2/16/14.
 */
@Component( role = ProjectRule.class, hint = "approved" )
public class ApprovedRule implements ProjectRule {

    @Override
    public boolean validate(MavenProject mvnProject, MavenSession mvnSession, Settings settings) {
        /*
        System.out.println("ApprovedRule ");

        Map<String, Profile> profileMap = settings.getProfilesAsMap();

        Profile profile = profileMap.get("dependency");

        Properties properties = profile.getProperties();

        Parameters parameters = new Parameters();

        parameters.setGregHome(properties.getProperty("greg.home"));
        parameters.setGregModuleEndpoint(properties.getProperty("greg.module.endpoint"));
        parameters.setGregDependencyEndpoint(properties.getProperty("greg.dependency.endpoint"));
        parameters.setGregArtifactEndpoint(properties.getProperty("greg.artifact.endpoint"));
        parameters.setGregUsername(properties.getProperty("greg.username"));
        parameters.setGregPassword(properties.getProperty("greg.password"));
        parameters.setTrustStorePassword(properties.getProperty("trust.store.password"));

        System.out.println("getGregHome : " + parameters.getGregHome());

        GRegDependencyClient client = new GRegDependencyClient(parameters);

        return client.checkDependecies();
        */

        return false;
    }

}

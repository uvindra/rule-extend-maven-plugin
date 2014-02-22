package com.wso2.build.plugin;

import com.wso2.build.interfaces.containers.RuleContainer;
import com.wso2.build.interfaces.rules.ProjectRule;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.PlexusContainerException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

import java.util.Map;
import java.util.Properties;


/**
 * Created by uvindra on 2/10/14.
 * @goal extend
 */
public class RuleExtenderMojo extends AbstractMojo {


    private RuleContainer ruleContainer = null;

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    /**
     * The current Maven session.
     *
     * @parameter expression="${session}"
     * @required
     * @readonly
     */
    private MavenSession mavenSession = null;

    /**
     * The settings.xml file in .m2.
     *
     * @parameter expression="${settings}"
     * @required
     */
    private Settings settings = null;


    @Parameter(required = true)
    private String hint;

    @Parameter(required = true)
    private String gregHome;



    private static final String dependencyProfile = "dependency";
    private static final String registryHomeProperty = "greg.home";
    private static final String moduleEndpointProperty = "greg.module.endpoint";
    private static final String dependencyEndpointProperty = "greg.dependency.endpoint";
    private static final String artifactEndpointProperty = "greg.artifact.endpoint";
    private static final String lifecycleEndpointProperty = "greg.lifecycle.endpoint";
    private static final String userNameProperty = "greg.username";
    private static final String passwordProperty = "greg.password";
    private static final String trustStorePassword = "trust.store.password";

    @Override
    public void execute() throws MojoExecutionException {
        System.out.println("RuleExtenderMojo execute start");

        try
        {
            PlexusContainer container = new DefaultPlexusContainer();


            ruleContainer = container.lookup(RuleContainer.class);

            System.out.println("groupId : " + mavenProject.getGroupId());
            System.out.println("artifactId : " + mavenProject.getArtifactId());

            System.out.println("hint : " + hint);

            //System.out.println("gregHome path : " + gregHome);

            //System.out.println("componentParameters size : " + configurations.get(homeObject));



            if (false == executeRule()) {
                throw new MojoExecutionException("Rule failed");
            }

            container.dispose();

        }
        catch (PlexusContainerException e){
            e.printStackTrace();
        }
        catch (ComponentLookupException e){
            e.printStackTrace();
        }
    }





    private boolean executeRule() {
        ProjectRule projectRule = ruleContainer.getProjectRule(hint);

        if (null != projectRule) {
            return projectRule.validate(mavenProject, mavenSession, settings);
        }

        return false;
    }
}

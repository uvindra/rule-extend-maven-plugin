package com.wso2.build.plugin;

import com.wso2.build.interfaces.containers.RuleContainer;
import com.wso2.build.interfaces.rules.ProjectRule;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.PlexusContainerException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;


/**
 * Created by uvindra on 2/10/14.
 * @goal extend
 */
public class RuleExtenderMojo extends AbstractMojo {

    private RuleContainer ruleContainer = null;

    /**
     * The project currently being build.
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
     * The current Maven session.
     *
     * @parameter expression="${hint}"
     * @required
     *
     */
    private String hint;


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
            return projectRule.validate(mavenProject, mavenSession);
        }


        return false;
    }
}

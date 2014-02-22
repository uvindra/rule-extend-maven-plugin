package com.wso2.build.rules.repositories;

import com.wso2.build.utils.Utility;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * Created by uvindra on 2/23/14.
 */

/**
 *
 * @goal repositories_ban_child
 */
public class BanReposChildMojo extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    private static final String repositoriesTag = "repositories";

    @Override
    public void execute() throws MojoExecutionException {
        MavenProject parentProject = mavenProject.getParent();

        boolean hasRepositoriesTag = Utility.isElementSpecified(mavenProject, repositoriesTag);

        // This is not a Parent pom and it has a repositories tag present
        if (null != parentProject && true == hasRepositoriesTag) {
            throw new MojoExecutionException("Child pom contains repositories tag");
        }
    }
}

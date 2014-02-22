package com.wso2.build.rules.plugin_repositories;

import com.wso2.build.utils.Utility;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * Created by uvindra on 2/23/14.
 */

/**
 *
 * @goal plugin_repositories_ban_child
 */
public class BanPluginReposChildMojo extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    private static final String pluginRepositoriesTag = "pluginRepositories";

    @Override
    public void execute() throws MojoExecutionException {
        MavenProject parentProject = mavenProject.getParent();

        boolean hasPluginRepositoriesTag = Utility.isElementSpecified(mavenProject, pluginRepositoriesTag);

        // This is not a Parent pom and it has a pluginRepositories tag present
        if (null != parentProject && true == hasPluginRepositoriesTag) {
            throw new MojoExecutionException("Child pom contains pluginRepositories tag");
        }
    }
}

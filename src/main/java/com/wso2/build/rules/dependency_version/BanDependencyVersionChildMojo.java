package com.wso2.build.rules.dependency_version;

import com.wso2.build.utils.Utility;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;


/**
 * Created by uvindra on 2/22/14.
 */

/**
 *
 * @goal dependency_version_ban_child
 */
public class BanDependencyVersionChildMojo extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    private static final String versionTag = "version";
    private static final String dependencyTag = "dependency";

    @Override
    public void execute() throws MojoExecutionException {
        MavenProject parentProject = mavenProject.getParent();

        boolean isVersionPresent = Utility.hasParentChildElement(mavenProject, dependencyTag, versionTag);

        // This is not a Parent pom and it has a version tag present in a dependency section
        if (null != parentProject && true == isVersionPresent) {
            throw new MojoExecutionException("Child pom contains version tag in dependency section");
        }
    }
}

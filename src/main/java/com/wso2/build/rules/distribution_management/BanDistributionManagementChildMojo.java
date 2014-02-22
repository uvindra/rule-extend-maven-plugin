package com.wso2.build.rules.distribution_management;

import com.wso2.build.utils.Utility;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * Created by uvindra on 2/23/14.
 */

/**
 *
 * @goal distribution_management_ban_child
 */
public class BanDistributionManagementChildMojo extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    private static final String distributionManagementTag = "distributionManagement";

    @Override
    public void execute() throws MojoExecutionException {
        MavenProject parentProject = mavenProject.getParent();

        boolean hasdistributionManagementTag = Utility.isElementSpecified(mavenProject, distributionManagementTag);

        // This is not a Parent pom and it has a distributionManagement tag present
        if (null != parentProject && true == hasdistributionManagementTag) {
            throw new MojoExecutionException("Child pom contains a distributionManagement tag");
        }
    }
}

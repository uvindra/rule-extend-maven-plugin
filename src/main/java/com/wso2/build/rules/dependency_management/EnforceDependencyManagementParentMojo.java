package com.wso2.build.rules.dependency_management;

import com.wso2.build.utils.Utility;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * Created by uvindra on 2/20/14.
 */

/**
 *
 * @goal dependency_management_enforce_parent
 */
public class EnforceDependencyManagementParentMojo extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    private static final String dependenciesTag = "dependencies";
    private static final String dependencyManagementTag = "dependencyManagement";

    @Override
    public void execute() throws MojoExecutionException {
        MavenProject parentProject = mavenProject.getParent();

        boolean hasDependencyManagementSection = Utility.hasChildParentElement(mavenProject, dependenciesTag, dependencyManagementTag);

        // This is a Parent pom and this does not have a dependencyMangement section
        if (null == parentProject && false == hasDependencyManagementSection) {
            throw new MojoExecutionException("Parent pom does not contain dependencyMangement section");
        }
    }
}

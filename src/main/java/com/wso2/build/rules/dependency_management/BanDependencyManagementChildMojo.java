package com.wso2.build.rules.dependency_management;

import com.wso2.build.utils.Utility;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * Created by uvindra on 2/22/14.
 */

/**
 *
 * @goal dependency_management_ban_child
 */
public class BanDependencyManagementChildMojo extends AbstractMojo {

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

        // This is not a Parent pom and it has a dependencyMangement section
        if (null != parentProject && true == hasDependencyManagementSection) {
            throw new MojoExecutionException("Child pom contains dependencyManagement section");
        }
    }

    /**
    * For injecting a test project to test the Mojo
    */
    public void injectTestProject(MavenProject mavenProject) {
        this.mavenProject = mavenProject;
    }
}

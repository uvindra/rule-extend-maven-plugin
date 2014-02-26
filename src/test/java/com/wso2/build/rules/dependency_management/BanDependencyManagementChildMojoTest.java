package com.wso2.build.rules.dependency_management;

import com.wso2.build.utils.Helper;
import junit.framework.Assert;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.testng.annotations.Test;
import java.net.URL;

/**
 * Created by uvindra on 2/26/14.
 */
public class BanDependencyManagementChildMojoTest {
    /**
     *
     * Test for rule failure
     */
    @Test(expectedExceptions = MojoExecutionException.class)
    public void testDependencyManagementInChild() throws Exception {
        URL parentURL = this.getClass().getResource("/dependency_management/dependency_management_in_parent.xml");
        URL childURL = this.getClass().getResource("/dependency_management/child/dependency_management_in_child.xml");

        BanDependencyManagementChildMojo mojo = new BanDependencyManagementChildMojo();

        MavenProject mavenProject = Helper.getTestChildProject(parentURL, childURL);

        Assert.assertNotNull(mavenProject);

        mojo.injectTestProject(mavenProject);

        mojo.execute();
    }

    /**
     *
     * Test for rule success
     */
    @Test
    public void testDependencyManagementNotInChild() throws Exception {
        URL parentURL = this.getClass().getResource("/dependency_management/dependency_management_in_parent.xml");
        URL childURL = this.getClass().getResource("/dependency_management/child/dependency_management_not_in_child.xml");

        BanDependencyManagementChildMojo mojo = new BanDependencyManagementChildMojo();

        MavenProject mavenProject = Helper.getTestChildProject(parentURL, childURL);

        Assert.assertNotNull(mavenProject);

        mojo.injectTestProject(mavenProject);

        mojo.execute();
    }
}

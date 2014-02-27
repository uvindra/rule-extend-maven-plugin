package com.wso2.build.rules.dependency_version;

import com.wso2.build.utils.Helper;
import junit.framework.Assert;
import org.apache.maven.project.MavenProject;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Created by uvindra on 2/27/14.
 */
public class BanDependencyVersionChildMojoTest {
    @Test
    public void testCarbonDependencyVersionInChild() throws Exception {
        URL parentURL = this.getClass().getResource("/dependency_version/parent_pom.xml");
        URL childURL = this.getClass().getResource("/dependency_version/child/carbon_dependency_version_in_child.xml");

        BanDependencyVersionChildMojo mojo = new BanDependencyVersionChildMojo();

        MavenProject mavenProject = Helper.getTestChildProject(parentURL, childURL);

        Assert.assertNotNull(mavenProject);

        mojo.injectTestProject(mavenProject);

        mojo.execute();
    }
}

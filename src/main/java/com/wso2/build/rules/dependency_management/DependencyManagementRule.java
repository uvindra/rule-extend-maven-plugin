package com.wso2.build.rules.dependency_management;

import com.wso2.build.interfaces.rules.ProjectRule;
import org.apache.commons.io.FileUtils;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.component.annotations.Component;

import java.io.*;

/**
 * Created by uvindra on 2/20/14.
 */
@Component(role = ProjectRule.class, hint = "dependency_management")
public class DependencyManagementRule implements ProjectRule {

    private static final String dependencyManagementTag = "<dependencyManagement>";

    @Override
    public boolean validate(MavenProject mvnProject, MavenSession mvnSession, Settings settings) {
        MavenProject parentProject = mvnProject.getParent();

        Model model = mvnProject.getModel();

        File pomFile = model.getPomFile();

        String pomString = "";

        try {
            pomString = FileUtils.readFileToString(pomFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean hasDependencyManagementSection = pomString.contains(dependencyManagementTag);

        // This is a Parent pom and this does not have a dependencyMangement section
        if (null == parentProject && false == hasDependencyManagementSection) {
            System.out.println("Parent pom does not contain dependencyMangement section");
            return false;
        }
        // This is not a Parent pom and it has a dependencyMangement section
        else if (null != parentProject && true == hasDependencyManagementSection) {
            System.out.println("Child pom contains dependencyMangement section");
            return false;
        }

        return true;
    }
}

package com.wso2.build.utils;

import org.apache.commons.io.IOUtils;
import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;
import java.io.*;
import java.net.URL;

/**
 * Created by uvindra on 2/26/14.
 */
public class Helper {

    public static MavenProject getTestParentProject(URL url) {
        MavenProject mavenProject = null;

        File pomFile = new File(url.getFile());

        Model model = new Model();

        model.setPomFile(pomFile);
        mavenProject = new MavenProject(model);

        return mavenProject;
    }

    public static MavenProject getTestChildProject(URL parentURL, URL childURL) {
        MavenProject childProject = null;

        File pomFile = new File(childURL.getFile());

        Model model = new Model();

        model.setPomFile(pomFile);
        childProject = new MavenProject(model);

        MavenProject parentProject = getTestParentProject(parentURL);

        if (null != parentProject) {
            childProject.setParent(parentProject);
        }
        else {
            childProject = null;
        }

        return childProject;
    }
}

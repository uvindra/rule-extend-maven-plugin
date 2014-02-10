package com.wso2.build.interfaces.rules;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;

/**
 * Created by uvindra on 2/10/14.
 */
public interface ProjectRule {
    boolean validate(MavenProject mvnProject, MavenSession mvnSession);
}

package com.wso2.build.rules.version;

import com.wso2.build.interfaces.rules.ProjectRule;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.component.annotations.Component;
import java.util.StringTokenizer;


/**
 * Created by uvindra on 2/1/14.
 */


@Component( role = ProjectRule.class, hint = "version" )
public class VersionRule implements ProjectRule {

    @Override
    public boolean validate(MavenProject mvnProject, MavenSession mvnSession, Settings settings) {
        String version = mvnProject.getVersion();

        StringTokenizer tokenizer = new StringTokenizer(version, ".");

        System.out.println(mvnProject.getArtifactId());
        System.out.println(version);

        if (3 != tokenizer.countTokens()) {
            return false;
        }

        return true;
    }
}

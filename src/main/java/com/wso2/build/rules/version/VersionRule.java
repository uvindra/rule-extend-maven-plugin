package com.wso2.build.rules.version;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import java.util.StringTokenizer;


/**
 * Created by uvindra on 2/1/14.
 */


/**
 * @goal version
 */
public class VersionRule extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    @Override
    public void execute() throws MojoExecutionException {
        String version = mavenProject.getVersion();

        StringTokenizer tokenizer = new StringTokenizer(version, ".");

        if (3 != tokenizer.countTokens()) {
            throw new MojoExecutionException("version format is invalid");
        }
    }
}

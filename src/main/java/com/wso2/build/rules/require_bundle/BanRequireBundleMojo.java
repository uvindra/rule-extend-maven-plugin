package com.wso2.build.rules.require_bundle;

import com.wso2.build.utils.Utility;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * Created by uvindra on 2/19/14.
 */

/**
 * @goal require_bundle_ban
 */
public class BanRequireBundleMojo extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    private static final String requireBundleTag = "Require-Bundle";

    @Override
    public void execute() throws MojoExecutionException {
        if (true == Utility.isBundlePluginInstructionExist(mavenProject, requireBundleTag)) {
            throw new MojoExecutionException("Require-Bundle header has been specified");
        }
    }

}

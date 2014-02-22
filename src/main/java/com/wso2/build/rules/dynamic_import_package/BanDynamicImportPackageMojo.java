package com.wso2.build.rules.dynamic_import_package;

import com.wso2.build.utils.Utility;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;


/**
 * Created by uvindra on 2/19/14.
 */

/**
 * @goal dynamic_import_package_ban
 */
public class BanDynamicImportPackageMojo extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    private static final String dynamicImportPackageTag = "DynamicImport-Package";

    @Override
    public void execute() throws MojoExecutionException {
        if (true == Utility.isBundlePluginInstructionExist(mavenProject, dynamicImportPackageTag)) {
            throw new MojoExecutionException("DynamicImportPackage header specified");
        }
    }

}

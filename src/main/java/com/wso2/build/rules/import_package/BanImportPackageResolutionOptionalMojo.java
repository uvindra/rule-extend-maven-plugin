package com.wso2.build.rules.import_package;

import com.wso2.build.utils.Utility;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import java.util.List;

/**
 * Created by uvindra on 2/22/14.
 */

/**
 * @goal import_package_ban_resolution_optional
 */

public class BanImportPackageResolutionOptionalMojo extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    private static final String resolutionOptionalAllValue = "*;resolution:=optional";
    private static final String importPackageTag = "Import-Package";

    @Override
    public void execute() throws MojoExecutionException {
        List<String> importPackageValues = Utility.getBundlePluginInstructionValues(mavenProject, importPackageTag);

        for (String importPackageValue : importPackageValues) {

            if (true == importPackageValue.contains(",")) {
                String[] splitValue = importPackageValue.split(",");

                for (String value : splitValue) {
                    validateResolutionOptional(value);
                }
            }
            else {
                validateResolutionOptional(importPackageValue);
            }
        }
    }


    private void validateResolutionOptional(String importPackageEntry) throws MojoExecutionException {
        if (true == importPackageEntry.contains(resolutionOptionalAllValue)) {
            throw new MojoExecutionException("*;resolution:=optional has been specified in Import-Package");
        }
    }
}

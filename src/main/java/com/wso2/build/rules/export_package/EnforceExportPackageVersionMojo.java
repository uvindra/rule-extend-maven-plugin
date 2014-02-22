package com.wso2.build.rules.export_package;

import com.wso2.build.utils.Utility;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import java.util.List;

/**
 * Created by uvindra on 2/19/14.
 */

/**
 * @goal export_package_enforce_version
 */
public class EnforceExportPackageVersionMojo extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    private static final String exportPackageTag = "Export-Package";
    private static final String versionValue = "version=";
    private static final String exclusionValue = "!";
    private static final String resolutionValue = "resolution";

    @Override
    public void execute() throws MojoExecutionException {
        List<String> exportPackageValues = Utility.getBundlePluginInstructionValues(mavenProject, exportPackageTag);

        for (String exportPackageValue : exportPackageValues) {

            if (true == exportPackageValue.contains(",")) {
                String[] splitValue = exportPackageValue.split(",");

                for (String value : splitValue) {
                    validateVersion(value);
                }
            }
            else {
                validateVersion(exportPackageValue);
            }
        }
    }


    private void validateVersion(String exportPackageEntry) throws MojoExecutionException{
        if (true == exportPackageEntry.contains(exclusionValue)) {
            return;
        }

        if (true == exportPackageEntry.contains(resolutionValue)) {
            return;
        }

        if (true != exportPackageEntry.contains(versionValue)) {
            throw new MojoExecutionException("Export-Package specified without version");
        }

    }
}

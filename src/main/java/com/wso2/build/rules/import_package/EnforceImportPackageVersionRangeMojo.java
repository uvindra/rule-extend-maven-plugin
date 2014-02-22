package com.wso2.build.rules.import_package;

import com.wso2.build.utils.Utility;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import java.util.List;

/**
 * Created by uvindra on 2/19/14.
 */


/**
 * @goal import_package_enforce_version_range
 */
public class EnforceImportPackageVersionRangeMojo extends AbstractMojo {

    /**
     * The mavenProject currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject = null;

    private static final String importPackageTag = "Import-Package";
    private static final String versionBeginValue = "version=\"[";
    private static final String versionEndValue = ")\"";
    private static final String exclusionValue = "!";
    private static final String resolutionValue = "resolution";

    private boolean isFoundVersion = false;

    @Override
    public void execute() throws MojoExecutionException {
        List<String> importPackageValues = Utility.getBundlePluginInstructionValues(mavenProject, importPackageTag);

        for (String importPackageValue : importPackageValues) {

            if (true == importPackageValue.contains(",")) {
                String[] splitValue = importPackageValue.split(",");

                for (String value : splitValue) {
                    validateVersion(value);
                }
            }
            else {
                validateVersion(importPackageValue);
            }
        }
    }


    private void  validateVersion(String importPackageEntry) throws MojoExecutionException {
        // Start of version entry found previously, this can only happen if there
        // are multiple Import-Package entries specified which would lead to this
        // function being called in a loop
        if (true == isFoundVersion) {

            // Reset the state of the flag that signifies that version was found
            isFoundVersion = false;

            // Can assume that this is the remainder of the version entry that is being
            // processed due to tokenization using the ","
            if (true == importPackageEntry.contains(versionEndValue)) {
                return; // No need to validate because this is a partial entry
            }
        }

        if (true == importPackageEntry.contains(exclusionValue)) {
            return;
        }

        if (true == importPackageEntry.contains(resolutionValue)) {
            return; // Since the resolution option is set no need to check version
        }

        if (true != importPackageEntry.contains(versionBeginValue)) {
            throw new MojoExecutionException("version range not specified in Import-Package");
        }
        else {
            isFoundVersion = true;
        }
    }
}

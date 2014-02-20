package com.wso2.build.rules.import_package;

import com.wso2.build.interfaces.rules.ProjectRule;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.util.xml.Xpp3Dom;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by uvindra on 2/19/14.
 */
@Component( role = ProjectRule.class, hint = "import" )
public class ImportPackageRule implements ProjectRule {

    private static final String mvnBundlePluginName = "maven-bundle-plugin";
    private static final String instructionsTag = "instructions";
    private static final String importPackageTag = "Import-Package";
    private static final String versionBeginValue = "version=\"[";
    private static final String versionEndValue = ")\"";
    private static final String exclusionValue = "!";
    private static final String resolutionValue = "resolution";
    private static final String resolutionOptionalAllValue = "*;resolution:=optional";

    private boolean isFoundVersion = false;

    @Override
    public boolean validate(MavenProject mvnProject, MavenSession mvnSession, Settings settings) {
        List<Plugin> plugins = mvnProject.getBuildPlugins();

        for (Plugin plugin : plugins) {
            if (true == plugin.getArtifactId().equals(mvnBundlePluginName)) {
                Xpp3Dom config = (Xpp3Dom) plugin.getConfiguration();

                List<String> importPackageValues = getImportPackageValues(config);

                for (String importPackageValue : importPackageValues) {

                    if (true == importPackageValue.contains(",")) {
                        String[] splitValue = importPackageValue.split(",");

                        for (String value : splitValue) {
                            if (false == validateVersion(value)) {
                                return false;
                            }
                        }
                    }
                    else {
                        if (false == validateVersion(importPackageValue)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }


    private List<String> getImportPackageValues(Xpp3Dom config) {
        Xpp3Dom[] instructions = config.getChildren(instructionsTag);

        List<String> importPackageValues = new LinkedList<String>();

        for (Xpp3Dom instruction : instructions) {
            Xpp3Dom[] exportPackages = instruction.getChildren(importPackageTag);

            for (Xpp3Dom exportPackage : exportPackages) {
                String exportPackageValue = exportPackage.getValue();

                importPackageValues.add(exportPackageValue);
            }
        }

        return importPackageValues;
    }

    private boolean validateVersion(String importPackageEntry) {
        // Start of version entry found previously, this can only happen if there
        // are multiple Import-Package entries specified which would lead to this
        // function being called in a loop
        if (true == isFoundVersion) {

            // Reset the state of the flag that signifies that version was found
            isFoundVersion = false;

            // Can assume that this is the remainder of the version entry that is being
            // processed due to tokenization using the ","
            if (true == importPackageEntry.contains(versionEndValue)) {
                return true; // No need to validate because this is a partial entry
            }
        }

        if (true == importPackageEntry.contains(exclusionValue)) {
            return true;
        }

        if (true == importPackageEntry.contains(resolutionValue)) {
            if (true == importPackageEntry.contains(resolutionOptionalAllValue)) {
                System.out.println("*;resolution:=optional has been specified");
                return false;
            }

            return true;
        }

        if (true != importPackageEntry.contains(versionBeginValue)) {
            System.out.println("version=\"[ not specified");
            return false;
        }
        else {
            isFoundVersion = true;
            return true;
        }
    }
}

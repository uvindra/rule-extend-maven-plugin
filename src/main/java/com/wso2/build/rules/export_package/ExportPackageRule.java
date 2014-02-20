package com.wso2.build.rules.export_package;

import com.wso2.build.interfaces.rules.ProjectRule;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Plugin;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.util.xml.Xpp3Dom;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by uvindra on 2/19/14.
 */
@Component( role = ProjectRule.class, hint = "export" )
public class ExportPackageRule implements ProjectRule {

    private static final String mvnBundlePluginName = "maven-bundle-plugin";
    private static final String instructionsTag = "instructions";
    private static final String exportPackageTag = "Export-Package";
    private static final String versionValue = "version=";
    private static final String exclusionValue = "!";
    private static final String resolutionValue = "resolution";

    @Override
    public boolean validate(MavenProject mvnProject, MavenSession mvnSession, Settings settings) {
        List<Plugin> plugins = mvnProject.getBuildPlugins();

        for (Plugin plugin : plugins) {
            if (true == plugin.getArtifactId().equals(mvnBundlePluginName)) {

                Xpp3Dom config = (Xpp3Dom) plugin.getConfiguration();

                List<String> exportPackageValues = getExportPackageValues(config);

                for (String exportPackageValue : exportPackageValues) {

                    if (true == exportPackageValue.contains(",")) {

                        String[] splitValue = exportPackageValue.split(",");

                        for (String value : splitValue) {
                            if (false == validateVersion(value)) {
                                return false;
                            }
                        }
                    }
                    else {
                        if (false == validateVersion(exportPackageValue)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private List<String> getExportPackageValues(Xpp3Dom config) {
        Xpp3Dom[] instructions = config.getChildren(instructionsTag);

        List<String> exportPackageValues = new LinkedList<String>();

        for (Xpp3Dom instruction : instructions) {
            Xpp3Dom[] exportPackages = instruction.getChildren(exportPackageTag);

            for (Xpp3Dom exportPackage : exportPackages) {
                exportPackageValues.add(exportPackage.getValue());
            }
        }

        return exportPackageValues;
    }

    private boolean validateVersion(String exportPackageEntry) {
        if (true == exportPackageEntry.contains(exclusionValue)) {
            return true;
        }

        if (true == exportPackageEntry.contains(resolutionValue)) {
            return true;
        }

        if (true != exportPackageEntry.contains(versionValue)) {
            System.out.println("version=\"[ not specified");
            return false;
        }

        return true;
    }
}

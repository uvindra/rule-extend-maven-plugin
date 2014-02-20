package com.wso2.build.rules.dynamic_import_package;

import com.wso2.build.interfaces.rules.ProjectRule;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import java.util.List;


/**
 * Created by uvindra on 2/19/14.
 */
@Component(role = ProjectRule.class, hint = "dynamic_import_package")
public class DynamicImportPackageRule implements ProjectRule {

    private static final String mvnBundlePluginName = "maven-bundle-plugin";
    private static final String instructionsTag = "instructions";
    private static final String dynamicImportPackageTag = "DynamicImport-Package";

    @Override
    public boolean validate(MavenProject mvnProject, MavenSession mvnSession, Settings settings) {
        List<Plugin> plugins = mvnProject.getBuildPlugins();

        for (Plugin plugin : plugins) {
            if (true == plugin.getArtifactId().equals(mvnBundlePluginName)) {

                Xpp3Dom config = (Xpp3Dom) plugin.getConfiguration();

                Xpp3Dom[] instructions = config.getChildren(instructionsTag);

                for (Xpp3Dom instruction : instructions) {
                    Xpp3Dom[] dynamicImportPackages = instruction.getChildren(dynamicImportPackageTag);

                    if (0 < dynamicImportPackages.length) {
                        System.out.println("DynamicImportPackage header specified");
                        return false;
                    }
                }
            }
        }

        return true;
    }

}

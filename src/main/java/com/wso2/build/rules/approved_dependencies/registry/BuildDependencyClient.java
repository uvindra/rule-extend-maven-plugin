package com.wso2.build.rules.approved_dependencies.registry;

import com.wso2.build.rules.approved_dependencies.beans.Artifact;
import com.wso2.build.rules.approved_dependencies.beans.Parameters;
import com.wso2.build.stub.*;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.wso2.carbon.base.ServerConfiguration;
import org.wso2.carbon.utils.CarbonUtils;

import java.io.File;
import java.rmi.RemoteException;
import java.util.*;


/**
 * Created by uvindra on 2/16/14.
 */
public class BuildDependencyClient {

    private ConfigurationContext configContext = null;
    private ModuleStub moduleStub = null;
    private ManageGenericArtifactServiceStub artifactServiceStub = null;
    private RelationAdminServiceStub adminStub = null;
    private CustomLifecyclesChecklistAdminServiceStub lifecycleStub = null;

    private Map<String, Artifact> artifactMap = new HashMap<String, Artifact>();
    private HashMap<String, LinkedList<String>> artifactUsageMap = new HashMap<String, LinkedList<String>>();
    private boolean isLoaded = false;

    private static final String groupStartTag = "<group>";
    private static final String groupEndTag = "</group>";
    private static final String nameStartTag = "<name>";
    private static final String nameEndTag = "</name>";
    private static final String versionStartTag = "<version>";
    private static final String versionEndTag = "</version>";
    private static final String artifactSearchString = "artifacts";

    private static final String moduleArtifactPath = "/_system/governance/trunk/modules/";
    private static final String artifactRootPath = "/_system/governance";

    private static final String axis2Conf = ServerConfiguration.getInstance().getFirstProperty("Axis2Config.clientAxis2XmlLocation");


    public void loadDependecies(Parameters parameters) {
        try {
            String axis2Repo = parameters.getGregHome() + File.separator +"repository" + File.separator +
                    "deployment" + File.separator + "client";

            System.setProperty("javax.net.ssl.trustStore", parameters.getGregHome() + File.separator + "repository" +
                    File.separator + "resources" + File.separator + "security" + File.separator +
                    "wso2carbon.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", parameters.getTrustStorePassword());
            System.setProperty("javax.net.ssl.trustStoreType", "JKS");
            System.setProperty("carbon.repo.write.mode", "true");

            configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(
                    axis2Repo, axis2Conf);

            moduleStub = new ModuleStub(configContext, parameters.getGregModuleEndpoint(), false);

            adminStub = new RelationAdminServiceStub(configContext, parameters.getGregDependencyEndpoint(), false);

            artifactServiceStub = new ManageGenericArtifactServiceStub(configContext, parameters.getGregArtifactEndpoint(), false);

            lifecycleStub = new CustomLifecyclesChecklistAdminServiceStub(configContext, parameters.getGregLifecycleEndpoint(), false);

            CarbonUtils.setBasicAccessSecurityHeaders(parameters.getGregUsername(), parameters.getGregPassword(), moduleStub._getServiceClient());
            CarbonUtils.setBasicAccessSecurityHeaders(parameters.getGregUsername(), parameters.getGregPassword(), adminStub._getServiceClient());
            CarbonUtils.setBasicAccessSecurityHeaders(parameters.getGregUsername(), parameters.getGregPassword(), artifactServiceStub._getServiceClient());
            CarbonUtils.setBasicAccessSecurityHeaders(parameters.getGregUsername(), parameters.getGregPassword(), lifecycleStub._getServiceClient());
        }
        catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        String[] artifactIds = getArtifactIDs();
        long endTime = System.currentTimeMillis();

        System.out.println("getArtifactIds time : " + (endTime - startTime));

        startTime = System.currentTimeMillis();

        for (String artifactId : artifactIds) {
            String module = getModule(artifactId);

            int tagStart = module.indexOf(nameStartTag);
            int tagEnd = module.indexOf(nameEndTag);

            String name = module.substring(tagStart + nameStartTag.length(), tagEnd);

            tagStart = module.indexOf(versionStartTag);
            tagEnd = module.indexOf(versionEndTag);

            String version = module.substring(tagStart + versionStartTag.length(), tagEnd);

            loadModuleDependencyArtifacts(name, version);
        }

        endTime = System.currentTimeMillis();
        System.out.println("Final time : " + (endTime - startTime));

        isLoaded = true;
    }


    public int getArtifactsSize() { return artifactMap.size(); }

    public Artifact searchArtifact(String artifactName) { return  artifactMap.get(artifactName); }

    public List<String> getArtifactUsage(String artifactName) { return artifactUsageMap.get(artifactName); }

        public boolean isLoaded() { return isLoaded; }

    public Iterator getArtifactIterator() { return artifactMap.entrySet().iterator(); }


    private String[] getArtifactIDs() {
        ModuleStub.GetModuleArtifactIDs artifactIDs = new ModuleStub.GetModuleArtifactIDs();
        ModuleStub.GetModuleArtifactIDsResponse getModuleArtifactIDsResponse = null;

        String[] artifactIdStrings = new String[0];

        try {
            getModuleArtifactIDsResponse = moduleStub.getModuleArtifactIDs(artifactIDs);

            artifactIdStrings = getModuleArtifactIDsResponse.get_return();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
        catch (GetModuleArtifactIDsServiceGovernanceException e) {
            e.printStackTrace();
        }

        return artifactIdStrings;
    }

    private String getModule(String artifactID){
        ModuleStub.GetModule getModule = new ModuleStub.GetModule();
        getModule.setArtifactId(artifactID);

        String module = "";

        try {
            ModuleStub.GetModuleResponse getModuleResponse = moduleStub.getModule(getModule);

            module = getModuleResponse.get_return();


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (GetModuleServiceGovernanceException e) {
            e.printStackTrace();
        }

        return module;
    }


    private void loadModuleDependencyArtifacts(String moduleName, String moduleVersion){
        com.wso2.build.stub.RelationAdminServiceStub.GetAssociationTree getAssociationTree = new com.wso2.build.stub.RelationAdminServiceStub.GetAssociationTree();

        // example path would be : "/_system/governance/trunk/modules/org.wso2.carbon.registry.info/4.2.0"
        getAssociationTree.setPath(moduleArtifactPath + moduleName + "/" + moduleVersion);
        getAssociationTree.setType("depends");

        RelationAdminServiceStub.AssociationTreeBean tree = null;

        try {
            RelationAdminServiceStub.GetAssociationTreeResponse getAssociationTreeResponse = adminStub.getAssociationTree(getAssociationTree);

            tree = getAssociationTreeResponse.get_return();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (RelationAdminServiceRegistryExceptionException e) {
            e.printStackTrace();
        }

        if (true != tree.isTreeCacheSpecified()) {
            return; // No tree cache exists
        }

        String[] dependencyTree = tree.getTreeCache();

        List<String> artifactPaths = new LinkedList<String>();

        for (String dependency : dependencyTree) {
            // Only load dependencies that are artifacts, this needs to be done
            // because modules can have other modules as dependencies
            if (true == dependency.contains(artifactSearchString)) {
                artifactPaths.add(dependency.substring(artifactRootPath.length()));
            }
        }

        for (String artifactPath : artifactPaths) {
            Artifact artifact = getArtifact(artifactPath);

            artifact.setState(getLifecycleState(artifactPath));

            String key = artifact.getGroupId() + ":" + artifact.getArtifactId() + ":" + artifact.getVersion();
            artifactMap.put(key, artifact);

            if (true == artifactUsageMap.containsKey(key)) {
                LinkedList<String> usedModules = artifactUsageMap.get(key);

                usedModules.add(moduleName + moduleVersion);
            }
            else {
                LinkedList<String> usedModules = new LinkedList<String>();

                usedModules.add(moduleName + ":" + moduleVersion);

                artifactUsageMap.put(key, usedModules);
            }
        }
    }


    private Artifact getArtifact(String artifactPath) {
        ManageGenericArtifactServiceStub.GetArtifactContent getArtifactContent = new ManageGenericArtifactServiceStub.GetArtifactContent();

        // example path would be : "trunk/artifacts/org.apache.axis2.wso2/axis2/1.6.1.wso2v10"
        getArtifactContent.setPath(artifactPath);

        String artifactData = "";

        try {
            ManageGenericArtifactServiceStub.GetArtifactContentResponse getArtifactContentResponse = artifactServiceStub.getArtifactContent(getArtifactContent);

            artifactData = getArtifactContentResponse.get_return();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ManageGenericArtifactServiceRegistryExceptionException e) {
            e.printStackTrace();
        }

        Artifact artifact = new Artifact();

        int tagStart = artifactData.indexOf(nameStartTag);
        int tagEnd = artifactData.indexOf(nameEndTag);

        artifact.setArtifactId(artifactData.substring(tagStart + nameStartTag.length(), tagEnd));

        tagStart = artifactData.indexOf(versionStartTag);
        tagEnd = artifactData.indexOf(versionEndTag);

        artifact.setVersion(artifactData.substring(tagStart + versionStartTag.length(), tagEnd));

        tagStart = artifactData.indexOf(groupStartTag);
        tagEnd = artifactData.indexOf(groupEndTag);

        artifact.setGroupId(artifactData.substring(tagStart + groupStartTag.length(), tagEnd));

        return artifact;
    }


    private String getLifecycleState(String artifactPath) {

        CustomLifecyclesChecklistAdminServiceStub.GetLifecycleBean getLifecycleBean = new CustomLifecyclesChecklistAdminServiceStub.GetLifecycleBean();

        // example path would be : "/_system/governance/trunk/artifacts/org.apache.axis2.wso2/axis2/1.6.1.wso2v10"
        String fullPath = artifactRootPath + artifactPath;

        getLifecycleBean.setPath(fullPath);

        CustomLifecyclesChecklistAdminServiceStub.LifecycleBean lifecycleBean = null;

        try {
            CustomLifecyclesChecklistAdminServiceStub.GetLifecycleBeanResponse getLifecycleBeanResponse = lifecycleStub.getLifecycleBean(getLifecycleBean);

            lifecycleBean = getLifecycleBeanResponse.get_return();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (CustomLifecyclesChecklistAdminServiceExceptionException e) {
            e.printStackTrace();
        }

        CustomLifecyclesChecklistAdminServiceStub.Property[] properties = lifecycleBean.getLifecycleProperties();

        assert(1 == properties.length);  // Assumption that there will be only one property

        String[] values = properties[0].getValues();

        assert(1 == values.length); //Assumption that there will be only a single value for the property

        return values[0];
    }
}

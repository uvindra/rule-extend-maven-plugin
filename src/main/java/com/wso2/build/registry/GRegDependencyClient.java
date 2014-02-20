package com.wso2.build.registry;

import com.wso2.build.beans.Parameters;
import com.wso2.build.stub.*;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.wso2.carbon.base.ServerConfiguration;
import org.wso2.carbon.utils.CarbonUtils;

import java.io.File;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by uvindra on 2/16/14.
 */
public class GRegDependencyClient {

    private ConfigurationContext configContext = null;
    private ModuleStub moduleStub = null;
    private ManageGenericArtifactServiceStub artifactServiceStub = null;
    private RelationAdminServiceStub adminStub = null;
    private CustomLifecyclesChecklistAdminServiceStub lifecycleStub = null;

    private String GREG_HOME;
    private String axis2Repo;
    private String username;
    private String password;
    private String moduleEndpointURL;
    private String dependencyEndpointURL;
    private String artifactEndpointURL;
    private String lifecycleEndpointURL;

    private static final String nameStartTag = "<name>";
    private static final String nameEndTag = "</name>";
    private static final String versionStartTag = "<version>";
    private static final String versionEndTag = "</version>";
    private static final String artifactSearchString = "artifacts";

    private static final String moduleArtifactPath = "/_system/governance/trunk/modules/";
    private static final String artifactRootPath = "/_system/governance";

    private static final String axis2Conf = ServerConfiguration.getInstance().getFirstProperty("Axis2Config.clientAxis2XmlLocation");
    
    public GRegDependencyClient(Parameters parameters) {
        GREG_HOME = parameters.getGregHome();
        axis2Repo = GREG_HOME + File.separator +"repository" + File.separator +
                "deployment" + File.separator + "client";

        username = parameters.getGregUsername();
        password = parameters.getGregPassword();
        moduleEndpointURL = parameters.getGregModuleEndpoint();
        dependencyEndpointURL = parameters.getGregDependencyEndpoint();
        artifactEndpointURL = parameters.getGregArtifactEndpoint();
        lifecycleEndpointURL = parameters.getGregLifecycleEndpoint();

        System.setProperty("javax.net.ssl.trustStore", GREG_HOME + File.separator + "repository" +
                File.separator + "resources" + File.separator + "security" + File.separator +
                "wso2carbon.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", parameters.getTrustStorePassword());
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("carbon.repo.write.mode", "true");
    }
    
    public boolean checkDependecies() {
        try {
            configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(
                    axis2Repo, axis2Conf);

            moduleStub = new ModuleStub(configContext, moduleEndpointURL, false);

            adminStub = new RelationAdminServiceStub(configContext, dependencyEndpointURL, false);

            artifactServiceStub = new ManageGenericArtifactServiceStub(configContext, artifactEndpointURL, false);

            lifecycleStub = new CustomLifecyclesChecklistAdminServiceStub(configContext, lifecycleEndpointURL, false);

            CarbonUtils.setBasicAccessSecurityHeaders(username, password, moduleStub._getServiceClient());
            CarbonUtils.setBasicAccessSecurityHeaders(username, password, adminStub._getServiceClient());
            CarbonUtils.setBasicAccessSecurityHeaders(username, password, artifactServiceStub._getServiceClient());
            CarbonUtils.setBasicAccessSecurityHeaders(username, password, lifecycleStub._getServiceClient());
        }
        catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }

        String[] artifactIds = getArtifactIDs();

        for (int i = 0; i< artifactIds.length; ++i) {
            String module = getModule(artifactIds[i]);

            int tagStart = module.indexOf(nameStartTag);
            int tagEnd = module.indexOf(nameEndTag);

            String name = module.substring(tagStart + nameStartTag.length(), tagEnd);

            tagStart = module.indexOf(versionStartTag);
            tagEnd = module.indexOf(versionEndTag);

            String version = module.substring(tagStart + versionStartTag.length(), tagEnd);

            //System.out.println("*********************");
            //System.out.println("module : " + module);
            //System.out.println("*********************");
            //String[] dependencies = getModuleDependencies(artifactIds[i]);


            getModuleDependencies(name, version);

        }

        getLifecycles();

        return true;
    } 

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

    private void getModuleDependencies(){
        com.wso2.build.stub.RelationAdminServiceStub.GetAssociationTree getAssociationTree = new com.wso2.build.stub.RelationAdminServiceStub.GetAssociationTree();

        getAssociationTree.setPath("/_system/governance/trunk/modules/org.wso2.carbon.registry.info/4.2.0");
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

        String[] dependencyTree = tree.getTreeCache();

        System.out.println("dependencyTree length : " + dependencyTree.length);

        for (int i = 0; i < dependencyTree.length; ++i) {
            System.out.println("dependencyTree " + i + ": " + dependencyTree[i]);
        }
    }


    private void getModuleDependencies(String moduleName, String moduleVersion){
        com.wso2.build.stub.RelationAdminServiceStub.GetAssociationTree getAssociationTree = new com.wso2.build.stub.RelationAdminServiceStub.GetAssociationTree();

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
            return; // No tree chache exists
        }

        String[] dependencyTree = tree.getTreeCache();

        List<String> artifactPaths = new LinkedList<String>();

        for (String dependency : dependencyTree) {

            if (true == dependency.contains(artifactSearchString)) {
                //System.out.println("Found artifact : " + dependencyTree[i]);

                artifactPaths.add(dependency.substring(artifactRootPath.length()));
            }
        }

        //System.out.println("=============================");
        //System.out.println("Artifacts");
        //System.out.println("=============================");



        for (String artifactPath : artifactPaths) {
            //System.out.println(artifactPaths.get(i));
            //System.out.println("**************************");
            getArtifacts(artifactPath);

            getLifecycleState(artifactPath);
        }
    }

    private void getArtifacts() {
        ManageGenericArtifactServiceStub.GetArtifactContent getArtifactContent = new ManageGenericArtifactServiceStub.GetArtifactContent();

        getArtifactContent.setPath("trunk/artifacts/org.apache.axis2.wso2/axis2/1.6.1.wso2v10");

        String artifact = "";

        try {
            ManageGenericArtifactServiceStub.GetArtifactContentResponse getArtifactContentResponse = artifactServiceStub.getArtifactContent(getArtifactContent);

            artifact = getArtifactContentResponse.get_return();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ManageGenericArtifactServiceRegistryExceptionException e) {
            e.printStackTrace();
        }

        System.out.println("Artifact : " + artifact);
    }


    private void getArtifacts(String artifactPath) {
        ManageGenericArtifactServiceStub.GetArtifactContent getArtifactContent = new ManageGenericArtifactServiceStub.GetArtifactContent();

        getArtifactContent.setPath(artifactPath);

        String artifact = "";

        try {
            ManageGenericArtifactServiceStub.GetArtifactContentResponse getArtifactContentResponse = artifactServiceStub.getArtifactContent(getArtifactContent);

            artifact = getArtifactContentResponse.get_return();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ManageGenericArtifactServiceRegistryExceptionException e) {
            e.printStackTrace();
        }

        System.out.println("Artifact : " + artifact);
    }

    private void getLifecycles() {
        System.out.println("****************************************************************");
        System.out.println("****************************************************************");
        CustomLifecyclesChecklistAdminServiceStub.GetLifecycleBean getLifecycleBean = new CustomLifecyclesChecklistAdminServiceStub.GetLifecycleBean();
        getLifecycleBean.setPath("/_system/governance/trunk/artifacts/org.apache.axis2.wso2/axis2/1.6.1.wso2v10");

        CustomLifecyclesChecklistAdminServiceStub.LifecycleBean lifecycleBean = null;

        try {
            CustomLifecyclesChecklistAdminServiceStub.GetLifecycleBeanResponse getLifecycleBeanResponse = lifecycleStub.getLifecycleBean(getLifecycleBean);

            lifecycleBean = getLifecycleBeanResponse.get_return();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (CustomLifecyclesChecklistAdminServiceExceptionException e) {
            e.printStackTrace();
        }

        //System.out.println("lifecycleBean : " + lifecycleBean.getLifecycleProperties());

        CustomLifecyclesChecklistAdminServiceStub.Property[] properties = lifecycleBean.getLifecycleProperties();

        for (CustomLifecyclesChecklistAdminServiceStub.Property property : properties) {
            String[] values = property.getValues();

            for (String value : values) {
                System.out.println("Value : " + value);
            }
        }
    }

    private String getLifecycleState(String artifactPath) {
        System.out.println("****************************************************************");
        System.out.println("****************************************************************");
        CustomLifecyclesChecklistAdminServiceStub.GetLifecycleBean getLifecycleBean = new CustomLifecyclesChecklistAdminServiceStub.GetLifecycleBean();

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

        System.out.println("artifactPath : " + fullPath);

        CustomLifecyclesChecklistAdminServiceStub.Property[] properties = lifecycleBean.getLifecycleProperties();

        assert(1 == properties.length);  // Assumption that there will be only one property

        String[] values = properties[0].getValues();

        assert(1 == values.length); //Assumption that there will be only a single value for the property

        return values[0];
    }
}


/**
 * ModuleCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.wso2.build.stub;

    /**
     *  ModuleCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ModuleCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ModuleCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ModuleCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getModule method
            * override this method for handling normal response from getModule operation
            */
           public void receiveResultgetModule(
                    com.wso2.build.stub.ModuleStub.GetModuleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getModule operation
           */
            public void receiveErrorgetModule(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getModuleDependencies method
            * override this method for handling normal response from getModuleDependencies operation
            */
           public void receiveResultgetModuleDependencies(
                    com.wso2.build.stub.ModuleStub.GetModuleDependenciesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getModuleDependencies operation
           */
            public void receiveErrorgetModuleDependencies(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for getModuleArtifactIDs method
            * override this method for handling normal response from getModuleArtifactIDs operation
            */
           public void receiveResultgetModuleArtifactIDs(
                    com.wso2.build.stub.ModuleStub.GetModuleArtifactIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getModuleArtifactIDs operation
           */
            public void receiveErrorgetModuleArtifactIDs(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for addModule method
            * override this method for handling normal response from addModule operation
            */
           public void receiveResultaddModule(
                    com.wso2.build.stub.ModuleStub.AddModuleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addModule operation
           */
            public void receiveErroraddModule(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for updateModule method
            * override this method for handling normal response from updateModule operation
            */
           public void receiveResultupdateModule(
                    com.wso2.build.stub.ModuleStub.UpdateModuleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateModule operation
           */
            public void receiveErrorupdateModule(Exception e) {
            }

           /**
            * auto generated Axis2 call back method for deleteModule method
            * override this method for handling normal response from deleteModule operation
            */
           public void receiveResultdeleteModule(
                    com.wso2.build.stub.ModuleStub.DeleteModuleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteModule operation
           */
            public void receiveErrordeleteModule(Exception e) {
            }
                


    }
    
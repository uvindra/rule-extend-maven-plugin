
/**
 * ManageGenericArtifactServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.wso2.build.stub;

    /**
     *  ManageGenericArtifactServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ManageGenericArtifactServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ManageGenericArtifactServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ManageGenericArtifactServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for setArtifactUIConfiguration method
            * override this method for handling normal response from setArtifactUIConfiguration operation
            */
           public void receiveResultsetArtifactUIConfiguration(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.SetArtifactUIConfigurationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setArtifactUIConfiguration operation
           */
            public void receiveErrorsetArtifactUIConfiguration(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getArtifactViewRequestParams method
            * override this method for handling normal response from getArtifactViewRequestParams operation
            */
           public void receiveResultgetArtifactViewRequestParams(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.GetArtifactViewRequestParamsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getArtifactViewRequestParams operation
           */
            public void receiveErrorgetArtifactViewRequestParams(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listContentArtifacts method
            * override this method for handling normal response from listContentArtifacts operation
            */
           public void receiveResultlistContentArtifacts(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.ListContentArtifactsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listContentArtifacts operation
           */
            public void receiveErrorlistContentArtifacts(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getStoragePath method
            * override this method for handling normal response from getStoragePath operation
            */
           public void receiveResultgetStoragePath(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.GetStoragePathResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getStoragePath operation
           */
            public void receiveErrorgetStoragePath(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for canChange method
            * override this method for handling normal response from canChange operation
            */
           public void receiveResultcanChange(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.CanChangeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from canChange operation
           */
            public void receiveErrorcanChange(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getArtifactContent method
            * override this method for handling normal response from getArtifactContent operation
            */
           public void receiveResultgetArtifactContent(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.GetArtifactContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getArtifactContent operation
           */
            public void receiveErrorgetArtifactContent(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAllLifeCycleState method
            * override this method for handling normal response from getAllLifeCycleState operation
            */
           public void receiveResultgetAllLifeCycleState(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.GetAllLifeCycleStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllLifeCycleState operation
           */
            public void receiveErrorgetAllLifeCycleState(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listContentArtifactsByLC method
            * override this method for handling normal response from listContentArtifactsByLC operation
            */
           public void receiveResultlistContentArtifactsByLC(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.ListContentArtifactsByLCResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listContentArtifactsByLC operation
           */
            public void receiveErrorlistContentArtifactsByLC(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getArtifactUIConfiguration method
            * override this method for handling normal response from getArtifactUIConfiguration operation
            */
           public void receiveResultgetArtifactUIConfiguration(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.GetArtifactUIConfigurationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getArtifactUIConfiguration operation
           */
            public void receiveErrorgetArtifactUIConfiguration(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listArtifacts method
            * override this method for handling normal response from listArtifacts operation
            */
           public void receiveResultlistArtifacts(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.ListArtifactsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listArtifacts operation
           */
            public void receiveErrorlistArtifacts(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listArtifactsByLC method
            * override this method for handling normal response from listArtifactsByLC operation
            */
           public void receiveResultlistArtifactsByLC(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.ListArtifactsByLCResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listArtifactsByLC operation
           */
            public void receiveErrorlistArtifactsByLC(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addArtifact method
            * override this method for handling normal response from addArtifact operation
            */
           public void receiveResultaddArtifact(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.AddArtifactResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addArtifact operation
           */
            public void receiveErroraddArtifact(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listArtifactsByName method
            * override this method for handling normal response from listArtifactsByName operation
            */
           public void receiveResultlistArtifactsByName(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.ListArtifactsByNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listArtifactsByName operation
           */
            public void receiveErrorlistArtifactsByName(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for editArtifact method
            * override this method for handling normal response from editArtifact operation
            */
           public void receiveResulteditArtifact(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.EditArtifactResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editArtifact operation
           */
            public void receiveErroreditArtifact(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listContentArtifactsByName method
            * override this method for handling normal response from listContentArtifactsByName operation
            */
           public void receiveResultlistContentArtifactsByName(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.ListContentArtifactsByNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listContentArtifactsByName operation
           */
            public void receiveErrorlistContentArtifactsByName(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addRXTResource method
            * override this method for handling normal response from addRXTResource operation
            */
           public void receiveResultaddRXTResource(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.AddRXTResourceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addRXTResource operation
           */
            public void receiveErroraddRXTResource(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAvailableAspects method
            * override this method for handling normal response from getAvailableAspects operation
            */
           public void receiveResultgetAvailableAspects(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.GetAvailableAspectsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAvailableAspects operation
           */
            public void receiveErrorgetAvailableAspects(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRxtAbsPathFromRxtName method
            * override this method for handling normal response from getRxtAbsPathFromRxtName operation
            */
           public void receiveResultgetRxtAbsPathFromRxtName(
                    com.wso2.build.stub.ManageGenericArtifactServiceStub.GetRxtAbsPathFromRxtNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRxtAbsPathFromRxtName operation
           */
            public void receiveErrorgetRxtAbsPathFromRxtName(Exception e) {
            }
                


    }
    
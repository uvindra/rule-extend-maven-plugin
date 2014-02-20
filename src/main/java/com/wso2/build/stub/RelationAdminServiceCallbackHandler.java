
/**
 * RelationAdminServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.wso2.build.stub;

    /**
     *  RelationAdminServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class RelationAdminServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public RelationAdminServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public RelationAdminServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getAssociationTree method
            * override this method for handling normal response from getAssociationTree operation
            */
           public void receiveResultgetAssociationTree(
                    com.wso2.build.stub.RelationAdminServiceStub.GetAssociationTreeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAssociationTree operation
           */
            public void receiveErrorgetAssociationTree(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDependencies method
            * override this method for handling normal response from getDependencies operation
            */
           public void receiveResultgetDependencies(
                    com.wso2.build.stub.RelationAdminServiceStub.GetDependenciesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDependencies operation
           */
            public void receiveErrorgetDependencies(Exception e) {
            }
                
               // No methods generated for meps other than in-out
                


    }
    
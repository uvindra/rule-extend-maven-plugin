
/**
 * ManageGenericArtifactServiceRegistryExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class ManageGenericArtifactServiceRegistryExceptionException extends Exception{

    private static final long serialVersionUID = 1392695905023L;
    
    private com.wso2.build.stub.ManageGenericArtifactServiceStub.ManageGenericArtifactServiceRegistryException faultMessage;

    
        public ManageGenericArtifactServiceRegistryExceptionException() {
            super("ManageGenericArtifactServiceRegistryExceptionException");
        }

        public ManageGenericArtifactServiceRegistryExceptionException(String s) {
           super(s);
        }

        public ManageGenericArtifactServiceRegistryExceptionException(String s, Throwable ex) {
          super(s, ex);
        }

        public ManageGenericArtifactServiceRegistryExceptionException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ManageGenericArtifactServiceStub.ManageGenericArtifactServiceRegistryException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ManageGenericArtifactServiceStub.ManageGenericArtifactServiceRegistryException getFaultMessage(){
       return faultMessage;
    }
}
    
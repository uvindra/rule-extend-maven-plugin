
/**
 * ManageGenericArtifactServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class ManageGenericArtifactServiceExceptionException extends Exception{

    private static final long serialVersionUID = 1392695905031L;
    
    private com.wso2.build.stub.ManageGenericArtifactServiceStub.ManageGenericArtifactServiceException faultMessage;

    
        public ManageGenericArtifactServiceExceptionException() {
            super("ManageGenericArtifactServiceExceptionException");
        }

        public ManageGenericArtifactServiceExceptionException(String s) {
           super(s);
        }

        public ManageGenericArtifactServiceExceptionException(String s, Throwable ex) {
          super(s, ex);
        }

        public ManageGenericArtifactServiceExceptionException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ManageGenericArtifactServiceStub.ManageGenericArtifactServiceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ManageGenericArtifactServiceStub.ManageGenericArtifactServiceException getFaultMessage(){
       return faultMessage;
    }
}
    
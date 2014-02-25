
/**
 * GetModuleArtifactIDsServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class GetModuleArtifactIDsServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1392539976694L;

    private com.wso2.build.stub.ModuleStub.GetModuleArtifactIDsServiceGovernanceException faultMessage;


        public GetModuleArtifactIDsServiceGovernanceException() {
            super("GetModuleArtifactIDsServiceGovernanceException");
        }

        public GetModuleArtifactIDsServiceGovernanceException(String s) {
           super(s);
        }

        public GetModuleArtifactIDsServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public GetModuleArtifactIDsServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ModuleStub.GetModuleArtifactIDsServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ModuleStub.GetModuleArtifactIDsServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    
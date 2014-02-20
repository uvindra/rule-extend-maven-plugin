
/**
 * DeleteModuleServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class DeleteModuleServiceGovernanceException extends java.lang.Exception{

    private static final long serialVersionUID = 1392539976680L;
    
    private com.wso2.build.stub.ModuleStub.DeleteModuleServiceGovernanceException faultMessage;

    
        public DeleteModuleServiceGovernanceException() {
            super("DeleteModuleServiceGovernanceException");
        }

        public DeleteModuleServiceGovernanceException(java.lang.String s) {
           super(s);
        }

        public DeleteModuleServiceGovernanceException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public DeleteModuleServiceGovernanceException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ModuleStub.DeleteModuleServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ModuleStub.DeleteModuleServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    
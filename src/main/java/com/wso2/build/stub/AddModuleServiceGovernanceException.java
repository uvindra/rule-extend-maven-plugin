
/**
 * AddModuleServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class AddModuleServiceGovernanceException extends java.lang.Exception{

    private static final long serialVersionUID = 1392539976667L;
    
    private com.wso2.build.stub.ModuleStub.AddModuleServiceGovernanceException faultMessage;

    
        public AddModuleServiceGovernanceException() {
            super("AddModuleServiceGovernanceException");
        }

        public AddModuleServiceGovernanceException(java.lang.String s) {
           super(s);
        }

        public AddModuleServiceGovernanceException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public AddModuleServiceGovernanceException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ModuleStub.AddModuleServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ModuleStub.AddModuleServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    
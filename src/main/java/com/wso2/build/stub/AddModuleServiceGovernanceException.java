
/**
 * AddModuleServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class AddModuleServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1392539976667L;

    private com.wso2.build.stub.ModuleStub.AddModuleServiceGovernanceException faultMessage;


        public AddModuleServiceGovernanceException() {
            super("AddModuleServiceGovernanceException");
        }

        public AddModuleServiceGovernanceException(String s) {
           super(s);
        }

        public AddModuleServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public AddModuleServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ModuleStub.AddModuleServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ModuleStub.AddModuleServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    
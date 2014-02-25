
/**
 * UpdateModuleServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class UpdateModuleServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1392539976687L;

    private com.wso2.build.stub.ModuleStub.UpdateModuleServiceGovernanceException faultMessage;


        public UpdateModuleServiceGovernanceException() {
            super("UpdateModuleServiceGovernanceException");
        }

        public UpdateModuleServiceGovernanceException(String s) {
           super(s);
        }

        public UpdateModuleServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public UpdateModuleServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ModuleStub.UpdateModuleServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ModuleStub.UpdateModuleServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    
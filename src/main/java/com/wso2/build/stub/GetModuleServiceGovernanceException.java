
/**
 * GetModuleServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class GetModuleServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1392539976700L;

    private com.wso2.build.stub.ModuleStub.GetModuleServiceGovernanceException faultMessage;


        public GetModuleServiceGovernanceException() {
            super("GetModuleServiceGovernanceException");
        }

        public GetModuleServiceGovernanceException(String s) {
           super(s);
        }

        public GetModuleServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public GetModuleServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ModuleStub.GetModuleServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ModuleStub.GetModuleServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    
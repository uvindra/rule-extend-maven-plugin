
/**
 * GetModuleDependenciesServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class GetModuleDependenciesServiceGovernanceException extends java.lang.Exception{

    private static final long serialVersionUID = 1392539976673L;
    
    private com.wso2.build.stub.ModuleStub.GetModuleDependenciesServiceGovernanceException faultMessage;

    
        public GetModuleDependenciesServiceGovernanceException() {
            super("GetModuleDependenciesServiceGovernanceException");
        }

        public GetModuleDependenciesServiceGovernanceException(java.lang.String s) {
           super(s);
        }

        public GetModuleDependenciesServiceGovernanceException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public GetModuleDependenciesServiceGovernanceException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ModuleStub.GetModuleDependenciesServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ModuleStub.GetModuleDependenciesServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    
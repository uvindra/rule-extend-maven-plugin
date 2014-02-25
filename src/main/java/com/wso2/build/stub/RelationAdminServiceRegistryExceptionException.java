
/**
 * RelationAdminServiceRegistryExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class RelationAdminServiceRegistryExceptionException extends Exception{

    private static final long serialVersionUID = 1392625640022L;
    
    private com.wso2.build.stub.RelationAdminServiceStub.RelationAdminServiceRegistryException faultMessage;

    
        public RelationAdminServiceRegistryExceptionException() {
            super("RelationAdminServiceRegistryExceptionException");
        }

        public RelationAdminServiceRegistryExceptionException(String s) {
           super(s);
        }

        public RelationAdminServiceRegistryExceptionException(String s, Throwable ex) {
          super(s, ex);
        }

        public RelationAdminServiceRegistryExceptionException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.RelationAdminServiceStub.RelationAdminServiceRegistryException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.RelationAdminServiceStub.RelationAdminServiceRegistryException getFaultMessage(){
       return faultMessage;
    }
}
    
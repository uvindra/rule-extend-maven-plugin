
/**
 * CustomLifecyclesChecklistAdminServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class CustomLifecyclesChecklistAdminServiceExceptionException extends Exception{

    private static final long serialVersionUID = 1392897717841L;
    
    private com.wso2.build.stub.CustomLifecyclesChecklistAdminServiceStub.CustomLifecyclesChecklistAdminServiceException faultMessage;

    
        public CustomLifecyclesChecklistAdminServiceExceptionException() {
            super("CustomLifecyclesChecklistAdminServiceExceptionException");
        }

        public CustomLifecyclesChecklistAdminServiceExceptionException(String s) {
           super(s);
        }

        public CustomLifecyclesChecklistAdminServiceExceptionException(String s, Throwable ex) {
          super(s, ex);
        }

        public CustomLifecyclesChecklistAdminServiceExceptionException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.CustomLifecyclesChecklistAdminServiceStub.CustomLifecyclesChecklistAdminServiceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.CustomLifecyclesChecklistAdminServiceStub.CustomLifecyclesChecklistAdminServiceException getFaultMessage(){
       return faultMessage;
    }
}
    
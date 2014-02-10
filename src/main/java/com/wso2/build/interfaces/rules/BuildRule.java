package com.wso2.build.interfaces.rules;

import java.io.File;

/**
 * Created by uvindra on 2/1/14.
 */
public interface BuildRule {
    boolean validate(File[] files);
}

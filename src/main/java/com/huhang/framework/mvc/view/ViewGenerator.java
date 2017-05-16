package com.huhang.framework.mvc.view;

import com.huhang.framework.mvc.controller.ControllerContext;

/**
 * Created by joanna on 4/11/17.
 */
//Builder pattern
public interface ViewGenerator {
    public void generate(ControllerContext controllerContext);
}

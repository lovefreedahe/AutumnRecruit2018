package com.wangrupeng.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.SOURCE)
public @interface Annotation {
    TestAnnotation[] value();
}


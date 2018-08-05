package com.wangrupeng.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Documented
@Target(ElementType.TYPE)
@Inherited
public @interface TestAnnotation {
}

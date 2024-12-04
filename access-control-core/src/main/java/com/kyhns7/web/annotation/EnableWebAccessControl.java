package com.kyhns7.web.annotation;

import com.kyhns7.annotation.EnableAccessControl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableAccessControl
@Import({AccessControlWebConfiguration.class})
@Documented
public @interface EnableWebAccessControl {

}

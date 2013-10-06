package com.common;

import java.lang.annotation.*;

/**
 * Mark the getter and setter of a bean with this
 * annotation if you want to make that property
 * of the bean editable at runtime through the
 * PropertyManager and the PropertyManagerServlet.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyEditable {
}

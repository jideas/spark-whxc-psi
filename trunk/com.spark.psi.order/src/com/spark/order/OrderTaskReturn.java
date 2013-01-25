package com.spark.order;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface OrderTaskReturn {
	String getReturnName();
	String getParmName();   
}
                 
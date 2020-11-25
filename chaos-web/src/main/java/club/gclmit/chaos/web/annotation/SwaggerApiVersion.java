package club.gclmit.chaos.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Swagger API 版本注解
 * </p>
 *
 * @author gclm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SwaggerApiVersion {

    /**
     * 标识版本号，从v1开始
     * @return 默认值 v1
     */
    String[] value();

}
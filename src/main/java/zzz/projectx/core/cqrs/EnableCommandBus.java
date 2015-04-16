package zzz.projectx.core.cqrs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ CommandHandlerRegistrar.class, CommandBusInfrastructureConfiguration.class })
public @interface EnableCommandBus {

	String packageToScan();

}

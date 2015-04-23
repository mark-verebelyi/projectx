package zzz.projectx.core;

import org.springframework.beans.factory.config.BeanDefinition;

public interface ComponentNameTemplate {

	String nameOfComponent(BeanDefinition beanDefinition);

	String nameOfComponent(String beanClassName);

	String nameOfRequestChannelForComponent(BeanDefinition beanDefinition);

	String nameOfRequestChannelForComponent(String beanClassName);

	String nameOfServiceActivatorForComponent(BeanDefinition beanDefinition);

	String nameOfServiceActivatorForComponent(String beanClassName);

}

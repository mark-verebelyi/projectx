package zzz.projectx.core.cqrs;

import org.springframework.beans.factory.config.BeanDefinition;

interface ComponentNameTemplate {

	String nameOfComponent(BeanDefinition beanDefinition);

	String nameOfComponent(String beanClassName);

	String nameOfRequestChannelForComponent(BeanDefinition beanDefinition);

	String nameOfRequestChannelForComponent(String beanClassName);

	String nameOfServiceActivatorForComponent(BeanDefinition beanDefinition);

	String nameOfServiceActivatorForComponent(String beanClassName);

}

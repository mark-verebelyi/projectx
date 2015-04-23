package zzz.projectx.core;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import org.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractComponentNameTemplate implements ComponentNameTemplate {

	private final String namePattern;
	private final String requestChannelPattern;
	private final String serviceActivatorPattern;

	public AbstractComponentNameTemplate(final String namePattern, final String requestChannelPattern, final String serviceActivatorPattern) {
		checkArgument(isNotBlank(namePattern), "namePattern can not be blank");
		checkArgument(isNotBlank(requestChannelPattern), "requestChannelPattern can not be blank");
		checkArgument(isNotBlank(serviceActivatorPattern), "serviceActivatorPattern can not be blank");
		this.namePattern = namePattern;
		this.requestChannelPattern = requestChannelPattern;
		this.serviceActivatorPattern = serviceActivatorPattern;
	}

	@Override
	public String nameOfComponent(final String beanClassName) {
		checkArgument(isNotBlank(beanClassName), "beanClassName can not be blank");
		return String.format(namePattern, beanClassName);
	}

	@Override
	public String nameOfRequestChannelForComponent(final String beanClassName) {
		checkArgument(isNotBlank(beanClassName), "beanClassName can not be blank");
		return String.format(requestChannelPattern, beanClassName);
	}

	@Override
	public String nameOfServiceActivatorForComponent(final String beanClassName) {
		checkArgument(isNotBlank(beanClassName), "beanClassName can not be blank");
		return String.format(serviceActivatorPattern, beanClassName);
	}

	@Override
	public String nameOfComponent(final BeanDefinition beanDefinition) {
		checkArgument(beanDefinition != null, "beanDefinition can not be null");
		return nameOfComponent(beanDefinition.getBeanClassName());
	}

	@Override
	public String nameOfRequestChannelForComponent(final BeanDefinition beanDefinition) {
		checkArgument(beanDefinition != null, "beanDefinition can not be null");
		return nameOfRequestChannelForComponent(beanDefinition.getBeanClassName());
	}

	@Override
	public String nameOfServiceActivatorForComponent(final BeanDefinition beanDefinition) {
		checkArgument(beanDefinition != null, "beanDefinition can not be null");
		return nameOfServiceActivatorForComponent(beanDefinition.getBeanClassName());
	}

}

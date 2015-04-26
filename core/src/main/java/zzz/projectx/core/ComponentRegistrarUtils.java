package zzz.projectx.core;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;

public final class ComponentRegistrarUtils {

	private static final String PACKAGE_TO_SCAN_ANNOTATION_ATTRIBUTE = "packageToScan";

	private ComponentRegistrarUtils() {
		throw new IllegalStateException("Utility class, do not instantiate");
	}

	public static Set<BeanDefinition> findComponents(final Class<?> componentType, final Class<?> enablingAnnotationType,
			final AnnotationMetadata metadata) {
		checkArgument(componentType != null, "componentType can not be null");
		checkArgument(enablingAnnotationType != null, "enablingAnnotationType can not be null");
		checkArgument(metadata != null, "metadata can not be null");
		final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AssignableTypeFilter(componentType));
		return scanner.findCandidateComponents(getPackageToScan(enablingAnnotationType, metadata));
	}

	private static String getPackageToScan(final Class<?> enablingAnnotationType, final AnnotationMetadata importingClassMetadata) {
		final Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(enablingAnnotationType.getName());
		return (String) annotationAttributes.get(PACKAGE_TO_SCAN_ANNOTATION_ATTRIBUTE);
	}

}

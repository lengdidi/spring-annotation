package ld.config;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class MyTypeFilter implements TypeFilter {

	// metadataReader : 读取到的当前正在扫描的类的信息
	// metadataReaderFactory : 可以获取到其他任何类的信息
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		Resource resource = metadataReader.getResource();
		String className = classMetadata.getClassName();
		System.out.println("----------->" + className);
		if (className.contains("er")) {
			return true;
		}
		return false;
	}

}

package ld.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

	/**
	 * 返回值就是导入到容器中的组件的全类名
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//		importingClassMetadata.get
		// 不能放回null，可以返回空数组
		return new String[] { "com.ld.bean.Blue", "com.ld.bean.Yellow" };
	}

}

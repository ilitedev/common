/*
     Litedev Projects
     Copyright (C) 2020 litedev.cn

     This program is free software: you can redistribute it and/or modify
     it under the terms of the GNU General Public License as published by
     the Free Software Foundation, either version 3 of the License, or
     (at your option) any later version.

     This program is distributed in the hope that it will be useful,
     but WITHOUT ANY WARRANTY; without even the implied warranty of
     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     GNU General Public License for more details.

     You should have received a copy of the GNU General Public License
     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package cn.litedev.common.base.dict;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;

/**
 * 扫描分析枚举pkg生成数据字典
 *
 * @author fanlei
 */
@Slf4j
public class EnumsToDict {

    private static final PathMatchingResourcePatternResolver RESOLVER = new PathMatchingResourcePatternResolver();
    private static final StandardEnvironment ENVIRONMENT = new StandardEnvironment();
    private static final SimpleMetadataReaderFactory REGISTER = new SimpleMetadataReaderFactory();

    /**
     * 扫描并分析
     *
     * @param pkgName    枚举所在包名称
     * @param dictionary 数据字典实例
     */
    public static void scan(String pkgName, Dictionary dictionary) {
        String resourcePath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + ClassUtils.convertClassNameToResourcePath(ENVIRONMENT.resolveRequiredPlaceholders(pkgName))
                + '/' + "**/*.class";
        try {
            Resource[] rs = RESOLVER.getResources(resourcePath);
            for (Resource r : rs) {
                MetadataReader metadataReader = REGISTER.getMetadataReader(r);
                Class<?> clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
                for (Object enumConstant : clazz.getEnumConstants()) {
                    try {
                        dictionary.add(clazz.getSimpleName(), new DictionaryItem(enumConstant.toString(), ReflectionUtils.invokeMethod(clazz.getMethod("getDesc"), enumConstant).toString()));
                    } catch (NoSuchMethodException ignored) {
                        log.error("Enum [" + clazz.getSimpleName() + "] hasn't the method [getDesc()]");
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ignored) {
        }


    }
}

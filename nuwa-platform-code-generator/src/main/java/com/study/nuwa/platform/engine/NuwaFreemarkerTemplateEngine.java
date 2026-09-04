package com.study.nuwa.platform.engine;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * Freemarker 模板引擎占位。
 * <p>
 * 原自定义 {@code outputCustomFile} 逻辑在 MyBatis Plus 3.5.9 中
 * {@code AbstractTemplateEngine#outputCustomFile(Map, TableInfo, Map)} 签名有变化，
 * 升级后保留父类默认行为即可。如需自定义输出文件，建议继承
 * {@link com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine} 并使用
 * MyBatis Plus 新版的 {@link com.baomidou.mybatisplus.generator.InjectionConfig}。
 *
 * @author Nuwa
 * @since 2021-10-12
 */
public class NuwaFreemarkerTemplateEngine extends FreemarkerTemplateEngine {
}

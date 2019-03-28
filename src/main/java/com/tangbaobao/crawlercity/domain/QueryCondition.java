package com.tangbaobao.crawlercity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tangxuejun
 * @version 2019-03-25 22:18
 */
@Getter
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryCondition {
    //开发者APP_KEY
    @Value("${amap.key}")
    private String key;
    private String keywords;
    /**
     * 设置显示下级行政区级数（行政区级别包括：国家、省/直辖市、市、区/县、乡镇/街道多级数据）
     * <p>
     * 可选值：0、1、2、3等数字，并以此类推
     * <p>
     * 0：不返回下级行政区；
     * <p>
     * 1：返回下一级行政区；
     * <p>
     * 2：返回下两级行政区；
     * <p>
     * 3：返回下三级行政区；
     */
    private String subDistrict;
    private int page;
    private int offset;
    private String extensions;
    private int filter;
    //回调函数
    private String callback;
    //JSON，XML
    private String output;

    @Override
    public String toString() {
        return "QueryCondition{" +
                "key='" + key + '\'' +
                ", keywords='" + keywords + '\'' +
                ", subDistrict='" + subDistrict + '\'' +
                ", page=" + page +
                ", offset=" + offset +
                ", extensions='" + extensions + '\'' +
                ", filter=" + filter +
                ", callback='" + callback + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}

/*
  遇到一个小问题，记录下：
  用@Builder之后，用@Value读取appclication.yml文件中的配置，springboot报错：
  Parameter 0 of constructor in com.tangbaobao.crawlercity.domain.QueryCondition required a bean of type 'java.lang.String' that could not be found.
  打开编译后的class文件查看之后这个类只生成了一个带有全部参数的构造方法，问题很显然，
  加了@Value之后相当于要从spring容器中获取一个类型为String，name为amap.key的bean
  但是spring还没有对yml中的amap.key的bean实例化，所以在实例化QueryCondition这个类的时候找不到所需要的属性，所以报错，
  解决办法：给QueryCondition加入空的构造器，在实例化QueryCondition的时候不会去找amap.key这个Bean
 */

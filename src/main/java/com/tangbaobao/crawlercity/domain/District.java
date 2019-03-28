package com.tangbaobao.crawlercity.domain;

import com.tangbaobao.crawlercity.enums.DistrictLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-03-25 21:35
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //区域编码
    private String adCode;
    //城市编码
    private String cityCode;
    //区域名称
    private String name;
    //城市中心点
    private String center;
    //地区级别
    @Enumerated(EnumType.STRING)
    private DistrictLevel level;
    //父级地区的adCode
    private String parentAdCode;
    //下级区域列表
    @Transient
    private List<District> districts;

}

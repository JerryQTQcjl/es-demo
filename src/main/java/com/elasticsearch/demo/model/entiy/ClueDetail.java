package com.elasticsearch.demo.model.entiy;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author jerry chan
 * @date 2021/10/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ClueDetail extends EsBaseEntity {

    private static final long serialVersionUID = 678781791133533174L;
    private Long corpId;
    private String name;
    private String phone;
    private String company;
    private Byte status;

    public ClueDetail setCompany(String company) {
        if (StrUtil.isBlank(company) || company.length() > 80) {
            company = "";
        }
        this.company = company;
        return this;
    }

    private String address = "";

    private String industry = "";

    private Integer industryId;

    private Integer country;

    private Integer province;

    private Integer city;

    private Integer region;

    private String regionValue;

    private Integer desire;

    private String email = "";

    public ClueDetail setEmail(String email) {
        if (StrUtil.isBlank(email) || StrUtil.isBlank(email) || email.length() > 80) {
            email = "";
        }
        this.email = email;
        return this;
    }

    private String qq = "";

    public ClueDetail setQq(String qq) {
        if (StrUtil.isBlank(qq) || qq.length() < 5 || qq.length() > 11) {
            qq = "";
        }
        this.qq = qq;
        return this;
    }

    private String wx = "";

    public ClueDetail setWx(String wx) {
        if (StrUtil.isBlank(wx) || wx.length() < 5 || wx.length() > 20) {
            wx = "";
        }
        this.wx = wx;
        return this;
    }

    private Byte sex = 0;

    private Byte inputMethod;

    private Integer source;

    private String remark;

    public ClueDetail setRemark(String remark) {
        if (StrUtil.isNotBlank(remark) && remark.length() > 512) {
            remark = "";
        }
        this.remark = remark;
        return this;
    }

    private String reason;

    private Date createTime;

    private Date modifyTime;

    /**
     * 订单成交时间
     */
    private Date orderTime;

}

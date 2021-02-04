package com.ckf.crm.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuan
 * @since 2021-06-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("on_line")
public class OnLine extends SimpleSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String oId;

    private String oLoginName;

    private String oIpAddress;

    private String oLoginSite;

    private String oBrowserType;

    private String oOs;

    private Integer oState;

    private String oCreateTime;

    private String oLastTime;

    private Integer oExpireTime;


    @Override
    public void setAttribute(Object key, Object value)
    {
        super.setAttribute(key, value);
    }

    @Override
    public Object removeAttribute(Object key)
    {
        return super.removeAttribute(key);
    }
}

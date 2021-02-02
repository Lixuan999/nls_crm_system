package com.ckf.crm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 安详的苦丁茶
 * @since 2020-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_log")
public class TemLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    private String logUserName;

    private String logIpAddr;

    private String logBasePath;

    private String logDescription;

    private String logUri;

    private String logUrl;

    private String logMethod;

    private Integer logState;

    private Object logParameter;

    private String logResult;

    private String logStartTime;

    private Integer logSpendTime;


}

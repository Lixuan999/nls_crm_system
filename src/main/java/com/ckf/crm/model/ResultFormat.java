package com.ckf.crm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author P安详的苦丁茶
 * @version 1.0
 * @date 2020/2/17 14:41
 * 统一返回格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ApiModel("返回统一格式模型")
public class ResultFormat<T> {

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码", required = true)
    private Integer code;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息", required = true)
    private String msg;

    /**
     * 内容
     */
    @ApiModelProperty(value = "返回内容", required = true)
    private T data;

}

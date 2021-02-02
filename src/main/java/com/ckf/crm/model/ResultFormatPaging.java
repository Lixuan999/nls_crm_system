package com.ckf.crm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/2/17 14:41
 * 统一返回格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ApiModel("返回统一分页模型")
public class ResultFormatPaging<T> {
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码", required = true)
    private Integer code;

    /**
     * 数据的条数
     */
    @ApiModelProperty(value = "返回条数", required = true)
    private Long count;

    /**
     * 内容
     */
    @ApiModelProperty(value = "返回内容", required = true)
    private T data;

}

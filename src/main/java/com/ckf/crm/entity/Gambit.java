package com.ckf.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <p>
 *
 * </p>
 *
 * @author 安详的苦丁茶
 * @since 2020-03-27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("gambit")
public class Gambit extends BaseEntity implements Serializable {


    @TableId(type = IdType.AUTO)
    private Integer gambitId;

    private String gHeadline;

    private String gAuditor;

    private String gAudiTime;

    private String gFounder;

    private String gCheckState;


}

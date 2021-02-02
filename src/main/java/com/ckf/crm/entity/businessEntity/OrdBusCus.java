package com.ckf.crm.entity.businessEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ckf.crm.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/4/6 0:16
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ord_bus_cus")
public class OrdBusCus extends BaseEntity {

    private Integer ordId;

    private Integer busId;

    private Integer cusId;

}

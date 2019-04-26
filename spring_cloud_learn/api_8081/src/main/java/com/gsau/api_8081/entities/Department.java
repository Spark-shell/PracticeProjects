package com.gsau.api_8081.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author WangGuoQing
 * @date 2019/4/26 14:35
 * @Desc 部门实体
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Department implements Serializable {
    public int id;
    public String dnName;
    public String dbSource;
}

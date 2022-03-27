package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MPBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 项目动态对象 project_news
 *
 * @author chen
 * @date 2022-02-27
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ProjectNews extends MPBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目动态id */
    @TableId
    private Integer newsId;

    /** 动态名称 */
    @Excel(name = "动态名称")
    private String projectNewsName;

    /** 动态内容 */
    @Excel(name = "动态内容")
    private String projectNewsContent;

    /** 项目id */
    @Excel(name = "项目id")
    private Integer projectId;


}

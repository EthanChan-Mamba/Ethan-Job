package com.ruoyi.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.MPBaseEntity4;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MPBaseEntity;

/**
 * projectItems对象 project_items
 *
 * @author ruoyi
 * @date 2022-01-08
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ProjectItems extends MPBaseEntity4
{
    private static final long serialVersionUID = 1L;

    /** 项目id,主键 */
    private int projectId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目所属类型id，0默认 */
    @Excel(name = "项目所属类型id，0默认")
    private Integer projectType;

    /** 项目备注、描述 */
    @Excel(name = "项目备注、描述")
    private String projectRemark;

    /** 项目状态 0失效；1进行中；2超时；3延期；4完成 */
    @Excel(name = "项目状态 0失效；1进行中；2超时；3延期；4完成")
    private Integer projectStatus;

    /** 项目开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTimeStart;

    /** 项目结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTimeEnd;


}

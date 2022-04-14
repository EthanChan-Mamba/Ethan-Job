package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MPBaseEntity;

import java.util.Date;

/**
 * 任务项目个体对象 project_mission_item
 *
 * @author chen
 * @date 2022-02-13
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ProjectMissionItem extends MPBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目任务id */
    @TableId
    private Integer missionItemId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String missionItemName;

    /** 所属项目任务id */
    @Excel(name = "所属项目任务id")
    private Integer projectId;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String missionItemRemark;

    /** 任务状态1进行中；2超时；3失效；4完成；5延期 */
    @Excel(name = "任务状态1进行中；2超时；3失效；4完成；5延期")
    private Integer missionItemStatus;

    /** 任务开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTimeStart;

    /** 任务结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTimeEnd;

    /** 所属项目任务id */
    @Excel(name = "所属项目任务id")
    private Integer projectMissionTypeId;


}

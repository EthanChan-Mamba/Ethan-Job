package com.ruoyi.business.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.business.domain.ProjectMissionItem;
import com.ruoyi.business.service.IProjectMissionItemService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务项目个体Controller
 *
 * @author chen
 * @date 2022-02-13
 */
@Controller
@RequestMapping("/ethan-business/ProjectMissionItem")
public class ProjectMissionItemController extends BaseController
{
    private String prefix = "ethan-business/ProjectMissionItem";

    @Autowired
    private IProjectMissionItemService projectMissionItemService;

    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:view')")
    @GetMapping()
    public String ProjectMissionItem()
    {
        return prefix + "/ProjectMissionItem";
    }

    /**
     * 查询任务项目个体列表
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectMissionItem projectMissionItem)
    {
        startPage();
        List<ProjectMissionItem> list = projectMissionItemService.selectProjectMissionItemList(projectMissionItem);
        return getDataTable(list);
    }

    /**
     * 导出任务项目个体列表
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:export')")
    @Log(title = "任务项目个体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProjectMissionItem projectMissionItem)
    {
        List<ProjectMissionItem> list = projectMissionItemService.selectProjectMissionItemList(projectMissionItem);
        ExcelUtil<ProjectMissionItem> util = new ExcelUtil<ProjectMissionItem>(ProjectMissionItem.class);
        return util.exportExcel(list, "任务项目个体数据");
    }

    /**
     * 新增任务项目个体
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存任务项目个体
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:add')")
    @Log(title = "任务项目个体", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProjectMissionItem projectMissionItem)
    {
        return toAjax(projectMissionItemService.save(projectMissionItem));
    }

    /**
     * 修改任务项目个体
     */
    @GetMapping("/edit/{missionItemId}")
    public String edit(@PathVariable("missionItemId") Long missionItemId, ModelMap mmap)
    {
        ProjectMissionItem projectMissionItem = projectMissionItemService.getById(missionItemId);
        mmap.put("projectMissionItem", projectMissionItem);
        return prefix + "/edit";
    }

    /**
     * 修改保存任务项目个体
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:edit')")
    @Log(title = "任务项目个体", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProjectMissionItem projectMissionItem)
    {
        return toAjax(projectMissionItemService.updateById(projectMissionItem));
    }

    /**
     * 删除任务项目个体
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:remove')")
    @Log(title = "任务项目个体", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(projectMissionItemService.removeByIds(Arrays.asList(ids.split(","))));
    }
}

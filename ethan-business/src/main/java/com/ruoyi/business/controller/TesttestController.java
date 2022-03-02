package com.ruoyi.business.controller;

import com.ruoyi.business.annotation.ProjectLog;
import com.ruoyi.business.domain.ProjectMissionItem;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * swagger 用户测试方法
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/testtest/user")
public class TesttestController extends BaseController
{
    private final static Map<Integer, UserEntity> users = new LinkedHashMap<Integer, UserEntity>();
    {
        users.put(1, new UserEntity(1, "admin", "admin123", "15888888888"));
        users.put(2, new UserEntity(2, "ry", "admin123", "15666666666"));
    }

    @GetMapping("/list")
    @ProjectLog(description = "测试", logType = BusinessType.UPDATE)
    public AjaxResult userList(ProjectMissionItem projectMissionItem)
    {
        List<UserEntity> userList = new ArrayList<UserEntity>(users.values());
        return AjaxResult.success(userList);
    }

}

class UserEntity
{
    private Integer userId;

    private String username;

    private String password;

    private String mobile;

    public UserEntity()
    {

    }

    public UserEntity(Integer userId, String username, String password, String mobile)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
}

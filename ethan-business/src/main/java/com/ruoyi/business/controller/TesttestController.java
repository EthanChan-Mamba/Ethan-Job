package com.ruoyi.business.controller;

import com.ruoyi.business.annotation.ProjectLog;
import com.ruoyi.business.domain.ProjectMissionItem;
import com.ruoyi.business.enums.BusinessTypeWithName;
import com.ruoyi.business.service.impl.TestImpl;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    TestImpl testImpl;

    @GetMapping("/test")
    public Map test() throws InterruptedException {
        Map i = testImpl.selectProjectItemsList(10);
        System.out.println(i);
        System.out.println("11111111111111111111");
        return i;
    }

    @GetMapping("/test1")
    public int test1() throws InterruptedException {
        int i = testImpl.upProjectItemsList("3", 1);
        System.out.println("222222222222222222");
        return i;
    }

    @GetMapping("/test2")
    public boolean test2() throws InterruptedException {
        int j = testImpl.upProjectItemsList1("4", 1);
        return j > 0;
    }

    private final static Map<Integer, UserEntity> users = new LinkedHashMap<Integer, UserEntity>();
    static {
        users.put(1, new UserEntity(1, "admin", "admin123", "15888888888"));
        users.put(2, new UserEntity(2, "ry", "admin123", "15666666666"));
    }

    @GetMapping("/list")
    @ProjectLog(description = "测试", logType = BusinessTypeWithName.UPDATE)
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

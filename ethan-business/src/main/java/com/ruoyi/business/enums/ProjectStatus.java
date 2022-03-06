package com.ruoyi.business.enums;

/**
 * 用户状态
 *
 * @author ruoyi
 */
public enum ProjectStatus
{
    /**
     * 失效
     */
    FAILURE(-1, "失效"),
    /**
     * 失效
     */
    NOTBEGIN(0, "未开始"),
    /**
     * 进行中
     */
    ONGOING(1, "进行中"),
    /**
     * 超时
     */
    TIMEOUT(2, "超时"),
    /**
     * 延期
     */
    DELAY(3, "延期"),
    /**
     * 完成
     */
    COMPLETE(4, "完成");

    private final int code;
    private final String info;

    ProjectStatus(int code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public int getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }

    public static Integer getCode(String info) {
        ProjectStatus[] missionItemStatusArray = values();
        for (ProjectStatus missionItemStatus : missionItemStatusArray) {
            if (missionItemStatus.getInfo().equals(info)) {
                return missionItemStatus.getCode();
            }
        }
        return null;
    }

    public static String getType(int code) {
        ProjectStatus[] missionItemStatusArray = values();
        for (ProjectStatus missionItemStatus : missionItemStatusArray) {
            if (missionItemStatus.getCode() == code) {
                return missionItemStatus.getInfo();
            }
        }
        return null;
    }
}

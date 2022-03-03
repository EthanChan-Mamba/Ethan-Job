package com.ruoyi.business.enums;

/**
 * 用户状态
 *
 * @author ruoyi
 */
public enum MissionItemStatus
{
    /**
     * 进行中
     */
    ONGOING(1, "进行中"),
    /**
     * 超时
     */
    TIMEOUT(2, "超时"),
    /**
     * 失效
     */
    FAILURE(3, "失效"),
    /**
     * 完成
     */
    COMPLETE(4, "完成"),
    /**
     * 延期
     */
    DELAY(5, "延期");

    private final int code;
    private final String info;

    MissionItemStatus(int code, String info)
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
        MissionItemStatus[] missionItemStatusArray = values();
        for (MissionItemStatus missionItemStatus : missionItemStatusArray) {
            if (missionItemStatus.getInfo().equals(info)) {
                return missionItemStatus.getCode();
            }
        }
        return null;
    }

    public static String getType(int code) {
        MissionItemStatus[] missionItemStatusArray = values();
        for (MissionItemStatus missionItemStatus : missionItemStatusArray) {
            if (missionItemStatus.getCode() == code) {
                return missionItemStatus.getInfo();
            }
        }
        return null;
    }
}

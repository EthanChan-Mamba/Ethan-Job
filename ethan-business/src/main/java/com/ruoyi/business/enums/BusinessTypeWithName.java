package com.ruoyi.business.enums;

/**
 * 用户状态
 *
 * @author ruoyi
 */
public enum BusinessTypeWithName
{
    /**
     * 其它
     */
    OTHER("OTHER", "其它"),
    /**
     * 新增
     */
    INSERT("INSERT", "新增"),
    /**
     * 修改
     */
    UPDATE("UPDATE", "修改"),
    /**
     * 删除
     */
    DELETE("DELETE", "删除"),
    /**
     * 授权
     */
    GRANT("GRANT", "授权"),
    /**
     * 导出
     */
    EXPORT("EXPORT", "导出"),
    /**
     * 导入
     */
    IMPORT("IMPORT", "导入"),
    /**
     * 强退
     */
    FORCE("FORCE", "强退"),
    /**
     * 生成代码
     */
    GENCODE("GENCODE", "生成代码"),
    /**
     * 清空数据
     */
    CLEAN("CLEAN", "清空数据");

    private final String code;
    private final String info;

    BusinessTypeWithName(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}

package com.hysea.entity.menu;

import com.hysea.entity.command.CommandTemplate;
import com.hysea.entity.process.Flow;
import lombok.Data;

import java.util.List;

@Data
public class Menu {

    /**
     * 菜单id
     */
    Integer menuId;

    /**
     * 父id
     */
    Integer parentId;

    /**
     * 菜单标题
     */
    String menuTitle;

    /**
     * 菜单描述
     */
    String menuDescribe;

    /**
     * 命令流程
     */
    Flow flow;

    public static Menu selectAndKillPid = new Menu();

    static {
        selectAndKillPid.setFlow(Flow.selectAndKillPid);
    }


}

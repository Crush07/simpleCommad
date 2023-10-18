package com.hysea.entity.process;

import com.hysea.entity.command.CommandTemplate;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Flow {

    Integer processId;

    String processName;

    List<CommandTemplate> commandTemplateList = new ArrayList<>();

    public static Flow selectAndKillPid = new Flow();

    static {
        selectAndKillPid.setProcessName("查询并杀死进程");
        selectAndKillPid.getCommandTemplateList().add(CommandTemplate.selectPid);
        selectAndKillPid.getCommandTemplateList().add(CommandTemplate.killPid);
    }

}

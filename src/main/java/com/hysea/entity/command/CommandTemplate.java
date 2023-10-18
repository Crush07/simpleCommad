package com.hysea.entity.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

@Data
public class CommandTemplate {

    List<CommandTemplateNode> commandTemplateNodeList = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommandTemplateNode{

        /**
         * 命令模板节点内容
         */
        String commandTemplateNodeContent;

        /**
         * 命令模板节点类型
         * 1.固定值
         * 2.变量
         */
        Integer commandTemplateNodeTypeId;

    }

    public static CommandTemplate selectPid = new CommandTemplate();
    public static CommandTemplate killPid = new CommandTemplate();

    static {

        selectPid.getCommandTemplateNodeList().add(new CommandTemplateNode("netstat -ano|findstr ",1));
        selectPid.getCommandTemplateNodeList().add(new CommandTemplateNode("要查询pid的端口号",2));

        killPid.getCommandTemplateNodeList().add(new CommandTemplateNode("taskkill /pid",1));
        killPid.getCommandTemplateNodeList().add(new CommandTemplateNode("要kill的pid",2));
        killPid.getCommandTemplateNodeList().add(new CommandTemplateNode("-f",1));

    }

    public static String buildCommand(CommandTemplate commandTemplate, List<String> varList){

        StringJoiner joiner = new StringJoiner(" ");
        for(int i = 0;i < commandTemplate.getCommandTemplateNodeList().size();i++){
            if(commandTemplate.getCommandTemplateNodeList().get(i).getCommandTemplateNodeTypeId() == 2){
                joiner.add(varList.get(0));
                varList.remove(0);
            }else{
                joiner.add(commandTemplate.getCommandTemplateNodeList().get(i).getCommandTemplateNodeContent());
            }
        }

        return joiner.toString();
    }

    public static String buildCommand(CommandTemplate commandTemplate){

        Scanner sc = new Scanner(System.in);
        StringJoiner joiner = new StringJoiner(" ");
        for(int i = 0;i < commandTemplate.getCommandTemplateNodeList().size();i++){
            if(commandTemplate.getCommandTemplateNodeList().get(i).getCommandTemplateNodeTypeId() == 2){
                System.out.print("请输入" + commandTemplate.getCommandTemplateNodeList().get(i).getCommandTemplateNodeContent() + "：");
                String next = sc.next();
                joiner.add(next);
            }else{
                joiner.add(commandTemplate.getCommandTemplateNodeList().get(i).getCommandTemplateNodeContent());
            }
        }

        return joiner.toString();
    }

}

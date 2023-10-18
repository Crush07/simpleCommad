package com.hysea;

import com.hysea.entity.command.CommandTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String commandPrefix = "cmd.exe /c ";
        Runtime runtime = Runtime.getRuntime();

        HashMap<Integer, CommandTemplate> menuIdCommandTemplateMap = new HashMap<>();
        menuIdCommandTemplateMap.put(1,CommandTemplate.selectPid);
        menuIdCommandTemplateMap.put(2,CommandTemplate.killPid);

        String s = CommandTemplate.buildCommand(menuIdCommandTemplateMap.get(1));

        Process process = runtime.exec(commandPrefix + s);
        InputStream inputStream = process.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"gb2312"));
        String line = null;
        while((line = br.readLine()) != null) {
            System.out.println(line);
        }

        s = CommandTemplate.buildCommand(menuIdCommandTemplateMap.get(2));

        process = runtime.exec(commandPrefix + s);
        inputStream = process.getInputStream();

        br = new BufferedReader(new InputStreamReader(inputStream,"gb2312"));
        line = null;
        while((line = br.readLine()) != null) {
            System.out.println(line);
        }

    }
}
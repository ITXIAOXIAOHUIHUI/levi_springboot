package com.springboot.levi.netty.common;

import lombok.Data;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-27 14:10
 */
@Data
public class Demo {
    String name;
    
    Boolean happy;
    public static void main(String[] args) {
        /*List<Demo> list = new ArrayList<>();
        Demo demo = new Demo();
        demo.setHappy(true);
        demo.setName("222");
        list.add(demo);

        Map<String, Demo> collect = list.stream().collect(Collectors.toMap(Demo::getName, t->t));
        Demo demo1 = collect.get("222");
        demo1.setName("2222111");
        System.out.println(collect.toString());*/

        String friendNickName = "曹锡贵";
        try {
            searchMyFriendAndSend(friendNickName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void searchMyFriendAndSend(String friendNickName) throws InterruptedException {
        // 创建Robot对象
        Robot robot = getRobot();
        //打开微信 Ctrl+Alt+W
        assert robot != null;
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_W);
        //释放Ctrl按键，像Ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_ALT);

        // 该延迟不能少，否则无法搜索
        robot.delay(1000);

        // Ctrl + F 搜索指定好友
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // 将好友昵称发送到剪切板
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(friendNickName);
        clip.setContents(tText, null);
        // 以下两行按下了ctrl+v，完成粘贴功能
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(1000);

        while(true){
            // 发送消息
            sendMsg();
        }

    }

    private static void sendMsg() throws InterruptedException {

        String[] mottoes = {
                "优秀的曹老师，无敌的曹老师",
                "天天可以到处开车",
                "牛批的曹老师就问你，腻不腻害！老师",
                "为何如此的优秀，带带我",
                "太优秀了，带带我",
                "[呲牙][坏笑]",
                "[奸笑]"
        };
        for (String motto : mottoes) {
            sendOneMsg(motto);
        }

        sendOneMsg("[得意]就问你，腻不腻害！");
    }

    private static void sendOneMsg(String msg) {
        // 创建Robot对象
        Robot robot = getRobot();
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 将字符串复制到剪切板
        Transferable tText = new StringSelection(msg);
        clip.setContents(tText, null);
        // 以下两行按下了ctrl+v，完成粘贴功能
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        // 回车发送
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(1000);
    }

    private static Robot getRobot(){
        // 创建Robot对象
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return robot;
    }
}

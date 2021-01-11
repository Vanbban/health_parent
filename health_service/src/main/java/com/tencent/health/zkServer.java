package com.tencent.health;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author: Vanbban
 * @create 2021-01-08 10:55
 * * Java 调用系统命令
 *  * 通过 java.lang.Runtime 类用操作系统命令
 *  * 然后调用run.exec()进程来执行命令程序
 *  *  cmd /c dir 是执行完dir命令后结束程序。
 *  *  cmd /k dir 是执行完dir命令后不结束程序。
 *  *  cmd /c start dir 会打开一个新窗口后执行dir指令，原程序结束。
 *  *  cmd /k start dir 会打开一个新窗口后执行dir指令，原程序不结束。
 *  *  可用cmd /k start cmd /? 查看系统帮助
 *  *  说白了 加 start 就是打开命令窗口操作  不加start 就是在控制台查看
 *  * @author lrd
 */
public class zkServer {
    public static void main(String[] args)  {

        Runtime run = Runtime.getRuntime();

        try {
            //可打开exe程序，也可执行cmd命令，注意 路径\要使用\\，表示转义
            // Process p = run.exec("E:\\一卡通测试工具\\串口调试助手\\kComm.exe");  //执行exe程序
            Process p = run.exec("cmd /k start zkServer.cmd");                  //执行CMD命令
            //执行CMD命令

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("GBK")));
            String lineMes;
            while ((lineMes = br.readLine()) != null)
                System.out.println(lineMes);// 打印输出信息

            //检查命令是否执行失败。
            if (p.waitFor() != 0) {
                if (p.exitValue() == 1)//0表示正常结束，1：非正常结束
                    System.err.println("命令执行失败!");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

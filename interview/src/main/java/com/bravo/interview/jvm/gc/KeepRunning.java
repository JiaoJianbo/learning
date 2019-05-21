package com.bravo.interview.jvm.gc;

/**
 * @author: Bobby
 *
 * 写一个持续运行的程序，模拟排查问题的过程。
 *
 * 在 Linux 系统下：
 * 1. 用 top 命令，查看 CPU，内存和 load average      使用情况，找出 CPU 使用率较高的
 * 2. ps -ef 或者 jps 查看线程
 * 3. ps -mp [vmid] -o THREAD,tid,time      查看线程 CPU 使用时间等信息
 * 4. printf "%x\n" [vmid]      将十进制的线程ID转为十六进制格式，也可用计算器
 * 5. jstack [vmid] | grep [线程ID十六进制格式]     注意：十六进制中英文要小写
 * 基本就能定位到具体的代码行了。
 *
 * Windows 上也可以使用 jstack 和 用jconsole更直观的监控
 *
 * 另外，常用的还有 jinfo, jstat, jmap 和 jvisualvm 等命令和工具
 */
public class KeepRunning {

    public static void main(String[] args) {
        String s = null;

        while (true){
            s = "abc";
        }
    }
}

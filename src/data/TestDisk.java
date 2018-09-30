/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import data.Disk;
/**
 * 模拟磁盘类 模拟磁盘的第0块和第1块存放FAT，根目录位置在模拟磁盘的第2块
 *
 * @author chenjiawei
 */
public class TestDisk {
    
    public static void main(String[] args) {
        Disk test = new Disk();
      
        test.Init();
        test.updatToDisk();
        test.displayDisk();
    }
}

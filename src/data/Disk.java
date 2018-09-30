/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 模拟磁盘类 模拟磁盘的第0块和第1块存放FAT，根目录位置在模拟磁盘的第2块
 *
 * @author chenjiawei
 */
public class Disk {

    private static final String DISK_PATH = "C:\\Users\\chenjiawei\\Desktop\\java\\FileSystemSim\\tempDisk.dat";
    private static final int ROOT_LOC = 2;           // root的盘块号
    private static final int DISK_SIZE = 128;           // 模拟磁盘盘块有128块 
    private static final int BLOCK_SIZE = 64;           // 模拟磁盘每个物理块的大小为64B
    private static final int REG_SIZE = 8;           // 每个登记项大小为8B
    private static final int REG_NUM = 8;           //每个块中最多可以存放8个登记项
    private static final byte SPACE_AVAILIABLE = (byte)'?';           //磁盘空间为空时的默认字节？？
    private DiskBlock[] simDisk = new DiskBlock[DISK_SIZE];  //模拟磁盘的物理组织，128个盘块，每块大小64B

    private DiskBlock[] FAT = new DiskBlock[2];                 //FAT为模拟磁盘的第0块和第1块

    public Disk() {
        this.init();
        this.loadFromDisk();      //初始化结束后加载模拟磁盘中的数据
    }

    /*构造方法*/
    private void init() {
        for (int i = 0; i < DISK_SIZE; i++) {
            if (i == 0 || i == 1 || i == 2) {
                this.simDisk[i] = new DiskBlock((byte) 0);   //FAT
            } else {
                this.simDisk[i] = new DiskBlock((byte)128 );
            }
        }
        this.FAT[0] = this.simDisk[0];   //
        this.FAT[1] = this.simDisk[1];  //

        //this.loadFromDisk();                                        //加载模拟磁盘的数据
    }

    public void Init() {
        for (int i = 0; i < DISK_SIZE; i++) {
            if (i == 0 || i == 1) {
                this.simDisk[i] = new DiskBlock((byte) 0);   //FAT
            } else {
                this.simDisk[i] = new DiskBlock((byte) 128);
            }
        }
        this.FAT[0] = this.simDisk[0];   //
        this.FAT[1] = this.simDisk[1];  //

        //this.loadFromDisk();                                        //加载模拟磁盘的数据
    }

    /**
     * 创建一个新的模拟磁盘文件
     */
    public void createDisk() {

        try (DataOutputStream output = new DataOutputStream(new FileOutputStream("tempDisk.dat"));) {
            for (int i = 0; i < DISK_SIZE; i++) {
                output.write(this.simDisk[i].getBlock());
            }
        } catch (IOException ex) {

        }
    }

    public DiskBlock[] getFAT() {
        return this.FAT;
    }     //可将FAT作为参数传递给生成磁盘使用情况的函数

    public DiskBlock[] getSimDisk() {
        return this.simDisk;
    }

//********************************************************************************************************************************
    public class DiskBlock {

        // DiskBlock类描述单个磁盘盘块，为Disk的内部类
        private byte[] block = new byte[BLOCK_SIZE];

        public DiskBlock() {
            this.block = new byte[BLOCK_SIZE];
            this.init();
        }

        /**
         * 将当前磁盘块对象中的所有字节的默认值设置为val
         *
         * @param val 该磁盘块所有字节的默认值
         */
        public DiskBlock(byte val) {
            this.block = new byte[BLOCK_SIZE];
            for (int i = 0; i < BLOCK_SIZE; i++) {
                block[i] = val;
            }
        }

        public DiskBlock(int size) {
            this.block = new byte[size];
        }

        /**
         * 初始化
         */
        private void init() {
            for (int i = 0; i < BLOCK_SIZE; i++) {
                block[i] = Byte.MIN_VALUE;
            }
        }

        /**
         * 返回物理块中第 i + 1 个字节
         *
         * @param i 从0开始的下标
         * @return
         */
        public byte get_Byte(int bNum) {
            if (bNum >= 0 && bNum < BLOCK_SIZE) {
                return this.block[bNum];
            } else {
                System.out.println("error");
                return SPACE_AVAILIABLE;
            }
        }

        /**
         * 将物理块中第i 个字节的值设置为value
         *
         * @param i 从0开始的下标
         * @param value 第i + 1块被设置的值
         */
        public void set_Byte(int bNum, byte value) {
              if (bNum >= 0 && bNum < BLOCK_SIZE) {
                  this.block[bNum] = value;
              } else {
                  System.out.println("error");
              }
        }

        public byte[] getBlock() {
            return this.block;
        }

        public void setBlock(byte[] block) {
            this.block = block;
        }
        /**
         * 查找当前块中空闲的登记项空间
         * @return 如果存在，返回第一个找到的空间登记项空间（0 - 7），如果不存在，返回 -1
         */
        public int findAvailableRegItem() {
            int i;
            for (i = 0; i < REG_NUM; i ++) {    
                int j = 0;
                for (j = 0; j < REG_SIZE; j ++ ) {
                    if (this.block[i * REG_SIZE + j] != SPACE_AVAILIABLE) break;
                }
                if (j == REG_SIZE) return i;
            }
            if (i == REG_NUM) return -1;
            return -1;
        }
        
        
        /**
         * 在当前块的实例对象中以登记项大小为单位（8B）返回一个字节数组
         *
         * @param n 第n个大小为8B的连续磁盘空间（从0开始算起）
         * @return byte[] x = new byte[REG_SIZE]
         */
        public byte[] getRegItem(int n) {
            byte[] x = new byte[REG_SIZE];
            for (int i = 0; i < REG_SIZE; i++) {
                x[i] = this.get_Byte(n * REG_SIZE + i);
            }
            return x;
        }

        /**
         * 在当前块的实例对象中以登记项大小为单位（8B）修改一个连续的磁盘空间
         *
         * @param n 第n个大小为8B的连续磁盘空间（从0开始算起）
         * @param data 新写入的数据，大小为8B
         */
        public void setRegItem(int n, byte[] data) {
            for (int i = 0; i < REG_SIZE; i++) {
                this.set_Byte((n * REG_SIZE) + i, data[i]);
            }
        }

        /**
         * 在当前块中以登记项大小为单位（8B）返回当前登记项的文件名或者目录名
         *
         * @param n 第n个大小为8B的连续磁盘空间（从0开始算起）
         * @return 文件名或者路径名
         */
        public String getRegItemName(int n) {
            StringBuilder strb = new StringBuilder();
            byte[] strs = new byte[3];
            strs[0] = this.get_Byte(n * REG_SIZE + 0);
            strs[1] = this.get_Byte(n * REG_SIZE + 1);
            strs[2] = this.get_Byte(n * REG_SIZE + 2);
            strb.append((char) strs[0]);
            strb.append((char) strs[1]);
            strb.append((char) strs[2]);
            return strb.toString();
        }
        /**
         * 判断磁盘块是否是空闲的
         * @return 如果是空闲的返回true，否则返回false
         */
        public boolean isFree() {
            for (int i = 0; i < REG_NUM; i ++) {
                if (this.get_Byte(i * REG_SIZE) != SPACE_AVAILIABLE) return false;
            }
            return true;
        }
    }
    //********************************************************************************************************************************

    /**
     * 将data数组中的字节数据写入当前类中存储的磁盘，从block_id盘块的第begin个字节（从0开始）开始写入。
     *
     * @param block_id 模拟磁盘中的盘块号
     * @param begin 被写入盘块中的开始写入的起始字节下标
     * @param data 被写入的数据，为字节数组
     * @return true if success, false if fail, otherwise
     */
    public boolean writeToBlock(int block_id, int begin, byte[] data) {
        if (begin + data.length - 1 > BLOCK_SIZE - 1) {
            return false;
        }
        for (int i = 0; i < data.length; i++) {
            this.simDisk[block_id].set_Byte(begin + i, data[i]);
            //System.out.println(this.simDisk[block_id].get_Byte(i));
        }
        return true;
    }

    /**
     * 从模拟磁盘中加载信息，包括FAT和已经创建好并保存在模拟磁盘上的文件
     */
    public void loadFromDisk() {
        try (DataInputStream input = new DataInputStream(new FileInputStream("tempDisk.dat"));) {
            for (int i = 0; i < DISK_SIZE; i++) {                                  //加载模拟磁盘中128个块的信息              
                for (int j = 0; j < 64; j++) {
                    byte b = input.readByte();
                    this.simDisk[i].set_Byte(j, b);
                    //if (j == 2) System.out.println(b);
                }
            }
        } catch (IOException ex) {

        }
    }

    /**
     * 将类中存储的磁盘数据更新到模拟磁盘文件中
     */
    public void updatToDisk() {
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream("tempDisk.dat"));) {
            for (int i = 0; i < DISK_SIZE; i++) {
                output.write(this.simDisk[i].getBlock());
                //System.out.println(this.simDisk[i].get_Byte(2));
            }
        } catch (IOException ex) {

        }
    }

    /**
     * 分配磁盘空间后更新FAT相应表项
     *
     * @param diskNum FAT项中的盘块号
     * @param nextNum FAT项中的内容
     */
    public void fat_alloc(int diskNum, int nextNum) {
        if (diskNum <= 63) {
            FAT[0].set_Byte(diskNum, (byte) nextNum);
        } else {
            FAT[1].set_Byte(diskNum - 64, (byte) nextNum);
        }
    }

    /**
     * 回收磁盘空间后更新FAT相应表项， FAT占两个盘块
     *
     * @param diskNum FAT项中的盘块号
     */
    public void fat_retrieve(int diskNum) {
        if (diskNum <= 63) {
            FAT[0].set_Byte(diskNum, (byte) 0);
        } else {
            FAT[1].set_Byte(diskNum - 64, (byte) 0);
        }
    }

    /**
     * 将当前当前FAT更新到模拟磁盘的FAT数据中
     */
    public void updateFATToDisk() {

    }

    /**
     * 将模拟磁盘中的FAT数据加载到当前FAT中
     */
    public void loadFATfromDisk() {
        try (DataInputStream input = new DataInputStream(new FileInputStream("tempDisk.dat"));) {
            for (int i = 0; i < FAT.length; i++) {
                for (int j = 0; j < 64; j++) {
                    this.FAT[i].set_Byte(j, input.readByte());           //FAT每项占用1B
                    System.out.println(i * 64 + j + "  " + Integer.toBinaryString(this.FAT[i].get_Byte(j)));
                }
            }
        } catch (IOException ex) {
        }
    }

    /**
     * 显示模拟磁盘中的字节信息
     */
    public void displayDisk() {
        for (int i = 0; i < DISK_SIZE; i++) {  //i * 64 + j 
            System.out.print(i + " ");
            for (int j = 0; j < 64; j++) {
                System.out.print((int) (this.simDisk[i].get_Byte(j)) + " ");
            }
            System.out.println();
        }
    }

    /**
     * 查找特定目录，返回该目录下的盘块
     *
     * @param Path 目录路径
     * @return 返回盘块号
     */
    public int searchDir(String Path) {
        String[] dirs = Path.split("\\\\");
        /*for (String dir : dirs) {
         System.out.print(dir + " ");
         }
         System.out.println();*/
        if (!dirs[0].equals("root")) return -1;
        int curBlock = ROOT_LOC;
        for (int i = 1; i < dirs.length; i++) {
            for (int j = 0; j < 1; j++) {
                //System.out.print(this.simDisk[curBlock].getRegItemName(j) + " ");
                String name = this.simDisk[curBlock].getRegItemName(j);
                if (i != 1 && name.equals(dirs[i - 1])) {    //equals
                    curBlock = this.simDisk[curBlock].getRegItem(j)[6];
                    // System.out.print(curBlock);
                    break;
                } else if (i == 1) {
                    curBlock = this.simDisk[curBlock].getRegItem(j)[6];
                    break;
                }
                //System.out.println();
            }
        }
        return curBlock;
    }

    /**
     * 检查文件目录，确认路径下是否包含路径名所示的文件后
     *
     * @param Path 文件路径名
     * @return 如果包含，返回true，否则返回false
     */
    public boolean isContainItem(String Path) {
        String[] dirs = Path.split("\\\\");
        int curBlock = ROOT_LOC, i = 0;
        for (i = 1; i < dirs.length; i++) {
            for (int j = 0; j < 1; j++) {
                String name = this.simDisk[curBlock].getRegItemName(j);
                if (name.equals(dirs[i - 1])) {
                    curBlock = this.simDisk[curBlock].getRegItem(j)[6];
                    break;
                }
            }
        }
        if (i == dirs.length - 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 在dir目录所在盘块中创建一个新的entry。
     *
     * @param dir 文件登记项创建的目录
     * @param entry 文件登记项
     * @return true if allocate successfully, false if allocate unsuccessfully
     */
    public boolean fileRegister(String dir, FileRegEntry entry) {
        return true;
    }
    /**
     * 寻找磁盘中第一个空闲的磁盘块并返回该磁盘块的位置
     * @return 如果存在，返回该磁盘块的位置，否则返回 -1
     */
    public int findNextAvailiableBlock() {
        int blockNum = 3;
        for (int i = blockNum; i < DISK_SIZE; i ++) {
            if (this.simDisk[i].isFree()) return i;
        }
        return -1;  //磁盘不存在空闲的磁盘块
    }
    
    /**
     * 释放从起始块的第n0个字节到终止块的第n1个字节的指定连续磁盘空间，
     * @param block_begin
     * @param block_end
     * @param byte_begin
     * @param byte_end
     * @return 
     */
    public boolean freeSpaceFromDisk(int block_begin, int block_end, int first_byte_begin, int last_byte_end) {
        if (block_begin < 0 || block_end > DISK_SIZE || first_byte_begin < 0 || last_byte_end > BLOCK_SIZE) return false;
        for (int i = first_byte_begin; i < BLOCK_SIZE; i ++) this.simDisk[block_begin].set_Byte(i, SPACE_AVAILIABLE);
        if (block_begin == block_end) {
            this.updatToDisk();          //更新磁盘
            return true;
        }                  //如果起始块等于终于块即可返回
        for (int i = 0; i <= last_byte_end; i++) this.simDisk[block_end].set_Byte(i, SPACE_AVAILIABLE);
        if (block_begin + 1 == block_end) {
            this.updatToDisk();         //更新磁盘
            return true;
        }    
        for (int i = block_begin - 1; i < block_end; i ++) {
            for (int j = 0; j < BLOCK_SIZE; j ++ ) this.simDisk[i].set_Byte(j, SPACE_AVAILIABLE);
        }
        this.updatToDisk();               //更新磁盘
        return true;
    }
    
    
    
}

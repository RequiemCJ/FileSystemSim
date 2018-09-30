/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 * 实验中模拟的一些文件操作
 *
 * @author chenjiawei
 */
public class SimFile {
    private static final int NAME_BEGIN = 0;   //登记项中文件名或目录名的起始位置
    private static final int TYPENAME_BEGIN = 3;    //登记项中文件类型的起始位置 目录登记项该位置为保留字
    private static final int ATTRI_LOC = 5;         //登记项中文件属性或目录属性的位置
    private static final int BLOCKBEGIN_LOC = 6;        //登记项中文件或目录起始盘块号的位置
    private static final int FILESIZE_LOC = 7;          //登记项中文件长度的位置  目录登记项该位置为保留字节
    
    //private static Disk disk = new Disk();            //将模拟磁盘绑定到当前类
    public static Disk disk = new Disk();            //将模拟磁盘绑定到当前类

    /**
     * 已打开文件表中读、写指针的结构
     */
    public class pointer {

        int dnum;           //磁盘盘块号
        int bnum;           //磁盘盘块内第几个字节

        pointer(int dnum, int bnum) {
            this.dnum = dnum;
            this.bnum = bnum;
        }

        pointer() {
            this.dnum = 0;
            this.bnum = 0;
        }
    }

    /**
     * 已打开文件表项类型定义
     */
    public class OFTLE {

        char[] fileName = new char[20];          //文件绝对路径名
        char fileAttri;                                 //文件的属性，用1个字节表示，所以此用char类型
        int startDBNum;                                     //文件起始盘块号
        int fileLen;                                        //文件长度，文件占用的字节数
        int flag;                                          //操作类型，用“0”表示以读操作方式打开文件，用“1”表示以写操作方式打开文件。
        pointer read = new pointer();        //读文件的位置，文件打开时dnum为文件起始盘块号，bnum为“0”
        pointer write = new pointer();      //写文件的位置，文件刚建立时dnum为起始盘块号，bnum为“0”，打开文件时dnum和bnum为文件的末尾

        OFTLE() {

        }

        public OFTLE(char[] fileName, char fileAttri, int startDBNum, int fileLen, int flag, pointer read, pointer write) {
            this.fileName = fileName;
            this.fileAttri = fileAttri;
            this.startDBNum = startDBNum;
            this.fileLen = fileLen;
            this.flag = flag;
            this.read = read;
            this.write = write;
        }
    }

    /**
     * 已打开文件登记表定义
     */
    public class openFile {

        OFTLE[] file;                   //已打开文件登记表
        int length;                     //已打开文件登记表中登记的文件数量

        openFile() {

        }
    }
    /**
     * 在特定盘块的空闲登记项空间进行登记v
     * @param block 进行登记的位置，也即磁盘块号
     * @param n 磁盘块中进行登记的登记项空间位置
     * @param regInfo 登记项数据，大小为8B
     * @return 登记成功返回true，登记失败返回false
     */
    private static boolean register_Item(int block, int n, byte[] regInfo) {
        if (n < 0 || n > 7) return false;
        disk.getSimDisk()[block].setRegItem(n, regInfo);
        return true;
    }
    
    
    /**
     * 建立新文件的文件操作
     * @param fileName 新建文件的文件名
     * @param fileAttri 文件属性
     * @return 创建成功返回true，创建失败返回false
     */
    public static boolean create_file(String fileName, byte fileAttri) {
        String[] dirs = fileName.split("\\\\");
        byte[] regData = new byte[8];
        if (dirs[0] != "root")
        if ((int) fileAttri % 2 == 1 || disk.isContainItem(fileName)) {  //判断是否为只读属性 或者 检查文件目录发现有重名文件
            if ((int) fileAttri % 2 == 1) System.out.println("文件属性为只读属性");
            else if(disk.isContainItem(fileName)) System.out.println("文件已存在，建立文件失败");
            return false;
        }
        int curBlock = disk.searchDir(fileName);      //定位到当前文件目录块
        if (curBlock == -1 ) {
            System.out.println("路径名错误");
            return false;
        }              
        int availLoc = disk.getSimDisk()[curBlock].findAvailableRegItem();   //寻找空闲登记项的位置
        if (availLoc == -1 ) {
            System.out.println("无空闲登记项");
            return false;
        }              
        // 文件名+文件属性
        if (dirs[dirs.length - 1].length() > 3) {
            System.out.println("文件名超出限制");
            return false;
        } else {
            for (int i = 0; i < dirs[dirs.length - 1].length(); i ++) {
                regData[NAME_BEGIN + i] = (byte)dirs[dirs.length - 1].charAt(i);
            }
        }
        
        regData[ATTRI_LOC] = fileAttri;         
        //regData[BLOCKBEGIN_LOC] = 0;            //文件的起始盘块号
        //regData[FILESIZE_LOC] = 0;                         //文件长度
        /*填写已打开文件表*/
        System.out.println("建立文件成功");
        return true;
    }

    /*  public static boolean open_file() {
        
     }
    
     public static boolean read_file() {
        
     }
    
     public static boolean write_file() {
        
     }
    
     public static boolean close_file() {
        
     }
    
     public static boolean delete_file() {
        
     }
     */
    public static void typefile() {

    }

    public static void change() {

    }
}

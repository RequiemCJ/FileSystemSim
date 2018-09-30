/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *目录登记项
 * @author chenjiawei
 */
public class DirResEntry extends Entry {
    private Byte[] dirName = new Byte[3];          //目录名
    private Byte[] revByte1 = new Byte[2];          //保留2B
    private Byte dirAttri;                             //目录属性
    private Byte startDBnum;                            //起始盘块号
    private Byte revByte2;                             //保留1B

    public Byte[] getDirectoryName() {
        return dirName;
    }

    public void setDirectoryName(Byte[] directoryName) {
        this.dirName = directoryName;
    }

    public Byte getDirectoryAttribute() {
        return dirAttri;
    }

    public void setDirectoryAttribute(Byte directoryAttribute) {
        this.dirAttri = directoryAttribute;
    }

    public Byte getStartDBnum() {
        return startDBnum;
    }

    public void setStartDBnum(Byte startDBnum) {
        this.startDBnum = startDBnum;
    }
    
    public DirResEntry() {
       
    }
}

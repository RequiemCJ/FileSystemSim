/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 * 文件登记项
 * @author chenjiawei
 */
public class FileRegEntry extends Entry {
    private Byte[] fileName = new Byte[3];          //文件名
    private Byte[] fileType = new Byte[2];          //文件类型名
    private Byte fileAttri;                             //文件属性
    private Byte startDBnum;                            //起始盘块号
    private Byte fileLen;                             //文件长度
    
    public FileRegEntry() {
        
    }
    
    public Byte[] getFileName() {
        return fileName;
    }

    public void setFileName(Byte[] fileName) {
        this.fileName = fileName;
    }

    public Byte[] getFileType() {
        return fileType;
    }

    public void setFileType(Byte[] fileType) {
        this.fileType = fileType;
    }

    public Byte getFileAttribute() {
        return fileAttri;
    }

    public void setFileAttribute(Byte fileAttribute) {
        this.fileAttri = fileAttribute;
    }

    public Byte getStartDBnum() {
        return startDBnum;
    }

    public void setStartDBnum(Byte startDBnum) {
        this.startDBnum = startDBnum;
    }

    public Byte getFileLength() {
        return fileLen;
    }

    public void setFileLength(Byte fileLength) {
        this.fileLen = fileLength;
    }
}

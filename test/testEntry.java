/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chenjiawei
 */
public class testEntry {
    public static void main(String[] args) {
        char c = '$';
        byte bc = (byte)c;
        //System.out.println((char)bc + " " + bc) ;
        String s = "C:\\Users\\chenjiawei\\Desktop\\大三上学期\\操作系统分析与设计实习2018";
        search(s);
    }
    
    
    public static void search(String path) {
        String[] dirs = path.split("\\\\");   //     ------ \ or \\ is invalid.
        for (int i = 0; i < dirs.length; i ++) {
            System.out.println(dirs[i]);
        }
        
    }
    
    
}

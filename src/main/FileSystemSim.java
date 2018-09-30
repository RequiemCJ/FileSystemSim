/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.Disk;
import data.SimFile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import panels.DesktopPanel;

/**
 *
 * @author chenjiawei
 */
public class FileSystemSim extends Application {

    @Override
    public void start(Stage primaryStage) {

        //TreeViewSample testPane= new TreeViewSample();
        DesktopPanel test = new DesktopPanel();
        //Dock test = new Dock();
        //FATPanel test = new FATPanel();
        Scene scene = new Scene(test, 300, 250);

        //primaryStage.setMaximized(true);
        //primaryStage.setResizable(false);
        // primaryStage.initStyle(StageStyle.UNDECORATED);  
        primaryStage.setTitle("FileSystem");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // launch(args);
        Disk disk = new Disk();
        //SimFile.create_file("ddas", (byte)2);
        //disk.Init();
        //SimFile.updatToDisk();
        //disk.displayDisk();
        /*char n1 = 'h', n2 = 'k', n3 = '$';
         byte[] c0 = {'t', 'e', '0', 6, 6, 6, 96, 6};
         byte[] c1 = {'t', 'e', '1', 6, 6, 6, 101, 6};
         byte[] c2 = {'t', 'e', '2', 6, 6, 6, 104, 6};
         byte[] c3 = {'t', 'e', '3', 6, 6, 6, 109, 6};
         //byte[] data = {(byte)n1, (byte)n2, (byte)n3, 33, 1, 44, 5 };
         disk.writeToBlock(2, 0, c0);
         disk.writeToBlock(96, 0, c1);
         disk.writeToBlock(101, 0, c2);
         disk.writeToBlock(104, 0, c3);
         disk.updatToDisk();
        
         disk.displayDisk();
         disk.searchDir("te0\\te1\\te2\\te3");*/
        //SimFile.create_file("te0\\te1\\te2\\te3", (byte)3);
        SimFile.disk.freeSpaceFromDisk(111, 113, 0, 0);
        // SimFile.create_file("te0\\te1\\te2\\te3", (byte)4);
        //System.out.println(SimFile.disk.searchDir("root\\te1\\te2\\te3"));
        SimFile.disk.displayDisk();
    }

}

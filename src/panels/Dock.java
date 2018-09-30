/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 *
 * @author chenjiawei
 */
public class Dock extends HBox{
    private Button FileSystemBt = new Button(" ");
    //private Image fsImage;
    
    public Dock() {
        this.init();
    }
    
    private void init() {
        
        this.setMaxHeight(70);
        
        this.setStyle("-fx-background-color : grey");
        this.getChildren().addAll(FileSystemBt);
        this.setAlignment(Pos.CENTER);
        HBox.setMargin(this.FileSystemBt, new Insets(10, 10, 10, 10));
    }
}

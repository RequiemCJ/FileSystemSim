/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;

/**
 *
 * @author chenjiawei
 */
public class FATPanel extends ScrollPane {
    private final int FAT_SIZE = 128;               
    private GridPane gridPane = new GridPane();
    private Label[] FATItems = new Label[FAT_SIZE];
    private Separator[] spts = new Separator[FAT_SIZE];
    private Label[] FATContents = new Label[FAT_SIZE];

    public FATPanel() {
        this.init();
    }
    /**
     * 设置FAT特定项的内容为itemContent
     * @param itemOrder FAT项
     * @param itemContent FAT项的内容
     */
    public void setFATItem(int itemOrder, int itemContent) {       
        this.FATContents[itemOrder].setText(itemContent + " ");
    }
    
    public void setItemColor1() {
        
    }
    
    public void setItemColor2() {
        
    }

    private void init() {

        for (int i = 0; i < this.FATItems.length; i++) {
            this.FATItems[i] = new Label(i + "");
            this.FATItems[i].setMinSize(30, 30);
            this.FATItems[i].setAlignment(Pos.CENTER);
            this.FATItems[i].setStyle("-fx-border-color: rgb(49, 89, 23); -fx-border-radius: 30;");
            this.gridPane.add(this.FATItems[i], i, 0);
            GridPane.setMargin(this.FATItems[i], new Insets(2, 2, 2, 2));
        }

        for (int i = 0; i < this.spts.length; i++) {
            spts[i] = new Separator();
            this.gridPane.add(spts[i], i, 1);
        }

        for (int i = 0; i < this.FATContents.length; i++) {
            this.FATContents[i] = new Label("");
            this.FATContents[i].setMinSize(30, 30);
            this.FATContents[i].setAlignment(Pos.CENTER);
            this.FATContents[i].setStyle("-fx-border-color: rgb(49, 89, 23); -fx-border-radius: 30;");
            this.gridPane.add(this.FATContents[i], i, 2);
            GridPane.setMargin(this.FATContents[i], new Insets(2, 2, 2, 2));
        }

        this.setMinHeight(300);
        this.setMinWidth(1300);
        this.setContent(this.gridPane);
        this.setVbarPolicy(ScrollBarPolicy.NEVER);  //取消垂直滚动条

        //this.setPadding(new Insets(10, 10, 10, 10));
    }

}

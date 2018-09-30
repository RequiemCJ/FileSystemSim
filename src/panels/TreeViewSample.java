/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author chenjiawei
 */
public class TreeViewSample extends StackPane {

    private final ImageView rootNode = new ImageView(new Image(getClass().getResourceAsStream("/image/crossIcon.png")));
    private TreeItem<String> rootItem = new TreeItem<>("root", rootNode);
    private TreeItem<String> rootItem2 = new TreeItem<>("root", rootNode);
    private TreeView<String> tree = new TreeView<> (rootItem);

    public TreeViewSample() {
        this.init();
        this.getChildren().add(this.tree);
        this.setPrefSize(500, 500);
    }

    private void init() {
        rootNode.setFitHeight(16);
        rootNode.setFitWidth(16);
        
        this.rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<>("File" + i);
            rootItem.getChildren().add(item);
            rootItem2.getChildren().add(item);
        }
        rootItem.getChildren().add(rootItem2);
        //this.tree.setRoot(rootItem);
    }
}

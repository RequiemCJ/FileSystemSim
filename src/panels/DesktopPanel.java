/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import java.util.Stack;
import javafx.scene.layout.BorderPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;

/**
 *
 * @author chenjiawei
 */
public class DesktopPanel extends BorderPane{
    
    private Dock dock = new Dock();
    
    public DesktopPanel() {
        this.init();
        Stack<Character> s = new Stack<>();
    }
    
    private void init() {
      //  this.widthProperty().addListener(ov -> this.dock.se);
       this.setPrefSize(USE_PREF_SIZE, USE_PREF_SIZE);
       this.setBottom(this.dock);
        //this.setStyle("-fx-background-image: url(\"/image/desktopImage.jpg\");");
    }
    
}

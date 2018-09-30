/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author chenjiawei
 */
public class DiskPanel extends BorderPane {

    private final int DISK_SIZE = 128;
    private Label[] diskBlocks = new Label[DISK_SIZE];
    private GridPane gridPane = new GridPane();
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("已使用", 2), new PieChart.Data("未使用", 98));
    private PieChart chart = new PieChart(this.pieChartData);
    private Label caption = new Label("");
    private Byte[][] physicBlock = new Byte[DISK_SIZE][8];    //模拟磁盘有128个物理块，每个物理块大小为64B

    public DiskPanel() {
        this.init();
    }

    /**
     *
     * @param blockItem 磁盘块序号
     */
    private void setBlockUsed(int blockItem) {

    }

    private void init() {
        
        for (int i = 0; i < this.diskBlocks.length; i++) {
            /*可视化*/
            this.diskBlocks[i] = new Label(i + "");
            this.diskBlocks[i].setMinSize(30, 30);
            this.diskBlocks[i].setAlignment(Pos.CENTER);
            this.diskBlocks[i].setStyle("-fx-border-color: rgb(49, 89, 23); -fx-border-radius: 30;");
            this.gridPane.add(this.diskBlocks[i], i % 8, i / 8);
            GridPane.setMargin(this.diskBlocks[i], new Insets(2, 2, 2, 2));
        }
        this.gridPane.setAlignment(Pos.CENTER);

        this.chart.setTitle("磁盘使用情况");

        this.setCenter(this.gridPane);
        this.setRight(this.chart);

    }

}

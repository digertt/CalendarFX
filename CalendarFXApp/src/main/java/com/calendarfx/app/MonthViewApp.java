/*
 *  Copyright (C) 2017 Dirk Lemmermann Software & Consulting (dlsc.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.calendarfx.app;

import com.calendarfx.model.Calendar;
import com.calendarfx.view.MonthView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MonthViewApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        MonthView monthView = new MonthView();

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(monthView); // introPane);

        Calendar positive = new Calendar("Positive");
        Calendar mildNegative = new Calendar("Mild Negative");
        Calendar negative = new Calendar("Negative");
        Calendar trigger = new Calendar("Trigger Event");

        positive.setShortName("pos");
        mildNegative.setShortName("mng");
        negative.setShortName("neg");
        trigger.setShortName("trg");

        positive.setStyle(Calendar.Style.STYLE1);
        mildNegative.setStyle(Calendar.Style.STYLE2);
        negative.setStyle(Calendar.Style.STYLE3);
        trigger.setStyle(Calendar.Style.STYLE4);

        monthView.getCalendars().addAll(positive, mildNegative, negative, trigger);

        Scene scene = new Scene(stackPane);
        primaryStage.setTitle("Month View");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.calendarfx.app;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import com.calendarfx.model.Calendar;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import fr.brouillard.oss.cssfx.CSSFX;
import com.calendarfx.view.CalendarView;
import javafx.scene.canvas.Canvas;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import javafx.application.Platform;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Stage primaryStage;

    public void switchToIntroOne(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Intro1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIntroTwo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Intro2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIntroThree(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Intro3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIntroFour(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Intro4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void startCalendar(ActionEvent event) throws IOException {
        CalendarView calCore = new CalendarView();//alldayview
        calCore.setEnableTimeZoneSupport(true);
        calCore.setShowDeveloperConsole(true);
        Canvas testCanvas = new Canvas(150, 150);

        Calendar positive = new Calendar("Positive");
        Calendar mildNegative = new Calendar("Mild Negative");
        Calendar negative = new Calendar("Negative");
        Calendar trigger = new Calendar("Trigger Event");

        positive.setShortName("pos");
        mildNegative.setShortName("mng");
        negative.setShortName("neg");
        trigger.setShortName("trg");

        positive.setStyle(Style.STYLE1);
        mildNegative.setStyle(Style.STYLE2);
        negative.setStyle(Style.STYLE3);
        trigger.setStyle(Style.STYLE4);

        CalendarSource calCoreSource = new CalendarSource("Core");
        calCoreSource.getCalendars().addAll(positive, mildNegative, negative, trigger);

        calCore.getCalendarSources().setAll(calCoreSource);
        calCore.setRequestedTime(LocalTime.now());

        StackPane initialPane = new StackPane();
        initialPane.getChildren().addAll(calCore); // introPane);

        Thread timeUpdate = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calCore.setToday(LocalDate.now());
                        calCore.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        timeUpdate.setPriority(Thread.MIN_PRIORITY);
        timeUpdate.setDaemon(true);
        timeUpdate.start();

        Scene initialScene = new Scene(initialPane);
        initialScene.focusOwnerProperty().addListener(it -> System.out.println("focus owner: " + initialScene.getFocusOwner()));
        CSSFX.start(initialScene);

        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("WellTracked");
        primaryStage.setScene(initialScene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        primaryStage.show();

    }

}

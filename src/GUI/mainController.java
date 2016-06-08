package GUI;


import AppCommunication.CommunicationMediator;
import CentralPoint.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Qunfo on 29-3-2016.
 */
public class mainController implements Initializable {

    @FXML
    private ListView lvMissions;
    @FXML
    private ListView lvTeams;
    @FXML
    private ListView lvNotifications;

    private CentralPoint centralPoint;
    private ObservableList<Mission> missionListObservable;
    private ObservableList<Team> teamListObservable;
    private ObservableList<Notification> notificationObservableList;

    private Timer keepNotificationsUpToDate;

    //TODO JAVADOC
    public mainController() {
        try {
            centralPoint = new CentralPoint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        missionListObservable = FXCollections.observableArrayList(centralPoint.getAllMissions());
        teamListObservable = FXCollections.observableArrayList(centralPoint.getAllTeams());

        Thread comCom = new Thread(new Runnable() {
            @Override
            public void run() {
                new CommunicationMediator(centralPoint);
            }
        });

        comCom.start();

        keepNotificationsUpToDate = new Timer();
    }

    //TODO JAVADOC
    @FXML
    private void publishOnWebpage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Resources/publishOnWebScreen.fxml"));
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
            missionsdetailcontroller MC = loader.getController();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO JAVADOC
    private void createMissionView(Mission selectedMission) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Resources/missionscreen.fxml"));
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
            missionsdetailcontroller MC = loader.getController();
            MC.setMissionController(selectedMission, centralPoint);
            stage.setTitle("Missie : " + selectedMission.getName());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO JAVADOC
    public void ShowStaffInField() {

        ObservableList<Staff> staffOnLocation = centralPoint.getStaffOnLocation();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Resources/Task.fxml"));
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
            taskController taskControllers = loader.getController();
            taskControllers.setList(staffOnLocation);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Platform.runLater(() -> tvStaffOnLocation.setItems(staffOnLocation));
    }

    //TODO JAVADOC
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllElements();
        lvMissions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mission>() {
            @Override
            public void changed(ObservableValue observable, Mission oldValue, Mission newValue) {
                createMissionView((Mission) lvMissions.getSelectionModel().getSelectedItem());
            }
        });

        keepNotificationsUpToDate.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        notificationObservableList = FXCollections.observableList(centralPoint.getAllNotifications());
                        loadNotifications();
                    }
                }, 0, 1000
        );
    }

    //TODO JAVADOC
    private void loadAllElements() {
        loadTeams();
        loadMission();
        loadNotifications();
    }

    //TODO JAVADOC
    private void loadTeams() {
        lvTeams.setItems(teamListObservable);
    }

    //TODO JAVADOC
    private void loadMission() {
        lvMissions.setItems(missionListObservable);
    }

    //TODO JAVADOC
    private void loadNotifications() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lvNotifications.setItems(notificationObservableList);
            }
        });
    }

    //TODO JAVADOC
    public void makeNewMission() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Resources/screenmission.fxml"));
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
            createMissionController createMissionController = loader.getController();
            createMissionController.setCentralPoint(centralPoint);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

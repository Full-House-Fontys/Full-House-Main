package Database;

import CentralPoint.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhict on 06-05-16.
 */
public class DaoNotification extends DaoGeneric<Notification> {

    private final static String TABLENAME = DbTables.MELDING.toString();
    private final String ID = "ID";
    private final String Titel = "Titel";
    private final String Inhoud = "Inhoud";
    private final String Tijdstip= "Tijdstip";
    private final String MissieID = "MissieID";
    private final String GebruikerID = "GebruikerID";

    /**
     * uses daoGenerics
     * database class of Mission table
     * @param connection database connection
     */
    public DaoNotification(Connection connection) {
        super(connection, TABLENAME);
    }

    /**
     * Gives specific data when needed
     *
     * @param id for the specific item
     * @return list of objects
     */

    @Override
    public ObservableList<Notification> getSpecificList(int id) {
        List<Notification> notificationList = new ArrayList();
        ObservableList<Notification> notificationObservableList = FXCollections.observableArrayList(notificationList);
        ResultSet rs;

        String query = "SELECT * FROM " + TABLENAME + " WHERE MissieID="+id;

        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                notificationObservableList.add(new Notification(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6)));

            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return notificationObservableList;
    }

    /**
     * Gives list of objects
     *
     * @return list of objects
     */
    @Override
    public ObservableList<Notification> getAllRecord() {
        List<Notification> notificationList = new ArrayList();
        ObservableList<Notification> notificationObservableList = FXCollections.observableArrayList(notificationList);
        ResultSet rs;

        String query = "SELECT * FROM " + TABLENAME;

        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                notificationObservableList.add(new Notification(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6)));

            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return notificationObservableList;
    }

    /**
     * Updates the notification with an int as id
     * @param value list of to update with int
     * @param key   key of row
     *              Update bool in a table row
     * @return bool is completed
     */

    @Override
    public boolean update(Notification value, int key) {
        throw new NotImplementedException();
    }

    /**
     * Updates the notifiction with an String as id
     * @param value list of to update with int
     * @param key   key of row
     *              Update bool in a table row
     * @return bool is completed
     */

    @Override
    public boolean update(Notification value, String key) {
        throw new NotImplementedException();
    }

    /**
     * Insert data with given value
     *
     * @param value the notification to insert
     * @return true if done correctly
     */

    @Override
    public boolean insert(Notification value) {
        throw new NotImplementedException();
    }

    /**
     * Delete data in database of given key as int
     *
     * @param key to identify the notification to delete
     * @return true if done correctly
     */
    @Override
    public boolean delete(int key) {
        throw new NotImplementedException();
    }

    /**
     * Inserts two ints in a database, useful for multiple to multiple relationships with pairing tables
     *
     * @param id first id
     * @param id1 second id
     */

    @Override
    public void insertTwoInts(int id, int id1) {
        throw new NotImplementedException();
    }

    /**
     * get specific Notifcation object
     *
     * @param value object value
     * @param key   key
     * @return
     */

    @Override
    public Notification getObject(Notification value, int key) {
        throw new NotImplementedException();
    }
}

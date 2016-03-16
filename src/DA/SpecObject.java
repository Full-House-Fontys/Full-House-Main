package DA;

import java.awt.geom.Point2D;
import java.sql.ResultSet;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class SpecObject {
    public static Personeel getPojo(ResultSet rs) throws Exception {
        Personeel personeel = new Personeel();
        personeel.setName(rs.getString("Voornaam"));
        personeel.setLastName(rs.getString("Achternaam"));
        Point2D location = new Point2D.Double(rs.getDouble("LocatieX"), rs.getDouble("LocatieY"));
        personeel.setLocation(location);

        return personeel;
    }
}
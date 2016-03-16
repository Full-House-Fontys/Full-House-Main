package DA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class DBRead {
    static void closeAll(PreparedStatement ps, ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
        }
        try {
            ps.close();
        } catch (Exception e) {
        }
    }

    static  <T> ArrayList<String> getColums(String tableName){
        ArrayList<String> column = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String queryString = DBSpecifics.queryString(tableName, QueryType.COLUMN);
            //ps = conn.prepareStatement(queryString);
            ps.setString(1, tableName);
            rs = ps.executeQuery();
            if (rs.next()) {
                column.add(SpecObject.getColumn(rs));
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            closeAll(ps, rs);
        }
        return column;
    }

    public <T> T getPojoForPrimarKey(Connection conn, String tableName, String primaryKey) throws Exception {
        T currentPojo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String queryString = DBSpecifics.queryString(tableName, QueryType.READ, primaryKey);
            ps = conn.prepareStatement(queryString);
            ps.setInt(1, Integer.parseInt(primaryKey));

            rs = ps.executeQuery();
            if (rs.next()) {
                currentPojo = DBSpecifics.getPojoFromResultSet(tableName,
                        rs);
            }
        } finally {
            closeAll(ps, rs);
        }
        return currentPojo;
    }

    public <T> T getMaterials(Connection conn, String tableName) {
        List<T> materials = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        return null;
    }
}

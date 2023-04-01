import java.sql.*;

class Data{
    int subjectCode;
    String subName;
    String paperDetail;
    String url;
    Data(int subjectCode,String subName,String paperDetail,String url){
        this.subjectCode = subjectCode;
        this.subName = subName; this.paperDetail = paperDetail;
        this.url = url;
    }
}
public class Practical_01_DataBase {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/db";
    static final String USER = "root";
    static final String PASS = "MyN3wP4ssw0rd";
    public static Connection getConnection() throws Exception {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    static void createTable(Statement stmt){
        try{
            String sql = "CREATE TABLE Papers " +
                    "(subjectCode" +
                    " INTEGER not NULL, " +
                    " subName VARCHAR(255), " +
                    " paperDetail VARCHAR(255), " +
                    " url VARCHAR(255), " +
                    " PRIMARY KEY (subjectCode" +
                    "))";
            stmt.executeUpdate(sql);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    static void insertPaperDetail(Statement stmt,Data newData){
        try {
            String sql = "INSERT INTO Papers (subjectCode" +
                    ", subName, paperDetail, url) " +
                    "VALUES (" + newData.subjectCode
                    +
                    ", '" + newData.subName +
                    "', '" + newData.paperDetail +
                    "', '" + newData.url + "')";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static ResultSet searchData(Statement stmt){
        try{
            String sql = "SELECT subjectCode" +
                    ", subName, paperDetail, url FROM Papers";
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    static void updatePaperDetail(Statement stmt, Data updatedData){
        try {
            String sql = "UPDATE Papers " +
                    "SET subName = '" + updatedData.subName + "', " +
                    "paperDetail = '" + updatedData.paperDetail + "', " +
                    "url = '" + updatedData.url + "' " +
                    "WHERE subjectCode" +
                    " = " + updatedData.subjectCode
                    ;
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void deletePaperDetail(Statement stmt, int subjectCode){
        try {
            String sql = "DELETE FROM Papers WHERE subjectCode" +
                    " = " + subjectCode
                    ;
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static ResultSet searchData(Statement stmt, String searchString){
        ResultSet rs = null;
        try{
            String sql = "SELECT subjectCode, subName, paperDetail, url FROM Papers " +
                    "WHERE subjectCode LIKE '%" + searchString +
                    "%' OR subName LIKE '%" + searchString +
                    "%' OR paperDetail LIKE '%" + searchString + "%'";
            rs = stmt.executeQuery(sql);
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    static String getSearchResult(ResultSet rs) throws Exception {
        StringBuilder searchResult = new StringBuilder();
        while (rs.next()) {
        	searchResult.append(rs.getString("subjectCode")).append(" | ");
            searchResult.append(rs.getString("subName")).append(" | ");
            searchResult.append(rs.getString("paperDetail")).append(" | ");
            searchResult.append(rs.getString("url")).append("\n");
        }
        return searchResult.toString();
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = getConnection();
            stmt = conn.createStatement();

            // Creating table
//            createTable(stmt);

            //Insert data
//            insertPaperDetail(stmt,new Data(3160714,"DATA MINING","2019_paper","http://example.com/paper"));
//            insertPaperDetail(stmt,new Data(3160707,"Advance java Programming","2019_paper","http://example.com/paper"));
//            insertPaperDetail(stmt,new Data(3160002,"Contributor Personality Development Program","2019_paper","http://example.com/paper"));
//            insertPaperDetail(stmt,new Data(3160704,"THEORY OF COMPUTATION","2019_paper","http://example.com/paper"));
//            insertPaperDetail(stmt,new Data(3160712,"MICROPROCESSOR AND INTERFACING","2019_paper","http://example.com/paper"));
//            insertPaperDetail(stmt,new Data(3160717,"DATA VISUALIZATION","2019_paper","http://example.com/paper"));

            // Updating paper detail
//            updatePaperDetail(stmt, new Data(3160714,"DATA MINING","2022_paper","http://example.com/paper"));

            // Deleting paper detail
//            deletePaperDetail(stmt, 3160714);

            //search from database
            rs = searchData(stmt,"");
            // Displaying data
            System.out.println(getSearchResult(rs));

            if(rs!=null)rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // close resources
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

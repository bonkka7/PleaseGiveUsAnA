import java.sql.*;

public class Reservation{
    private String user;
    private int idx;
    static Connection reservationConn = null;
    //Possibly connecting to front end through here - local hosting on spring boot

    public static void main(String[] args){
        try{
            reservationConn = DriverManager.getConnection("jdbc:mysql://128.153.191.132:3306/pguaa", "pleaseguaa", "756337");
            System.out.println("good");
            reservationConn.close();
        }catch(SQLException e){
            System.out.println("Cannot connect to database: " + e.getMessage());
        }

    }

    public int signIn(String user, String pass) throws SQLException {
        String findUser = "SELECT password, idx FROM Users WHERE username = user";
        Statement userFind = reservationConn.createStatement();
        ResultSet res = userFind.executeQuery(findUser);
        if(res.next()){
            if(res.getString("password") == pass){
                this.user = user;
                this.idx = res.getInt("idx");
                return 0; //signed in all good
            }
            return 1; //wrong password
        }
        return 2; //username not found
    }

    public int createUser(String user, String pass) throws SQLException {
        if(signIn(user,pass) != 2){return 1;} // username already used
        Statement add = reservationConn.createStatement();
        add.executeUpdate("INSERT INTO Users(username, password, idx) VALUES (user, pass, 0)");
        String tbl = "CREATE TABLE " + user + "Recipe(ID int, name VARCHAR(64), description VARCHAR(150), picture VARCHAR(2000), meal CHAR(1), timeCook int, servings int, avgRate int, instructions TEXT, PRIMARY KEY(ID))";
        add.execute(tbl);//recipe table
        tbl = "CREATE TABLE " + user + "Utensils(recipeID int, utensil VARCHAR(32), FOREIGN KEY(recipeID) REFERENCES " + user + "Recipe(ID) ON DELETE CASCADE)";
        add.execute(tbl);//utensils table
        tbl = "CREATE TABLE " + user + "Ingredients(recipeID int, ingredients VARCHAR(32), FOREIGN KEY(recipeID) REFERENCES " + user + "Recipe(ID) ON DELETE CASCADE)";
        add.execute(tbl);//ingredients table
        tbl = "CREATE TABLE " + user + "Review(recipeID int, rating int, review TEXT, FOREIGN KEY(recipeID) REFERENCES " + user + "Recipe(ID) ON DELETE CASCADE)";
        add.execute(tbl);
        add.close();
        return 0; //added fine
    }


}
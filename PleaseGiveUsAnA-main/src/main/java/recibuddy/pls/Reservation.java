package recibuddy.pls;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reservation{
    private String user;
    private int idx;
    static Connection reservationConn = null;

    public int signIn() throws SQLException {
        return signIn("Anon", "");
    }
    public int signIn(String user, String pass) throws SQLException {
        try{
            reservationConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pguaa", "root", "756337");
            String findUser = "SELECT password, idx FROM Users WHERE username = '" + user + "'";
            Statement userFind = reservationConn.createStatement();
            ResultSet res = userFind.executeQuery(findUser);
            if(res.next()){
                if(Objects.equals(res.getString("password"), pass)){
                    this.user = user;
                    this.idx = res.getInt("idx");
                    reservationConn.close();
                    return 0; //signed in all good
                }
                reservationConn.close();
                return 1; //wrong password
            }
            reservationConn.close();
            return 2; //username not found
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 3; //didn't connect
    }

    public int createUser(String user, String pass) throws SQLException {
        if(signIn(user,pass) != 2){return 1;} // username already used
        try{
            reservationConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pguaa", "root", "756337");
            Statement add = reservationConn.createStatement();
            add.executeUpdate("INSERT INTO Users(username, password, idx) VALUES ('" + user + "', '" + pass + "', 0)");
            add.close();
            this.user = user;
            this.idx = 0;
            return 0; //added fine
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 2;
    }

    public int addRecipe(String user, String name, String descr, String pic, char meal, int time, int servings, String instr, List<String> ing, List<Integer> amount, List<String> measure, List<String> ut) throws SQLException {
        int x = 1;
        try{
            reservationConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pguaa", "root", "756337");
            Statement add = reservationConn.createStatement();
            String findUser = "SELECT password, idx FROM Users WHERE username = '" + user + "'";
            Statement userFind = reservationConn.createStatement();
            ResultSet res = userFind.executeQuery(findUser);
            int idx = 0;
            if(res.next()) {
                idx = res.getInt("idx");
            }
            x = addRecipe2(idx,user,name,descr,pic,meal,time,servings,0,instr,ing,amount,measure,ut);
            idx++;
            String update = "UPDATE Users SET idx = " + idx + " WHERE username = '" + user + "'";
            add.executeUpdate(update);
            add.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return x; //couldn't add
    }

    public int editRecipe(int recipeID, String user, String name, String descr, String pic, char meal, int time, int servings, int rate, String instr, List<String> ing, List<Integer> amount, List<String> measure, List<String> ut) throws SQLException {
        try{
            reservationConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pguaa", "root", "756337");
            Statement add = reservationConn.createStatement();
            String update = String.format("DELETE FROM Recipe(ID, user, name, description, picture, meal, timeCook, servings, avgRate, instructions) WHERE ID = %d AND user = '%s'", recipeID,user);
            add.executeUpdate(update);
            add.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return addRecipe2(recipeID,user,name,descr,pic,meal,time,servings,rate,instr,ing,amount,measure,ut);
    }

    public int addRecipe2(int idx, String user, String name, String descr, String pic, char meal, int time, int servings, int rate, String instr, List<String> ing, List<Integer> amount, List<String> measure, List<String> ut) throws SQLException {
        try{
            reservationConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pguaa", "root", "756337");
            Statement add = reservationConn.createStatement();
            String update = String.format("INSERT INTO Recipe(ID, user, name, description, picture, meal, timeCook, servings, avgRate, instructions) VALUES (%d, '%s', '%s', '%s', '%s', '%c', %d, %d, %d, '%s')", idx, user, name, descr, pic, meal, time, servings, rate, instr);
            add.executeUpdate(update);
            for(int x = 0; x < ing.size(); x++){
                update = String.format("INSERT INTO Ingredients(recipeID, user, ingredients, amount, measurement) VALUES (%d, '%s', '%s', %d, '%s')", idx, user, ing.get(x), amount.get(x), measure.get(x));
                add.executeUpdate(update);
            }
            for (String s : ut) {
                update = String.format("INSERT INTO Utensils(recipeID, user, utensil) VALUES (%d, '%s', '%s')", idx, user, s);
                add.executeUpdate(update);
            }
            add.close();
            return 0; //added fine
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 1; //couldn't add
    }

    public int deleteRecipe(int recipeID, String user){
        try{
            reservationConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pguaa", "root", "756337");
            Statement add = reservationConn.createStatement();
            String update = "DELETE FROM Recipe WHERE ID = " + recipeID + " AND user = '" + user + "'";
            add.executeUpdate(update);
            add.close();
            reservationConn.close();
            return 0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 1;
    }

    //doesn't work
    public int addReview(int id, String user, int rate, String review){
        try{
            reservationConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pguaa", "root", "756337");
            Statement add = reservationConn.createStatement();
            String update = "INSERT INTO Review(recipeID, user, rating, review) VALUES (" + id + ", '" + user + "', " + rate + ", '" + review + "')";
            add.executeUpdate(update);
            update = "SELECT rating FROM Review WHERE recipeID = " + id + " AND user = '" + user + "'";
            int count = 0;
            int sum = 0;
            ResultSet res = add.executeQuery(update);
            while (res.next()) {
                sum += res.getInt("rating");
                count++;
            }
            res.close();
            sum = sum / count;
            update = "UPDATE Recipe SET avgRate = " + sum + " WHERE recipeID = " + id + " AND user = '" + user + "'";
            add.executeUpdate(update);
            add.close();
            reservationConn.close();
            return 0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 1;
    }

    public List<Recipe> nameSearch(String recipeName){
        String statement = "SELECT * FROM Recipe WHERE name LIKE '%" + recipeName + "%'";
        return search(statement);
    }
    public List<Recipe> userName(String user, String recipeName){
        List<Recipe> names = nameSearch(recipeName);
        for(int i = 0; i < names.size(); i++){
            if(names.get(i).getUser() != user){
                names.remove(i);
                i--;
            }
        }
        return names;
    }

    public List<Recipe> userSearch(String user){
        String statement = "SELECT * FROM Recipe WHERE user = '" + user + "'";
        return search(statement);
    }

    public List<Integer> userIDSearch(String user){
        String statement = "SELECT * FROM Recipe WHERE user = '" + user + "'";
        List<Integer> ret = new ArrayList<>();
        List<Recipe> rec= search(statement);
        for (Recipe recipe : rec) {
            ret.add(recipe.getID());
        }
        return ret;
    }

    public List<Recipe> ratingSearch(int rate){
        String statement = "SELECT * FROM Recipe WHERE avgRate >= " + rate;
        return search(statement);
    }

    public List<Recipe> userRating(String user, int rating){
        List<Recipe> names = ratingSearch(rating);
        for(int i = 0; i < names.size(); i++){
            if(names.get(i).getUser() != user){
                names.remove(i);
                i--;
            }
        }
        return names;
    }

    public List<Recipe> mealSearch(char meal){
        String statement = "SELECT * FROM Recipe WHERE meal = '" + meal + "'";
        return search(statement);
    }

    public List<Recipe> userMeal(String user, char meal){
        List<Recipe> names = mealSearch(meal);
        for(int i = 0; i < names.size(); i++){
            if(names.get(i).getUser() != user){
                names.remove(i);
                i--;
            }
        }
        return names;
    }

    public List<Recipe> search(String query){
        List<Recipe> recipes = new ArrayList<>();
        try {
            reservationConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pguaa", "root", "756337");
            Statement add = reservationConn.createStatement();
            ResultSet res = add.executeQuery(query);
            while (res.next()) {
                int id = res.getInt("recipeID");
                String user = res.getString("user");
                String name = res.getString("name");
                String descr = res.getString("description");
                String pic = res.getString("picture");
                char meal = res.getString("meal").charAt(0);
                int time = res.getInt("timeCook");
                int servings = res.getInt("servings");
                int rate = res.getInt("avgRate");
                String instr = res.getString("instructions");
                Recipe recipe = new Recipe(id,user,name,descr,pic,meal,time,servings,rate,instr);
                Statement ingstat = reservationConn.createStatement();
                String statement = "SELECT * FROM Ingredient WHERE recipeID = " + id + " AND user = '" + user + "'";
                ResultSet ingres = ingstat.executeQuery(statement);
                while(ingres.next()) {
                    String ing = ingres.getString("ingredients");
                    String measurement = ingres.getString("measurement");
                    int amount = ingres.getInt("amount");
                    recipe.addIngredient(new Ingredient(ing, amount, measurement));
                }
                ingres.close();
                statement = "SELECT * FROM Utensils WHERE recipeID = " + id + " AND user = '" + user + "'";
                ResultSet utres = ingstat.executeQuery(statement);
                while(utres.next()) {
                    recipe.addUtensil(utres.getString("utensil"));
                }
                utres.close();
                ingstat.close();
                recipes.add(recipe);
            }
            reservationConn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return recipes;
    }

    public List<Recipe> ingredientSearch(List<String> ings){
        List<Recipe> recipes = new ArrayList<>();
        try {
            reservationConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pguaa", "root", "756337");
            Statement add = reservationConn.createStatement();
            for (String ing : ings) {
                String statement = "SELECT * FROM Ingredients WHERE ingredient LIKE '%" + ing + "%'";
                ResultSet res = add.executeQuery(statement);
                while (res.next()) {
                    int id = res.getInt("recipeID");
                    String user = res.getString("user");
                    List<Recipe> moros = search("SELECT * FROM Recipe WHERE ID = " + id + " AND user = '" + user + "'");
                    recipes.removeAll(moros);
                    recipes.addAll(moros);
                }
                res.close();
            }
            add.close();
            reservationConn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return recipes;
    }

    public List<Recipe> userIngredient(String user, List<String> ings){
        List<Recipe> names = ingredientSearch(ings);
        for(int i = 0; i < names.size(); i++){
            if(names.get(i).getUser() != user){
                names.remove(i);
                i--;
            }
        }
        return names;
    }

    public List<Recipe> allergenSearch(List<String> allergen){
        List<Recipe> with = ingredientSearch(allergen);
        List<Recipe> all = search("SELECT * FROM Recipe");
        all.removeAll(with);
        return all;
    }

    public List<Recipe> userAllergen(String user, List<String> allergen){
        List<Recipe> names = allergenSearch(allergen);
        for(int i = 0; i < names.size(); i++){
            if(names.get(i).getUser() != user){
                names.remove(i);
                i--;
            }
        }
        return names;
    }

}

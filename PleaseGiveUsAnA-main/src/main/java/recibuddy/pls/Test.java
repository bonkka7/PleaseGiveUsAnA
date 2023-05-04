package recibuddy.pls;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args){
        Reservation using = new Reservation();
        //checking signIn
        /*try{
            int x = using.signIn("Anon", "");
            System.out.println(x);
        } catch (SQLException e) {
            System.out.println("Error. Could not sign in.");
        }*/
        //Checking createUser, with new user and existing
        /*try{
            int x = using.createUser("Anon", "1234");
            System.out.println(x);
        } catch (SQLException e) {
            System.out.println("Error. Could not create user.");
        }
        try{
            int x = using.createUser("bonkka", "1234");
            System.out.println(x);
        } catch (SQLException e) {
            System.out.println("Error. Could not create user.");
        }*/
        //trying to signIn on new created user
        /*try{
            int x = using.signIn("bonkka", "1234");
            System.out.println(x);
        } catch (SQLException e) {
            System.out.println("Error. Could not sign in.");
        }*/

        try{
            int x = using.signIn();
            System.out.println(x);
        }catch (SQLException e) {
            System.out.println("Error. Could not sign in.");
        }

        //testing deleteRecipe
        /*int i = using.deleteRecipe(0);
        System.out.println(i);*/


       /* String name = "Ramen";
        String descr = "Such a great meal to feed the sobbing children.";
        String pic = "picture.com";
        char meal = 'd';
        int time = 20;
        int servings = 1;
        String instr = "Put water in pot over medium heat. Cover and wait to boil. Add noodles, cover. Cook for 7 minutes, stir with spoon half way through. Drain most of water and add seasoning. Turn off heat. Pour into bowl and let sit for 5 minutes before consumption.";
        List<String> ing = new ArrayList<>();
        ing.add("Water");
        ing.add("Ramen noodles");
        List<Integer> amount = new ArrayList<>();
        amount.add(2);
        amount.add(1);
        List<String> measure = new ArrayList<>();
        measure.add("Cups");
        measure.add("bag");
        List<String> ut = new ArrayList<>();
        ut.add("Bowl");
        ut.add("Pot");
        ut.add("Spoon");
        ut.add("Fork");

        try{
            int x = using.addRecipe(name,descr,pic,meal,time,servings,instr,ing,amount,measure,ut);
            System.out.println(x);
        }catch (SQLException e) {
            System.out.println("Error. Could not add recipe.");
        }*/


        //doesn't work. have to fix :(
        /*int x = using.addReview(3, "bonkka", 2, "It was alright but it was not very flavorful");
        System.out.println(x);*/
    }
}

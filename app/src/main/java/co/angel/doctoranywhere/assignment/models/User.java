package co.angel.doctoranywhere.assignment.models;

import com.google.gson.Gson;

import java.util.ArrayList;

public class User {
    public String name;
    public String image;
    public ArrayList<String> items;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdrental;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author user
 */
@Named(value = "actorController")
@SessionScoped
public class ActorController implements Serializable {

    // these fields map to components in the actor.xhtml
    String firstName;
    String lastName;
    String response;

    // this is our class that uses Hibernate to query the database
    ActorHelper helper;
    // this is our Actor POJO
    Actor actor;

    public ActorController() {
        helper = new ActorHelper();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getResponse() {
        // if the firstname and lastname components in the actor.xhtml
        // have data in them, then insert it into the database
        if (firstName != null && lastName != null) {

            // get the current timestamp in SQL format
            Date date = new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());

            // initialize an actor so that it contains the data
            // input in the actor.xhtml
            actor = new Actor(firstName, lastName, timeStamp);

            // call the helper method that inserts the data into
            // the database
            if (helper.insertActor(actor) == 1) {
                // if the insert works
                firstName = null;
                lastName = null;
                response = "Actor Added.";
                return response;
            } else {
                // if the insert doesn't work
                firstName = null;
                lastName = null;
                response = "Actor Not Added.";
                return response;
            }
        } else {
            // if nothing was input into the form
            //don't display a message 
            response = " ";
            return response;
        }
    }

    public void setResponse(String response) {
        this.response = response;
    }

}

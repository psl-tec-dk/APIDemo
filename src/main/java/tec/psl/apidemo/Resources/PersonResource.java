package tec.psl.apidemo.Resources;

import tec.psl.apidemo.DataModels.Person;
import tec.psl.apidemo.DataService.PersonDataService;
import tec.psl.apidemo.DataSource.MySQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/person")
public class PersonResource {
    private PersonDataService dataService = new PersonDataService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Person> getAllPersons() {
        return dataService.getAllPersons();
    }

    @GET
    @Produces("text/plain")
    @Path("/{persId}")
    public String getPersonById(@PathParam("persId") int persId) {
        return "GET getPersonsById ID = " + persId;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person addPerson(Person person) {
        return dataService.addPerson(person);
    }

    @PUT
    @Produces("text/plain")
    @Path("/{persId}")
    public String updatePerson(@PathParam("persId") int persId) {
        return "PUT updatePerson ID = " + persId;
    }
    @DELETE
    @Produces("text/plain")
    @Path("/{persId}")
    public String deletePerson(@PathParam("persId") int persId) {
        return "DELETE deletePerson ID = " + persId;
    }

}
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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{persId}")
    public Person getPersonById(@PathParam("persId") int persId) {
        return dataService.getPersonById(persId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person addPerson(Person person) {
        return dataService.addPerson(person);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{persId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Person updatePerson(@PathParam("persId") int persId, Person person) {
        return dataService.updatePerson(persId, person);
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{persId}")
    public int deletePerson(@PathParam("persId") int persId) {
        return dataService.deletePerson(persId);
    }

}
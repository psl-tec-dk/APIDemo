package tec.psl.apidemo.DataService;


import tec.psl.apidemo.DataModels.Person;
import tec.psl.apidemo.DataSource.MySQL;

import java.util.ArrayList;

public class PersonDataService {

    private MySQL dbh = new MySQL();

    public ArrayList<Person> getAllPersons() {
        return dbh.getAllPersons();
    }

    public Person getPersonById(int persId) {
        return dbh.getPersonById(persId);
    }

    public Person addPerson(Person person) {
        return dbh.addPerson(person);
    }

    public Person updatePerson(int persId, Person person){
        return dbh.updatePerson(persId, person);
    }

    public int deletePerson(int persId){
        return dbh.deletePerson(persId);
    }
}

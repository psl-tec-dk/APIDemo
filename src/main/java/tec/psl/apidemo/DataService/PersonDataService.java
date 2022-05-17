package tec.psl.apidemo.DataService;


import tec.psl.apidemo.DataModels.Person;
import tec.psl.apidemo.DataSource.MySQL;

import java.util.ArrayList;

public class PersonDataService {

    private MySQL dbh = new MySQL();

    public ArrayList<Person> getAllPersons() {
        return dbh.getAllPersons();
    }

    public Person addPerson(Person person) {
        return dbh.addPerson(person);
    }
}

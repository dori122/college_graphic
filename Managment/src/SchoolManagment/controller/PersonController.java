package SchoolManagment.controller;

import SchoolManagment.dao.PersonDao;
import SchoolManagment.model.Person;
import java.util.List;

public class PersonController {
    private final PersonDao dao = new PersonDao();

    public Person add(Person p)           throws Exception { dao.insert(p); return p; }
    public List<Person> search(String l)  throws Exception { return dao.searchByLname(l); }
    public void update(Person p)          throws Exception { dao.update(p); }
    public void delete(int id)            throws Exception { dao.delete(id); }
}

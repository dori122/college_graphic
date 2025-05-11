package SchoolManagment.model;

/** Simple DTO matching your DB table. */
public class Person {
  private int id;
  private String fname;
  private String lname;

  public Person() { }
  public Person(int id, String fname, String lname) {
    this.id = id;
    this.fname = fname;
    this.lname = lname;
  }

  public int    getId()       { return id; }
  public void   setId(int id) { this.id = id; }

  public String getFname()      { return fname; }
  public void   setFname(String f) { this.fname = f; }

  public String getLname()       { return lname; }
  public void   setLname(String l) { this.lname = l; }
}

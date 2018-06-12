package db;

import java.util.List;

import vo.Person;

public interface DBConnection {
    /**
     * Close the connection.
     */
    public void close();

    /**
     * Save person into persons db.
     */
    public void addPerson(Person person);

    /**
     * Get person by firstname and lastname.
     */
    public Person getPerson(String firstName, String lastName);
    
    
    /**
     * get all entries from persons db.
     */
    public List<Person> getPersons();
    
    /**
     * check if the user exists in persons db.
     */ 
    public boolean isUserExist(String firstName, String lastName);
    
    /**
     * Return whether the admin credential is correct from admin db. (This is not needed for main
     * course, just for demo and extension)
     * 
     * @param userId
     * @param password
     * @return boolean
     */
    public boolean verifyLogin(String userId, String password);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preparation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nicolasvondru
 */
public class DBQuery {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public DBQuery(){
        emf = Persistence.createEntityManagerFactory("PreparationPU");
        em = emf.createEntityManager();
    }
    
    private void openConnection(){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }else{
            System.out.println("Connection already active...");
        }
    }
    
    private void closeConnection(){
        if(em.getTransaction().isActive()){
            em.getTransaction().commit();
        }else{
            System.out.println("No active connection...");
        }
    }
    
    
    public void insertPerson(Person person){
        openConnection();
        System.out.println("active...");
        em.persist(person);
        closeConnection();
    }
    
    public List getAllPersons(){
        openConnection();
        List persons = em.createNamedQuery("Person.findAll").getResultList();
        closeConnection();
        return persons;
    }
    public void deletePerson(Person person){
        openConnection();
        em.remove(person);
        closeConnection();
    }
    
    
    
}

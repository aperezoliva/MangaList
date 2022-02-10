/**
* Alexander Perez Oliva - aperezoliva
* CIS175
* Feb 9, 2022
*/
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListManga;

/**
 * @author krazy
 *
 */
public class MangaListHelper {
	static EntityManagerFactory emfactory =
			Persistence.createEntityManagerFactory("MangaList");
	
	public void insertItems(ListManga lm) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(lm);
		em.getTransaction().commit();
		em.close();
	}
	
	public void DeleteItems(ListManga deleteItem) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListManga> typedQuery = em.createQuery("select lm from ListManga lm where lm.author = :selectedAuthor and lm.title = :selectedTitle and lm.genre = :selectedGenre", ListManga.class);
		
		typedQuery.setParameter("selectedAuthor", deleteItem.getAuthor());
		typedQuery.setParameter("selectedTitle", deleteItem.getTitle());
		typedQuery.setParameter("selectedGenre", deleteItem.getGenre());
		
		typedQuery.setMaxResults(1);
		
		ListManga result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public void EditItems(ListManga editItem) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(editItem);
		em.getTransaction().commit();
		em.close();
	}
	
	public ListManga searchForItemById(int findId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListManga found = em.find(ListManga.class, findId);
		em.close();
		return found;
	}
	
	public List<ListManga> searchForItemByAuthor(String authorName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListManga> typedQuery = em.createQuery("select lm from ListManga lm where lm.author = :selectedAuthor", ListManga.class);
		typedQuery.setParameter("selectedAuthor", authorName);
		
		List<ListManga> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<ListManga> searchForItemByTitle(String titleName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListManga> typedQuery = em.createQuery("select lm from ListManga lm where lm.title = :selectedTitle", ListManga.class);
		typedQuery.setParameter("selectedTitle", titleName);
		
		List<ListManga> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<ListManga> searchForItemByGenre(String genreName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListManga> typedQuery = em.createQuery("select lm from ListManga lm where lm.genre = :selectedGenre", ListManga.class);
		typedQuery.setParameter("selectedGenre", genreName);
		
		List<ListManga> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<ListManga> displayAllManga(){
		EntityManager em = emfactory.createEntityManager();
		List<ListManga> allItems = em.createQuery("SELECT i FROM ListManga i").getResultList();
		return allItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}

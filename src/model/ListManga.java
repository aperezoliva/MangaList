/**
* Alexander Perez Oliva - aperezoliva
* CIS175
* Feb 10, 2022
*/
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author krazy
 *
 */

@Entity
@Table(name="titles")
public class ListManga {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="TITLE")
	private String title;
	@Column(name="AUTHOR")
	private String author;
	@Column(name="GENRE")
	private String genre;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public ListManga() {
		
	}
	
	public ListManga(String title, String author, String genre) {
		this.title = title;
		this.author = author;
		this.genre = genre;
	}
	
	public String returnMangaDetails() {
		return "Title: " + this.title + " | Author: " + this.author + " | Genre: " + this.genre;
	}
}

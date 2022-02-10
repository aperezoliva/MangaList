/**
* Alexander Perez Oliva - aperezoliva
* CIS175
* Feb 10, 2022
*/
package view;

import java.util.List;
import java.util.Scanner;

import controller.MangaListHelper;
import model.ListManga;

/**
 * @author krazy
 *
 */
public class MangaView {
	static Scanner in = new Scanner(System.in);
	static MangaListHelper lmh = new MangaListHelper();
	
	private static void addAManga() { // adds entity
		System.out.print("Enter the author: ");
		String author = in.nextLine(); 
		System.out.print("Enter the title: ");
		String title = in.nextLine();
		System.out.print("Enter the genre: ");
		String genre = in.nextLine();
		
		ListManga toAdd = new ListManga(title, author, genre);
		lmh.insertItems(toAdd); // adds all the information to the helper
	}
	
	private static void deleteManga() { // deletes entity
		System.out.print("Select author to delete: ");
		String author = in.nextLine();
		System.out.print("Select title to delete: ");
		String title = in.nextLine();
		System.out.print("Select genre to delete: ");
		String genre = in.nextLine(); // Not sure if I could just attach the genre to the title?
		
		ListManga toDelete = new ListManga(title, author, genre);
		lmh.DeleteItems(toDelete);
	}
	
	private static void editManga() { // edits entity
		System.out.println("---Run a search command (1 - 3)");
		System.out.println("---1. Search by author");
		System.out.println("---2. Search by title");
		System.out.println("---3. Search by genre");
		int searchQuery = in.nextInt();
		in.nextLine();
		List<ListManga> foundItems;
		
		if (searchQuery == 1) {
			System.out.print("Enter the author's name: ");
			String authorName = in.nextLine();
			foundItems = lmh.searchForItemByAuthor(authorName);
		} else if (searchQuery == 2) {
			System.out.print("Enter the manga's title: "); 
			String mangaTitle = in.nextLine();
			foundItems = lmh.searchForItemByTitle(mangaTitle);
		} else {
			System.out.print("Enter the manga's genre: ");
			String mangaGenre = in.nextLine();
			foundItems = lmh.searchForItemByGenre(mangaGenre);
		} 
		
		if (!foundItems.isEmpty()) {
			System.out.println("Succesfully found results.");
			for (ListManga l : foundItems) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Select ID to edit: ");
			int editId = in.nextInt();

			ListManga toEdit = lmh.searchForItemById(editId);
			System.out.println("Retrieved " + toEdit.getTitle() + " from " + toEdit.getAuthor() + " | Genre: " + toEdit.getGenre());
			System.out.println("---1. Update Author");
			System.out.println("---2. Update Title");
			System.out.println("---3. Update Genre");
			int update = in.nextInt();
			if (update == 1) {
				System.out.print("Name Change: ");
				String changeName = in.nextLine();
				toEdit.setAuthor(changeName);
			} else if (update == 2) {
				System.out.print("Title Change: ");
				String changeTitle = in.nextLine();
				toEdit.setTitle(changeTitle);
			} else if (update == 3) {
				System.out.print("Genre Change: ");
				String changeGenre = in.nextLine();
				toEdit.setGenre(changeGenre);
			}
			
			lmh.EditItems(toEdit);
		} else {
			System.out.println("! SEARCH FAILED !");
			
		}
			in.nextLine();
		}
		
	private static void viewMangaList() { // displays all entities
		List<ListManga> allItems = lmh.displayAllManga();
		for (ListManga singleItem : allItems) {
			System.out.println(singleItem.returnMangaDetails());
		}

	}
	
	private static void startUp() { // startup
		boolean goAgain = true;
		
		System.out.println("Manga Lists, your #1 resource for manga lists!");
		while (goAgain) {
			System.out.println("---Run a Command (1 - 4)");
			System.out.println("---1. Add a manga");
			System.out.println("---2. Delete a manga");
			System.out.println("---3. Edit a manga");
			System.out.println("---4. View all mangas added");
			System.out.println("---5. Close Program");
			int userCommand = in.nextInt();
			in.nextLine();
			
			if (userCommand == 1) {
				addAManga();
			} else if (userCommand == 2) {
				deleteManga();
			} else if (userCommand == 3) {
				editManga();
			} else if (userCommand == 4) {
				viewMangaList();
			} else {
				lmh.cleanUp();
				System.out.println("---Exiting Program");
				goAgain = false;
			}
		}
	}
	
	public static void main(String[] args) { // main
		// TODO Auto-generated method stub
		startUp();

	}
}

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Store {
    private HashSet<Book> listOfBooks;
    private HashSet<String> listOfCategories;
    private int numberOfBooks; // we need this variable because for each book in the list has a specific quantity

    public Store() {
        listOfBooks = new HashSet<>(); // we initiate this object to avoid nullPointerException error
        this.retrieveData();
        listOfCategories = new HashSet<>(); // we initiate this object to avoid nullPointerException error

        // here we going to list all the categories
        listOfCategories.add("Computer Science");
        listOfCategories.add("Physics");
        listOfCategories.add("Engineering");
        listOfCategories.add("Mathematics");
        listOfCategories.add("Medicine");
        listOfCategories.add("Biology");
        listOfCategories.add("Management");
        listOfCategories.add("Chemistry");
        listOfCategories.add("History");
        listOfCategories.add("Self-Help");

    }

    public boolean addBook(String name, String author, int yearOfPublish, int monthOfPublish, String category) {
        if (isValidCategory(category)) {
            // in this case the input is valid
            for (Book i : listOfBooks) {
                if (i.getName().equals(name) && i.getAuthor().equals(author) && i.getDateOfString().equals(yearOfPublish + " " + monthOfPublish)) {
                    i.increaseQuantity(); // we could increase the quantity of that book
                    this.numberOfBooks++;
                    this.saveChanges();
                    return true;
                }
            }
            listOfBooks.add(new Book(name, author, yearOfPublish, monthOfPublish, category));
            this.saveChanges();
            numberOfBooks++;
            return true;
        } else {
            // in this case the category is not valid
            return false;
        }
    }

    public boolean rentBook(int bookId) {
//        List<Book> newList = listOfBooks.stream().filter(book -> book.getId() != bookId).collect(Collectors.toList()); // we gonna use this filter to remove any book with same Id
//        if (newList.size() != listOfBooks.size()) {
//            listOfBooks = new HashSet<>(newList);
//            return true;
//        } else {
//            return false; // that means we won't change any thing from the list
//        }

        // we need to find a book with the same ID
        for (Book i : listOfBooks) {
            if (i.getId() == bookId && i.getQuantity() > 0) {
                // now, we found it!
                i.decreaseQuantity();
                this.numberOfBooks--;
                this.saveChanges();
                return true;
            }
        }
        return false;
    }

    public boolean rentBook(String bookName) {
//        List<Book> newList = listOfBooks.stream().filter(book -> !book.getName().equals(bookName)).collect(Collectors.toList()); // we gonna use this filter to remove any book with same Id
//        if (newList.size() != listOfBooks.size()) {
//            // if there are no difference between the size of the previous list and the new one that means we removed nothing!!!
//            listOfBooks = new HashSet<>(newList);
//            return true;
//        } else {
//            return false; // that means we won't change any thing from the list
//        }

        // we need to find a book with the same Name
        for (Book i : listOfBooks) {
            if (i.getName().equals(bookName) && i.getQuantity() > 0) {
                // now, we found it!
                i.decreaseQuantity();
                this.numberOfBooks--;
                this.saveChanges();
                return true;
            }
        }
        return false;
    }

    public boolean changeBookCategory(int bookId, String newCategory) {
        if (isValidCategory(newCategory)) {
            for (Book i : listOfBooks) {
                if (i.getId() == bookId) {
                    listOfCategories.remove(i);
                    this.saveChanges();
                    return true;
                }
            }
            return false; // therefore, there is no book with that Id
        } else {
            return false; // the category must be in the list!!!
        }
    }

    public boolean changeBookCategory(String bookName, String newCategory) {
        if (isValidCategory(newCategory)) {
            for (Book i : listOfBooks) {
                if (i.getName().equals(bookName)) {
                    listOfCategories.remove(i);
                    this.saveChanges();
                    return true;
                }
            }
            return false; // therefore, there is no book with that name
        } else {
            return false; // the category must be in the list!!!
        }
    }

    public boolean isValidCategory(String category) {
        return listOfCategories.contains(category);
    }

    public ObservableList<Book> getAvailableBooks() {
        ObservableList<Book> availableList = FXCollections.observableList(listOfBooks.stream().filter(book -> book.getQuantity() > 0).collect(Collectors.toList())); // Here we used stream concept to filter our list from books that are not available
        return availableList;
    }

    public ObservableList<Book> getRentedBooks() {
        ObservableList<Book> rentedBooks = FXCollections.observableList(listOfBooks.stream().filter(book -> book.getNumberOfRentedBooks() > 0).collect(Collectors.toList()));
        return rentedBooks;
    }

    public ObservableList<Book> getObservableList() {
        return FXCollections.observableList(listOfBooks.stream().collect(Collectors.toList())); // We convert our HashSet to ObservableList then pass it
    }

    public ObservableList<String> getCatList() {
        return FXCollections.observableList(listOfCategories.stream().collect(Collectors.toList()));
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    /**
     * this method will be called to retrieve the data from the permanent storage
     */
    private void retrieveData() {
        File dir = new File("books"); // this is the directory which contains the Object
        File[] objectFiles = dir.listFiles(); // each of these files contain an object

        // we need to loop each file and extract its object
        for (int i = 0; i < objectFiles.length; i++) {
            try (ObjectInputStream read = new ObjectInputStream(new FileInputStream(objectFiles[i]))) {

                this.listOfBooks.add((Book)read.readObject());

            } catch (FileNotFoundException e) {
                System.out.println(" >> Error: The file myData.txt not found in your program....");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(" >> Error: there is no Object int the file " + objectFiles[i].toString());
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println(" >> Error: make sure that the object at file " + objectFiles[i].toString() + " exist in your system");
                System.exit(0);
            }
        }
        calculateNumberOfBooks(); // to calculate the number of books after retrieving it
        calculateNextID();
    }

    /**
     * This method calculates the number of books
     */
    public void calculateNumberOfBooks () {
        this.numberOfBooks = 0;
        for (Book i: listOfBooks) {
            this.numberOfBooks += i.getQuantity();
        }
    }

    /**
     * This method calculates the next ID
     */
    public void calculateNextID () {
        // we have to find the maximum ID
        int maxID = -1;
        for (Book i : listOfBooks) {
            if (i.getId() > maxID) {
                maxID = i.getId();
            }
        }
        Book.setNextId(maxID+1); // we have to increase one because of the next ID must be greater than the maximum one!
    }

    /**
     * This method will called to save the changes in the permanent storage
     */
    private void saveChanges() {
        for (Book i : listOfBooks) {
            try (ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream("books/" + i.getId() + ".ser"))) {

                write.writeObject(i); // here we going to write the object

            } catch (FileNotFoundException e) {
                System.out.println(" >> Error: the file book/" + i.getId() + ".ser is not exist");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(" >> Error: make sure that the object is serializable");
            }
        }

    }
}

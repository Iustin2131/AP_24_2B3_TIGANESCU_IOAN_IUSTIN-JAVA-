package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) throws SQLException {

        try {
            var collectionOFBooks = new CollectionOfBooksDAO();
            collectionOFBooks.create(1, "Cartea ballast");
            collectionOFBooks.create(2, "Harry Potter");

            var booksinfo = new BooksInfoDAO();
            booksinfo.create(1,"Cartea ballast", "Tiganescu Iustin", "Romana", "2002.12.25");

            booksinfo.create(2, " Harry Potter ", " J.K. Rowling ", "Romana", "June 26, 1997");

            booksinfo.create(3, " Moara cu noroc ", " Ioan Slavici ", "Romana", "June 26, 197");
            Database.getConnection().commit();
            booksinfo.Print();

            System.out.println("\n-------------------------------------------------------\n");

            System.out.println(" Cartea cu Id: 3 este " + booksinfo.findById(3));
            System.out.println(" Id-ul cartii ,,Cartea ballast'' este " + booksinfo.findByName("Cartea ballast"));

        } catch (SQLException e) {
            System.err.println(e);

            Database.rollback();
        }
    }
}
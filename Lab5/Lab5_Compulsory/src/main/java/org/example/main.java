package org.example;

import java.util.List;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        // Creating a document repository
        DocumentRepository repository = new DocumentRepository();

        // Creating some sample persons and documents
        Person person1 = new Person("Taylor Swift", "SING001");
        Person person2 = new Person("Ferucio Laurențiu", "PROF002");

        Document document1 = new Document("Resume", "PDF", "Taylor Swift este una dintre cele mai faimoase cântărețe și compozitoare ale timpurilor moderne. Născută pe 13 decembrie 1989 în Reading, Pennsylvania, Swift și-a început cariera muzicală la vârsta de 14 ani când a semnat un contract de înregistrare cu casa de discuri Big Machine Records.");
        Document document2 = new Document("Profile", "JPG", "Un profesor universitar este o persoană dedicată și pasionată de educație și cercetare, care își dedică viața pentru a îndruma și inspira studenții în călătoria lor academică și profesională. Profesorii universitari sunt recunoscuți pentru experiența lor vastă în domeniile lor de specialitate și pentru capacitatea lor de a transmite cunoștințele și expertiza lor către generațiile viitoare.");
        Document document3 = new Document("Contract Taylor Swift", "DOC", "În ceea ce privește Taylor Swift, puteți încerca să luați legătura cu echipa ei de management sau să verificați site-ul oficial sau rețelele de socializare pentru informații despre modalitățile de contact. Totuși, trebuie să țineți cont că celebritățile pot primi o mulțime de solicitări și este posibil să nu răspundă la toate.");

        repository.addDocument(person1, document1);
        repository.addDocument(person1, document2);
        repository.addDocument(person2, document3);

        List<Document> johnsDocuments = repository.findDocuments(person1);
        System.out.println("Documents for John Doe:");
        for (Document doc : johnsDocuments) {
            System.out.println("Name: " + doc.name() + ", Format: " + doc.format() + " , Continut:" + doc.content());
        }

        List<Document> janesDocuments = repository.findDocuments(person2);
        System.out.println("\nDocuments for Jane Smith:");
        for (Document doc : janesDocuments) {
            System.out.println("Name: " + doc.name() + ", Format: " + doc.format() + " , Continut:" + doc.content());
        }

        System.out.println("---------------------------------------------");
        Repository repo = new Repository("src");
        repo.displayContent();
    }

}

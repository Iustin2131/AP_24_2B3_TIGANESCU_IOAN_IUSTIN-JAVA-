package org.example;

import java.util.*;

public class DocumentRepository {
    private Map<Person, List<Document>> documents;
    public DocumentRepository() {
        this.documents = new HashMap<>();
    }
    public void addDocument(Person person, Document document) {
        documents.computeIfAbsent(person, k -> new ArrayList<>()).add(document);
    }
    public List<Document> findDocuments(Person person) {
        return documents.getOrDefault(person, Collections.emptyList());
    }
}

record Person(String name, String id) {}

record Document(String name, String format, String content) {}

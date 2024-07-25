package com.school;

public class Task {
    final private String id; // cannot be null or updatable
    private String name; // cannot be null
    private String description; // cannot be null
    static int idCounter = 1;

    public Task(String id, String name, String description) {
        if(id == null || name == null || description == null) {
            throw new NullPointerException("id, name, or description are null");
        }
        checkLengthOfPhrase(id, 10);
        checkLengthOfPhrase(name, 20);
        checkLengthOfPhrase(description, 50);
        checkIfAllDigits(id);

        this.id = Integer.parseInt(id) >= idCounter ? id : String.format("%s", idCounter++);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
        this.description = description.substring(0, 1).toUpperCase() + description.substring(1);
    }

    public Task(Task task, String id) {
        checkIfPhraseIsNull(id);
        checkLengthOfPhrase(id, 10);
        checkIfAllDigits(id);
        this.id = Integer.parseInt(id) >= idCounter ? id : String.format("%s", idCounter++);
        this.name = task.name;
        this.description = task.description;
    }

//    Getters and Setters

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        checkIfPhraseIsNull(name);
        checkLengthOfPhrase(name, 20);
        this.name = name;
    }

    public void setDescription(String description) {
        checkIfPhraseIsNull(description);
        checkLengthOfPhrase(description, 50);
        this.description = description;
    }

    private void checkIfAllDigits(String phrase) {
        if(!phrase.matches("[0-9]+")) {
            throw new IllegalArgumentException(String.format("%s is invalid", phrase));
        }
    }

    private void checkLengthOfPhrase(String phrase, int length) {
        if(phrase.length() > length) {
            throw new IllegalArgumentException(String.format("%s is too long", phrase));
        }
    }

    private void checkIfPhraseIsNull(String phrase) {
        if(phrase == null) {
            throw new NullPointerException("String is null");
        }
    }
}
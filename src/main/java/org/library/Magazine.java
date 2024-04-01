package org.library;

public class Magazine extends LibraryItem{
    Integer issueNumber;
    Magazine(String title, String author, Integer year, Integer issueNumber) {
        super(title, author, year, year.toString() + "-" + issueNumber.toString());
        this.issueNumber = issueNumber;
    }

    public Integer getIssueNumber(){
        return this.issueNumber;
    }
}

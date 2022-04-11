package client;

import constant.Constant;
import manager.SearchEngineManager;
import model.Document;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String defaultTerm = Constant.searchKeyWord1;
        String defaultTerm2 = Constant.searchKeyWord2;
        SearchEngineManager searchEngineManager = new SearchEngineManager();
        searchEngineManager.createLibrary();
        displayWelcomeScreen();
        displaySearchResult(searchEngineManager,defaultTerm);
        displaySearchResult(searchEngineManager,defaultTerm2);
    }

    static void displayWelcomeScreen(){
        System.out.println(Constant.INDEX_SCREEN);
        System.out.println("Document1: " + Constant.Doc1);
        System.out.println("Document2: " + Constant.Doc2);
        System.out.println("Document3: " + Constant.Doc3);
    }
    static void displaySearchResult(SearchEngineManager searchEngineManager, String term) {
        searchEngineManager.calculateTFScore(term);
        searchEngineManager.calculateIDFScore(term);
        List<Document> sortedDocsByTFIDF = searchEngineManager.calculateTFIDF();
        System.out.println(Constant.DOCUMENTLIST_PART1 + "\"" + term + "\"" + Constant.DOCUMENTLIST_PART2);
        for (Document document : sortedDocsByTFIDF) {
            System.out.print(document.getId() +", ");
        }
    }
}
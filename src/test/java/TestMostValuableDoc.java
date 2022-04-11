import constant.Constant;
import manager.SearchEngineManager;
import model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMostValuableDoc {

    SearchEngineManager searchEngineManager;

    @BeforeEach
    void setUp() {
        searchEngineManager = new SearchEngineManager();
        searchEngineManager.createLibrary();

    }

    @org.junit.jupiter.api.Test
    @DisplayName("Most valuable doc list")
    void testFindMostValuableDocs() {

        String defaultTerm = Constant.searchKeyWord1;
        searchEngineManager.calculateTFScore(defaultTerm);
        List<Document> sortedDocsByTFIDF = searchEngineManager.calculateTFIDF();
        System.out.println(sortedDocsByTFIDF.get(0).getId());
        assertEquals(2, sortedDocsByTFIDF.get(0).getId(),
                "2 is id the doc" + defaultTerm);
        System.out.println("The id of most valuable doc for the default term \""+ defaultTerm + "\" is " + sortedDocsByTFIDF.get(0).getId());
    }
}

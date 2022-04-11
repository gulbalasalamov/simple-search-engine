import constant.Constant;
import manager.SearchEngineManager;
import model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestToCheckNumberOfDocs {
    SearchEngineManager searchEngineManager;

    @BeforeEach
    void setUp() {
        searchEngineManager = new SearchEngineManager();
        searchEngineManager.createLibrary();

    }

    @org.junit.jupiter.api.Test
    @DisplayName("Doc list confirm including a search term")
    void testDocListConfirmForTerm() {

        String defaultTerm = Constant.searchKeyWord1;
        searchEngineManager.calculateTFScore(defaultTerm);
        List<Document> sortedDocsByTFIDF = searchEngineManager.calculateTFIDF();
        assertEquals(3, sortedDocsByTFIDF.size(),
                "3 docs included " + defaultTerm);
        for (Document d : sortedDocsByTFIDF
        ) {
            System.out.println("included docs id: " + d.getId());
        }
    }
}

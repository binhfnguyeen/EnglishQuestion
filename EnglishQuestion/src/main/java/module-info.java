module com.heulwen.englishquestion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.heulwen.englishquestion to javafx.fxml;
    exports com.heulwen.englishquestion;
    exports com.heulwen.services;
    exports com.heulwen.pojo;
}

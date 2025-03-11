package com.heulwen.englishquestion;

import com.heulwen.pojo.Question;
import com.heulwen.services.QuestionServices;
import com.heulwen.services.Ultis;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable {

//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }
    @FXML
    private RadioButton rdoA;
    @FXML
    private RadioButton rdoB;
    @FXML
    private RadioButton rdoC;
    @FXML
    private RadioButton rdoD;
    @FXML
    private Text txtA;
    @FXML
    private Text txtB;
    @FXML
    private Text txtC;
    @FXML
    private Text txtD;
    @FXML
    private Text txtContent;
    List<Question> questions;
    private int currentIdx = 0;

    public void checkHandler() {
        Question q = this.questions.get(this.currentIdx);
        boolean v1 = rdoA.isSelected() == true && q.getChoices().get(0).isCorrect() == true;
        boolean v2 = rdoB.isSelected() == true && q.getChoices().get(1).isCorrect() == true;
        boolean v3 = rdoC.isSelected() == true && q.getChoices().get(2).isCorrect() == true;
        boolean v4 = rdoD.isSelected() == true && q.getChoices().get(3).isCorrect() == true;

        if (v1 || v2 || v3 || v4) {
            Ultis.getAlert("CORRECT!!").show();
        } else {
            Ultis.getAlert("INCORRECT").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        QuestionServices s = new QuestionServices();
        try {
            this.questions = s.getQuestions(3);
            this.loadQuestion();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadQuestion() throws SQLException {
        Question q = this.questions.get(this.currentIdx);
        if (q.getChoices() == null) {
            QuestionServices s = new QuestionServices();
            q.setChoices(s.getChoices(q.getId()));
        }

        this.txtContent.setText(q.getContent());
        this.txtA.setText(q.getChoices().get(0).getContent());
        this.txtB.setText(q.getChoices().get(1).getContent());
        this.txtC.setText(q.getChoices().get(2).getContent());
        this.txtD.setText(q.getChoices().get(3).getContent());
    }

    public void nextHandler(ActionEvent e) throws SQLException {
        if (this.currentIdx < this.questions.size()) {
            this.currentIdx++;
        } else {
            this.currentIdx = 0;
        }
        this.loadQuestion();
    }

    public void previousHandler(ActionEvent e) throws SQLException {
        if (this.currentIdx > 0) {
            this.currentIdx--;
        } else {
            this.currentIdx = this.currentIdx - 1;
        }
        this.loadQuestion();
    }
}

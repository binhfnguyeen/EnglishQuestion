/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.heulwen.pojo.Choice;
import com.heulwen.pojo.Question;
import com.heulwen.services.QuestionServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
/**
 *
 * @author Dell
 */
public class QuestionTestsuite {
    private static QuestionServices s = new QuestionServices();
    
    @Test
    public void test() throws SQLException{
        try {
            List<Question> questions = s.getQuestions(2);
            Assertions.assertEquals(questions.size(), 2);
            
            questions.forEach(s1 -> System.out.println(s1));
        } catch(SQLException ex){
            Logger.getLogger(QuestionTestsuite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testChoice() {
        try {
            List<Question> questions = s.getQuestions(5);
            for (var x: questions) {
                List<Choice> choices = s.getChoices(x.getId());
                Set<Choice> tmp = new HashSet<>(choices);
                
                Assertions.assertEquals(choices.size(), tmp.size());
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTestsuite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testCases(){
        try {
            List<Question> questions = s.getQuestions(2);
            for(var x: questions)
                Assertions.assertNotEquals(x.getCategoryId(), 0);
        } catch(SQLException ex){
            Logger.getLogger(QuestionTestsuite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testChoices(){
         try {
            List<Question> questions = s.getQuestions(2);
            for(var x: questions){
               List<Choice> choices = s.getChoices(x.getId());
               Set<Choice> tmp = new HashSet<>(choices);
               
               Assertions.assertEquals(choices.size(), tmp.size());
            }
        } catch(SQLException ex){
            Logger.getLogger(QuestionTestsuite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void testRightNumberOfChoice() {
        try {
            List<Question> questions = s.getQuestions(5);
            for (var x: questions) {
                List<Choice> choices = s.getChoices(x.getId());
                Assertions.assertEquals(choices.size(), 4);
                
                Assertions.assertTrue(choices.stream().filter(c -> c.isCorrect() == true).count() == 1);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTestsuite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

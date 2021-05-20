package sasthoseba.com.sasthoseba;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Common_Question_Details extends Activity {

    String QuestionHolder, AnswerHolder, TypeHolder, DateHolder;
    private TextView Question, Answer, Type, Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity_question_details);

        Question=(TextView) findViewById(R.id.questionDetails);
        Answer=(TextView) findViewById(R.id.answerDetails);
        Type=(TextView) findViewById(R.id.questionTypeDetails);
        Date=(TextView) findViewById(R.id.date);

        QuestionHolder = getIntent().getStringExtra("Question");
        AnswerHolder = getIntent().getStringExtra("Answer");
        TypeHolder = getIntent().getStringExtra("Type");
        DateHolder = getIntent().getStringExtra("Date");

        Question.setText(QuestionHolder);
        Answer.setText(AnswerHolder);
        Type.setText(TypeHolder);
        Date.setText(DateHolder);
    }
}

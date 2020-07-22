package android.support.v4.app.quizblaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract. QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Father is four times the age of his daughter. If after 5 years, he would be threee times of daughter’s age, then further after 5 years, how many times he would be of his daughter’s age?", "1.5 times", "2 times", "2.5 times", 3);
        addQuestion(q1);
        Question q2 = new Question("What is Aman's present age, if after 20 years his age will be 10 times his age 10 years back?", "13.3 years", " 10 years", "7.7 years", 1);
        addQuestion(q2);
        Question q3 = new Question(" The product of three consecutive even numbers is 4032. The product of the first and the third number Is 252. What is five times the second number?\n", "80", "100", "70", 1);
        addQuestion(q3);
        Question q4 = new Question("The sum of the squares of two consecutive even numbers is 6500. Which is the smaller number?", "56", "52", "48", 1);
        addQuestion(q4);
        Question q5 = new Question("36% of 245 - 40% of 210= 10 - ?", "4.9", "6.8", "None of these", 3);
        addQuestion(q5);
        Question q6 = new Question("15.4 × 13.5 × 7 = 2598.75", "11.5", "12.5", "13.5", 2);
        addQuestion(q6);
        Question q7 = new Question(" The ratio between the angles of a quadrilateral is 7 : 2 : 5 : 6 respectively. What is the sum of double the smallest angle and half the largest angle of the quadrilateral?", "135", " 198", " 162", 1);
        addQuestion(q7);
        Question q8 = new Question("The largest and the smallest angles of a triangle are in the ratio of 3:1 respectively. The second largest triangle?", "564", "123", "153", 3);
        addQuestion(q8);
        Question q9 = new Question("Manish bought 25 kg of rice at Rs.32 per kg and 15kg of rice at Rs.36 per kg. What profit did he get when he mixed the two varieties together and sold it at Rs.40.20 per kg?\n", "15%", "20%", "25%", 2);
        addQuestion(q9);
        Question q10 = new Question("Ajar has 60 litres of milk. From the jar, 12 litres of milk was taken out and replaced by an equal amount of water. If 12 litres of the newly formed mixture is taken out of the jar, what Is the final quantity of milk left In the Jar?", " 38.2 litres", "38.3 litres" , "38.4 litres", 3);
        addQuestion(q10);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put( QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put( QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put( QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put( QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put( QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert( QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
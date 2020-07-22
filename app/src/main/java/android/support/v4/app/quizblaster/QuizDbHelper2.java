package android.support.v4.app.quizblaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz2.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper2( Context context) {
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
        Question q1 = new Question("She was a devoted wife and looked _______ her husband.", "after", "upon", "for", 1);
        addQuestion(q1);
        Question q2 = new Question("My voice reverberated _______ the walls of the castle.", "from", "on", "in", 1);
        addQuestion(q2);
        Question q3 = new Question("The President was given a hearty welcome.", "cordially", "warmly", "warm", 3);
        addQuestion(q3);
        Question q4 = new Question("All that glitters are not gold.", "gleaming", "gleams", "shining", 1);
        addQuestion(q4);
        Question q5 = new Question("Application", "applicant", "apple", "applicable", 2);
        addQuestion(q5);
        Question q6 = new Question("Layer", "lay", "layering", "lie", 1);
        addQuestion(q6);
        Question q7 = new Question("Select the odd one out", "Canteen", "Stage", "Makeup", 1);
        addQuestion(q7);
        Question q8 = new Question("Select the odd one out ", "Chamber", "Tennis", "Lawyer", 2);
        addQuestion(q8);
        Question q9 = new Question("Suganya is typing.", "simple past", "present continuous", "past perfect", 2);
        addQuestion(q9);
        Question q10 = new Question("Mahatma Gandhi was born in Porbandar.", "simple present", "simple future", "simple past", 3);
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
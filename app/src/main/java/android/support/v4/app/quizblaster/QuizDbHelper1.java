package android.support.v4.app.quizblaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper1 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz1.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper1( Context context) {
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
        Question q1 = new Question("2 3 10 38 172 ", "192", "38", "10", 3);
        addQuestion(q1);
        Question q2 = new Question("8 19 52 151 447", "151", "447", "52", 2);
        addQuestion(q2);
        Question q3 = new Question("CUP : LIP :: BIRD : ?", "BEAK", "GRASS", "BUSH", 1);
        addQuestion(q3);
        Question q4 = new Question("Safe : Secure :: Protect :", "Lock", "Guard", "Conserve", 2);
        addQuestion(q4);
        Question q5 = new Question("Pointing to a girl in the photograph, Ajay said, \"Her mother's brother is the only son of my mother's father.\" How is the girl's mother related to Ajay?", "Aunt", "Sister", "Mother", 1);
        addQuestion(q5);
        Question q6 = new Question("If 'X $ Y' means 'X is father of Y'; 'X # Y' means 'X is mother of Y'; 'X × Y' means 'X is sister of Y', then how is D related to N in N # A $ B × D ?", "Nephew", " Grandson", "Cannot be Determined", 3);
        addQuestion(q6);
        Question q7 = new Question("One morning Udai and Vishal were talking to each other face to face at a crossing. If Vishal's shadow was exactly to the left of Udai, which direction was Udai facing?", "East", "South", "North", 3);
        addQuestion(q7);
        Question q8 = new Question("A man walks 5 km toward south and then turns to the right. After walking 3 km he turns to the left and walks 5 km. Now in which direction is he from the starting place?", "North-Wast", "South-West", "North-East", 2);
        addQuestion(q8);
        Question q9 = new Question("How many small cubes will have only two faces coloured?", "14", "16", "18", 2);
        addQuestion(q9);
        Question q10 = new Question("35 19 11 7 5 4.5 3.5", "1", "2", "3", 2);
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
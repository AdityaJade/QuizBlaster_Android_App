package android.support.v4.app.quizblaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper3 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz3.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper3( Context context) {
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
        Question q1 = new Question("Sesame oil belongs to family", "Graminae", "Ephorbiaceae", "Leguminosae", 1);
        addQuestion(q1);
        Question q2 = new Question("The leaves of Tylophora indica are used in the treatment of", "Ashma", "General debility", "Cough", 2);
        addQuestion(q2);
        Question q3 = new Question("Which of the following capsule sizes has the smallest capacity", "5", "4", "0", 3);
        addQuestion(q3);
        Question q4 = new Question("The solution used to rinse vagina is called as", "Douches", "Irrigation", "Enema", 1);
        addQuestion(q4);
        Question q5 = new Question("Gel property of carbopol is affected by", "Solubility", "pH", "Acidity", 2);
        addQuestion(q5);
        Question q6 = new Question("Sesame oil belongs to family", "Graminae", "Ephorbiaceae", "Leguminosae", 1);
        addQuestion(q6);
        Question q7 = new Question("The leaves of Tylophora indica are used in the treatment of", "Ashma", "General debility", "Cough", 1);
        addQuestion(q7);
        Question q8 = new Question("Which of the following capsule sizes has the smallest capacity", "5", "4", "0", 2);
        addQuestion(q8);
        Question q9 = new Question("The solution used to rinse vagina is called as", "Douches", "Irrigation", "Enema", 2);
        addQuestion(q9);
        Question q10 = new Question("Gel property of carbopol is affected by", "Solubility", "pH", "Acidity", 3);
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
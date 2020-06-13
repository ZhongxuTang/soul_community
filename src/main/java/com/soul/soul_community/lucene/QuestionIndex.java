package com.soul.soul_community.lucene;

import cn.hutool.core.date.DateUtil;
import com.soul.soul_community.entity.ArticleType;
import com.soul.soul_community.entity.Question;
import com.soul.soul_community.entity.User;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName QuestionIndex
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/5/1 2:59
 * @Version 1.0
 **/
@Component
public class QuestionIndex {



    private Directory directory = null;

    @Value("${questionLucenePath}")
    private String questionLucenePath;


    /**
     * 获取IndexWriter实例
     * @return
     */
    private IndexWriter getWriter() throws IOException {
        directory = FSDirectory.open(Paths.get(questionLucenePath));
        /*配置支持中文*/
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        indexWriter.commit();
        return indexWriter;
    }

    /**
     * 添加问答资源索引
     * @param question
     */
    public void addQuestionIndex(Question question){

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            IndexWriter indexWriter = getWriter();
            Document document = getIndexableFieldsByQuestion(question);

            indexWriter.addDocument(document);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 更新资源索引
     * @param question
     */
    public void updateQuestionIndex(Question question) {

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            IndexWriter indexWriter = getWriter();
            Document document = getIndexableFieldsByQuestion(question);

            indexWriter.updateDocument(new Term("id", String.valueOf(question.getQuestionId())), document);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



    /**
     * 删除资源索引
     * @param questionId
     */
    public void deleteIndex(String questionId){

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            IndexWriter indexWriter = getWriter();
            indexWriter.deleteDocuments(new Term("id",questionId));
            indexWriter.forceMergeDeletes();    //强制删除
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public List<Question> searchQuestion(String keyWords) throws IOException, ParseException {

        keyWords = keyWords.replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5]","");

        directory = FSDirectory.open(Paths.get(questionLucenePath));
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        SmartChineseAnalyzer smartChineseAnalyzer = new SmartChineseAnalyzer();

        QueryParser queryParser = new QueryParser("name",smartChineseAnalyzer);
        Query query = queryParser.parse(keyWords);

        QueryParser queryParser_t = new QueryParser("name",smartChineseAnalyzer);
        Query query_t = queryParser_t.parse(keyWords);

        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
        booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query_t, BooleanClause.Occur.SHOULD);

        TopDocs topDocs = indexSearcher.search(booleanQuery.build(),10);

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        List<Question> questionIdList = new ArrayList<>();
        for (ScoreDoc scoreDoc:scoreDocs){
            User user = new User();
            ArticleType articleType = new ArticleType();
            Document document = indexSearcher.doc(scoreDoc.doc);

            user.setUserId(Integer.parseInt(document.get("userId")));
            user.setUserName(document.get("userName"));
            user.setUserHeadPortrait(document.get("userHeadPortrait"));

            ArticleType articleType1 = new ArticleType();
            articleType1.setArticleTypeName(document.get("type"));

            Question question = new Question();

            question.setQuestionId(Integer.parseInt(document.get("id")));
            question.setQuestionTitle(document.get("name"));
            question.setArticleType(articleType1);
            question.setQuestionContent(document.get("content"));
            question.setQuestionPostDate(DateUtil.parse(document.get("questionPostDate")));
            question.setQuestionAnswerQuantity(Integer.parseInt(document.get("questionAnswerQuantity")));
            questionIdList.add(question);

        }
        return questionIdList;
    }


    private Document getIndexableFieldsByQuestion(Question question){

        Document document = new Document();

        document.add(new StringField("id",String.valueOf(question.getQuestionId()),Field.Store.YES));
        document.add(new TextField("name",question.getQuestionTitle(),Field.Store.YES));
        document.add(new StringField("type",question.getArticleType().getArticleTypeName(),Field.Store.YES));
        document.add(new TextField("content",question.getQuestionContent(),Field.Store.YES));
        document.add(new StringField("questionPostDate", DateUtil.format(question.getQuestionPostDate(),"yyyy-MM-dd HH:mm"),Field.Store.YES));
        document.add(new StringField("questionAnswerQuantity",String.valueOf(question.getQuestionAnswerQuantity()),Field.Store.YES));
        document.add(new StringField("userId",String.valueOf(question.getUser().getUserId()),Field.Store.YES));
        document.add(new StringField("userName", question.getUser().getUserName(), Field.Store.YES));
        document.add(new StringField("userHeadPortrait",question.getUser().getUserHeadPortrait(),Field.Store.YES));
        return document;
    }

}

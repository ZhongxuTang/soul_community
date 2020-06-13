package com.soul.soul_community.lucene;

import cn.hutool.core.date.DateUtil;
import com.soul.soul_community.entity.Article;
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
 * @ClassName ArticleIndex
 * @Deacription 资源索引
 * @Author Lemonsoul
 * @Date 2020/4/14 21:14
 * @Version 1.0
 **/
@Component
public class ArticleIndex {

    private Directory directory = null;

    @Value("${articleLucenePath}")
    private String articleLucenePath;


    /**
     * 获取IndexWriter实例
     * @return
     */
    private IndexWriter getWriter() throws IOException {
        directory = FSDirectory.open(Paths.get(articleLucenePath));
        /*配置支持中文*/
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        indexWriter.commit();
        return indexWriter;
    }

    /**
     * 添加文章资源索引
     * @param article
     */
    public void addArticleIndex(Article article){

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            IndexWriter indexWriter = getWriter();
            Document document = getIndexableFieldsByArticle(article);

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
     * @param article
     */
    public void updateArticleIndex(Article article){

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            IndexWriter indexWriter = getWriter();
            Document document = getIndexableFieldsByArticle(article);

            indexWriter.updateDocument(new Term("id",String.valueOf(article.getArticleId())),document);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }



    /**
     * 删除资源索引
     * @param articleId
     */
    public void deleteIndex(String articleId){

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            IndexWriter indexWriter = getWriter();
            indexWriter.deleteDocuments(new Term("id",articleId));
            indexWriter.forceMergeDeletes();    //强制删除
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public List<Article> searchArticle(String keyWords) throws IOException, ParseException {

        keyWords = keyWords.replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5]","");

        directory = FSDirectory.open(Paths.get(articleLucenePath));
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

        List<Article> articleList = new ArrayList<>();
        for (ScoreDoc scoreDoc:scoreDocs){
            User user = new User();
            ArticleType articleType = new ArticleType();
            Document document = indexSearcher.doc(scoreDoc.doc);

            user.setUserName(document.get("userName"));
            user.setUserHeadPortrait(document.get("userHeadPortrait"));
            user.setUserId(Integer.parseInt(document.get("userId")));

            articleType.setArticleTypeName(document.get("articleTypeName"));

            Article article = new Article();
            article.setArticleId(Integer.parseInt(document.get("id")));
            article.setArticleName(document.get("name"));
            article.setArticleCreateTime(DateUtil.parse(document.get("publishDate")));
            article.setContent(document.get("content"));
            article.setSummary(document.get("summary"));
            article.setUser(user);
            article.setArticleType(articleType);
            articleList.add(article);
        }
        return articleList;
    }



    private Document getIndexableFieldsByArticle(Article article) {
        Document document = new Document();
        document.add(new StringField("id", String.valueOf(article.getArticleId()), Field.Store.YES));
        document.add(new TextField("name", article.getArticleName(), Field.Store.YES));
        document.add(new StringField("publishDate", DateUtil.format(article.getArticleCreateTime(), "yyyy-MM-dd HH:mm"), Field.Store.YES));
        document.add(new StringField("summary",article.getSummary(),Field.Store.YES));
        document.add(new TextField("content", article.getContent(), Field.Store.YES));
        document.add(new StringField("articleTypeName",article.getArticleType().getArticleTypeName(),Field.Store.YES));
        document.add(new StringField("userId",String.valueOf(article.getUser().getUserId()),Field.Store.YES));
        document.add(new StringField("userName", article.getUser().getUserName(), Field.Store.YES));
        document.add(new StringField("userHeadPortrait",article.getUser().getUserHeadPortrait(),Field.Store.YES));
        return document;
    }

}

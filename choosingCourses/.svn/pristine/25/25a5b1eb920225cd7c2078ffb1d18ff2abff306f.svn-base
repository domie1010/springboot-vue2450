package com.longwang.lucene;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.longwang.entity.Article;
import com.longwang.util.DateUtil;
import com.longwang.util.StringUtil;
import com.longwang.util.WriterAndDirManager;

/**
 * 文章索引类
 * @author Long
 *
 */
@Component
public class ArticleIndex {

  private Directory dir;

  @Value("${lucenePath}")
  private String lucenePath; // 索引目录

  /**
   * 获取IndexWriter实例
   * @return
   * @throws Exception
   */
  private IndexWriter getWriter() throws Exception {
    dir = FSDirectory.open(Paths.get(lucenePath));
    SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
    IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
    IndexWriter writer = new IndexWriter(dir, iwc);
    return writer;
  }

  /**
   * 添加文章索引
   * @param article
   * @throws Exception
   */
  public void addIndex(Article article) throws Exception {
    IndexWriter writer = getWriter();
    Document doc = new Document();
    doc.add(new StringField("id", String.valueOf(article.getArticleId()), Field.Store.YES));
    doc.add(new StringField("publishDate", DateUtil.formatDate(article.getPublishDate(), "yyyy-MM-dd HH:mm:ss"),
        Field.Store.YES));
    doc.add(new TextField("title", article.getTitle(), Field.Store.YES));
    doc.add(new TextField("content", article.getContentNoTag(), Field.Store.YES));
    writer.addDocument(doc);
    writer.close();
  }

  /**
   * 删除指定文章的索引
   * @param articleId
   * @throws Exception
   */
  public void deleteIndex(String articleId) throws Exception {
    IndexWriter writer = getWriter();
    writer.deleteDocuments(new Term("id", articleId));
    writer.forceMergeDeletes(); // 强制删除
    writer.commit();
    writer.close();
  }

  /**
   * 修改文章索引
   * @param article
   * @throws Exception
   */
  public void updateIndex(Article article) throws Exception {
    IndexWriter writer = getWriter();
    Document doc = new Document();
    doc.add(new StringField("id", String.valueOf(article.getArticleId()), Field.Store.YES));
    doc.add(new StringField("imageName", article.getImageName(), Field.Store.YES));
    doc.add(new StringField("author", article.getAuthor(), Field.Store.YES));
    doc.add(new StringField("click", String.valueOf(article.getClick()), Field.Store.YES));
    doc.add(new StringField("commentNum", String.valueOf(article.getCommentNum()), Field.Store.YES));
    doc.add(new StringField("isOriginal", String.valueOf(article.getIsOriginal()), Field.Store.YES));
    doc.add(new StringField("isTop", String.valueOf(article.getIsTop()), Field.Store.YES));
    doc.add(new StringField("classifyId", String.valueOf(article.getClassify().getClassifyId()), Field.Store.YES));
    doc.add(new StringField("classifyName", article.getClassify().getClassifyName(), Field.Store.YES));
    doc.add(new StringField("publishDate", DateUtil.formatDate(article.getPublishDate(), "yyyy-MM-dd HH:mm:ss"),
        Field.Store.YES));
    doc.add(new TextField("title", article.getTitle(), Field.Store.YES));
    doc.add(new TextField("content", article.getContentNoTag(), Field.Store.YES));
    writer.updateDocument(new Term("id", String.valueOf(article.getArticleId())), doc);
    writer.close();
  }

  /**
   * 查询博客信息
   * @param q
   * @return
   * @throws Exception
   */
  public List<Article> searchArticle(String q) throws Exception {
    dir = FSDirectory.open(Paths.get(lucenePath));
    WriterAndDirManager wadm = new WriterAndDirManager();
    IndexWriter writer = null;
    writer = wadm.getIndexWriter(dir);
    writer.commit();

    IndexReader reader = wadm.getIndexReader(dir);
    IndexSearcher is = new IndexSearcher(reader);

    BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
    SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(true);
    QueryParser parser = new QueryParser("title", analyzer);
    Query query = parser.parse(q);

    QueryParser parser2 = new QueryParser("content", analyzer);
    Query query2 = parser2.parse(q);

    booleanQuery.add(query, BooleanClause.Occur.SHOULD);
    booleanQuery.add(query2, BooleanClause.Occur.SHOULD);

    TopDocs hits = is.search(booleanQuery.build(), 100);
    QueryScorer scorer = new QueryScorer(query);
    Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
    SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
    Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
    highlighter.setTextFragmenter(fragmenter);

    List<Article> articleList = new LinkedList<>();
    for (ScoreDoc scoreDoc : hits.scoreDocs) {
      Document doc = is.doc(scoreDoc.doc);
      Article article = new Article();
      article.setArticleId(Integer.parseInt(doc.get("id")));
      article.setPublishDate(DateUtil.formatString(doc.get("publishDate"), "yyyy-MM-dd HH:mm:ss"));
      String title = doc.get("title");
      String content = doc.get("content");
      if (title != null) {
        TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
        String hTitle = highlighter.getBestFragment(tokenStream, title);
        if (StringUtil.isEmpty(hTitle)) {
          article.setTitle(title);
        } else {
          article.setTitle(hTitle);
        }
      }

      if (content != null) {
        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
        String hContent = highlighter.getBestFragment(tokenStream, content);
        if (StringUtil.isEmpty(hContent)) {
          if (content.length() <= 100) {
            article.setContent(content);
          } else {
            article.setContent(content.substring(0, 100));
          }
        } else {
          article.setContent(hContent);
        }
      }
      articleList.add(article);
    }
    writer.close();
    return articleList;
  }
}

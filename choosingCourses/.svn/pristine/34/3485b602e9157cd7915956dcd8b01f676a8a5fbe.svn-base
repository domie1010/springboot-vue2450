package com.longwang.util;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

/**
 * 用于管理indexWriter和Directory的管理类，所有的indexWirter和Directory都在这里做管理
 * 理论上来讲，一个文件夹只需要一个Directory和indexWriter就够了，不需要重复new对象
 * 当有多个文件夹时，用map来存储和管理
 * 
 *
 */
@Component
public class WriterAndDirManager {

  /**
   * 存储indexWriter的map
   */
  private Map<Directory, IndexWriter> writerMap = new ConcurrentHashMap<>();

  /**
   * 存储Directory的map
   */
  private Map<String, Directory> dirMap = new ConcurrentHashMap<>();

  /**
   * 存储Directory的map
   */
  private Map<Directory, IndexReader> readerMap = new ConcurrentHashMap<>();

  /**
   * 得到IndexReader对象
   * @param directory  文件夹对象
   * @return 返回一个可以使用的IndexReader对象
   * @throws IOException
   */
  public IndexReader getIndexReader(Directory directory) throws IOException {
    // 如果map中有则直接返回
    if (readerMap.containsKey(directory)) {
      IndexReader indexReader = readerMap.get(directory);
      // 只有当RefCount大于0时 才认为indexReader未被关闭
      if (indexReader.getRefCount() > 0) {
        return indexReader;
      }
    }
    IndexReader indexReader = DirectoryReader.open(directory);
    readerMap.put(directory, indexReader);
    return indexReader;
  }

  /**
   * 获取Directory
   * 
   * @param path 传入文件夹路径
   * @return 返回一个Directory对象
   * @throws IOException
   */
  public Directory getDirectory(String path) throws IOException {
    // 如果map中有则直接返回
    if (dirMap.containsKey(path)) {
      return dirMap.get(path);
    }
    // 没有则new 一个加入map
    Directory directory = FSDirectory.open(Paths.get(path));
    dirMap.put(path, directory);
    return directory;
  }

  /**
   * 得到IndexWriter对象
   * @param directory 传入directory文件夹对象
   * @return 返回可以使用，没有被close掉的IndexWriter对象
   * @throws IOException
   */
  public IndexWriter getIndexWriter(Directory directory) throws IOException {
    // 如果map中有则直接返回
    if (writerMap.containsKey(directory)) {
      IndexWriter indexWriter = writerMap.get(directory);
      if (indexWriter.isOpen()) {
        return indexWriter;
      }
    }
    // 中文分词器
    SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
    // 索引配置
    IndexWriterConfig config = new IndexWriterConfig(analyzer);
    // 获取索引实例
    IndexWriter indexWriter = new IndexWriter(directory, config);
    // 没有则new一个加入map
    writerMap.put(directory, indexWriter);
    return indexWriter;
  }
}

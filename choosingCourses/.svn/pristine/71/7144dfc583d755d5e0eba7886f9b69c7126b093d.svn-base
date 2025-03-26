package com.longwang.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.longwang.entity.Article;
import com.longwang.entity.User;
import com.longwang.lucene.ArticleIndex;
import com.longwang.run.StartupRunner;
import com.longwang.service.ArticleService;
import com.longwang.service.UserService;
import com.longwang.util.DateUtil;
import com.longwang.util.StringUtil;

/**
 * 文章控制器
 * 
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/admin/article")
public class ArticleAdminController {

  @Resource
  private ArticleService articleService;

  @Resource
  private StartupRunner startupRunner;

  @Resource
  private ArticleIndex articleIndex;

  @Resource
  private UserService userService;

  @Value("${imageFilePath}")
  private String imageFilePath; // 图片上传路径

  /**
   * 生成所有帖子索引(审核通过的资源帖子)
   * 
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/genAllIndex")
  public boolean genAllIndex() {
    List<Article> articleList = articleService.list();
    for (Article article : articleList) {
      try {
        article.setContentNoTag(StringUtil.stripHtml(article.getContent())); // 去除html标签
        articleIndex.addIndex(article);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return false;
      }
    }
    return true;
  }

  /**
   * 前台分页查询文章
   * 
   * @param article
   * @param publishDates
   * @param page
   * @param pageSize
   * @return
   */
  @RequestMapping("/list")
  public Map<String, Object> list(Article article,
      @RequestParam(value = "publishDates", required = false) String publishDates,
      @RequestParam(value = "p", required = false) Integer p,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "pageSize", required = false) Integer pageSize, HttpServletRequest request) {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    User user = (User) request.getSession().getAttribute("user");
    List<Article> retArt = new ArrayList<>();

    String s_bPublishDate = null; // 开始时间
    String s_ePublishDate = null; // 结束时间
    if (StringUtil.isNotEmpty(publishDates)) {
      String[] strs = publishDates.split(" - "); // 拆分时间段
      s_bPublishDate = strs[0];
      s_ePublishDate = strs[1];
    }
    if (p != null && p == 1) {
      User u = userService.findById(user.getUserId());
      article.setUserId(u.getUserId());
    } else if (p != null && p == 2) {
      User u = userService.findById(user.getUserId());
      String artIds = u.getArticleIds();
      List<String> result = new ArrayList<>();
      if (StringUtils.isNotBlank(artIds)) {
        result = Arrays.asList(StringUtils.split(artIds, ","));
      }
      List<Integer> retIds = new ArrayList<>();
      for (String temp : result) {
        retIds.add(Integer.valueOf(temp).intValue());
      }
      retArt = articleService.findByListId(retIds);
    }
    Long total = articleService.getCount(article, s_bPublishDate, s_ePublishDate);
    if (p != null && p == 2) {
      total = (long) retArt.size();
    }
    int totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1); // 总页数
    resultMap.put("totalPage", totalPage);
    resultMap.put("errorNo", 0);
    if (p != null && p == 2) {
      resultMap.put("data", retArt);
    } else {
      resultMap.put("data", articleService.list(article, s_bPublishDate, s_ePublishDate, page - 1, pageSize));
    }
    resultMap.put("total", total);
    return resultMap;
  }

  /**
   * 根据ID查找文章
   * 
   * @param articleId
   * @return
   */
  @RequestMapping("/findById")
  public Map<String, Object> findById(Integer articleId) {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    Map<String, Object> trmpMap = new HashMap<String, Object>();
    Article article = articleService.findById(articleId);
    trmpMap.put("articleId", article.getArticleId());
    trmpMap.put("title", article.getTitle());
    trmpMap.put("content", article.getContent());
    trmpMap.put("publishDate", article.getPublishDate());
    trmpMap.put("author", article.getAuthor());
    trmpMap.put("classify", article.getClassify().getClassifyId());
    trmpMap.put("click", article.getClick());
    trmpMap.put("commentNum", article.getCommentNum());
    trmpMap.put("isTop", article.getIsTop());
    trmpMap.put("isOriginal", article.getIsOriginal());
    trmpMap.put("imageName", article.getImageName());
    resultMap.put("errorNo", 0);
    resultMap.put("data", trmpMap);
    return resultMap;
  }

  /**
   * 添加或者修改文章
   * 
   * @param article
   * @return
   */
  @RequestMapping("/save")
  public Map<String, Object> save(Article article, @RequestParam(value = "_mode", required = false) String mode)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    if (article.getIsTop() == null) {
      article.setIsTop(0);
    }
    if (article.getIsOriginal() == null) {
      article.setIsOriginal(0);
    }
    if (article.getClick() == null) {
      article.setClick(0);
    }
    if (article.getCommentNum() == null) {
      article.setCommentNum(0);
    }
    if (StringUtil.isEmpty(article.getImageName())) {
      article.setImageName("jzytp.JPG");
    }
    article.setPublishDate(new Date());
    article.setContentNoTag(StringUtil.Html2Text(article.getContent()));
    articleService.save(article);
    if ("add".equals(mode)) {
      articleIndex.addIndex(article);
    } else if ("edit".equals(mode)) {
      articleIndex.updateIndex(article);
    }
    resultMap.put("errorNo", 0);
    resultMap.put("data", 1);
    startupRunner.loadData();
    return resultMap;
  }

  /**
   * 批量删除文章
   * 
   * @param ids
   * @return
   */
  @RequestMapping("/delete")
  public Map<String, Object> delete(@RequestParam(value = "articleId") String ids) throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    String[] idsStr = ids.split(",");
    for (int i = 0; i < idsStr.length; i++) {
      articleService.delete(Integer.parseInt(idsStr[i]));
      articleIndex.deleteIndex(idsStr[i]);
    }
    resultMap.put("errorNo", 0);
    resultMap.put("data", 1);
    startupRunner.loadData();
    return resultMap;
  }

  /**
   * 新闻内容图片上传处理
   * 
   * @param file
   * @param CKEditorFuncNum
   * @return
   */
  @RequestMapping("/ckeditorUpload")
  public String ckeditorUpload(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum) {
    String fileName = file.getOriginalFilename(); // 获取文件名
    String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 获取文件的后缀
    String newFileName = "";
    try {
      newFileName = DateUtil.getCurrentDateStr() + suffixName; // 生成新的文件名
      FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath + newFileName)); // 上传
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // 回调到页面
    StringBuffer sb = new StringBuffer();
    sb.append("<script type=\"text/javascript\">");
    sb.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + "/static/images/" + newFileName
        + "','')");
    sb.append("</script>");
    return sb.toString();
  }

}

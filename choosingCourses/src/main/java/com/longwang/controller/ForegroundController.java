package com.longwang.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.longwang.entity.Article;
import com.longwang.entity.Classify;
import com.longwang.entity.User;
import com.longwang.lucene.ArticleIndex;
import com.longwang.service.ArticleService;
import com.longwang.service.ClassifyService;
import com.longwang.service.UserService;
import com.longwang.util.DateUtil;

/**
 * 前台页面请求
 * @author LongWang
 *
 */
@Controller
@RequestMapping("/foreground")
public class ForegroundController {

  @Resource
  private ArticleService articleService;


  @Resource
  private ArticleIndex articleIndex;

  @Resource
  private UserService userService;

  @Resource
  private ClassifyService classifyService;

  @Value("${downloadImagePath}")
  private String downloadImagePath; // 图片下载路径

  /**
   * 文章专栏
   * @return
   */
  @RequestMapping("/article")
  public String article() {
    return "/foreground/article";
  }

  @RequestMapping("/mixed_pic")
  public String mixed_pic() {
    return "/foreground/mixed_pic";
  }

  /**
   * 点点滴滴
   * @return
   */
  @RequestMapping("/timeline")
  public String timeline() {
    return "/foreground/timeline";
  }

  /**
   * 关于本站
   * @return
   */
  @RequestMapping("/about")
  public String about() {
    return "/foreground/about";
  }

  /**
   * 文章详情
   * @return
   */
  @RequestMapping("/detail/{id}")
  public ModelAndView detail(@PathVariable(value = "id", required = false) Integer id, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    articleService.increaseClick(id);
    Article article = articleService.findById(id);
    Article tempArticle = new Article();
    tempArticle.setClassify(article.getClassify());
    session.setAttribute("similarityList", articleService.list(tempArticle, null, null, 0, 10));
    session.setAttribute("RandomArticleList", articleService.getRandomArticle(10)); // 10条随机文章
    // 判断该文章是否收藏
    boolean flag = false;
    User user = (User) session.getAttribute("user");
    if (user != null) {
      String artIds = user.getArticleIds();
      if(artIds!=null){
        List<String> idsList = Arrays.asList(artIds.split(","));
        if (idsList.contains(id.toString())) {
          mav.addObject("flag", true);
        }else{
          mav.addObject("flag", flag);

        }
      }else{
        mav.addObject("flag", flag);

      }

    }
    mav.addObject("article", article);
    mav.setViewName("/foreground/detail");
    return mav;
  }

  /**
   * 文章详情
   * @return
   */
  @RequestMapping("/myDetail/{id}")
  public ModelAndView myDetail(@PathVariable(value = "id", required = false) Integer id, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    articleService.increaseClick(id);
    Article article = articleService.findById(id);
    Article tempArticle = new Article();
    tempArticle.setClassify(article.getClassify());
    session.setAttribute("similarityList", articleService.list(tempArticle, null, null, 0, 10));
    session.setAttribute("RandomArticleList", articleService.getRandomArticle(10)); // 10条随机文章
    // 判断该文章是否收藏
    boolean flag = false;
    User user = (User) session.getAttribute("user");
    if (user != null && user.getArticleIds() != null) {
      String artIds = user.getArticleIds();
      List<String> idsList = Arrays.asList(artIds.split(","));
      if (idsList.contains(id.toString())) {
        mav.addObject("flag", true);
      } else {
        mav.addObject("flag", flag);
      }
    }
    List<Classify> list = classifyService.findAll();
    mav.addObject("list", list);
    mav.addObject("article", article);
    mav.setViewName("/myDetail");
    return mav;
  }

  @RequestMapping("/otherPerson/{id}")
  public ModelAndView otherPerson(@PathVariable(value = "id", required = false) Integer id, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    User user = userService.findById(id);
    User curUser = (User) session.getAttribute("user");
    if (curUser == null) {
      mav.setViewName("redirect:/webLogin");
      return mav;
    }
    String uidStr = curUser.getUserIds();
    boolean flag = false;
    if (uidStr != null) {
      List<String> uidList = Arrays.asList(uidStr.split(","));
      flag = uidList.contains(id.toString());
    }
    // 查询该用户 的所有文章
    List<Article> artList = articleService.findByUserId(id);
    mav.addObject("artList", artList);
    mav.addObject("user", user);
    mav.addObject("personFlag", flag);
    mav.addObject("artCount", artList.size());
    mav.setViewName("otherPerson");
    return mav;
  }

  /**
   * 根据关键字查询相关文章信息
   * @param q
   * @return
   * @throws Exception
   */
  @RequestMapping("/q")
  @ResponseBody
  public Map<String, Object> search(@RequestParam(value = "q", required = false) String q,
                                    @RequestParam(value = "page", required = false) String page) throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    int pageSize = 5;
    List<Article> articleList = articleIndex.searchArticle(q);
    for (Article a : articleList) {
      Article article = articleService.findById(a.getArticleId());
      a.setClick(article.getClick());
      a.setCommentNum(article.getCommentNum());
      a.setAuthor(article.getAuthor());
      a.setImageName(article.getImageName());
      a.setClassify(article.getClassify());
      a.setIsOriginal(article.getIsOriginal());
      a.setIsTop(article.getIsTop());
    }
    int total = articleList.size();
    int totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1); // 总页数
    Integer toIndex = articleList.size() >= Integer.parseInt(page) * pageSize ? Integer.parseInt(page) * pageSize
            : articleList.size();
    resultMap.put("data", articleList.subList((Integer.parseInt(page) - 1) * pageSize, toIndex));
    resultMap.put("q", q);
    resultMap.put("totalPage", totalPage);
    resultMap.put("total", total);
    return resultMap;
  }


  @RequestMapping(value = "/downloadImage", method = RequestMethod.GET)
  public void testDownload(HttpServletResponse res, String fileName) throws Exception {
    res.setHeader("content-type", "application/octet-stream");
    res.setContentType("application/octet-stream");
    String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 获取文件的后缀
    res.setHeader("Content-Disposition", "attachment;filename=" + DateUtil.getCurrentDateStr() + suffixName);
    byte[] buff = new byte[1024];
    BufferedInputStream bis = null;
    OutputStream os = null;
    try {
      os = res.getOutputStream();
      bis = new BufferedInputStream(new FileInputStream(new File(downloadImagePath + fileName)));
      int i = bis.read(buff);
      while (i != -1) {
        os.write(buff, 0, buff.length);
        os.flush();
        i = bis.read(buff);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (bis != null) {
        try {
          bis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    System.out.println("success");
  }
}

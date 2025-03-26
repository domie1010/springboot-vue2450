package com.longwang.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.longwang.entity.Article;
import com.longwang.entity.Classify;
import com.longwang.entity.User;
import com.longwang.service.ArticleService;
import com.longwang.service.ClassifyService;
import com.longwang.service.NoticeService;
import com.longwang.service.UserService;
import com.longwang.util.DateUtil;
import com.longwang.util.StringUtil;

/**
 * 根路径以及其他请求处理
 * 
 * @author LongWang
 *
 */
@Controller
public class IndexController {

  @Value("${imageFilePath}")
  private String imageFilePath; // 文件路径

  @Resource
  private NoticeService noticeService;

  @Resource
  private UserService userService;

  @Resource
  private ArticleService articleService;
  @Resource
  private ClassifyService classifyService;



  @RequestMapping("/")
  public String index(HttpSession session) {

    // 查询公告
    session.setAttribute("noticeList", noticeService.list(0, 5));
    return "index";// 跳转到index.html
  }


  @RequestMapping("/delete")
  public Map<String, Object> delete(Integer userId) {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    userService.delete(userId);
    resultMap.put("errorNo", 0);
    return resultMap;
  }
  /**
   * 登录页面
   * 
   * @return
   */
  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  /**
   * 前台登录页面
   * 
   * @return
   */
  @RequestMapping("/webLogin")
  public String webLogin() {
    return "webLogin";
  }

  /**
   * 注册
   * 
   * @return
   */
  @RequestMapping("/regist")
  public String regist() {
    return "regist";
  }

  /**
   * 保存注册信息
   * 
   * @param user
   * @return
   */
  @RequestMapping("/saveUser")
  public String saveUser(User user) {
      List<Article> randomArticle = articleService.getRandomArticle(3);
      String ids="";
      for (int i = 0; i < randomArticle.size(); i++) {
          Integer articleId = randomArticle.get(i).getArticleId();
          ids+=articleId+",";
      }
      ids = ids.substring(0, ids.length() -1);
      user.setArticleIds(ids);
      userService.save(user);

    return "webLogin";
  }

  /**
   * 退出登录
   * 
   * @param request
   * @return
   */
  @RequestMapping("/quit")
  public String quit(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.removeAttribute("user");
    return "index";
  }


  /**
   * 退出登录
   * 
   * @param request
   * @return
   */
  @RequestMapping("/quitAdmin")
  public String quitAdmin(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.removeAttribute("user");
    return "login";
  }

  /**
   * 验证登录
   *
   * @param user
   * @param request
   * @return
   */
  @RequestMapping("/checkLogin")
  public ModelAndView checkLogin(User user, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    HttpSession session = request.getSession();
    User u = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    if (u == null) {
      mav.addObject("user", user);
      mav.addObject("errorInfo", "用户名或者密码错误!");
      mav.setViewName("webLogin");
    } else {
      u.setLatelyLoginTime(new Date());
      userService.save(u);
      session.setAttribute("user", u);
      mav.addObject("username", u.getUsername());
      mav.addObject("user", u);
      mav.addObject("success", true);
      mav.setViewName("/index");
    }
    return mav;
  }

  /**
   * 查看个人信息
   * 
   * @return
   */
  @RequestMapping("viewPerson")
  public ModelAndView viewPerson(HttpServletRequest request) {
    User user = (User) request.getSession().getAttribute("user");
    ModelAndView mav = new ModelAndView();
    User u = userService.findById(user.getUserId());
    mav.addObject("user", u);
    mav.setViewName("/viewPerson");
    return mav;
  }

  /**
   * 查看个人课程收藏夹
   * 
   * @return
   */
  @RequestMapping("viewCollection")
  public ModelAndView viewCollection(HttpServletRequest request, HttpSession session) {
    User user = (User) request.getSession().getAttribute("user");
    ModelAndView mav = new ModelAndView();
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
    List<Article> retArt = articleService.findByListId(retIds);
    session.setAttribute("noticeList", noticeService.list(0, 5));
    mav.addObject("retArt", retArt);
    mav.addObject("user", u);
    mav.setViewName("/viewCollection");
    return mav;
  }

  /**
   * 查看个人关注用户
   * 
   * @return
   */
  @RequestMapping("viewFocusUser")
  public ModelAndView viewFocusUser(HttpServletRequest request, HttpSession session) {
    User user = (User) request.getSession().getAttribute("user");
    ModelAndView mav = new ModelAndView();
    User u = userService.findById(user.getUserId());
    String userIds = u.getUserIds();
    List<String> result = new ArrayList<>();
    if (StringUtils.isNotBlank(userIds)) {
      result = Arrays.asList(StringUtils.split(userIds, ","));
    }
    List<Integer> retIds = new ArrayList<>();
    for (String temp : result) {
      retIds.add(Integer.valueOf(temp).intValue());
    }
    List<User> retArt = userService.findByListId(retIds);
    session.setAttribute("noticeList", noticeService.list(0, 5));
    mav.addObject("retArt", retArt);
    mav.addObject("user", u);
    mav.setViewName("/viewFocusUser");
    return mav;
  }

  /**
   * 保存用户信息
   * 
   * @param user
   * @return
   */
  @RequestMapping("/save")
  public ModelAndView save(User user) {
    ModelAndView mav = new ModelAndView();
    userService.save(user);
    mav.setViewName("/index");
    return mav;
  }

  /**
   * 写笔记页面
   * 
   * @param request
   * @return
   */
  // @RequestMapping("notePage")
  // public String notePage(HttpServletRequest request, Model model) {
  // User user = (User) request.getSession().getAttribute("user");
  // if (user == null) {
  // return "webLogin";
  // }
  // List<Classify> list = classifyService.findAll();
  // model.addAttribute("list", list);
  // return "one";
  // }

  @RequestMapping("notePage")
  public ModelAndView notePage(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      mav.setViewName("/webLogin");
      return mav;
    }
    List<Classify> list = classifyService.findAll();
    mav.addObject("list", list);
    mav.setViewName("/one");
    return mav;
  }

  /**
   * 保存笔记
   * 
   * @param article
   * @param request
   * @return
   */
  @RequestMapping("addNote")
  public ModelAndView addNote(Article article, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    // 获取当前用户信息
    User user = (User) request.getSession().getAttribute("user");
    article.setUserId(user.getUserId());
    article.setPublishDate(new Date());
    article.setClick(0);
    article.setCommentNum(0);
    article.setContentNoTag(StringUtil.Html2Text(article.getContent()));
    articleService.save(article);
    mav.setViewName("/index");
    return mav;
  }

  @RequestMapping("saveNote")
  public ModelAndView saveNote(Article article, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    Article a = articleService.findById(article.getArticleId());
    article.setPublishDate(a.getPublishDate());
    // 获取当前用户信息
    articleService.save(article);
    mav.setViewName("/index");
    return mav;
  }

  /**
   * 查看笔记
   * 
   * @return
   */
  @RequestMapping("viewNote")
  public String viewNote(HttpSession session) {
    session.setAttribute("noticeList", noticeService.list(0, 5));
    return "mylist";
  }

  @RequestMapping("/delete/{id}")
  public String delete(@PathVariable(value = "id") String id) throws Exception {
    articleService.delete(Integer.parseInt(id));
    return "mylist";
  }

  /**
   * 查看个人笔记加载数据列表
   * 
   * @param article
   * @param publishDates
   * @param page
   * @param pageSize
   * @return
   */
  @RequestMapping("/mylist")
  public Map<String, Object> list(Article article,
      @RequestParam(value = "publishDates", required = false) String publishDates,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "pageSize", required = false) Integer pageSize, HttpServletRequest request) {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    // User user = (User) request.getSession().getAttribute("user");
    // article.setUserId(user.getUserId());
    String s_bPublishDate = null; // 开始时间
    String s_ePublishDate = null; // 结束时间
    if (StringUtil.isNotEmpty(publishDates)) {
      String[] strs = publishDates.split(" - "); // 拆分时间段
      s_bPublishDate = strs[0];
      s_ePublishDate = strs[1];
    }
    Long total = articleService.getCount(article, s_bPublishDate, s_ePublishDate);
    int totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1); // 总页数
    resultMap.put("totalPage", totalPage);
    resultMap.put("errorNo", 0);
    resultMap.put("data", articleService.list(article, s_bPublishDate, s_ePublishDate, page - 1, pageSize));
    resultMap.put("total", total);
    return resultMap;
  }

  /**
   * 后台默认首页
   * 
   * @return
   */
  @RequestMapping("/index")
  public String root() {
    return "/common/index";
  }

  /**
   * 博主信息页面
   * 
   * @return
   */
  @RequestMapping("/blogger")
  public String blogger() {
    return "/blogger/index";
  }

  /**
   * 图片上传处理 @Title: ckeditorUpload @param file 图片文件 @return 参数说明 @return
   * Map<String,Object> 返回类型 @throws
   */
  @ResponseBody
  @RequestMapping("/upload")
  public Map<String, Object> ckeditorUpload(@RequestParam("file") MultipartFile file) {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    Map<String, Object> resultMap1 = new HashMap<String, Object>();
    String fileName = file.getOriginalFilename(); // 获取文件名
    String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 获取文件的后缀
    String newFileName = "";
    try {
      newFileName = DateUtil.getCurrentDateStr() + suffixName; // 新文件名
      FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath + newFileName)); // 上传
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    resultMap.put("code", 0);
    resultMap1.put("filePath", newFileName);
    resultMap.put("data", resultMap1);
    return resultMap;
  }

}

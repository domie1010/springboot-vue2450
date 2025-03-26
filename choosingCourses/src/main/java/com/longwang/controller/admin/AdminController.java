package com.longwang.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.longwang.entity.Admin;
import com.longwang.entity.User;
import com.longwang.service.AdminService;
import com.longwang.service.ArticleService;
import com.longwang.service.ClassifyService;
import com.longwang.service.UserService;

/**
 * 管理员控制器
 * @author LongWang
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

  @Value("${MD5Salt}")
  private String salt; // md5加密盐

  @Resource
  private AdminService adminService;
  @Resource
  private UserService userService;
  @Resource
  private ArticleService articleService;
  @Resource
  private ClassifyService classifyService;

  /**
   * 后台管理员登录验证
   * @param admin
   * @param request
   * @return
   */
  @RequestMapping("/login")
  public ModelAndView login(Admin admin, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    HttpSession session = request.getSession();
    try {
      Admin resultUser = adminService.findByUserNameAndPassword(admin.getUserName(), admin.getPassword());
      if (resultUser == null) {
        mav.addObject("errorInfo", "用户名或者密码错误!");
        mav.setViewName("/login");
      } else {
        session.setAttribute("adminUser", resultUser);
        // 统计用户总数
        Long userCount = userService.getCount();
        // 统计今天注册
        Long userRegCount = userService.getTodayRegistCount(new User(), "1", "1");
        // 统计今日登录
        Long userLogCount = userService.getCount(new User(), "1", "1");
        // 统计笔记总数
        Long artCount = articleService.getCount();
        // 统计笔记分类
        Long classCount = classifyService.getCount();

        session.setAttribute("userCount", userCount);
        session.setAttribute("userRegCount", userRegCount);
        session.setAttribute("userLogCount", userLogCount);
        session.setAttribute("artCount", artCount);
        session.setAttribute("classCount", classCount);

        mav.addObject("success", true);
        mav.setViewName("/admin/index");
      }
    } catch (Exception e) { // 用户名密码错误
      e.printStackTrace();
      mav.addObject("admin", admin);
      mav.addObject("errorInfo", "用户名或者密码错误!");
      mav.setViewName("/login");
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
    Admin admin = (Admin) request.getSession().getAttribute("adminUser");
    ModelAndView mav = new ModelAndView();
    Admin u = adminService.findById(admin.getAdminId());
    mav.addObject("user", u);
    mav.setViewName("/admin/adminViewPerson");
    return mav;
  }

  /**
   * 保存用户信息
   * 
   * @param user
   * @return
   */
  @RequestMapping("/save")
  public ModelAndView save(Admin user) {
    ModelAndView mav = new ModelAndView();
    adminService.save(user);
    mav.setViewName("/admin/index");
    return mav;
  }
}

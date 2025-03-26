package com.longwang.run;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.longwang.service.ArticleService;
import com.longwang.service.BloggerService;
import com.longwang.service.ClassifyService;
import com.longwang.service.LinkService;

/**
 * 启动服务加载数据
 * @author LongWang
 *
 */
@Component("startupRunner")
public class StartupRunner implements CommandLineRunner{
	
	@Autowired
	private ServletContext application;
	
	@Resource
	private BloggerService bloggerService;
	
	
	@Resource
	private LinkService linkService;
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private ClassifyService classifyService;
	
	@Override
	public void run(String... args) throws Exception {
		this.loadData();
	}
	
	/**
	 * 加载数据到applicaton缓存中
	* @Title: loadData   参数说明 
	* @return void    返回类型 
	* @throws
	 */
	public void loadData(){
		application.setAttribute("classifyList", classifyService.list(0, 20));//加载文章类别
		application.setAttribute("blogger", bloggerService.find());//加载博主信息
		application.setAttribute("clickArticleList", articleService.getClickArticle(10));//加载热门文章（点击排行）
		application.setAttribute("recommendArticleList", articleService.getRecommendArticle(10));//加载置顶原创文章（站长推荐）
		application.setAttribute("linkList", linkService.list(null, 0, 10));  //10条友情链接
	}
	
}

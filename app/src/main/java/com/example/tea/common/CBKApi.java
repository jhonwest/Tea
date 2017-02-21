package com.example.tea.common;
/**
 * 自定义查百科Api常量
 * @author dell
 *
 */
public class CBKApi {
	//获取最新apk版本路径
	public static final String UPDATE_APK_PATH = "http://baike.maimaicha.com/getversion.ashx";
	//首页数据路径
	public static final String HOMEPATH = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getSlideshow";
	//头条数据路径
	public static final String HEADLINEPATH = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getHeadlines";
	//内容新页 
	public static final String NEWCONTENTPATH = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getNewsContent";
	//百科	post： rows=15&page=0&type=16
	public static final String BAIKEPATH = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType";
	//搜索   post： rows=10&page=1&search=tea
	public static final String SEARCHPATH="http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.searcListByTitle";
	//资讯	post：rows=15&page=0&type=52
	public static final String INFORMATIONPATH = BAIKEPATH;
	//经营接口：同上  post：rows=15&page=0&type=53
	public static final String OPERATE = BAIKEPATH; 
	//数据接口：同上	post：rows=15&page=0&type=54
	public static final String DATAPAGER = BAIKEPATH;
}
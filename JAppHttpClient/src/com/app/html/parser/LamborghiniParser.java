package com.app.html.parser;

import java.util.List;

import org.htmlparser.Parser;

/**
 * @author jim_qiao 兰博基尼
 */
public class LamborghiniParser extends BaseParser{
	ParserTemplate parserTemplate = new ParserTemplate() {
		
		public List<News> doSearch(Parser parser) throws Exception {
			
			return null;
		}
	};

	@Override
	public String getEncoding() {
		return "gb2312";
	}

	@Override
	public String getUrl() {
		return "http://image.baidu.com/i?tn=baiduimage&ct=201326592&cl=2&lm=-1&fr=&nc=1&pv=&z=0&word=%C0%BC%B2%A9%BB%F9%C4%E1";
	}

	@Override
	public boolean hasApplyProxy() {
		return true;
	}
}

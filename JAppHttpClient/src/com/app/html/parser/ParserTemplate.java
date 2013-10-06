package com.app.html.parser;

import java.util.List;

import org.htmlparser.Parser;

public interface ParserTemplate {
	public abstract List<News> doSearch(Parser parser) throws Exception;
}

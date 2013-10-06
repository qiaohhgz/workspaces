package demo2;

import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

public interface IBookPage {
	List<Book> parseResults(String bookUrl) throws Exception;

	List<Book> parseOnePage(Parser parser) throws ParserException;

	String getImgSrc(Parser parser) throws ParserException;

	String getPage(Parser parser) throws ParserException;
}

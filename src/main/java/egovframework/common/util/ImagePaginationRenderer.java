package egovframework.common.util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

//http://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte:ptl:view:paginationtag
public class ImagePaginationRenderer  extends AbstractPaginationRenderer  {
	public ImagePaginationRenderer () {

		firstPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{1}&#160;</a>"; 
		previousPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"prev\"><img src=\"./images/p_prev.gif\" alt=\"이전페이지\"/>&#160;</a>";
		currentPageLabel = "<a href=\"#\" id=\"on_page\">{0}</a>&#160;";
		otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>&#160;";
		nextPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"next\"><img src=\"./images/p_next.gif\" alt=\"다음페이지\"/>&#160;</a>";
		lastPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{1}&#160;</a>";
		
	}
}

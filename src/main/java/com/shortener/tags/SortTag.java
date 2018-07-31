package com.shortener.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

//Тег для сортування
public class SortTag extends SimpleTagSupport {

    private final StringWriter sw = new StringWriter();
    private final static String AMPER = "&";
    private final static String QUEST = "?";
    private final static String EQUAL = "=";
    private final static String SORT = "sort";
    private String paramValue = "";
    private String innerHtml = "";

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Map<String, String[]> map = request.getParameterMap();
        if (isParamValuePresent(map)) {
            sw.append("<a class='active' href='");
        } else {
            sw.append("<a href='");
        }
        sw.append(QUEST);
        sw.append(SORT);
        sw.append(EQUAL);
        sw.append(paramValue);
        for (Entry<String, String[]> entry : map.entrySet()) {
            for (String value : entry.getValue()) {
                if (!entry.getKey().equals(SORT)) {
                    sw.append(AMPER);
                    sw.append(entry.getKey());
                    sw.append(EQUAL);
                    sw.append(value);
                }
            }
        }
        sw.append("'>");
        sw.append(innerHtml);
        sw.append("</a>");
        out.println(sw.toString());
    }

    private boolean isParamValuePresent(Map<String, String[]> map) {
        return map.entrySet().stream()
                .filter(entry -> entry.getKey().equals(SORT))
                .map(Entry::getValue)
                .flatMap(Arrays::stream)
                .anyMatch((str) -> str.equals(paramValue));
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public void setInnerHtml(String innerHtml) {
        this.innerHtml = innerHtml;
    }
}

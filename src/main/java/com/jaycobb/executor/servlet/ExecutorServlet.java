package com.jaycobb.executor.servlet;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class ExecutorServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(ExecutorServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		final String expressionParam = req.getParameter("exp");
		if (expressionParam == null || "".equals(expressionParam.trim())) {
			resp.getWriter().println("Empty");
			log.warning("EXPRESSION IS EMPTY");
			return;
		}
		log.info("RAW=" + expressionParam + "");
		final String expression = StringEscapeUtils.unescapeJava(expressionParam);
		log.info("DECODED=" + expression + "");
		JSONObject json = new JSONObject();
		Object messageObject = "";
		try {
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(expression);
			messageObject = exp.getValue();
			log.info("RESULT=" + messageObject);
		} catch (Exception e) {
			log.warning("ERROR=" + e.getMessage());
			messageObject = e.getMessage();
		}

		try {
			json.put("result", messageObject.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		resp.setContentType("text/x-json");
		resp.getWriter().println(json.toString());
	}

	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		Object two = parser.parseExpression("4 + 4").getValue();
		System.out.println(two);
	}


}

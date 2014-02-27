<%-- 
    Document   : updateItem
    Created on : Dec 2, 2013, 1:29:50 AM
    Author     : federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
--%>

<%@page import="eu.himeros.cophi.ocr.proofreader.model.Insertion"%>
<%@page import="eu.himeros.cophi.ocr.proofreader.model.OcrWord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="pageBean" class="eu.himeros.cophi.ocr.proofreader.controller.bean.OcrPageBean" scope="session"/>
<%
    String id=request.getParameter("id");
    String text=request.getParameter("text");
    String[] wLineWord=id.split("_");
    int lineId=Integer.parseInt(wLineWord[1]);
    int wordId=Integer.parseInt(wLineWord[2]);
    pageBean.updateInsertion(text.trim(),lineId,wordId);
    pageBean.save();
%>
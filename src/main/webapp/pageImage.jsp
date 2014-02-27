<%-- 
    Document   : index
    Created on : Nov 27, 2013, 2:56:06 PM
    Author     : federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
--%>

<%@page import="java.io.OutputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="org.primefaces.model.DefaultStreamedContent"%>
<%@page import="eu.himeros.cophi.ocr.proofreader.model.Deletion"%>
<%@page import="org.jdom2.Document"%>
<%@page import="eu.himeros.cophi.ocr.proofreader.controller.bean.OcrPageBean"%>
<%@page import="eu.himeros.cophi.ocr.proofreader.model.Insertion"%>
<%@page import="eu.himeros.cophi.ocr.proofreader.model.OcrWord"%>
<%@page import="eu.himeros.cophi.ocr.proofreader.model.OcrLine"%>
<%@page import="eu.himeros.cophi.ocr.proofreader.controller.bean.OcrBookBean"%>
<%@page import="eu.himeros.cophi.ocr.proofreader.model.OcrLibrary"%>
<%@page import="java.util.List"%>
<%@page import="eu.himeros.cophi.ocr.proofreader.model.OcrPage"%>
<%@page import="eu.himeros.cophi.ocr.proofreader.model.OcrBook"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="initBean" class="eu.himeros.cophi.ocr.proofreader.controller.bean.InitBean" scope="session"/>
<jsp:useBean id="libraryBean" class="eu.himeros.cophi.ocr.proofreader.controller.bean.OcrLibraryBean" scope="session"/>
<jsp:useBean id="bookBean" class="eu.himeros.cophi.ocr.proofreader.controller.bean.OcrBookBean" scope="session"/>
<jsp:useBean id="pageBean" class="eu.himeros.cophi.ocr.proofreader.controller.bean.OcrPageBean" scope="session"/>
<jsp:useBean id="pageImageBean" class="eu.himeros.cophi.ocr.proofreader.controller.bean.OcrPageImageBean" scope="session"/>
<% 
    pageImageBean.setLibraryBean(libraryBean);
    String lineId=request.getParameter("lineId");
    int x1=Integer.parseInt(request.getParameter("x1"));
    int y1=Integer.parseInt(request.getParameter("y1"));
    int x2=Integer.parseInt(request.getParameter("x2"));
    int y2=Integer.parseInt(request.getParameter("y2"));
    BufferedImage bimg=pageImageBean.getImage(lineId,x1,y1,x2,y2);
    //FB BEGIN 20140216
    response.setContentType("image/png");
    //response.setContentType("image/jpg");
    OutputStream os=response.getOutputStream();
    ImageIO.write(bimg,"png",os);
    //ImageIO.write(bimg,"jpg",os);
    //END 20140216
    os.close();
%>
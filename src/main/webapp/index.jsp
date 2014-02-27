<%-- 
    Document   : index
    Created on : Nov 27, 2013, 2:56:06 PM
    Author     : federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
--%>

<%@page import="java.awt.image.BufferedImage"%>
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
<!DOCTYPE html>
<html xmlns="http://www.">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script src="jquery-2.0.3.min.js"></script>
        <script>
        
        </script>
        <script lang="javascript">
            var prevText;
            var cleanSymbRe=/[♻☚]/gi;
            function adjustText(text){
                text=text.replace('˙','·');
                return text.replace(cleanSymbRe,'');
            }
            function update(id, newText) {
                newText=adjustText(newText);
                $.ajax({url:"updateWord.jsp",type:"POST",data:{id:id,text:newText}});
                document.getElementById(id).innerHTML = newText;
                document.getElementById(id).style = 'color: black;';
                
            }
            function storeOnFocus(text){
                prevText=text;
            }
            function updateOnBlur(id,newText,color){
                if(prevText!==newText||color!==1){
                    update(id,newText);
                }
            }
            function setSelected(k,b){
                if(k===b){
                    return 'true';
                }else{
                    return 'false';
                }
            }
            
        </script>

        <jsp:useBean id="initBean" class="eu.himeros.cophi.ocr.proofreader.controller.bean.InitBean" scope="session"/>
        <jsp:useBean id="libraryBean" class="eu.himeros.cophi.ocr.proofreader.controller.bean.OcrLibraryBean" scope="session"/>
        <jsp:useBean id="bookBean" class="eu.himeros.cophi.ocr.proofreader.controller.bean.OcrBookBean" scope="session"/>
        <jsp:useBean id="pageBean" class="eu.himeros.cophi.ocr.proofreader.controller.bean.OcrPageBean" scope="session"/>
        <jsp:useBean id="pageImageBean" class="eu.himeros.cophi.ocr.proofreader.controller.bean.OcrPageImageBean" scope="session"/>
        <%
            String p=request.getParameter("p");
            String b=request.getParameter("b");
            int currBookReference=0;
            int currPageReference=0;
            //pageBean.save();
            if(b!=null){
                currBookReference=Integer.parseInt(b);
            }
            if(p!=null){
                currPageReference=Integer.parseInt(p);
                
            }
            libraryBean.setInitBean(initBean);
            libraryBean.init();
            libraryBean.getLibrary().setCurrBookReference(currBookReference);
            bookBean.setLibraryBean(libraryBean);
            bookBean.initPages();
            bookBean.setCurrPageReference(currPageReference);
            pageBean.setBookBean(bookBean);
            pageBean.init();
            OcrPage currPage = pageBean.getPage();
            List<OcrLine> lines = currPage.getOcrLines();
        %>
        <div id="container" style="height: 100%">
            <div id="header" style="height: 10%">
                <%
                    List<OcrBook> books = libraryBean.getLibrary().getOcrBooks();
                %>
                <select onchange="if(this.value) window.location.href=this.value" style="font-size: 50%" id="book">
                    <%int k=0;for (OcrBook book : books) {%>
                    <% String selected=((k==currBookReference)?"selected=\"true\"":"");%>
                    <option <%=selected%> label="<%=book.getOcrBookLabel()%>" value="index.jsp?b=<%=k%>&p=<%=0%>"><%=book.getOcrBookLabel()%></option>
                    <%k++;}%>
                </select>
            </div>
            <div id="left" style="height: 20%">
                <%
                    List<OcrPage> pages=libraryBean.getLibrary().getCurrBook().getOcrPages();
                    %>
                <%int l=0;for (OcrPage progPage : pages) {%>
                <span style="font-size: 33%; line-height: 1px;"><a style="text-decoration:none;" href="index.jsp?b=<%=currBookReference%>&p=<%=l%>"><%=progPage.getOcrPageLabel()%></a></span>
                <%l++;}%>
            </div>
            <div id="main" style="float: left; width: 100%; height: 50%;overflow-y: scroll;">
                <form id="form" action="">

                <%int i=0;for (OcrLine line : lines) {%>
                <%
                    String lineId = line.getId();
                    int x1 = line.getScan().getCoords().getX1();
                    int y1 = line.getScan().getCoords().getY1();
                    int x2 = line.getScan().getCoords().getX2();
                    int y2 = line.getScan().getCoords().getY2();
                %>
                <br/>
                <img height="19" src="pageImage.jsp?lineId=<%=lineId%>&x1=<%=x1%>&y1=<%=y1%>&x2=<%=x2%>&y2=<%=y2%>"/><br/>
                <%int j=0;for (OcrWord word : line.getOcrWords()) {%>
                <span onfocus="storeOnFocus(this.textContent)" onblur="updateOnBlur('<%="w"+"_"+i+"_"+j%>',this.textContent,<%=word.getInsertion().getNlpDbl()%>)" contextmenu="m<%="w"+"_"+i+"_"+j%>" style="color: <%=word.getInsertion().getNlpColor()%>;" id="<%="w"+"_"+i+"_"+j%>" class="w" contenteditable="true"><%=word.getInsertion().getContent()%> </span>
                <%j++;}%>
                <br/>
                <%j=0;for (OcrWord word : line.getOcrWords()) {%>
                <menu type="context" id="m<%="w"+"_"+i+"_"+j%>">
                    <%for (Deletion deletion : word.getDeletions()) {%>
                      <menuitem onclick="update('<%="w"+"_"+i+"_"+j%>', '<%=deletion.getContent()%>')" label="<%=deletion.getContent()%>"></menuitem>
                    <%}%>
                    <menuitem onclick="update('<%="w"+"_"+i+"_"+j%>', '<%=word.getInsertion().getContent()%>')" label="<%=word.getInsertion().getContent()%>↶"></menuitem>
                </menu>
                <%j++;}%>
                <%i++;}%>
                </form>
            </div>
        </div>
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="/struts-tags" prefix="s" %>
        <%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>
                    <decorator:title default="Sistema de Seguros"></decorator:title>
                </title>
                <link rel="stylesheet" href="styles/main-decorator.css">
            </head>

            <body>
                <header>
                    <ul>
                        <a href="/insurance">
                            <li>Home</li>
                        </a>
                        <a href="/insurance/salvar-segurado">
                            <li>Segurado</li>
                        </a>
                        <a href="/insurance/salvar-apolice">
                            <li>Apólice</li>
                        </a>
                        <a href="/insurance/salvar-certificado">
                            <li>Certificado</li>
                        </a>
                    </ul>
                </header>
                <decorator:head />
                <decorator:body />
                <footer></footer>
            </body>

            </html>
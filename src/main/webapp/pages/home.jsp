<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="/struts-tags" prefix="s" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Sistema de Seguros</title>
            <link rel="stylesheet" href="styles/home.css">
        </head>

        <body>
            <main>
                <ul>
                    <a href="<s:url action="salvar-segurado" />"><li>Segurado</li></a>
                    <a href="<s:url action="salvar-apolice" />"><li>Apólice</li></a>
                    <a href="<s:url action="salvar-certificado" />"><li>Certificado</li></a>

                </ul>

            </main>
        </body>

        </html>
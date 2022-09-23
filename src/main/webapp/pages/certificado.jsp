<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="/struts-tags" prefix="s" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Certificado</title>
            <link rel="stylesheet" href="styles/certificado.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script>
                $(document).ready(function () {

                    $("#consultaForm").hide()

                    $("#cadastrar").click(function () {
                        $("#cadastroForm").show()
                        $("#consultaForm").hide()
                    })

                    $("#consultar").click(function () {
                        $("#cadastroForm").hide()
                        $("#consultaForm").show()
                    })
                });
            </script>
        </head>

        <body>
            <div class="menu">
                <ul>
                    <li id="cadastrar">Cadastrar</li>
                    <li id="consultar">Consultar</li>
                </ul>
            </div>
            <main>
                <div action="" id="cadastroForm">
                    <h3>TELA DE CRIAÇÃO DE CERTIFICADO</h3>
                </div>

                <div id="consultaForm">
                    <h3>TELA DE CONSULTA DE CERTIFICADO</h3>
                    </vid>

            </main>
        </body>

        </html>
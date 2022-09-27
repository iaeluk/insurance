<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="/struts-tags" prefix="s" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Certificado</title>
            <link rel="stylesheet" href="styles/certificado.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script>
                $(document).ready(function () {

                    $(".detalhes").click(function () {
                        let index = ($('.detalhes').index(this))
                        $(".buscarCertificadoPorId")[index].click()
                        $(".form-detalhes").removeClass("hidden")
  
                    })

                    $(".editar").click(function () {
                        let index = ($('.editar').index(this))
                        $(".editarCertificado")[index].click()
                        $(".form-edicao").removeClass("hidden")
  
                    })

                    $(".btn-fechar").click(function () {
                        $(".form-detalhes").addClass("hidden")
                        $(".form-edicao").addClass("hidden")
                    })


                    $("#cadastrar").click(function () {
                        window.location.href = "salvar-certificado";
                    })

                    $("#buscar").click(function () {
                        window.location.href = "buscar-certificado";
                    })


                    $(".excluir").click(function () {
                        let index = ($('.excluir').index(this))

                        Swal.fire({
                            title: 'Tem certeza?',
                            text: "Esta ação é irreversível!",
                            icon: 'warning',
                            showCancelButton: true,
                            confirmButtonColor: 'green',
                            cancelButtonColor: '#d33',
                            confirmButtonText: 'Sim, deletar',
                            cancelButtonText: 'Cancelar'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                Swal.fire(
                                    'Deletado!',
                                    'O Certificado foi deletado.',
                                    'success'
                                ).then(function() {
                                    $(".confirmarExclusao")[index].click()
                                })                            
                            }
                        })
                    })

                    let varSeguradoId = <s:property value="apoliceAuto.seguradoId"/>
                    $(".opcoesSegurados").each(function(){
                        if($(this).val() == varSeguradoId) {
                            $(this).attr("selected", "selected")
                        }
                    });
                });
            </script>
        </head>

        <body>
            <div class="menu">
                <ul>
                    <li id="cadastrar">Cadastrar</li>
                    <li id="buscar">Consultar</li>
                </ul>
            </div>
            <main>
                <s:if test="%{tipoFormulario=='salvarCertificado'}">
                    <form id="cadastroForm" method="post" action="salvar-certificado-submit">
                        <h2>CADASTRO DE CERTIFICADO</h2>
                        <h3>INSERÇÃO DE DADOS</h3>
                        <span>
                            <label for="apoliceAutoId">Apólice:</label>
                            <select name="certificado.apoliceAutoId" id="apoliceAutoId">
                                <s:iterator value="apolices">
                                     <option value="<s:property value="id" />">idApolice: <s:property value="id" /> | idSegurado: <s:property value="seguradoId" /></option>
                                 </s:iterator>
                            </select>
                        </span>

                        <span>
                            <s:textfield label="Cd Prod Ret" name="certificado.cdProdutoRet" type="number" required="true" minlength="5" maxlength="100"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Chave negócio" name="certificado.chaveNegocio" type="number" required="true"></s:textfield>
                        </span>

                        <span>
                            <label for="descricaoSituacao">Descrição situação:</label>
                            <select name="certificado.descricaoSituacao">
                                <option value="SITUACAO_A" selected>SITUACAO_A</option>
                                <option value="SITUACAO_B">SITUACAO_B</option>
                                <option value="SITUACAO_C">SITUACAO_C</option>
                                <option value="SITUACAO_D">SITUACAO_D</option>
                            </select>
                        </span>

                        <span>
                            <s:textfield label="Nome produto" name="certificado.nomeProduto" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Ramo" name="certificado.ramo" type="text" required="true"></s:textfield>
                        </span>
                        
                            <button type="submit"><a id="salvar">CADASTRAR</a></button>
                    </form>
                </s:if>

                <s:if test="%{tipoFormulario=='buscarCertificado'}">

                    <div id="consultaForm">
                        <table>
                            <caption>CONSULTA DE CERTIFICADO</caption>
                            <tr>
                                <th>ID</th>
                                <th>ID Apólice</th>
                                <th>Cd Produto Ret</th>
                                <th>Chave Negócio</th>
                                <th>Descrição Situação</th>
                                <th>Nome Produto</th>
                                <th>Ramo</th>
                                
                                <th>Edição/Exclusão</th>
                            </tr>
                            <s:iterator value="certificados">

                                <tr class="linha">
                                    <td>
                                        <s:property value="id" />
                                    </td>
                                    <td>
                                        <s:property value="apoliceAutoId" />
                                    </td>
                                    <td>
                                        <s:property value="cdProdutoRet" />
                                    </td>
                                    <td>
                                        <s:property value="chaveNegocio" />
                                    </td>
                                    <td>
                                        <s:property value="descricaoSituacao" />
                                    </td>
                                    <td>
                                        <s:property value="nomeProduto" />
                                    </td>
                                    <td>
                                        <s:property value="ramo" />
                                    </td>
                                    
                                    <td class="acoes">
                                        <s:url var="buscarCertificadoPorId" action="buscar-certificado-id">
                                            <s:param name="id" value="id" />
                                        </s:url>
                                        <s:url var="deletarCertificado" action="deletar-certificado-id">
                                            <s:param name="id" value="id" />
                                        </s:url>

                                        <s:url var="editarCertificado" action="editar-certificado-id">
                                            <s:param name="id" value="id" />
                                        </s:url>

                                        <span class="detalhes"><img src="https://cdn-icons-png.flaticon.com/512/709/709612.png" width="20px" alt="detalhes" title="Ver detalhes"></span>
                                        <span class="editar"><img src="https://cdn-icons-png.flaticon.com/512/1827/1827951.png" width="20px" alt="edição" title="Editar"></span>
                                        <span class="excluir"><img src="https://cdn-icons-png.flaticon.com/512/542/542673.png" width="20px" alt="lixeira" title="Excluir"></span>
                                        
                                        <s:a class="buscarCertificadoPorId" href="%{buscarCertificadoPorId}"></s:a>
                                        <s:a class="editarCertificado" href="%{editarCertificado}"></s:a>
                                        <s:a class="confirmarExclusao" href="%{deletarCertificado}"></s:a>


                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                        </vid>


                        <s:if test="%{verDetalhes==true}">
                            <div class="form-detalhes">
                                <span class="btn-fechar"><img src="https://cdn-icons-png.flaticon.com/512/3161/3161830.png" width="20px" alt="Fechar" title="Fechar"></span>
                                <div class="form-detalhes-items">
                                    <h3>Dados do Certificado</h3>
                                    <div class="dados-pessoais">
                                    <s:textfield label="Id" name="certificado.id" type="text" disabled="true" value="%{certificado.id}" />
                                    <s:textfield label="Apólice Id" name="certificado.apoliceAutoId" type="text" disabled="true" value="%{certificado.apoliceAutoId}" />
                                    <s:textfield label="Cd produto ret" name="certificado.cdProdutoRet" type="text" disabled="true" value="%{certificado.cdProdutoRet}" />
                                    <s:textfield label="Chave negócio" name="certificado.chaveNegocio" type="text" disabled="true" value="%{certificado.chaveNegocio}" />
                                    <s:textfield label="Descrição situação" name="certificado.descricaoSituacao" type="text" disabled="true" value="%{certificado.descricaoSituacao}" />
                                    <s:textfield label="Nome produto" name="certificado.nomeProduto" type="text" disabled="true" value="%{certificado.nomeProduto}" />
                                    <s:textfield label="Ramo" name="certificado.ramo" type="text" disabled="true" value="%{certificado.ramo}" />
                                    <s:textfield label="" name="" type="text" disabled="true" value="" />
                                </div>
                                </div>
                            </div>
                        </s:if>
                </s:if>

                <s:if test="%{verEdicao==true}">
                            <form class="form-edicao" method="post" action="editar-certificado-id-submit?id=<s:property value="id" />">
                                <span class="btn-fechar"><img src="https://cdn-icons-png.flaticon.com/512/3161/3161830.png" width="20px" alt="Fechar" title="Fechar"></span>
                                <div class="form-edicao-items">
                                    <h3>Dados da Apólice</h3>
                                    <div class="dados-pessoais">
                                        <s:textfield label="Id" name="certificado.id" type="text" disabled="true" value="%{certificado.id}" />
                                        <s:textfield label="Apólice Id" name="certificado.apoliceAutoId" type="text" value="%{certificado.apoliceAutoId}" />
                                        <s:textfield label="Cd produto ret" name="certificado.cdProdutoRet" type="number" value="%{certificado.cdProdutoRet}" />
                                        <s:textfield label="Chave negócio" name="certificado.chaveNegocio" type="number" value="%{certificado.chaveNegocio}" />
                                        <s:textfield label="Descrição situação" name="certificado.descricaoSituacao" type="text" value="%{certificado.descricaoSituacao}" />
                                        <s:textfield label="Nome produto" name="certificado.nomeProduto" type="text" value="%{certificado.nomeProduto}" />
                                        <s:textfield label="Ramo" name="certificado.ramo" type="text" value="%{certificado.ramo}" />
                                        <s:textfield label="" name="" type="text" value="" />
                                    </div>
                                </div>
                                </div>

                                <s:url var="editarCertificado" action="editar-certificado-id-submit"><s:param name="id" value="id" /></s:url>
                                <button type="submit"><s:a id="salvar" class="editarCertificado" href="%{editarCertificado}">ATUALIZAR</s:a></button>
                            </form>
                        </s:if>
            </main>
        </body>
        </html>
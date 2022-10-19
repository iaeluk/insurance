<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="/struts-tags" prefix="s" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Apólice</title>
            <link rel="stylesheet" href="styles/apolice.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script>
                $(document).ready(function () {

                    $(".detalhes").click(function () {
                        let index = ($('.detalhes').index(this))
                        $(".buscarApoliceAutoPorId")[index].click()
                        $(".form-detalhes").removeClass("hidden")
  
                    })

                    $(".editar").click(function () {
                        let index = ($('.editar').index(this))
                        $(".editarApoliceAuto")[index].click()
                        $(".form-edicao").removeClass("hidden")
  
                    })

                    $(".btn-fechar").click(function () {
                        $(".form-detalhes").addClass("hidden")
                        $(".form-edicao").addClass("hidden")
                    })


                    $("#cadastrar").click(function () {
                        window.location.href = "salvar-apolice";
                    })

                    $("#buscar").click(function () {
                        window.location.href = "buscar-apolice";
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
                                    'A apólice foi deletada.',
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
                <s:if test="%{tipoFormulario=='salvarApoliceAuto'}">
                    <form id="cadastroForm" method="post" action="salvar-apolice-submit">
                        <h2>CADASTRO DE APÓLICE</h2>
                        <h3>INSERÇÃO DE DADOS</h3>
                        <span>
                            <s:textfield label="Nome Corretor" name="apoliceAuto.nomeCorretor" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Nº documento corretor" name="apoliceAuto.numeroDocumentoCorretor" type="number" required="true" minlength="5" maxlength="100"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="E-mail corretor" name="apoliceAuto.emailCorretor" type="email" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:date name="apoliceAuto.dataInicioVigencia" var="formattedDate" format="dd-MM-yyyy" />
                            <s:textfield label="Data início vigência" name="apoliceAuto.dataInicioVigencia" value="%{formattedDate}" type="date" />
                        </span>

                        <span>
                            <s:date name="apoliceAuto.dataFimVigencia" var="formattedDate" format="dd-MM-yyyy" />
                            <s:textfield label="Data fim vigência" name="apoliceAuto.dataFimVigencia" value="%{formattedDate}" type="date" />
                        </span>

                        <span>
                            <s:textfield label="Cia" name="apoliceAuto.cia" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Descrição" name="apoliceAuto.descricao" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Item" name="apoliceAuto.item" type="text" required="true"></s:textfield>
                        </span>
                        
                        <span>
                            <s:textfield label="Quatidade dias renovação" name="apoliceAuto.quantidadeDiasRenovacao" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Ramo" name="apoliceAuto.ramo" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Segmento" name="apoliceAuto.segmento" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Sucursal" name="apoliceAuto.sucursal" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <label for="tipoApolice">Tipo de Apólice:</label>
                            <select name="apoliceAuto.tipoApolice">
                                <option value="TIPO_APOLICE_A">TIPO_APOLICE_A</option>
                                <option value="TIPO_APOLICE_B">TIPO_APOLICE_B</option>
                                <option value="TIPO_APOLICE_C">TIPO_APOLICE_C</option>
                                <option value="TIPO_APOLICE_D">TIPO_APOLICE_D</option>
                            </select>
                        </span>

                        <span>
                            <s:textfield label="Marca veículo" name="apoliceAuto.marcaVeiculo" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <label for="seguradoId">Segurado:</label>
                            <select name="apoliceAuto.seguradoId" id="seguradoId">
                                <s:iterator value="segurados">
                                     <option value="<s:property value="id" />"><s:property value="nome" /></option>
                                 </s:iterator>
                            </select>
                        </span>

                            <button type="submit"><a id="salvar">CADASTRAR</a></button>
                    </form>
                </s:if>

                <s:if test="%{tipoFormulario=='buscarApoliceAuto'}">

                    <div id="consultaForm">
                        <table>
                            <caption style="display: flex; justify-content: space-between;">
                                <p>CONSULTA DE APÓLICES</p>
                                <s:a action="relatorio-apolice">
                                    <div style="display: flex; flex-direction: column; align-items: center; cursor: pointer;" class="relatorio">
                                    <img src="https://cdn-icons-png.flaticon.com/512/4755/4755715.png" width="28px" alt="Relatório">
                                    <span style="font-size: .7rem;">Relatório</span>
                                    </div>
                                </s:a>
                            </caption>
                            <tr>
                                <th>ID</th>
                                <th>ID Segurado</th>
                                <th>Nome Corretor</th>
                                <th>Tipo Apólice</th>
                                <th>Marca Veículo</th>
                                <th>Data de Início</th>
                                <th>Data de Fim</th>
                                
                                <th>Edição/Exclusão</th>
                            </tr>
                            <s:iterator value="apolices">

                                <tr class="linha">
                                    <td>
                                        <s:property value="id" />
                                    </td>
                                    <td>
                                        <s:property value="seguradoId" />
                                    </td>
                                    <td>
                                        <s:property value="nomeCorretor" />
                                    </td>
                                    <td>
                                        <s:property value="tipoApolice" />
                                    </td>
                                    <td>
                                        <s:property value="marcaVeiculo" />
                                    </td>
                                    <td>
                                        <s:date name="dataInicioVigencia" format="dd/MM/yyyy" />
                                    </td>
                                    <td>
                                        <s:date name="dataFimVigencia" format="dd/MM/yyyy" />
                                    </td>
                                    
                                    <td class="acoes">
                                        <s:url var="buscarApoliceAutoPorId" action="buscar-apolice-id">
                                            <s:param name="id" value="id" />
                                        </s:url>
                                        <s:url var="deletarApoliceAuto" action="deletar-apolice-id">
                                            <s:param name="id" value="id" />
                                        </s:url>

                                        <s:url var="editarApoliceAuto" action="editar-apolice-id">
                                            <s:param name="id" value="id" />
                                        </s:url>

                                        <span class="detalhes"><img src="https://cdn-icons-png.flaticon.com/512/709/709612.png" width="20px" alt="detalhes" title="Ver detalhes"></span>
                                        <span class="editar"><img src="https://cdn-icons-png.flaticon.com/512/1827/1827951.png" width="20px" alt="edição" title="Editar"></span>
                                        <span class="excluir"><img src="https://cdn-icons-png.flaticon.com/512/542/542673.png" width="20px" alt="lixeira" title="Excluir"></span>
                                        
                                        <s:a class="buscarApoliceAutoPorId" href="%{buscarApoliceAutoPorId}"></s:a>
                                        <s:a class="editarApoliceAuto" href="%{editarApoliceAuto}"></s:a>
                                        <s:a class="confirmarExclusao" href="%{deletarApoliceAuto}"></s:a>


                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                        </vid>


                        <s:if test="%{verDetalhes==true}">
                            <div class="form-detalhes">
                                <span class="btn-fechar"><img src="https://cdn-icons-png.flaticon.com/512/3161/3161830.png" width="20px" alt="Fechar" title="Fechar"></span>
                                <div class="form-detalhes-items">
                                    <h3>Dados da Apólice</h3>
                                    <div class="dados-pessoais">
                                    <s:textfield label="Id" name="apoliceAuto.id" type="text" disabled="true" value="%{apoliceAuto.id}" />
                                   
                                    <s:textfield label="Cia" name="apoliceAuto.cia" type="text" disabled="true" value="%{apoliceAuto.cia}" />
                                    <s:textfield label="Nome Corretor" name="apoliceAuto.nomeCorretor" type="text" disabled="true" value="%{apoliceAuto.nomeCorretor}" />
                                    <s:textfield label="Nº documento corretor" name="apoliceAuto.numeroDocumentoCorretor" type="text" disabled="true" value="%{apoliceAuto.numeroDocumentoCorretor}" />
                                    <s:textfield label="E-mail corretor" name="apoliceAuto.emailCorretor" type="text" disabled="true" value="%{apoliceAuto.emailCorretor}" />
                                    <label for="dataInicioVigencia">Data início vigência:</label>
                                        <input type="text" disabled="true" value="<s:date name="%{apoliceAuto.dataInicioVigencia}" format="dd/MM/yyyy"></s:date>" >
                                    <label for="dataFimVigencia">Data fim vigência:</label>
                                        <input type="text" disabled="true" value="<s:date name="%{apoliceAuto.dataFimVigencia}" format="dd/MM/yyyy"></s:date>" >
                                    <s:textfield label="Descrição" name="apoliceAuto.descricao" type="text" disabled="true" value="%{apoliceAuto.descricao}" />
                                    <s:textfield label="Item" name="apoliceAuto.item" type="text" disabled="true" value="%{apoliceAuto.item}" />
                                    <s:textfield label="Quantidade dias renovação" name="apoliceAuto.quantidadeDiasRenovacao" type="text" disabled="true" value="%{apoliceAuto.quantidadeDiasRenovacao}" />
                                    <s:textfield label="Ramo" name="apoliceAuto.ramo" type="text" disabled="true" value="%{apoliceAuto.ramo}" />
                                    <s:textfield label="Segmento" name="apoliceAuto.segmento" type="text" disabled="true" value="%{apoliceAuto.segmento}" />
                                    <s:textfield label="Sucursal" name="apoliceAuto.sucursal" type="text" disabled="true" value="%{apoliceAuto.sucursal}" />
                                    <s:textfield label="Tipo Apólice" name="apoliceAuto.tipoApolice" type="text" disabled="true" value="%{apoliceAuto.tipoApolice}" />
                                    <s:textfield label="Marca veículo" name="apoliceAuto.marcaVeiculo" type="text" disabled="true" value="%{apoliceAuto.marcaVeiculo}" />
                                    <div class="editarSegurado" style="margin-top: -1px;">
                                        <label for="seguradoId">Segurado:</label>
                                            <select name="apoliceAuto.seguradoId" id="seguradoId" disabled="true">
                                                <s:iterator value="segurados">
                                                        <option class="opcoesSegurados" value="<s:property value="id" />"><s:property value="nome" /></option>
                                                 </s:iterator>
                                            </select>
                                        </div>
                                </div>
                                </div>
                            </div>
                        </s:if>
                </s:if>

                <s:if test="%{verEdicao==true}">
                            <form class="form-edicao" method="post" action="editar-apolice-id-submit?id=<s:property value="id" />">
                                <span class="btn-fechar"><img src="https://cdn-icons-png.flaticon.com/512/3161/3161830.png" width="20px" alt="Fechar" title="Fechar"></span>
                                <div class="form-edicao-items">
                                    <h3>Dados da Apólice</h3>
                                    <div class="dados-pessoais">
                                    <s:textfield label="Id" name="apoliceAuto.id" type="text" disabled="true" value="%{apoliceAuto.id}" />
                                    <s:textfield label="Cia" name="apoliceAuto.cia" type="text" value="%{apoliceAuto.cia}" />
                                    <s:textfield label="Nome Corretor" name="apoliceAuto.nomeCorretor" type="text" value="%{apoliceAuto.nomeCorretor}" />
                                    <s:textfield label="Nº documento corretor" name="apoliceAuto.numeroDocumentoCorretor" type="text" value="%{apoliceAuto.numeroDocumentoCorretor}" />
                                    <s:textfield label="E-mail corretor" name="apoliceAuto.emailCorretor" type="text" value="%{apoliceAuto.emailCorretor}" />
                                    <s:set var="varDataInicioVigencia"><s:date name="apoliceAuto.dataInicioVigencia" format="yyyy-MM-dd"/></s:set>
                                    <s:textfield label="Data início vigência" name="apoliceAuto.dataInicioVigencia" value="%{varDataInicioVigencia}" type="date"/>
                                    <s:set var="varDataFimVigencia"><s:date name="apoliceAuto.dataFimVigencia" format="yyyy-MM-dd"/></s:set>
                                    <s:textfield label="Data fim vigência" name="apoliceAuto.dataFimVigencia" value="%{varDataFimVigencia}" type="date"/>
                                    <s:textfield label="Descrição" name="apoliceAuto.descricao" type="text" value="%{apoliceAuto.descricao}" />
                                    <s:textfield label="Item" name="apoliceAuto.item" type="text" value="%{apoliceAuto.item}" />
                                    <s:textfield label="Quantidade dias renovação" name="apoliceAuto.quantidadeDiasRenovacao" type="text" value="%{apoliceAuto.quantidadeDiasRenovacao}" />
                                    <s:textfield label="Ramo" name="apoliceAuto.ramo" type="text" value="%{apoliceAuto.ramo}" />
                                    <s:textfield label="Segmento" name="apoliceAuto.segmento" type="text" value="%{apoliceAuto.segmento}" />
                                    <s:textfield label="Sucursal" name="apoliceAuto.sucursal" type="text" value="%{apoliceAuto.sucursal}" />
                                    <s:textfield label="Tipo Apólice" name="apoliceAuto.tipoApolice" type="text" value="%{apoliceAuto.tipoApolice}" />
                                    <s:textfield label="Marca veículo" name="apoliceAuto.marcaVeiculo" type="text" value="%{apoliceAuto.marcaVeiculo}" />
                                    <div class="editarSegurado" style="margin-top: 3px;">
                                    <label for="seguradoId">Segurado:</label>
                                        <select name="apoliceAuto.seguradoId" id="seguradoId">
                                            <s:iterator value="segurados">
                                                    <option class="opcoesSegurados" value="<s:property value="id" />"><s:property value="nome" /></option>
                                             </s:iterator>
                                        </select>
                                    </div>
                                </div>
                                </div>

                                <s:url var="editarApoliceAuto" action="editar-apolice-id-submit"><s:param name="id" value="id" /></s:url>
                                <button type="submit"><s:a id="salvar" class="editarApoliceAuto" href="%{editarApoliceAuto}">ATUALIZAR</s:a></button>
                            </form>
                        </s:if>
            </main>
        </body>
        </html>
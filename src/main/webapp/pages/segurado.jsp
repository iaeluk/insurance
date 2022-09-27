<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="/struts-tags" prefix="s" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Segurado</title>
            <link rel="stylesheet" href="styles/segurado.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script>
                $(document).ready(function () {

                    $(".cep").blur(function () {
                        $.get(`https://viacep.com.br/ws/${this.value}/json/`, function (data) {
                            let { cep, logradouro, complemento, bairro, localidade, uf, ddd } = data

                            $(".complemento").attr('value', complemento)
                            $(".logradouro").attr('value', logradouro)
                            $(".uf").attr('value', uf)
                            $(".bairro").attr('value', bairro)
                            $(".cidade").attr('value', localidade)
                        });
                    })

                    $(".detalhes").click(function () {
                        let index = ($('.detalhes').index(this))
                        $(".buscarSeguradoPorId")[index].click()
                        $(".form-detalhes").removeClass("hidden")
  
                    })

                    $(".editar").click(function () {
                        let index = ($('.editar').index(this))
                        $(".editarSegurado")[index].click()
                        $(".form-edicao").removeClass("hidden")
  
                    })

                    $(".btn-fechar").click(function () {
                        $(".form-detalhes").addClass("hidden")
                        $(".form-edicao").addClass("hidden")
                    })


                    $("#cadastrar").click(function () {
                        window.location.href = "salvar-segurado";
                    })

                    $("#buscar").click(function () {
                        window.location.href = "buscar-segurado";
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
                                    'O Segurado foi deletado.',
                                    'success'
                                ).then(function() {
                                    $(".confirmarExclusao")[index].click()
                                })                            
                            }
                        })
                    })
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
                <s:if test="%{tipoFormulario=='salvarSegurado'}">
                    <form id="cadastroForm" method="post" action="salvar-segurado-submit">
                        <h2>CADASTRO DE SEGURADO</h2>
                        <h3>DADOS PESSOAIS</h3>
                        <span>
                            <s:textfield label="Nome Completo" name="segurado.nome" type="text" required="true" minlength="5" maxlength="100"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Nº documento" name="segurado.numeroDocumento" type="number" required="true"></s:textfield>
                        </span>

                        <span>
                            <label for="tipoPessoa">Tipo de Pessoa:</label>
                            <select name="segurado.tipoPessoa">
                                <option value="FISICA" selected>Pessoa Física</option>
                                <option value="JURIDICA">Pessoa Jurídica</option>
                            </select>
                        </span>

                        <span>
                            <s:date name="segurado.dataNascimento" var="formattedDate" format="dd-MM-yyyy" />
                            <s:textfield label="Data de Nascimento" name="segurado.dataNascimento" value="%{formattedDate}" type="date" />
                        </span>

                        <span>
                            <s:textfield label="E-mail" name="segurado.email" type="email" required="true"></s:textfield>
                        </span>

                        <h3>ENDEREÇO</h3>
                        <span>
                            <s:textfield label="CEP" class="cep" name="endereco.cep" type="number" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Complemento" class="complemento" name="endereco.complemento" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Logradouro" class="logradouro" name="endereco.logradouro" type="text" required="true" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Descrição logradouro" name="endereco.descricaoTipoLogradouro" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Número" name="endereco.numero" type="number" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Tipo Logradouro" id="tipoLogradouro" name="endereco.tipoLogradouro" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Cidade" class="cidade" name="endereco.cidade" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="UF" class="uf" name="endereco.uf" type="text" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Bairro" class="bairro" name="endereco.bairro" type="text" required="true"></s:textfield>
                        </span>

                        <h3>CONTATO</h3>
                        <span>
                            <s:textfield label="DDD" id="ddd" name="telefone.ddd" type="number" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Número" name="telefone.numero" type="number" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Ramal" name="telefone.ramal" type="number" required="true"></s:textfield>
                        </span>

                        <span>
                            <s:textfield label="Descrição Telefone" name="telefone.descricaoTipoTelefone" type="text" required="true"></s:textfield>
                        </span>

                            <button type="submit"><a id="salvar">CADASTRAR</a></button>
                    </form>
                </s:if>

                <s:if test="%{tipoFormulario=='buscarSegurado'}">

                    <div id="consultaForm">
                        <table>
                            <caption>CONSULTA DE SEGURADO</caption>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Número do Documento</th>
                                <th>Tipo de Pessoa</th>
                                <th>Data de Nascimento</th>
                                <th>Email</th>
                                <th>Edição/Exclusão</th>
                            </tr>
                            <s:iterator value="segurados">
                                <tr class="linha">
                                    <td>
                                        <s:property value="id" />
                                    </td>
                                    <td style="text-align: left;">
                                        <s:property value="nome" />
                                    </td>
                                    <td>
                                        <s:property value="numeroDocumento" />
                                    </td>
                                    <td>
                                        <s:property value="tipoPessoa" />
                                    </td>
                                    <td>
                                        <s:date name="dataNascimento" format="dd/MM/yyyy" />
                                    </td>
                                    <td style="text-align: left;">
                                        <s:property value="email" />
                                    </td>
                                    <td class="acoes">
                                        <s:url var="buscarSeguradoPorId" action="buscar-segurado-id">
                                            <s:param name="id" value="id" />
                                        </s:url>
                                        <s:url var="deletarSegurado" action="deletar-segurado-id">
                                            <s:param name="id" value="id" />
                                        </s:url>

                                        <s:url var="editarSegurado" action="editar-segurado-id">
                                            <s:param name="id" value="id" />
                                        </s:url>

                                        <span class="detalhes"><img src="https://cdn-icons-png.flaticon.com/512/709/709612.png" width="20px" alt="detalhes" title="Ver detalhes"></span>
                                        <span class="editar"><img src="https://cdn-icons-png.flaticon.com/512/1827/1827951.png" width="20px" alt="edição" title="Editar"></span>
                                        <span class="excluir"><img src="https://cdn-icons-png.flaticon.com/512/542/542673.png" width="20px" alt="lixeira" title="Excluir"></span>
                                        
                                        <s:a class="buscarSeguradoPorId" href="%{buscarSeguradoPorId}"></s:a>
                                        <s:a class="confirmarExclusao" href="%{deletarSegurado}"></s:a>
                                        <s:a class="editarSegurado" href="%{editarSegurado}"></s:a>


                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                        </vid>


                        <s:if test="%{verDetalhes==true}">
                            <div class="form-detalhes">
                                <span class="btn-fechar"><img src="https://cdn-icons-png.flaticon.com/512/3161/3161830.png" width="20px" alt="Fechar" title="Fechar"></span>
                                <div class="form-detalhes-items">
                                    <h3>Dados pessoais</h3>
                                    <div class="dados-pessoais">
                                    <s:textfield label="Id" name="segurado.id" type="text" disabled="true" value="%{segurado.id}" />
                                    <s:textfield label="Nome" name="segurado.nome" type="text" disabled="true" value="%{segurado.nome}" />
                                    <s:textfield label="Número do documento" name="segurado.numeroDocumento" type="text" disabled="true" value="%{segurado.numeroDocumento}" />
                                    <s:textfield label="Tipo pessoa" name="segurado.tipoPessoa" type="text" disabled="true" value="%{segurado.tipoPessoa}" />
                                    <s:textfield label="E-mail" name="segurado.email" type="text" disabled="true" value="%{segurado.email}" />
                                    <label for="dataNascimento">Data de Nascimento:</label>
                                        <input type="text" disabled="true" value="<s:date name="%{segurado.dataNascimento}" format="dd/MM/yyyy"></s:date>" >
                                    </div>
            
                                    <h3>Endereço</h3>
                                    <div class="dados-pessoais">
                                    <s:textfield label="CEP" name="endereco.cep" type="text" disabled="true" value="%{endereco.cep}" />
                                    <s:textfield label="Complemento" name="endereco.complemento" type="text" disabled="true" value="%{endereco.complemento}" />
                                    <s:textfield label="Logradouro" name="endereco,logradouro" type="text" disabled="true" value="%{endereco.logradouro}" />
                                    <s:textfield label="Descrição Logradouro" name="endereco.descricaoTipoLogradouro" type="text" disabled="true" value="%{endereco.descricaoTipoLogradouro}" />
                                    <s:textfield label="Número" name="endereco.numero" type="text" disabled="true" value="%{endereco.numero}" />
                                    <s:textfield label="Tipo logradouro" name="endereco.tipoLogradouro" type="text" disabled="true" value="%{endereco.tipoLogradouro}" />
                                    <s:textfield label="Cidade" class="cidade" name="endereco.cidade" type="text" disabled="true" value="%{endereco.cidade}" />
                                    <s:textfield label="UF" name="endereco.uf" type="text" disabled="true" value="%{endereco.uf}" />
                                    <s:textfield label="Bairro" name="endereco.bairro" type="text" disabled="true" value="%{endereco.bairro}" />
                                    <s:textfield label="" type="text" disabled="true" value="" />
                                    </div>
            
                                    <h3>Contato</h3>
                                    <div class="dados-pessoais">
                                    <s:textfield label="DDD" name="telefone.ddd" type="text" disabled="true" value="%{telefone.ddd}" />
                                    <s:textfield label="Número" name="telefone.numero" type="text" disabled="true" value="%{telefone.numero}" />
                                    <s:textfield label="Ramal" name="telefone.ramal" type="text" disabled="true" value="%{telefone.ramal}" />
                                    <s:textfield label="Descrição" name="telefone.descricaoTipoTelefone" type="text" disabled="true" value="%{telefone.descricaoTipoTelefone}" />
                                    </div>
                                </div>
                            </div>
                        </s:if>
                </s:if>

                <s:if test="%{verEdicao==true}">
                    <form class="form-edicao" method="post">
                        <span class="btn-fechar"><img src="https://cdn-icons-png.flaticon.com/512/3161/3161830.png" width="20px" alt="Fechar" title="Fechar"></span>
                        <div class="form-edicao-items">
                        <h3>Dados pessoais</h3>
                        <div class="dados-pessoais">
                        <s:textfield label="Id" disabled="true" name="segurado.id" type="text" value="%{segurado.id}" />
                        <s:textfield label="Nome" name="segurado.nome" type="text" value="%{segurado.nome}" />
                        <s:textfield label="Número do documento" name="segurado.numeroDocumento" type="text" value="%{segurado.numeroDocumento}" />
                        <s:textfield label="Tipo pessoa" name="segurado.tipoPessoa" type="text" value="%{segurado.tipoPessoa}" />
                        <s:textfield label="E-mail" name="segurado.email" type="text" value="%{segurado.email}" />
                        <s:set var="varDate"><s:date name="segurado.dataNascimento" format="yyyy-MM-dd"/></s:set>
                        <s:textfield label="Data de Nascimento" name="segurado.dataNascimento" value="%{varDate}" type="date"/>
                        </div>

                        <h3>Endereço</h3>
                        <div class="dados-pessoais">
                        <s:textfield label="CEP" class="cep" name="endereco.cep" type="text" value="%{endereco.cep}" />
                        <s:textfield label="Complemento" class="complemento" name="endereco.complemento" type="text" value="%{endereco.complemento}" />
                        <s:textfield label="Logradouro" class="logradouro" name="endereco.logradouro" type="text" value="%{endereco.logradouro}" />
                        <s:textfield label="Descrição Logradouro" name="endereco.descricaoTipoLogradouro" type="text" value="%{endereco.descricaoTipoLogradouro}" />
                        <s:textfield label="Número" name="endereco.numero" type="text" value="%{endereco.numero}" />
                        <s:textfield label="Tipo logradouro" name="endereco.tipoLogradouro" type="text" value="%{endereco.tipoLogradouro}" />
                        <s:textfield label="Cidade" name="endereco.cidade" type="text" value="%{endereco.cidade}" />
                        <s:textfield label="UF" class="uf" name="endereco.uf" type="text" value="%{endereco.uf}" />
                        <s:textfield label="Bairro" class="bairro" name="endereco.bairro" type="text" value="%{endereco.bairro}" />
                        <s:textfield label="" disabled="true" type="text" value="" />
                        </div>

                        <h3>Contato</h3>
                        <div class="dados-pessoais">
                        <s:textfield label="DDD" name="telefone.ddd" type="text" value="%{telefone.ddd}" />
                        <s:textfield label="Número" name="telefone.numero" type="text" value="%{telefone.numero}" />
                        <s:textfield label="Ramal" name="telefone.ramal" type="text" value="%{telefone.ramal}" />
                        <s:textfield label="Descrição" name="telefone.descricaoTipoTelefone" type="text" value="%{telefone.descricaoTipoTelefone}" />
                        </div>
                        </div>

                        <s:url var="editarSegurado" action="editar-segurado-id"><s:param name="id" value="id" /></s:url>
                        <button type="submit"><s:a id="salvar" class="editarSegurado" href="%{editarSegurado}">ATUALIZAR</s:a></button>
                    </form>
                </s:if>

            </main>
        </body>
        </html>
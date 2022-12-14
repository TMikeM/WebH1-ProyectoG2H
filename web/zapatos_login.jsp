
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/jpg" href="css/image/logitoJogo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilosZapatos.css"">
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>

        <title>Zapatosa Jogo Bonito</title>
    </head>
    <body>
        <div class="container-login" ng-app="demoB1" ng-controller="b1Controller as b1">

            <div class="col-6">
                <h3>Zapateria Jogo Bonito</h3>
                <div class="row">
                    <div class="col-6 ">
                        <label>N° Zapato</label>
                        <input type="text" class="form-control input-margin" placeholder="Numero de Zapato" ng-model="b1.id_Zapato">
                    </div>
                    <div class="col-6 ">
                        <label>Zapato</label>
                        <input type="text" class="form-control input-margin" placeholder="Nombre del Zapato" ng-model="b1.nombreZapato">
                    </div>
                </div>

                <div class="excepcion">
                    <label class="color-text">Color</label>
                    <input type="text" class="form-control input-margin color" placeholder="Color del Zapato" ng-model="b1.color">

                </div>


                <div class="row">
                    <div class="col-6 "><label>Talla</label>
                        <input type="text" class="form-control input-margin" placeholder="Talla" ng-model="b1.talla" >
                    </div>
                    <div class="col-6">
                        <label>Precio</label>
                        <input type="text" class="form-control input-margin" placeholder="Precio" ng-model="b1.precio">
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <label>Proveedor</label>
                        <!--<input type="text" class="form-control" placeholder="Codigo autor" ng-model="b1.codigoAutor">-->
                        <select class="form-control input-margin" ng-model="b1.id_Proveedor">
                            <option ng-repeat="p in b1.Proveedores" value="{{p.id_Proveedor}}">{{p.nombre}}</option>
                        </select>
                    </div>
                    <div class="col-6">
                        <label>Categoria</label>
                        <!--<input type="text" class="form-control" placeholder="id categoria" ng-model="b1.idCategoria">-->
                        <select class="form-control input-margin" ng-model="b1.id_Categoria">
                            <option ng-repeat="c in b1.Categorias" value="{{c.id_Categoria}}">{{c.nombre}}</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-3"><button type="button" id="btn-sesion" class="submit" ng-click="b1.listar()">CONSULTAR</button></div>
                </div>

            </div>

            <div class="row">
                <div class="col-12">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">N° Zapato</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Color</th>
                                <th scope="col">Talla</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Proveedor</th>
                                <th scope="col">Categoria</th>
                                <th scope="col">Foto</th>


                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat=" zap in b1.Zapatos">
                                <td>{{zap.id_Zapato}}</td>
                                <td>{{zap.nombreZapato}}</td>
                                <td>{{zap.color}}</td>
                                <td>{{zap.talla}}</td>
                                <td>{{zap.precio}}</td>
                                <td>{{zap.id_Proveedor}}</td>
                                <td>{{zap.id_Categoria}}</td>
                                <td>
                                    <img src="{{zap.img}}" alt="zapato1" width="100px" height="100px" >
                                </td>
                            </tr>

                        </tbody>
                    </table>



                </div>
            </div>
        </div>

        <script>
            var app = angular.module('demoB1', []);
            app.controller('b1Controller', ['$http', controladorB1]);
            function controladorB1($http) {
                var b1 = this;
                consultarCategorias = function () {
                    var parametros = {proceso: 'listar'};
                    $http({
                        method: 'POST',
                        url: 'peticionesCategoria.jsp',
                        params: parametros
                    }).then(function (res) {
                        b1.Categorias = res.data.Categorias;
                    });
                };
                consultarProveedores = function () {
                    var parametros = {proceso: 'listar'};
                    $http({
                        method: 'POST',
                        url: 'peticionesProveedor.jsp',
                        params: parametros
                    }).then(function (res) {
                        b1.Proveedores = res.data.Proveedores;
                    });
                };
                consultarProveedores();
                consultarCategorias();

                b1.listar = function () {
                    var parametros = {
                        proceso: 'listar'
                    };
                    $http({
                        method: 'POST',
                        url: 'peticionesZapato.jsp',
                        params: parametros
                    }).then(function (res) {
                        b1.Zapatos = res.data.Zapatos;
                    });
                };

            };

        </script>
    </body>
</html>

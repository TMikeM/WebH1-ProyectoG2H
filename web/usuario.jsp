
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
    <head>

        <link rel="stylesheet" href="css/usuario.css">
        <link rel="icon" type="image/jpg" href="css/image/logitoJogo.jpg">
        <script src="https://kit.fontawesome.com/3681d38b22.js" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>



        <title>Inicio Jogo Bonito</title>
    </head>
    <body>
        <div ng-app="demoB1" ng-controller="b1Controller as b1">
            <div class="container" >
                <div class="titulos-container" >
                    <h1>ZAPATERIA JOGO BONITO</h1>
                    <img src="css/image/logitoJogo.jpg" width="250px" height="200px">
                    <h2>¡¡Bienvenido!!</h2>  
                </div>
                <div class="btns">
                    <button class="btn" id="btn-abrir-registrar">Registrarse</button>
                    <button class="btn" id="btn-abrir-inicio" >Inicio Sesion</button>
                </div>
            </div>
            <dialog class="register" id="registrar">
                <h2>Registrarse</h2>
                <form action="">
                    <input type="text" placeholder="Nombre" class="nombre" ng-model="b1.nombre">
                    <input type="text" placeholder="Apellido" class="apellido" ng-model="b1.apellido">
                    <input type="password" id="id1" placeholder="Contraseña" class="pass" ng-model="b1.contrasena">
                    <input type="password" id="id2" placeholder="Confirma Contraseña" ng-model="b1.contrasenaconfirm" class="repas" ng-change="b1.comparar()">
                    <button type="button" class="submit" ng-click="b1.guardar()">Registrarse </button>
                </form>
            </dialog>

            <dialog class="inicio" id="inicio">
                <h2>Iniciar Sesion</h2>
                <form action="">
                    <input type="text" placeholder="Nombre" class="nombre" ng-model="b1.nombre2">
                    <input type="text" placeholder="Apellido" class="apellido" ng-model="b1.apellido2">
                    <input type="password" placeholder="Contraseña" class="pass" ng-model="b1.contrasena2">
                    <button type="button" id="btn-sesion" class="submit" ng-click="b1.entrar()">Iniciar sesion </button>
                </form>
            </dialog>

        </div>

        <script>
            const btnAbrirInicio =
                    document.querySelector("#btn-abrir-inicio");
            const btnAbrirRegistrar =
                    document.querySelector("#btn-abrir-registrar");
            const inicio =
                    document.querySelector("#inicio");
            const registro =
                    document.querySelector("#registrar");
            btnAbrirInicio.addEventListener("click", () => {
                inicio.showModal();
            });
            btnAbrirRegistrar.addEventListener("click", () => {
                registro.showModal();
            });
            
        </script>

        <script>
            var app = angular.module('demoB1', []);
            app.controller('b1Controller', ['$http', controladorB1]);
            function controladorB1($http) {
                var b1 = this;


                b1.guardar = function () {

                    var parametros = {
                        proceso: 'guardar',
                        nombre: b1.nombre,
                        apellido: b1.apellido,
                        contrasena: b1.contrasena,
                        contrasenaConfirm: b1.contrasenaconfirm

                    };
                    $http({
                        method: 'POST',
                        url: 'peticionesUsuario.jsp',
                        params: parametros
                    }).then(function (res) {
                        if (parametros.contrasena === parametros.contrasenaConfirm) {
                            if (res.data.ok === true) {
                                if (res.data.guardar === true) {
                                    alert('Guardó');
                                } else {
                                    alert('No Guardó');
                                }
                            } else {
                                alert(res.data.errorMsg);
                            }
                        } else {
                            alert('Contraseñas Distintas');
                        }
                    });
                }
                ;
                b1.comparar = function () {
                    var a = b1.contrasena;
                    var b = b1.contrasenaconfirm;
                    if (a === b) {
                        document.getElementById("id2").style.background = "green";
                    } else {
                        document.getElementById("id2").style.background = "red";
                    }
                };

                b1.entrar = function () {

                    var parametros = {
                        proceso: 'iniciarsesion',
                        nombre: b1.nombre2,
                        apellido: b1.apellido2,
                        contrasena: b1.contrasena2
                    };
                    $http({
                        method: 'POST',
                        url: 'peticionesUsuario.jsp',
                        params: parametros
                    }).then(function (res) {
                        if(res.data.ok === true){
                        if (res.data.iniciarsesion === true) {
                            if(res.data.Usuario.nombre === "Yeferson"){
                                    window.location.href = "http://localhost:8080/Zapatos/zapatos.jsp";
                                 }else{
                                     alert('Otro usuario');
                                 }
                        } else {
                            alert('Sesion Incorrecta');
                        }}else{
                                alert(res.data.errorMsg);

                        }
                    });
                }
                ;
            }
        </script>
    </body>
</html>
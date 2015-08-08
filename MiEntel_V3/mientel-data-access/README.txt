----------------------------------------------------------
Instrucciones de Instalación DataSource y ConnectionPool
----------------------------------------------------------

Para la creación del DataSource, se debe proceder de la siguiente forma:

1- Ingresar a consola de administración WebLogic (http://<host>:7001/console)
2- Dentro de la consola, vamos a la opción: Services -> JDBC -> Data Sources
3- Dentro de Data Sources, hacemos click en "New", y especificamos la siguiente información:
	- Name: MientelV3_DS
	- JNDI Name: MientelV3_DS
	- Database Type: Oracle
	- Database Driver: Oracle's Driver (Thin XA) for instance connections; Versions: 9.0.1, 9.2.0, 10, 11
4- Hacemos click en "Next", en la siguiente pantalla de "Transaction Options", no se realizan cambios y se hace click nuevamente en "Next"
5- En la parte de "Connection Properties", especificamos la siguiente información:
	- Database Name: IWS
	- Host Name: 10.10.20.89
	- Port: 1521
	- Database User Name: ENTEL_MIENTELV3
	- Password: ENTEL_MIENTELV3
6- Hacemos click en "Next" y en la siguiente pantalla hacemos click en "Test Configuration" para verificar la conexión, el resto de datos no se modifican, volvemos a hacer click en "Next".
7- Finalmente, seleccionamos el servidor en donde vamos a montar el DataSource y hacemos click en "Finish"
8- Con esto, ya tenemos creado el DataSource, al haber realizado el procedimiento anterior se crea automaticamente un ConnectionPool, por lo que no es necesario realizar configuraciones adicionales.
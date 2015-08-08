#!/bin/bash
#Definiendo variables de entorno
export JAVA_HOME=`sed '/^\#/d' mientel3.properties | grep 'jdk.home'  | tail -n 1 | cut -d "=" -f2- | sed 's/^[[:space:]]*//;s/[[:space:]]*$//'`
export PATH=$PATH:`sed '/^\#/d' mientel3.properties | grep 'ant.home'  | tail -n 1 | cut -d "=" -f2- | sed 's/^[[:space:]]*//;s/[[:space:]]*$//'`
#
#Defiendo variables locales
_bea_home=`sed '/^\#/d' mientel3.properties | grep 'bea.home'  | tail -n 1 | cut -d "=" -f2- | sed 's/^[[:space:]]*//;s/[[:space:]]*$//' | sed 's,/,\\\/,g'`
_sources_path=`sed '/^\#/d' mientel3.properties | grep 'sources.path'  | tail -n 1 | cut -d "=" -f2- | sed 's/^[[:space:]]*//;s/[[:space:]]*$//'`
_properties=`sed '/^\#/d' mientel3.properties | grep 'properties.path'  | tail -n 1 | cut -d "=" -f2- | sed 's/^[[:space:]]*//;s/[[:space:]]*$//' | sed 's,/,\\\/,g'`
_properties_source="`echo "$_sources_path""mientel-properties/" | sed 's:\/:\\\/:g'`"
_xml=`sed '/^\#/d' mientel3.properties | grep 'xml.path'  | tail -n 1 | cut -d "=" -f2- | sed 's/^[[:space:]]*//;s/[[:space:]]*$//' | sed 's,/,\\\/,g'`
_logs=`sed '/^\#/d' mientel3.properties | grep 'logs.path'  | tail -n 1 | cut -d "=" -f2- | sed 's/^[[:space:]]*//;s/[[:space:]]*$//' | sed 's,/,\\\/,g'`
_aplicacion_properties="`echo "$_properties_source""aplicacion.properties" | sed 's:\\\::g'`"
_errorMessages_properties="`echo "$_properties_source""errorMessages.properties"  | sed 's:\\\::g'`"
_externalApps_properties="`echo "$_properties_source""externalApps.properties"  | sed 's:\\\::g'`"
_log4j_properties="`echo "$_properties_source""mientel_log4j.properties"  | sed 's:\\\::g'`"
_jrockit_path="`echo "$JAVA_HOME" |  sed 's,/,\\\/,g'`"
_svn_url=`sed '/^\#/d' mientel3.properties | grep 'svn.url'  | tail -n 1 | cut -d "=" -f2- | sed 's/^[[:space:]]*//;s/[[:space:]]*$//'`
_lib_dir=`sed '/^\#/d' mientel3.properties | grep 'libraries.dir'  | tail -n 1 | cut -d "=" -f2- | sed 's/^[[:space:]]*//;s/[[:space:]]*$//'`
_cer_app_dir=`sed '/^\#/d' mientel3.properties | grep 'cert_app.dir'  | tail -n 1 | cut -d "=" -f2- | sed 's/^[[:space:]]*//;s/[[:space:]]*$//'`
_keystore_path="`echo "$_cer_app_dir""keys/keystore.dat" | sed 's:\/:\\\/:g'`"
#
echo -e "Reemplazar fuentes existentes con la ultima revision? [Si/No/Terminar]: \c"
read
if [ $REPLY = "s" -o $REPLY = "S" -o $REPLY = "Si" ]; then
echo "Borrando fuentes viejas..."
rm -fr $_sources_path
mkdir $_sources_path
#
echo "Haciendo el checkout de nuevo..."
svn co $_svn_url $_sources_path
elif [ $REPLY = "t" -o $REPLY = "T" -o $REPLY = "Terminar" ]; then
exit 0
fi
#
echo -e "Modificar Rutas de Archivos de propiedades en la aplicacion y Proveedores de Autenticacion? [Si/No/Terminar]: \c"
read
if [ $REPLY = "s" -o $REPLY = "S" -o $REPLY = "Si" ]; then
echo "Modificando properties con rutas locales..."
#
echo "[mientel-properties/aplicacion.properties]"

sed -i 's/miEntel.webservices.properties.path=.*/miEntel.webservices.properties.path='"$_properties"'/g' $_aplicacion_properties
sed -i 's/miEntel.errorMessages.path=.*/miEntel.errorMessages.path='"$_properties""errorMessages.properties"'/g' $_aplicacion_properties
sed -i 's/miEntel.externalApps.path=.*/miEntel.externalApps.path='"$_properties""externalApps.properties"'/g' $_aplicacion_properties
sed -i 's/parametro.keystore=.*/parametro.keystore='"$_keystore_path"'/g' $_aplicacion_properties
sed -i 's/equipo.documentoApertura.plantilla.xml=.*/equipo.documentoApertura.plantilla.xml='"$_xml""ServicioTecnicoAperturaOT.xml"'/g' $_aplicacion_properties
#
echo "[mientel-properties/mientel_log4j.properties]"
sed -i 's/logPath=.*/logPath='"$_logs"'/g' $_log4j_properties
#
echo "[EntelUIResources/build.properties]"
sed -i 's/bea.home=.*/bea.home='"$_bea_home"'/g' "$_sources_path""EntelUIResources/build.properties"
#
echo "[mientel-uup-ejb/build.properties]"
sed -i 's/bea.home=.*/bea.home='"$_bea_home"'/g' "$_sources_path""mientel-uup-ejb/build.properties"
#
echo "[mientel-uup-ejb/epcs_uup.properties]"
sed -i 's/perfilamiento.service.properties.path=.*/perfilamiento.service.properties.path='"$_properties"'/g' "$_sources_path""mientel-uup-ejb/epcs_uup.properties"
#
echo "[mientel-uup-ejb/log4j.properties]"
sed -i 's/logPath=.*/logPath='"$_logs"'/g' "$_sources_path""mientel-uup-ejb/log4j.properties"
#
echo "[mientel-web-app/build.properties]"
sed -i 's/bea.home=.*/bea.home='"$_bea_home"'/g' "$_sources_path""mientel-web-app/build.properties"
#
echo "[mientel-web-app/WebContent/WEB-INF/classes/miEntel.properties]"
sed -i 's/miEntel.properties.path=.*/miEntel.properties.path='"$_properties""aplicacion.properties"'/g' "$_sources_path""mientel-web-app/WebContent/WEB-INF/classes/miEntel.properties"
#
echo "[mientel-web-app/WebContent/WEB-INF/classes/mientel_log4j.properties]"
sed -i 's/mientel.logPath.properties=.*/mientel.logPath.properties='"$_properties""mientel_log4j.properties"'/g' "$_sources_path""mientel-web-app/WebContent/WEB-INF/classes/mientel_log4j.properties"
#
echo "[mientel/build.properties]"
sed -i 's/bea.home=.*/bea.home='"$_bea_home"'/g' "$_sources_path""mientel/build.properties"
#
echo "[mientel/propagation]"
sed -i 's/bea.home=.*/bea.home='"$_bea_home"'/g' "$_sources_path""mientel-propagation/build.properties"
#
echo "[EPCSAuthenticationProvider/build.properties]"
sed -i 's/bea.home=.*/bea.home='"$_bea_home"'/g' "$_sources_path""EPCSAuthenticationProvider/build.properties"
#
echo "[EPCSAuthenticationProvider/build.properties]"
sed -i 's/jrockit.path=.*/jrockit.path='"$_jrockit_path"'/g' "$_sources_path""EPCSAuthenticationProvider/build.properties"
#
echo "[EPCSAuthenticationProvider/epcs_auth.properties]"
sed -i 's/authentication.service.properties.path=.*/authentication.service.properties.path='"$_properties"'/g' "$_sources_path""EPCSAuthenticationProvider/epcs_auth.properties"
#
echo "[EPCSAuthenticationProvider/log4j.properties]"
sed -i 's/logPath=.*/logPath='"$_logs"'/g' "$_sources_path""EPCSAuthenticationProvider/log4j.properties"
#
echo "[EPCSApplicationAuthenticationProvider/build.properties]"
sed -i 's/bea.home=.*/bea.home='"$_bea_home"'/g' "$_sources_path""EPCSApplicationAuthenticationProvider/build.properties"
#
echo "[EPCSApplicationAuthenticationProvider/build.properties]"
sed -i 's/jrockit.path=.*/jrockit.path='"$_jrockit_path"'/g' "$_sources_path""EPCSApplicationAuthenticationProvider/build.properties"
#
echo "[EPCSApplicationAuthenticationProvider/epcs_app_auth.properties]"
sed -i 's/authentication.service.properties.path=.*/authentication.service.properties.path='"$_properties"'/g' "$_sources_path""EPCSApplicationAuthenticationProvider/epcs_app_auth.properties"
#
echo "[EPCSApplicationAuthentication/log4j.properties]"
sed -i 's/logPath=.*/logPath='"$_logs"'/g' "$_sources_path""EPCSApplicationAuthenticationProvider/log4j.properties"
#
echo "[EPCSEncriptedAuthenticationProvider/build.properties]"
sed -i 's/bea.home=.*/bea.home='"$_bea_home"'/g' "$_sources_path""EPCSEncriptedAuthenticationProvider/build.properties"
#
echo "[EPCSEncriptedAuthenticationProvider/build.properties]"
sed -i 's/jrockit.path=.*/jrockit.path='"$_jrockit_path"'/g' "$_sources_path""EPCSEncriptedAuthenticationProvider/build.properties"
#
echo "[EPCSEncriptedAuthenticationProvider/log4j.properties]"
sed -i 's/logPath=.*/logPath='"$_logs"'/g' "$_sources_path""EPCSEncriptedAuthenticationProvider/log4j.properties"
#finaliza el cambio de propiedades 
elif [ $REPLY = "t" -o $REPLY = "T" -o $REPLY = "Terminar" ]; then
exit 0
fi
#
#Creando proveedor de autenticacion de usuarios
echo -e "Empaquetar EPCSAuthenticationProvider (Autenticacion de Usuarios)? [Si/No/Terminar]: \c"
read
if [ $REPLY = "s" -o $REPLY = "S" -o $REPLY = "Si" ]; then
echo "Empaquetando EPCSAuthenticationProvider"
cd "$_sources_path""EPCSAuthenticationProvider"
ant
rm -f "$_lib_dir""EPCSAuthenticationProvider.jar"
cp -f ${_sources_path}EPCSAuthenticationProvider/dist/* ${_lib_dir}
cp -f ${_sources_path}EPCSAuthenticationProvider/lib/* ${_lib_dir}
elif [ $REPLY = "t" -o $REPLY = "T" -o $REPLY = "Terminar" ]; then
exit 0
fi
#creando proveedor de autenticacion de aplicaciones
echo -e "Empaquetar EPCSApplicationAuthenticationProvider (Autenticacion de Aplicaciones)? [Si/No/Terminar]: \c"
read
if [ $REPLY = "s" -o $REPLY = "S" -o $REPLY = "Si" ]; then
echo "Empaquetando EPCSApplicationAuthenticationProvider"
cd "$_sources_path""EPCSApplicationAuthenticationProvider"
ant
rm -f "$_lib_dir""EPCSApplicationAuthenticationProvider.jar"
cp -f ${_sources_path}EPCSApplicationAuthenticationProvider/dist/* ${_lib_dir}
cp -f ${_sources_path}EPCSAuthenticationProvider/lib/* ${_lib_dir}
elif [ $REPLY = "t" -o $REPLY = "T" -o $REPLY = "Terminar" ]; then
exit 0
fi
#creando proveedor de autenticacion encriptado
echo -e "Empaquetar EPCSEncriptedAuthenticationProvider (Autenticacion via ID Encriptado? [Si/No/Terminar]: \c"
read
if [ $REPLY = "s" -o $REPLY = "S" -o $REPLY = "Si" ]; then
echo "Empaquetando EPCSEncriptedAuthenticationProvider"
cd "$_sources_path""EPCSEncriptedAuthenticationProvider"
ant
rm -f "$_lib_dir""\EPCSEncriptedAuthenticationProvider.jar"
cp -f ${_sources_path}EPCSEncriptedAuthenticationProvider/dist/* ${_lib_dir}
elif [ $REPLY = "t" -o $REPLY = "T" -o $REPLY = "Terminar" ]; then
exit 0
fi
#Creando bibliotecas WAR (Shared Library) y de la aplicacion (EAR)
echo -e "Empaquetar EntelUIResources? [Si/No/Terminar]: \c"
read
if [ $REPLY = "s" -o $REPLY = "S" -o $REPLY = "Si" ]; then
echo "Empaquetando WAR..."
cd "$_sources_path""EntelUIResources"
ant
rm -f "$_lib_dir""*.war"
cp ${_sources_path}EntelUIResources/dist/* ${_lib_dir}
elif [ $REPLY = "t" -o $REPLY = "T" -o $REPLY = "Terminar" ]; then
exit 0
fi
echo -e "Empaquetar Aplicacion MiEntel3? [Si/No/Terminar]: \c"
read
if [ $REPLY = "s" -o $REPLY = "S" -o $REPLY = "Si" ]; then
echo "Empaquetando EAR..."
ant -f "$_sources_path""mientel/build.xml"
cp ${_sources_path}mientel/dist/* ${_lib_dir}
elif [ $REPLY = "t" -o $REPLY = "T" -o $REPLY = "Terminar" ]; then
exit 0
fi

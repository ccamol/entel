<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>

<cm:search id="linkFacebook" query="idContenido = 'linkFacebook'" useCache="true"  />
<cm:search id="linkTwitter" query="idContenido = 'linkTwitter'" useCache="true"  />
<cm:search id="linkYoutube" query="idContenido = 'linkYoutube'" useCache="true"  />
<cm:search id="linkRss" query="idContenido = 'linkRss'" useCache="true"  />

<%@page import="com.bea.netuix.servlets.controls.page.BookBackingContext"%>
<%@page import="com.bea.netuix.servlets.controls.page.BookPresentationContext"%><f:view>

        <div class="pie clearfix versionPruebaFooter" id="pie-Entel">
            <!-- LINKS CORPORATIVOS-->
            <div class="pie-izquierda">
                <div class="clearfix">
                    <div class="alinear"><a target="_parent" href="http://www.entel.cl/corporativo/">Información Corporativa</a></div>
                    <div class="alinear"><a target="_parent" href="http://www.entel.cl/inversionistas/">Inversionistas</a></div>
                    <div class="alinear"><a target="_parent" href="#">Trabaja con Nosotros</a></div>
                    <div class="alinear"><a target="_parent" href="#">Extranet</a></div>
                    <div class="alinear"><a target="_parent" href="#">Derechos del Cliente</a></div>
                    <div class="alinear"><a target="_parent" href="#">Cómo ser Cliente</a></div>
                    <div class="alinear"><a target="_parent" href="http://foros.entel.cl/">Foro Entel</a></div>
                </div>
                <p>Copyright Empresa Nacional de Telecomunicaciones S.A. Todos los derechos reservados.</p>
            </div>

            <!-- /LINKS CORPORATIVOS-->
            <!-- COMPARTIR -->
            <div class="pie-derecha">
                <div class="clearfix">           
                    <!-- TWITER -->
                    <div class="alinear twitter" id="footer-Twitter" onclick="window.open('<cm:getProperty node="${linkTwitter[0]}" name="html" />', '_blank');">                   
                        <div class="tooltip">
                            <div class="tooltip-top"><span></span></div>
                            <div class="tooltip-main"><cm:getProperty node="${linkTwitter[0]}" name="titulo" /></div>
                            <div class="tooltip-bottom"><span></span></div>
                            <div class="tooltip-flecha"></div>
                        </div>                   
                    </div>
                    <!-- /TWITER -->
                    <!-- FACEBOOK -->
                    
                    <div class="alinear facebook" id="footer-Facebook" onclick="window.open('<cm:getProperty node="${linkFacebook[0]}" name="html" />', '_blank');">    
                        <div class="tooltip">
                            <div class="tooltip-top"><span></span></div>
                            <div class="tooltip-main"><cm:getProperty node="${linkFacebook[0]}" name="titulo" /></div>
                            <div class="tooltip-bottom"><span></span></div>
                            <div class="tooltip-flecha"></div>
                        </div>                       
                    </div>
                    
                    <!-- /FACEBOOK -->
                    <!-- YOUTUBE -->
                    <div class="alinear youtube" id="footer-YouTube" onclick="window.open('<cm:getProperty node="${linkYoutube[0]}" name="html" />', '_blank');">                   
                        <div class="tooltip">
                            <div class="tooltip-top"><span></span></div>
                            <div class="tooltip-main"><cm:getProperty node="${linkYoutube[0]}" name="titulo" /></div>
                            <div class="tooltip-bottom"><span></span></div>
                            <div class="tooltip-flecha"></div>
                        </div>               
                    </div>
                    <!-- /YOUTUBE -->
                    <!-- COMPARTIR -->
                 </div>
            </div>
            <!-- /COMPARTIR -->
        </div>
         <a name="marcaSGO2" id="marcaSGO2" ></a>
</f:view>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%
	final String path = "/";
	final String domain = "entel.cl";

	if (request.getParameter("__utma") != null) {
		Cookie utma = new Cookie("__utma", request.getParameter("__utma"));
		utma.setMaxAge(10 * 12 * 30 * 24 * 60 * 60);
		utma.setPath("/");
		utma.setDomain(domain);
		response.addCookie(utma);
	}

	if (request.getParameter("__utmb") != null) {
		Cookie utmb = new Cookie("__utmb", request.getParameter("__utmb"));
		utmb.setMaxAge(1 * 1 * 1 * 1 * 30 * 60);
		utmb.setPath("/");
		utmb.setDomain(domain);
		response.addCookie(utmb);
	}

	if (request.getParameter("__utmc") != null) {
		Cookie utmc = new Cookie("__utmc", request.getParameter("__utmc"));
		utmc.setMaxAge(1 * 1 * 1 * 1 * 30 * 60);
		utmc.setPath("/");
		utmc.setDomain(domain);
		response.addCookie(utmc);
	}

	if (request.getParameter("__utmz") != null) {
		Cookie utmz = new Cookie("__utmz", request.getParameter("__utmz"));
		utmz.setMaxAge(1 * 6 * 30 * 24 * 30 * 60);
		utmz.setPath("/");
		utmz.setDomain(domain);
		response.addCookie(utmz);
	}

	if (request.getParameter("__utmv") != null) {
		Cookie utmv = new Cookie("__utmv", request.getParameter("__utmv"));
		utmv.setMaxAge(1 * 6 * 30 * 24 * 30 * 60);
		utmv.setPath("/");
		utmv.setDomain(domain);
		response.addCookie(utmv);
	}

	if (request.getParameter("__utmk") != null) {
		Cookie utmk = new Cookie("__utmk", request.getParameter("__utmk"));
		utmk.setMaxAge(1 * 6 * 30 * 24 * 30 * 60);
		utmk.setPath("/");
		utmk.setDomain(domain);
		response.addCookie(utmk);
	}

	if (request.getParameter("__utmx") != null) {
		Cookie utmx = new Cookie("__utmx", request.getParameter("__utmx"));
		utmx.setMaxAge(1 * 1 * 1 * 1 * 30 * 60);
		utmx.setPath("/");
		utmx.setDomain(domain);
		response.addCookie(utmx);
	}

   if (request.getParameter("mxCookie") != null) {
      Cookie mxCookie = new Cookie("mxCookie", request.getParameter("mxCookie"));
      mxCookie.setPath("/");
      mxCookie.setDomain(domain);
      response.addCookie(mxCookie);
   }

   response.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
%>
<html>
<head>
</head>
<body>
<!-- Google Tag Manager -->
<noscript>
    <iframe src="//www.googletagmanager.com/ns.html?id=GTM-WB3K" height="0" width="0" style="display:none;visibility:hidden"></iframe>
</noscript>
<script>
    (function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
        new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
        j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
        '//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
    })(window,document,'script','dataLayer','GTM-WB3K');
</script>
<!-- End Google Tag Manager -->
</body>
</html>
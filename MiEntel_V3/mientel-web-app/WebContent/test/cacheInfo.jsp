<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bea.p13n.cache.* "%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Weblogic Portal Cache List</title>
</head>

<body>
<h2>Weblogic Portal Cache List</h2>

<hr>

<p>
  This page contains a list of the caches created by the Weblogic Portal product. 
  Each of these caches is an instance of <i>com.bea.p13n.cache.Cache</i>.
</p>

<p>
  Statically defined caches are established in the application-config.xml file for 
  the application. These can be configured in Weblogic Console by navigating to: <br>
  Applications --> [Portal App Name] --> Service Configuration --> Caches<br>
  Only statically defined caches appear in this location.
</p>

<p>
  The Portal product also makes use of dynamically generated caches. This JSP is 
  the best way to explore these caches. You may generally make dynamic Caches 
  administrable by adding entries in the application-config.xml file. 
</p>


<hr>

<%
  // process the form submission.
  String action = (String)request.getParameter("action");
  String cacheName = (String)request.getParameter("cacheName");
  String[] allCaches = CacheFactory.getCacheNames();
  String[] cachesToProcess = null;
  
  if(action != null && cacheName != null) {

      // get the caches that we are operating on
      if("all".equals(cacheName)) {
          cachesToProcess = allCaches;
      }
      else {
          cachesToProcess = new String[] { cacheName };
      }
      
      // perform the action.
      for(int i = 0; i < cachesToProcess.length; i++) {
          Cache curCache = CacheFactory.getCache(cachesToProcess[i]);

          if (curCache != null) {
              
              if(action.equals("Clear")) {
                  curCache.clear();
              }
              
              else if(action.equals("ResetStats")) {
                  curCache.resetStats();
              }

              else {
                  break;
              }
          }
      }
  }
%>

<table>
  <tr>
    <td>
      <form name="form" action="cacheInfo.jsp"/>
        <input type="submit" value="Refresh List">
      </form>
    </td>
    <td>
      <form name="form" action="cacheInfo.jsp"/>
        <input type="hidden" name="action" value="Clear" >
        <input type="hidden" name="cacheName" value="all" >
        <input type="submit" value="Clear All Caches">
      </form>
    </td>
    <td>
      <form name="form" action="cacheInfo.jsp"/>
        <input type="hidden" name="action" value="ResetStats" >
        <input type="hidden" name="cacheName" value="all" >
        <input type="submit" value="Reset All Stats">
      </form>
    </td>
  </tr>
</table>

<table border="1">
  <tr>
    <td>Cache Name</td>
    <td>Enabled</td>
    <td>Current Size</td>
    <td>Maximum Size</td>
    <td>TTL (milliseconds)</td>
    <td>Hit Count</td>
    <td>Miss Count</td>
    <td>Hit Rate</td>
    <td>Clear Cache</td>
    <td>Reset Stats</td>
  </tr>
<%
    for (int i = 0; i < allCaches.length; i++)
    {
            String curCacheName = allCaches[i];
            Cache curCache = CacheFactory.getCache(curCacheName);
            if (curCache != null)
            {
                String bgcolor = null;
                if(i % 2 == 0) {
                    bgcolor = "white";
                }
                else {
                    bgcolor = "#C0C0C0";
                }
%>
    <tr valign="top" bgcolor="<%= bgcolor %>">
      <td><%= curCacheName %></td>
      <td><%= curCache.isEnabled() %></td>
      <td><%= curCache.size() %></td>
      <td><%= curCache.getMaxEntries() %></td>
      <td><%= curCache.getTtl() %></td>
      <td><%= curCache.getHitCount() %></td>
      <td><%= curCache.getMissCount() %></td>
      <td><%= curCache.getHitRate() %>%</td>
      <td>
        <form name="form" action="cacheInfo.jsp"/>
          <input type="hidden" name="cacheName" value="<%=curCacheName%>" />
          <input type="hidden" name="action" value="Clear" />
          <input type="submit" value="Clear Cache" />
        </form>
      </td>
      <td>
        <form name="form" action="cacheInfo.jsp"/>
          <input type="hidden" name="cacheName" value="<%=curCacheName%>" />
          <input type="hidden" name="action" value="ResetStats" />
          <input type="submit" value="Reset Stats" />
        </form>
      </td>
    </tr>
<%                      
                }               
        }
%>
  </table>
</form>
</body>
</html>
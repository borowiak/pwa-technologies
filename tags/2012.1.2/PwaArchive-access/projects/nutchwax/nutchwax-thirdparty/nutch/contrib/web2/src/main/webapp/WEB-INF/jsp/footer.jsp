<%--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at
  
  http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<%@ include file="common.jsp"%>
<div id="footer"><c:if test="${nutchSearch.isSearch=='true'}">
 <table bgcolor="3333ff" align="right">
  <tr>
   <td bgcolor="ff9900"><a
    href="opensearch?query=<c:out value="${nutchSearch.queryString}"/>"><font
    color="ffffff"><b>RSS</b> </font></a></td>
  </tr>
 </table>
</c:if> <a href="http://wiki.apache.org/nutch/FAQ"> <img border="0"
 src="img/poweredbynutch_01.gif"> </a></div>

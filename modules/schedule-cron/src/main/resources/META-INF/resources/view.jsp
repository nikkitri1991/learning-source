<%@ include file="/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />

<portlet:actionURL var="scheduleURL" name="schedule">
    <portlet:param name="mvcPath" value="/view.jsp" />
</portlet:actionURL>

<form action="<%= scheduleURL.toString() %>" method="post">
    <button type="submit">Schedule Job</button>
</form>

<%@page import="com.liferay.training.gradebook.service.AssignmentLocalServiceUtil"%>
<%@ include file="init.jsp" %>


<portlet:renderURL var="listURL">
	<portlet:param name= "mvcPath" value = "/addAssignment.jsp" />
</portlet:renderURL>

<a href="<%=listURL%>"><button>AddEmp</button> </a>

<liferay-ui:search-container>
	<liferay-ui:search-container-results
		results="<%= AssignmentLocalServiceUtil.getAssignments(searchContainer.getStart(), searchContainer.getEnd()) %>"/>

	<liferay-ui:search-container-row
		className="com.liferay.training.gradebook.model.Assignment"
		modelVar="assignment"
	>
	<liferay-ui:search-container-column-text property="description" name="Description" />
	<liferay-ui:search-container-column-text property="title" name="Title" />
	<liferay-ui:search-container-column-text property="dueDate" name="DueDate" />
		
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>
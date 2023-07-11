<%@include file ="init.jsp"%>

<portlet:actionURL name = "addAssignment" var ="addAssignmentActionURL"></portlet:actionURL>
<div class="container" style="display: flex; justify-content: center; align-items: center; height: 100vh;">
    <aui:form action="<%=addAssignmentActionURL %>" name="assignmentForm" method="POST">
        <div class="form-group">
            <div class="col-md-6">
                <aui:input name="title" class="form-control" style="width: 300px;">
                    <aui:validator name="required"/>
                    <aui:validator name="alphanum"/>
                </aui:input>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-6">
                <aui:input name="description" style="width: 300px;">
                </aui:input>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-6">
                <aui:input name="dueDate" style="width: 300px;">
                </aui:input>
            </div>
        </div>
        <div class="text-center">
        	 <aui:button type="submit" style="width: 100px" name="submit" value="Submit"></aui:button>
        </div>
       
    </aui:form>
</div>

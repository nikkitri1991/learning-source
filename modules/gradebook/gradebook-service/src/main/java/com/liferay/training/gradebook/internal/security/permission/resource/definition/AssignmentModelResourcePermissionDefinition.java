package com.liferay.training.gradebook.internal.security.permission.resource.definition;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.ModelResourcePermissionDefinition;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.AssignmentLocalService;

import java.util.function.Consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * 
 * @author hgrahul
 * This Class Represents For Registering The Model and Top-Level Resource Permissions
 */
@Component(immediate = true, service = ModelResourcePermissionDefinition.class)
public class AssignmentModelResourcePermissionDefinition implements ModelResourcePermissionDefinition<Assignment>{
	/**
	 * Metadata Which Will Take Part In Assignment Model Registration Process For Top Level Permission
	 */
	
	@Override
	public Assignment getModel(long assignmentId) throws PortalException {
		return assignmentLocalService.getAssignment(assignmentId);
	}
	
	@Override
	public Class<Assignment> getModelClass() {
		return Assignment.class;
	}
	
	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return portletResourcePermission;
	}
	
	@Override
	public long getPrimaryKey(Assignment assignment) {
		return assignment.getAssignmentId();
	}
	
	@Override
	public void registerModelResourcePermissionLogics(ModelResourcePermission<Assignment> modelResourcePermission, Consumer<ModelResourcePermissionLogic<Assignment>> modelResourcePermissionLogicConsumer) {
		modelResourcePermissionLogicConsumer.accept(
			new StagedModelPermissionLogic<>(stagingPermission, "com_liferay_training_gradebook_web_portlet_GradebookPortlet", Assignment::getAssignmentId)
		);
		// Still For Workflow
		//modelResourcePermissionLogicConsumer.accept(
		//	new WorkflowedModelPermissionLogic<>(workflowPermission, modelResourcePermission, groupLocalService, Assignment::getAssignmentId)
		//);
	}
	
	
	/**
	 * For References Which We Will Need In This Registering The Model And Top Level Permission
	 */
	@Reference
	private AssignmentLocalService assignmentLocalService;
	
	@Reference
	private GroupLocalService groupLocalService;
	
	@Reference(target = "(resource.name=com.liferay.training.gradebook.model)")
	private PortletResourcePermission portletResourcePermission;
	
	@Reference
	private StagingPermission stagingPermission;
	
	@Reference
	private WorkflowPermission workflowPermission;
}

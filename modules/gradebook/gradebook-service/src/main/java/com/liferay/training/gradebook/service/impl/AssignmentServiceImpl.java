/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.training.gradebook.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.base.AssignmentServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author hgrahul
 */
@Component(
	property = {
		"json.web.service.context.name=gradebook",
		"json.web.service.context.path=Assignment"
	},
	service = AopService.class
)
public class AssignmentServiceImpl extends AssignmentServiceBaseImpl {
	/**
	 * The behavior would be executed whenever request for Adding a New Assignment (Remote or Webservice Call)
	 * @param groupId
	 * @param titleMap
	 * @param descriptionMap
	 * @param dueDate
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 */
	public Assignment addAssignment(long groupId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Date dueDate, ServiceContext serviceContext) throws PortalException {
		// Check Permission For Adding A New Assignment
		portletResourcePermission.check(getPermissionChecker(), groupId, ActionKeys.ADD_ENTRY);
		
		return assignmentLocalService.addAssignment(groupId, titleMap, descriptionMap, dueDate, serviceContext);
	}
	/**
	 * The behavior would be executed whenever request for Updating An Existing Assignment (Remote or Webservice Call)
	 * @param assignmentId
	 * @param titleMap
	 * @param descriptionMap
	 * @param dueDate
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 */
	public Assignment updateAssignment(long assignmentId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Date dueDate, ServiceContext serviceContext) throws PortalException {
		// Checking Permission For Updating An Existing Assignment
		assignmentModelResourcePermission.check(getPermissionChecker(), assignmentId, ActionKeys.UPDATE);
		
		return assignmentLocalService.updateAssignment(assignmentId, titleMap, descriptionMap, dueDate, serviceContext);
	}
	
	public Assignment deleteAssignment(long assignmentId) throws PortalException {
		// Checking Permission For Deleting An Existing Assignment
		assignmentModelResourcePermission.check(getPermissionChecker(), assignmentId, ActionKeys.DELETE);
		
		// Get The Assignment Instance For Deletion Purpose
		Assignment assignment = assignmentLocalService.getAssignment(assignmentId);
		
		// Return The Assignment Which Is Been Deleted.
		return assignmentLocalService.deleteAssignment(assignment);
	}
	
	public Assignment getAssignment(long assignmentId) throws PortalException {
		// Get The Assignment Instance For Deletion Purpose
		Assignment assignment = assignmentLocalService.getAssignment(assignmentId);

		// Checking Permission For Viewing An Individual Assignment
		assignmentModelResourcePermission.check(getPermissionChecker(), assignment, ActionKeys.VIEW);
		
		// Return The Assignment Instance
		return assignment;
	}
	
	public List<Assignment> getAssignmentsByGroupId(long groupId) {
		return assignmentLocalService.getAssignmentsByGroupId(groupId);
	}
	
	public List<Assignment> getAssignmentsByKeywords(long groupId, String keywords, int start, int end, OrderByComparator<Assignment> orderByComparator) {
		return assignmentLocalService.getAssignmentsByKeywords(groupId, keywords, start, end, orderByComparator);
	}
	
	public long getAssignmentsCountByKeywords(long groupId, String keywords) {
		return assignmentLocalService.getAssignmentsCountByKeywords(groupId, keywords);
	}
	
	/**
	 * For Checking Permissing Against What's Assigned To The User
	 */
	@Reference(policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY, target = "(model.class.name=com.liferay.training.gradebook.model.Assignment)")
	private volatile ModelResourcePermission<Assignment> assignmentModelResourcePermission;
	
	@Reference(policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY, target = "(resource.name=com.liferay.training.gradebook.model)")
	private volatile PortletResourcePermission portletResourcePermission;
}
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

import com.liferay.headless.delivery.dto.v1_0.ContentType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.base.AssignmentLocalServiceBaseImpl;
import com.liferay.training.gradebook.validator.AssignmentValidator;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author hgrahul
 */
@Component(
	property = "model.class.name=com.liferay.training.gradebook.model.Assignment",
	service = AopService.class
)
public class AssignmentLocalServiceImpl extends AssignmentLocalServiceBaseImpl {
	
	/**
	 * 
	 * The behavior would be executed whenever request for Adding a New Assignment
	 * 
	 * @param groupId
	 * @param titleMap
	 * @param descriptionMap
	 * @param dueDate
	 * @param serviceContext
	 * 
	 * @return
	 * @throws PortalException
	 */
	public Assignment addAssignment(long groupId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Date dueDate, ServiceContext serviceContext) throws PortalException {
		// Perform Validation Checks
		assignmentValidator.validate(titleMap, descriptionMap, dueDate);
		
		// Get Group(Site) and User Information
		Group 	group = groupLocalService.getGroup(groupId);
		long userId = serviceContext.getUserId();
		User user = userLocalService.getUser(userId);
		
		// Generate A New Primary Key For The Assignment
		long assignmentId = counterLocalService.increment(Assignment.class.getName());
		
		// Using The Generated Key To Create A New Assignment Entity
		Assignment assignment = createAssignment(assignmentId);
		
		// Populate The Field Values
		// 1. Update Actual Fields
		assignment.setTitleMap(titleMap);
		assignment.setDescriptionMap(descriptionMap);
		assignment.setDueDate(dueDate);
		
		// 2. Update Audit and Other Generic Fields
		assignment.setGroupId(groupId);
		assignment.setCompanyId(group.getCompanyId());
		assignment.setCreateDate(serviceContext.getCreateDate(new Date()));
		assignment.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		assignment.setUserId(userId);
		assignment.setUserName(user.getScreenName());
		
		assignment = super.addAssignment(assignment);
		
		// For Adding A Permissioned Resource Details
		boolean portletActions = false;
		boolean addGroupPermissions = true;
		boolean addGuestPermissions = true;
		
		// The Actual Call For Making A Permissioned Resource With Group, Guest And Portlet Details
		resourceLocalService.addResources(group.getCompanyId(), groupId, userId, Assignment.class.getName(), assignment.getAssignmentId(), portletActions, addGroupPermissions, addGuestPermissions);
		
		// Update Asset Resource
		updateAsset(assignment, serviceContext);
		
		// Persist And Return Assignment Entity Details To Databases
		return assignment;
	}
	
	public Assignment deleteAssignment(Assignment assignment) throws PortalException {
		// Delete The Permissioned Resource
		resourceLocalService.deleteResource(assignment, ResourceConstants.SCOPE_INDIVIDUAL);
	
		// Delete The Asset Resource
		assetEntryLocalService.deleteEntry(Assignment.class.getName(), assignment.getAssignmentId());
		
		// Delete The Actual Assignment Aswell
		return super.deleteAssignment(assignment);
	}
	
	/**
	 * The behavior would be executed whenever request for Updating An Existing Assignment
	 * 
	 * @param assignmentId
	 * @param titleMap
	 * @param descriptionMap
	 * @param dueDate
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 */
	public Assignment updateAssignment(long assignmentId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Date dueDate, ServiceContext serviceContext) throws PortalException {
		// Perform Validation Checks
		assignmentValidator.validate(titleMap, descriptionMap, dueDate);
		
		// First Get Assignment Information From The AssignmentId
		Assignment assignment = getAssignment(assignmentId);
		
		// Populate or Update New Information To The Assignment Entity
		assignment.setTitleMap(titleMap);
		assignment.setDescriptionMap(descriptionMap);
		assignment.setDueDate(dueDate);
		
		// Also Update Modification Date
		assignment.setModifiedDate(new Date());
		
		assignment = super.updateAssignment(assignment);
		
		// Update Asset Resource
		updateAsset(assignment, serviceContext);
		
		// Persist And Return Assignment Entity Details To Databases
		return assignment;
	}
	
	public List<Assignment> getAssignmentsByGroupId(long groupId) {
		return assignmentPersistence.findByGroupId(groupId);
	}
	
	public List<Assignment> getAssignmentsByGroupId(long groupId, int start, int end) {
	
		return assignmentPersistence.findByGroupId(groupId, start, end);
	}
	
	public List<Assignment> getAssignmentsByGroupId(long groupId, int start, int end, OrderByComparator<Assignment> orderByComparator) {
		return assignmentPersistence.findByGroupId(groupId, start, end, orderByComparator);
	}
	
	private DynamicQuery getKeywordSearchDynamicQuery(long groupId, String keyword) {
		DynamicQuery dynamicQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("groupId", groupId));
		
		if(Validator.isNotNull(keyword)) {
			Disjunction disjunctionQuery = RestrictionsFactoryUtil.disjunction();
			
			disjunctionQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("description", "%" + keyword + "%"));
			
			dynamicQuery.add(disjunctionQuery);
		}
		
		return dynamicQuery;
	}
	
	public long getAssignmentsCountByKeywords(long groupId, String keyword) {
		return assignmentLocalService.dynamicQueryCount(getKeywordSearchDynamicQuery(groupId, keyword));
	}
	
	public List<Assignment> getAssignmentsByKeywords(long groupId, String keyword, int start, int end, OrderByComparator<Assignment> orderByComparator) {
		return assignmentLocalService.dynamicQuery(getKeywordSearchDynamicQuery(groupId, keyword), start, end, orderByComparator);
	}
	
	/**
	 * Updating Assignment Information To The Asset Hierarchy
	 */
	private void updateAsset(Assignment assignment, ServiceContext serviceContext) throws PortalException {
		assetEntryLocalService.updateEntry(serviceContext.getUserId(), serviceContext.getScopeGroupId(), assignment.getCreateDate(), assignment.getModifiedDate(), Assignment.class.getName(), assignment.getAssignmentId(), assignment.getUuid(), 0, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), true, true, assignment.getCreateDate(), null, null, null, ContentTypes.TEXT_HTML, assignment.getTitle(serviceContext.getLocale()),  assignment.getDescription(serviceContext.getLocale()), null, null, null, 0, 0, serviceContext.getAssetPriority());
	}
	
	/**
	 * Silence Those Direct Calls
	 */
	@Override
	public Assignment addAssignment(Assignment assignment) {
		throw new UnsupportedOperationException("Not Supported..");
	}
	
	@Override
	public Assignment updateAssignment(Assignment assignment) {
		throw new UnsupportedOperationException("Not Supported..");
	}
	
	@Reference
	private AssignmentValidator assignmentValidator;
}
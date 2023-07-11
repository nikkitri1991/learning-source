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
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.model.Submission;
import com.liferay.training.gradebook.service.base.SubmissionLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author hgrahul
 */
@Component(
	property = "model.class.name=com.liferay.training.gradebook.model.Submission",
	service = AopService.class
)
public class SubmissionLocalServiceImpl extends SubmissionLocalServiceBaseImpl {
	public Submission addSubmission(long groupId,long studentId, long assignmentId,Date submitDate, String submissionText, String comment, int grade,ServiceContext serviceContext) throws PortalException {
		//Get group(site) and user Information
		Group group=groupLocalService.getGroup(groupId);
		System.out.println(group);
		long userId=serviceContext.getUserId();
		System.out.println(userId);
		User user=userLocalService.getUser(userId);
		System.out.println(user);
		long submissionId=counterLocalService.increment(Submission.class.getName());
		System.out.println(submissionId);
		Submission submission=createSubmission(submissionId);
		System.out.println(submission);
		//1. Update Actual Fields
		submission.setAssignmentId(assignmentId);
		submission.setStudentId(studentId);
		submission.setSubmitDate(submitDate);
		submission.setSubmissionText(submissionText);
		submission.setComment(comment);
		
		//2.update audit and other generic fields
		submission.setGroupId(groupId);
		submission.setCompanyId(group.getCompanyId());
		submission.setUserId(userId);
		submission.setUserName(user.getScreenName());
		submission.setCreateDate(serviceContext.getCreateDate(new Date()));
		submission.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		submission=super.addSubmission(submission);
		System.out.println("test");
		// Update Asset Resource
		 updateAsset(submission, serviceContext); 
		 return submission;
		
	}
	public Submission deleteSubmission(Submission submission) {
		//assetEntryLocalService.deleteEntry(Submission.class.getName(), submission.getSubmissionId());
		return super.deleteSubmission(submission);
		
	}
	public List<Submission> getSubmission(){
		return submissionPersistence.findAll();
	}
	public List<Submission>getSubmissionByAssignmentIdAndSubmissionId(long assignmentId, long studentId){
		return submissionPersistence.findByStudentIdAssignmentId(studentId, assignmentId);
		
	}
	private void updateAsset(Submission submission, ServiceContext serviceContext) throws PortalException {
		assetEntryLocalService.updateEntry(serviceContext.getUserId(), serviceContext.getScopeGroupId(), submission.getCreateDate(), submission.getModifiedDate(), Submission.class.getName(), submission.getSubmissionId(), submission.getUuid(), 0, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), true, true, submission.getCreateDate(), null, null, null, ContentTypes.TEXT_HTML, submission.getSubmissionText(), submission.getComment(), null, null, null, 0, 0, serviceContext.getAssetPriority());

}
}
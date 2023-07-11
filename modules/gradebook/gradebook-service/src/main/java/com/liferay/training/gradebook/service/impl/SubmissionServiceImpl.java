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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.training.gradebook.model.Submission;
import com.liferay.training.gradebook.service.base.SubmissionServiceBaseImpl;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author hgrahul
 */
@Component(
	property = {
		"json.web.service.context.name=gradebook",
		"json.web.service.context.path=Submission"
	},
	service = AopService.class
)
public class SubmissionServiceImpl extends SubmissionServiceBaseImpl {
	
	  public Submission addSubmission(long groupId,long studentId, long
	  assignmentId,Date submitDate, String submissionText, String comment, int
	  grade,ServiceContext serviceContext) throws PortalException {
	  
	  return submissionLocalService.addSubmission(groupId, studentId, assignmentId, submitDate, submissionText, comment, grade, serviceContext);
	  }
	  public Submission deleSubmission(Submission submission) {
		 return submissionLocalService.deleteSubmission(submission);
	  }
	 public List<Submission> getSubmission(){
		return  submissionLocalService.getSubmission();
		 
	 }
	 public List<Submission> getSubmissionByAssignmentIdAndSubmissionId(long assignmentId, long studentId){
		return submissionLocalService.getSubmissionByAssignmentIdAndSubmissionId(assignmentId, studentId);
	 }
}
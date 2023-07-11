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

package com.liferay.training.gradebook.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SubmissionService}.
 *
 * @author hgrahul
 * @see SubmissionService
 * @generated
 */
public class SubmissionServiceWrapper
	implements ServiceWrapper<SubmissionService>, SubmissionService {

	public SubmissionServiceWrapper() {
		this(null);
	}

	public SubmissionServiceWrapper(SubmissionService submissionService) {
		_submissionService = submissionService;
	}

	@Override
	public com.liferay.training.gradebook.model.Submission addSubmission(
			long groupId, long studentId, long assignmentId,
			java.util.Date submitDate, String submissionText, String comment,
			int grade,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _submissionService.addSubmission(
			groupId, studentId, assignmentId, submitDate, submissionText,
			comment, grade, serviceContext);
	}

	@Override
	public com.liferay.training.gradebook.model.Submission deleSubmission(
		com.liferay.training.gradebook.model.Submission submission) {

		return _submissionService.deleSubmission(submission);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _submissionService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.training.gradebook.model.Submission>
		getSubmission() {

		return _submissionService.getSubmission();
	}

	@Override
	public java.util.List<com.liferay.training.gradebook.model.Submission>
		getSubmissionByAssignmentIdAndSubmissionId(
			long assignmentId, long studentId) {

		return _submissionService.getSubmissionByAssignmentIdAndSubmissionId(
			assignmentId, studentId);
	}

	@Override
	public SubmissionService getWrappedService() {
		return _submissionService;
	}

	@Override
	public void setWrappedService(SubmissionService submissionService) {
		_submissionService = submissionService;
	}

	private SubmissionService _submissionService;

}
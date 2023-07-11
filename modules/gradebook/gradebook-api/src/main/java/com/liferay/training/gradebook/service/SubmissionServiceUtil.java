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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.training.gradebook.model.Submission;

import java.util.List;

/**
 * Provides the remote service utility for Submission. This utility wraps
 * <code>com.liferay.training.gradebook.service.impl.SubmissionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author hgrahul
 * @see SubmissionService
 * @generated
 */
public class SubmissionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.training.gradebook.service.impl.SubmissionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Submission addSubmission(
			long groupId, long studentId, long assignmentId,
			java.util.Date submitDate, String submissionText, String comment,
			int grade,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addSubmission(
			groupId, studentId, assignmentId, submitDate, submissionText,
			comment, grade, serviceContext);
	}

	public static Submission deleSubmission(Submission submission) {
		return getService().deleSubmission(submission);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<Submission> getSubmission() {
		return getService().getSubmission();
	}

	public static List<Submission> getSubmissionByAssignmentIdAndSubmissionId(
		long assignmentId, long studentId) {

		return getService().getSubmissionByAssignmentIdAndSubmissionId(
			assignmentId, studentId);
	}

	public static SubmissionService getService() {
		return _service;
	}

	private static volatile SubmissionService _service;

}
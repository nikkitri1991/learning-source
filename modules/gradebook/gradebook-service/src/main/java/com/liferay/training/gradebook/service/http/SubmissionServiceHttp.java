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

package com.liferay.training.gradebook.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.training.gradebook.service.SubmissionServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>SubmissionServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author hgrahul
 * @generated
 */
public class SubmissionServiceHttp {

	public static com.liferay.training.gradebook.model.Submission addSubmission(
			HttpPrincipal httpPrincipal, long groupId, long studentId,
			long assignmentId, java.util.Date submitDate, String submissionText,
			String comment, int grade,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SubmissionServiceUtil.class, "addSubmission",
				_addSubmissionParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, studentId, assignmentId, submitDate,
				submissionText, comment, grade, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.training.gradebook.model.Submission)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.training.gradebook.model.Submission
		deleSubmission(
			HttpPrincipal httpPrincipal,
			com.liferay.training.gradebook.model.Submission submission) {

		try {
			MethodKey methodKey = new MethodKey(
				SubmissionServiceUtil.class, "deleSubmission",
				_deleSubmissionParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, submission);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.training.gradebook.model.Submission)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.training.gradebook.model.Submission> getSubmission(
			HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				SubmissionServiceUtil.class, "getSubmission",
				_getSubmissionParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.training.gradebook.model.Submission>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.training.gradebook.model.Submission>
			getSubmissionByAssignmentIdAndSubmissionId(
				HttpPrincipal httpPrincipal, long assignmentId,
				long studentId) {

		try {
			MethodKey methodKey = new MethodKey(
				SubmissionServiceUtil.class,
				"getSubmissionByAssignmentIdAndSubmissionId",
				_getSubmissionByAssignmentIdAndSubmissionIdParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, assignmentId, studentId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.training.gradebook.model.Submission>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SubmissionServiceHttp.class);

	private static final Class<?>[] _addSubmissionParameterTypes0 =
		new Class[] {
			long.class, long.class, long.class, java.util.Date.class,
			String.class, String.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleSubmissionParameterTypes1 =
		new Class[] {com.liferay.training.gradebook.model.Submission.class};
	private static final Class<?>[] _getSubmissionParameterTypes2 =
		new Class[] {};
	private static final Class<?>[]
		_getSubmissionByAssignmentIdAndSubmissionIdParameterTypes3 =
			new Class[] {long.class, long.class};

}
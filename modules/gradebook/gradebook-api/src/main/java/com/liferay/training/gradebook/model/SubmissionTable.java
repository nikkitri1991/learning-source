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

package com.liferay.training.gradebook.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Gradebook_Submission&quot; database table.
 *
 * @author hgrahul
 * @see Submission
 * @generated
 */
public class SubmissionTable extends BaseTable<SubmissionTable> {

	public static final SubmissionTable INSTANCE = new SubmissionTable();

	public final Column<SubmissionTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Long> assignmentId = createColumn(
		"assignmentId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Long> submissionId = createColumn(
		"submissionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SubmissionTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Long> studentId = createColumn(
		"studentId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Date> submitDate = createColumn(
		"submitDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> submissionText = createColumn(
		"submissionText", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> comment = createColumn(
		"comment_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Integer> grade = createColumn(
		"grade", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private SubmissionTable() {
		super("Gradebook_Submission", SubmissionTable::new);
	}

}